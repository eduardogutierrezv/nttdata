package nttdata.usertest.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;


public class UserRequestDTO {

    @Schema(description = "Nombre del usuario", example = "Juan Rodriguez")
    private String nombre;

    @Schema(description = "Correo del usuario", example = "juan@rodriguez.cl")
    private String correo;

    @Schema(description = "Contraseña de usuario", example = "Hunter2!")
    private String contrasena;
    private ArrayList<TelefonoDTO> telefonos;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String contrasena, String correo, String nombre, ArrayList<TelefonoDTO> telefonos) {
        this.contrasena = contrasena;
        this.correo = correo;
        this.nombre = nombre;
        this.telefonos = telefonos;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public ArrayList<TelefonoDTO> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(ArrayList<TelefonoDTO> telefonos) {
        this.telefonos = telefonos;
    }
}
