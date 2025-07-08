package com.examplempresas.medi_express;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data// Genera automáticamente los métodos getter, setter, toString, equals y hashCode
@Schema(description = "DTO para crear un nuevo usuario")
public class UsuarioCreateDTO {
    
    @Schema(description =  "Nombre del usuario", example = "Juan")
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @Schema(description =  "Apellido del usuario", example = "Pérez")
    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;

    @Schema(description =  "Email del usuario", example = "juan.perez@example.com")
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    private String email;

    @Schema(description =  "Contraseña del usuario", example = "password123")
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @Schema(description =  "Rol del usuario", example = "ADMIN")
    @NotBlank(message = "El rol no puede estar vacío")
    private String rol;

}
