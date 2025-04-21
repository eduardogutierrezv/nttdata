package nttdata.usertest.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import nttdata.usertest.dto.UserUpdateRequestDTO;
import nttdata.usertest.service.UserService;
import nttdata.usertest.swagger.interfaces.UserDeleteSwagger;
import nttdata.usertest.swagger.interfaces.UserProfileSwagger;
import nttdata.usertest.swagger.interfaces.UserStateChangeSwagger;
import nttdata.usertest.swagger.interfaces.UserUpdateSwagger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.UUID;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/v1")
public class CoreRestController {

    private UserService userService;

    public CoreRestController(UserService userService){
        this.userService = userService;
    }

    @UserProfileSwagger
    @GetMapping(value = "/user-profile/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> userProfile(@PathVariable(name = "email") String email){
        return userService.getUser(email);
    }

    @UserDeleteSwagger()
    @DeleteMapping(value = "/user-delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@PathVariable(name="id") UUID id){
        return userService.deleteUser(id);
    }

    @UserUpdateSwagger()
    @PutMapping(value = "/user-update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@PathVariable(name = "id") UUID id,
                                        @RequestBody UserUpdateRequestDTO userUpdateRequestDTO) throws Exception {
        return userService.updateUser(userUpdateRequestDTO, id);
    }

    @UserStateChangeSwagger()
    @PatchMapping(value = "/user-desactivate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> inactiveUser(@PathVariable(name = "id") UUID id) throws Exception {
        return userService.userDesactivate(id);
    }

}
