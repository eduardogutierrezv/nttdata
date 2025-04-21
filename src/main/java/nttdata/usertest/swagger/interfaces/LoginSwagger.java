package nttdata.usertest.swagger.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import nttdata.usertest.swagger.SwaggerUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Iniciar sesion de usuario", description = "Se debe enviar el email más la contraseña")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inicio de sesion exitosa",
                content = @Content(mediaType = "application/json", examples = @ExampleObject(
                        name = "Usuario de ejemplo", value = SwaggerUtils.LOGIN_EXITO))
        ),
        @ApiResponse(responseCode = "400", description = "Login incorrecto",
                content = @Content(mediaType = "application/json", examples = {
                        @ExampleObject(name = "Contraseña incorrecta", value = SwaggerUtils.LOGIN_FALLIDO_PASS),
                        @ExampleObject(name = "Usuario desactivado", value = SwaggerUtils.LOGIN_FALLIDO_INACTIVO)
                })
        )
})


public @interface LoginSwagger { }
