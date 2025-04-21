package nttdata.usertest.controller;

import nttdata.usertest.dto.LoginRequestDTO;
import nttdata.usertest.dto.UserRequestDTO;
import nttdata.usertest.service.UserService;
import nttdata.usertest.swagger.interfaces.LoginSwagger;
import nttdata.usertest.swagger.interfaces.SaveUserSwagger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/user")
@RestController
public class UserRestcontroller {

    private UserService userService;

    public UserRestcontroller(UserService userService){
        this.userService = userService;
    }


    @SaveUserSwagger()
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser (@RequestBody UserRequestDTO user) throws Exception {
        return userService.saveUser(user);
    }

    @LoginSwagger()
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO userLogin) throws Exception {
        return userService.loginUser(userLogin);
    }

}
