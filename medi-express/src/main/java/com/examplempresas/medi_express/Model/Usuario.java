package com.examplempresas.medi_express.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity                  // Indica que esta clase es una entidad JPA
@Table(name = "usuarios")// Nombre de la tabla en la base de datos
@Data                    // Genera automáticamente los métodos getter, setter, toString, equals y hashCode
@AllArgsConstructor      // Genera un constructor con todos los parámetros
@NoArgsConstructor       // Genera un constructor sin parámetros
@Builder                 // Genera un constructor con el patrón Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @NotBlank(message = "El rol no puede estar vacío")
    private String rol;

}
