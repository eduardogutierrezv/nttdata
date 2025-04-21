package nttdata.usertest.swagger.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import nttdata.usertest.swagger.SwaggerUtils;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Guardar un usuario", description = "Endpoint que permite guardar al usuario, la contraseña debe que tener un minimo de 7 caracteres, numeros y letras")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente",
                content = @Content(mediaType = "application/json", examples = @ExampleObject(
                        name = "Usuario de ejemplo", value = SwaggerUtils.USUARIO_CREADO_EXITO))
        ),
        @ApiResponse(responseCode = "400", description = "No es posible registrar la cuenta",
                content = @Content(mediaType = "application/json", examples = {
                        @ExampleObject(name = "Correo ya existe", value = SwaggerUtils.CORREO_YA_REGISTRADO),
                        @ExampleObject(name = "Contraseña incorrecta", value = SwaggerUtils.CONTRASENA_INVALIDA),
                        @ExampleObject(name = "Correo invalido", value = SwaggerUtils.CORREO_INVALIDO)
                })
        )
})
public @interface SaveUserSwagger {}
