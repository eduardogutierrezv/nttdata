package nttdata.usertest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserUpdateRequestDTO {

    @Schema(description = "Nombre del usuario", example = "eduardo gutierrez")
    private String nombre;

    @Schema(description = "Correo del usuario", example = "lgv@gmail.com")
    private String correo;

    @Schema(description = "Contraseña", example = "Hunter22!")
    private String contrasena;
}

