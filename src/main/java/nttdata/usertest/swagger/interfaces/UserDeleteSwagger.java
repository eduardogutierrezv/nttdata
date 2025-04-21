package nttdata.usertest.swagger.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import nttdata.usertest.swagger.SwaggerUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Eliminar Usuario", description = "Se usa para eliminar al usuario", security = @SecurityRequirement(name = SwaggerUtils.NAME_SECURITY))
@Parameter(name = "Authorization", in = ParameterIn.HEADER, required = true, example = SwaggerUtils.TOKEN_EXAMPLE)
@ApiResponses(value = {
        @ApiResponse( responseCode = "200", description = "Eliminacion exitosa",
                content = @Content( mediaType = "application/json", examples = @ExampleObject(
                        name = "Usuario Eliminado", description = "Usuario eliminado exitosamente", value = SwaggerUtils.ELIMINACION_EXITOSA)
                )),

        @ApiResponse( responseCode = "400", description = "Usuario no encontrado",
                content = @Content( mediaType = "application/json", examples = { @ExampleObject(
                        name = "Error eliminar usuario", description = "El usuario a eliminar no existe", value = SwaggerUtils.ELIMINACION_NO_EXITOSA) }
                )),

        @ApiResponse( responseCode = "401",  description = "Problemas con el token",
                content = @Content( mediaType = "application/json",  examples = { @ExampleObject(
                        name = "El token no corresponde",  description = "El token no esta presente o no corresponde", value = SwaggerUtils.TOKEN_UNAUTHORIZED) }
                ))
})
public @interface UserDeleteSwagger {
}
