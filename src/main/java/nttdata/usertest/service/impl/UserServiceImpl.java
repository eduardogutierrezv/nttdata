package nttdata.usertest.service.impl;

import lombok.extern.slf4j.Slf4j;
import nttdata.usertest.dto.*;
import nttdata.usertest.entity.CellphoneEntity;
import nttdata.usertest.entity.UserEntity;
import nttdata.usertest.repository.UserRepository;
import nttdata.usertest.service.UserService;
import nttdata.usertest.utils.ConstantUtils;
import nttdata.usertest.utils.JwtUtils;
import nttdata.usertest.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;

    @Value(value = "${password.block}")
    private String passRestriccion;

    public UserServiceImpl(UserRepository userRepo){
        this.userRepo = userRepo;
    }


    @Override
    public ResponseEntity<?> saveUser(UserRequestDTO user) throws Exception {

        /*
        * para mejor manera de optimizar el response entity
        * seria mejor definir una clase con un objeto
        * y un campo con un objeto error , pero en este caso
        * como la pedida es definir respeustas diferentes se dejo
        * como opcional "?"
        * */
        MensajeErrorResponseDTO errorResponse = new MensajeErrorResponseDTO();
        Pattern patternEmail = Pattern.compile(ConstantUtils.REGEX_EMAIL);
        Pattern patternPass = Pattern.compile(passRestriccion);

        try {
            Matcher matcherEmail = patternEmail.matcher(user.getCorreo());
            Matcher matcherPass = patternPass.matcher(user.getContrasena());

            if(!matcherEmail.matches()){
                errorResponse.setMensaje("Formato de correo incorrecto.");
                return ResponseEntity.badRequest().body(errorResponse);
            }

            if(!matcherPass.matches()){
                errorResponse.setMensaje("La contraseña no cumple con los requisitos.");
                return ResponseEntity.badRequest().body(errorResponse);
            }


            if(userRepo.findByCorreo(user.getCorreo()).isPresent()){
                errorResponse.setMensaje("El correo ya está registrado");
                return ResponseEntity.badRequest().body(errorResponse);
            }

            UserEntity userSave = this.guardarUserH2(user);
            return ResponseEntity.ok(Utils.userEntityToSaveResponse(userSave));

        }catch (Exception ex){
            log.error(ex.getMessage());
            throw new Exception("Error Interno");
        }
    }

    @Override
    public ResponseEntity<?> getUser(String email) {

        UserEntity userEntity = new UserEntity();
        MensajeErrorResponseDTO errorResponse = new MensajeErrorResponseDTO();

        try {
            userEntity = this.userRepo.findByCorreo(email).orElse(null);

            if(userEntity==null){
                errorResponse.setMensaje("el usuario no existe");
                return ResponseEntity.badRequest().body(errorResponse);
            }

            return ResponseEntity.ok(userEntity);

        } catch (Exception e) {
            log.error("Error {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateUser(UserUpdateRequestDTO userUpdateRequestDTO, UUID id) throws Exception {

        MensajeErrorResponseDTO errorResponseDTO = new MensajeErrorResponseDTO();

        try {
            UserEntity userEntity = this.userRepo.findById(id).orElse(null);

            if(userEntity==null){
                errorResponseDTO.setMensaje("No se puede actualizar el usuario");
                return ResponseEntity.badRequest().body(errorResponseDTO);
            }

            return ResponseEntity.ok(updateUserH2(userUpdateRequestDTO, userEntity));

        }catch (Exception ex){
            log.error("Error {}", ex.getMessage());
            throw new Exception("Error");
        }

    }

    @Override
    public ResponseEntity<?> deleteUser(UUID id) {

        MensajeErrorResponseDTO errorResponseDTO = new MensajeErrorResponseDTO();

        try {
            UserEntity userEntity = this.userRepo.findById(id).orElse(null);

             if(userEntity==null){
                 errorResponseDTO.setMensaje("Error al eliminar al usuario");
                 return ResponseEntity.badRequest().body(errorResponseDTO);
             }

            this.userRepo.delete(userEntity);
            errorResponseDTO.setMensaje("Usuario eliminado");
            return ResponseEntity.ok(errorResponseDTO);

        } catch (Exception e) {
            log.error("Error {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> loginUser(LoginRequestDTO userLogin) throws Exception {

        UserEntity userEntity = new UserEntity();
        MensajeErrorResponseDTO errorResponseDTO = new MensajeErrorResponseDTO();

        try {
            LocalDateTime updateLogin = LocalDateTime.now();
            userEntity = this.userRepo.findByCorreoAndPassword(userLogin.getEmail(), userLogin.getPassword()).orElse(null);

            if(userEntity==null){
                errorResponseDTO.setMensaje("No corresponde al password");
                return ResponseEntity.badRequest().body(errorResponseDTO);
            }

            if(!userEntity.isActivo()){
                errorResponseDTO.setMensaje("El usuario no se encuentra activo");
                return ResponseEntity.badRequest().body(errorResponseDTO);
            }
            //se actualiza el la fecha del ultimo login.
            userEntity.setUltimoLogin(updateLogin);
            this.userRepo.save(userEntity);

            return ResponseEntity.ok(userEntity);

        }catch (Exception ex){
            log.error("Error {}", ex.getMessage());
            throw new Exception("Error");
        }

    }

    @Override
    public ResponseEntity<?> userDesactivate(UUID id) {
            MensajeErrorResponseDTO errorResponseDTO = new MensajeErrorResponseDTO();
            boolean responseActivate = false;

        try {
            UserEntity userEntity = this.userRepo.findById(id).orElse(null);

            if(userEntity==null){
                errorResponseDTO.setMensaje("No se encontro el usuario");
                return ResponseEntity.badRequest().body(errorResponseDTO);
            }

            responseActivate = !userEntity.isActivo();
            userEntity.setActivo(responseActivate);

            errorResponseDTO.setMensaje("El Usuario quedo en estado activado: "+responseActivate);
            this.userRepo.save(userEntity);

            return ResponseEntity.ok(errorResponseDTO);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private UserEntity guardarUserH2(UserRequestDTO user){
        UserEntity newUser = new UserEntity();
        LocalDateTime fechaCreacion = LocalDateTime.now();

        newUser.setNombre(user.getNombre());
        newUser.setCorreo(user.getCorreo());
        newUser.setPassword(user.getContrasena());
        newUser.setFechaCreacion(fechaCreacion);
        newUser.setFechaModificacion(null);
        newUser.setUltimoLogin(fechaCreacion);
        newUser.setToken(JwtUtils.generateToken(user.getCorreo()));
        newUser.setActivo(true);
        newUser.setTelefonos(telefonoBuild(user, newUser));

        return userRepo.save(newUser);
    }

    private UserEntity updateUserH2(UserUpdateRequestDTO userUpdateRequestDTO, UserEntity newUser){

        LocalDateTime fechaModificacion = LocalDateTime.now();

        if(userUpdateRequestDTO.getNombre() != null){
            newUser.setNombre(userUpdateRequestDTO.getNombre());
        }
        if(userUpdateRequestDTO.getContrasena() != null){
            newUser.setPassword(userUpdateRequestDTO.getContrasena());
        }
        if(userUpdateRequestDTO.getCorreo() != null){
            newUser.setCorreo(userUpdateRequestDTO.getCorreo());
        }

        newUser.setFechaModificacion(fechaModificacion);

        return userRepo.save(newUser);
    }

    private ArrayList<CellphoneEntity> telefonoBuild(UserRequestDTO user, UserEntity newUser){

        ArrayList<CellphoneEntity> cellphoneArray = new ArrayList<>();

        user.getTelefonos().forEach(cell -> {
            CellphoneEntity cellphone = new CellphoneEntity();
            String telefonoTxt = cell.getCodigoPais()
                    .concat(cell.getCodigoCiudad())
                    .concat(cell.getNumero());

            cellphone.setNumero(telefonoTxt);
            cellphone.setUsuario(newUser);
            cellphoneArray.add(cellphone);

        });
        return cellphoneArray;
    }
}
