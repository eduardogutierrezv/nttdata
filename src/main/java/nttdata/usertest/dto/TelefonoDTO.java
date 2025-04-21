package nttdata.usertest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelefonoDTO {

    @Schema(description = "Telefono del usuario", example = "49138183")
    private String numero;

    @Schema(description = "Codigo de ciudad", example = "9")
    private String codigoCiudad;

    @Schema(description = "Codigo de pais", example = "56")
    private String codigoPais;
}
