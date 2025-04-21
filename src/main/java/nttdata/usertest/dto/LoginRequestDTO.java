package nttdata.usertest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @Schema(description = "Email del usuario", example = "juan@rodriguez.cl")
    private String email;

    @Schema(description = "Contraseña del usuario", example = "Hunter2!")
    private String password;
}
