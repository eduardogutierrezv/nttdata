package nttdata.usertest.utils;

import nttdata.usertest.dto.UserSaveResponseDTO;
import nttdata.usertest.entity.UserEntity;

public class Utils {


    public static UserSaveResponseDTO userEntityToSaveResponse(UserEntity userEntity){
        return UserSaveResponseDTO.builder()
                .id(userEntity.getId())
                .creado(userEntity.getFechaCreacion())
                .modificado(userEntity.getFechaModificacion())
                .ultimoLogin(userEntity.getUltimoLogin())
                .token(userEntity.getToken())
                .activo(userEntity.isActivo())
                .build();
    }

}
