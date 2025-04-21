package nttdata.usertest.service;

import nttdata.usertest.dto.LoginRequestDTO;
import nttdata.usertest.dto.UserRequestDTO;
import nttdata.usertest.dto.UserUpdateRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UserService {

    ResponseEntity<?> saveUser(UserRequestDTO user) throws Exception;

    ResponseEntity<?> getUser(String email);

    ResponseEntity<?> updateUser(UserUpdateRequestDTO userUpdateRequestDTO, UUID id) throws Exception;

    ResponseEntity<?> deleteUser(UUID id);

    ResponseEntity<?> loginUser(LoginRequestDTO userLogin) throws Exception;

    ResponseEntity<?> userDesactivate(UUID id) throws Exception;
}
