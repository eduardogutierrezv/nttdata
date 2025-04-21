package nttdata.usertest.service.impl;

import nttdata.usertest.dto.LoginRequestDTO;
import nttdata.usertest.dto.TelefonoDTO;
import nttdata.usertest.dto.UserRequestDTO;
import nttdata.usertest.dto.UserUpdateRequestDTO;
import nttdata.usertest.entity.UserEntity;
import nttdata.usertest.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class UserServiceImplTest {


    @Mock
    private UserRepository userRepoMock;

    @InjectMocks
    private UserServiceImpl userService;

    UserEntity userGuardar = new UserEntity();

    UserRequestDTO userReq = new UserRequestDTO();

    @BeforeEach
    public void setup() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);
        userService= new UserServiceImpl(userRepoMock);

        java.lang.reflect.Field field = UserServiceImpl.class.getDeclaredField("passRestriccion");
        field.setAccessible(true);
        field.set(userService, "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{7,}$"); // ejemplo de regex


        userGuardar.setId(UUID.randomUUID());
        userGuardar.setCorreo("juan@rodriguez.cl");
        userGuardar.setNombre("Juan Rodriguez");
        userGuardar.setFechaCreacion(LocalDateTime.now());
        userGuardar.setUltimoLogin(LocalDateTime.now());
        userGuardar.setToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5jbCIsImlhdCI6MTc0NTEyMjc0MH0.78pLMSn7XaBM9tAqt5gI9fXsDa-329y9AM3x5PlmKqk7kp8h3hb0TEUWhknwifGRsmFdHTYMvePFUwqUhD-V9Q");
        userGuardar.setActivo(true);
        userGuardar.setTelefonos(new ArrayList<>());


        TelefonoDTO telefonoReq = new TelefonoDTO();
        ArrayList<TelefonoDTO> arrayTelefono = new ArrayList<>();
        telefonoReq.setCodigoPais("56");
        telefonoReq.setCodigoCiudad("9");
        telefonoReq.setNumero("49138184");
        arrayTelefono.add(telefonoReq);
        userReq.setNombre("Juan Rodriguez");
        userReq.setCorreo("juan@rodriguez.cl");
        userReq.setContrasena("Hunter21!");
        userReq.setTelefonos(arrayTelefono);
    }

    @Test
    void saveUser() throws Exception {


        Mockito.when(userRepoMock.findByCorreo("juan@rodriguez.cl"))
                .thenReturn(Optional.empty());

        Mockito.when(userRepoMock.save( any(UserEntity.class) )).thenReturn(userGuardar);

        ResponseEntity<?> response =  userService.saveUser(userReq);

        assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    void saveUserBadEmail() throws Exception {


        userReq.setCorreo("juan@rodriguez");

        ResponseEntity<?> response =  userService.saveUser(userReq);

        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("Formato de correo incorrecto"));

    }

    @Test
    void saveUserBadPassword() throws Exception {


        userReq.setContrasena("hunter");

        ResponseEntity<?> response =  userService.saveUser(userReq);

        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("La contraseña no cumple con los requisitos."));

    }

    @Test
    void EmailExist() throws Exception {

        Mockito.when(userRepoMock.findByCorreo("juan@rodriguez.cl"))
                .thenReturn(Optional.of(new UserEntity()));

        ResponseEntity<?> response =  userService.saveUser(userReq);

        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("El correo ya está registrado"));

    }


    @Test
    void getUserNotFound() {

        ResponseEntity<?> response =  userService.getUser("juan@rodriguez.cl");

        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("el usuario no existe"));

    }

    @Test
    void getUserFound() {

      Mockito.when(userRepoMock.findByCorreo("juan@rodriguez.cl"))
                .thenReturn(Optional.of(userGuardar));

        ResponseEntity<?> response =  userService.getUser("juan@rodriguez.cl");

        assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    void updateUserNotFound() throws Exception {

        UserUpdateRequestDTO userUpdateRequestDTO = new UserUpdateRequestDTO();
        userUpdateRequestDTO.setNombre("Eduardo Gutierrez");

        ResponseEntity<?> response =  userService.updateUser(userUpdateRequestDTO, UUID.randomUUID());

        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("No se puede actualizar el usuario"));
    }

    @Test
    void updateUser() throws Exception {

        UUID uuid = UUID.randomUUID();

        UserUpdateRequestDTO userUpdateRequestDTO = new UserUpdateRequestDTO();
        userUpdateRequestDTO.setNombre("Eduardo Gutierrez");
        userUpdateRequestDTO.setContrasena("Hunter23!");
        userUpdateRequestDTO.setCorreo("EduardoGutierrez@gmail.com");

        Mockito.when(userRepoMock.findById(uuid))
                .thenReturn(Optional.of(userGuardar));

        Mockito.when(userRepoMock.save( any(UserEntity.class) )).thenReturn(userGuardar);

        ResponseEntity<?> response =  userService.updateUser(userUpdateRequestDTO, uuid);

        assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    void deleteUserNotFound() {

        UUID uuid = UUID.randomUUID();

        ResponseEntity<?> response =  userService.deleteUser(uuid);

        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("Error al eliminar al usuario"));
    }

    @Test
    void deleteUser() {

        UUID uuid = UUID.randomUUID();

        Mockito.when(userRepoMock.findById(uuid))
                .thenReturn(Optional.of(userGuardar));

        ResponseEntity<?> response =  userService.deleteUser(uuid);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("Usuario eliminado"));
    }



    @Test
    void loginUserPasswordError() throws Exception {

        LoginRequestDTO loginReq = new LoginRequestDTO("juan@rodriguez.cl", "hunter2");
        ResponseEntity<?> response =  userService.loginUser(loginReq);

        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("No corresponde al password"));
    }


    @Test
    void loginUserActiveFalse() throws Exception {

        userGuardar.setActivo(false);

        Mockito.when(userRepoMock.findByCorreoAndPassword("juan@rodriguez.cl", "hunter2"))
                .thenReturn(Optional.of(userGuardar));

        LoginRequestDTO loginReq = new LoginRequestDTO("juan@rodriguez.cl", "hunter2");
        ResponseEntity<?> response =  userService.loginUser(loginReq);

        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("El usuario no se encuentra activo"));
    }

    @Test
    void loginUserOk() throws Exception {

        Mockito.when(userRepoMock.findByCorreoAndPassword("juan@rodriguez.cl", "hunter2"))
                .thenReturn(Optional.of(userGuardar));

        LoginRequestDTO loginReq = new LoginRequestDTO("juan@rodriguez.cl", "hunter2");
        ResponseEntity<?> response =  userService.loginUser(loginReq);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void userDesactivateNotFound() {

        UUID uuid = UUID.randomUUID();

        ResponseEntity<?> response =  userService.userDesactivate(uuid);

        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("No se encontro el usuario"));

    }

    @Test
    void userDesactivate() {

        UUID uuid = UUID.randomUUID();

        Mockito.when(userRepoMock.findById(uuid))
                .thenReturn(Optional.of(userGuardar));

        ResponseEntity<?> response =  userService.userDesactivate(uuid);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("El Usuario quedo en estado activado: false"));


    }
}