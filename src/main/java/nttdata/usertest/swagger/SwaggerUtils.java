package nttdata.usertest.swagger;

public class SwaggerUtils {

    public static final String USUARIO_CREADO_EXITO = "{\"id\":\"f89520df...\",\"creado\":\"2025-04-19T12:58:38.419\",\"token\":\"eyJhbGci...\",\"activo\":true}";

    public static final String CORREO_YA_REGISTRADO = "{\"mensaje\": \"El correo ya está registrado\"}";
    public static final String CONTRASENA_INVALIDA = "{\"mensaje\": \"La contraseña no cumple con los requisitos.\"}";
    public static final String CORREO_INVALIDO = "{\"mensaje\": \"Formato de correo incorrecto.\"}";

    public static final String LOGIN_EXITO = "{\"id\":\"6ad0e52d...\",\"nombre\":\"Juan Rodriguez\",\"token\":\"eyJhbGci...\",\"activo\":true,\"telefonos\":[{\"id\":1,\"numero\":\"56949138183\"}]}";
    public static final String LOGIN_FALLIDO_PASS = "{\"mensaje\": \"No corresponde al password\"}";
    public static final String LOGIN_FALLIDO_INACTIVO = "{\"mensaje\": \"El usuario no se encuentra activo\"}";
    public static final String BUSQUEDAD_EXITOSA = "{\"id\":\"979408a1-546a-46ab-93ac-dfa18e6c80e2\",\"nombre\":\"Juan Rodriguez\",\"correo\":\"juan@rodriguez.cl\",\"password\":\"Hunter2!\",\"fechaCreacion\":\"2025-04-19T16:19:24.942\",\"fechaModificacion\":null,\"ultimoLogin\":\"2025-04-19T16:19:24.942\",\"token\":\"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5jbCIsImlhdCI6MTc0NTA5Mzk2NX0.d-ekPx-LI-gt81tAWrkwmc_Y62k0BZtNqt6sKIdksFaBNFJ6dhE0DUeKcfAGod04SuAAyyU7xyGihGd7Ibq7Pw\",\"activo\":true,\"telefonos\":[{\"id\":1,\"numero\":\"56949138183\"}]}";
    public static final String BUSQUEDAD_NO_EXITOSA = "{\"mensaje\": \"el usuario no existe\"}";
    public static final String TOKEN_UNAUTHORIZED = "{\"timestamp\":\"2025-04-19T20:29:08.977+0000\",\"status\":401,\"error\":\"Unauthorized\",\"message\":\"Error token invalido\",\"path\":\"path del recurso no disponible\"}";
    public static final String TOKEN_EXAMPLE = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";
    public static final String NAME_SECURITY = "bearerAuth";
    public static final String ACTUALIZAR_NO = "{\"mensaje\": \"No se puede actualizar el usuario.\"}";;
    public static final String ACTUALIZACION_EXITOSA = "{\"id\":\"dd9cfe13-4e06-4c55-a854-7c8eba1a78a9\",\"nombre\":\"eduardo Rodriguez\",\"correo\":\"juan@rodriguez.cl\",\"password\":\"Hunter22!\",\"fechaCreacion\":\"2025-04-19T23:52:47.456\",\"fechaModificacion\":\"2025-04-19T23:56:09.272\",\"ultimoLogin\":\"2025-04-19T23:55:25.984\",\"token\":\"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5jbCIsImlhdCI6MTc0NTEyMTE2N30.zW0hJIBUor6WPvLEf1oJROdGqUcYuuhn5AmseripRu7I43guuIGXSxeEgqjqOHpW-0uKrig5VetTCH803ubqsQ\",\"activo\":true,\"telefonos\":[{\"id\":1,\"numero\":\"56949138183\"}]}";
    public static final String ELIMINACION_NO_EXITOSA ="{\"mensaje\": \"Error al eliminar al usuario\"}";
    public static final String ELIMINACION_EXITOSA = "{\"mensaje\": \"Usuario eliminado\"}";
    public static final String CAMBIO_ESTADO_EXITOSO = "{\"mensaje\": \"El Usuario quedo en estado activado: boolean\"}";
    public static final String CAMBIO_ESTADO_NO_EXITOSO = "{\"mensaje\": \"No se encontro el usuario\"}";

}
