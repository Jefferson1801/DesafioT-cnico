package com.prueba.demo.support.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prueba.demo.validation.PasswordConstraint;


@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDto {
    
    private String id;
    private String nombre;

    @Email(message = "El campo 'email' debe tener un formato de correo electrónico válido")
    private String correo;

    @PasswordConstraint
    private String contrasena;

    private Integer activo;
    private LocalDateTime diaCreado;
    private LocalDateTime diaModificado;
    private LocalDateTime diaUltimoLogueado;
    private List<TelefonoDto> telefono;

    @Setter
    @Getter
    public static class TelefonoDto {
        private String id;
        private String idUsuario;
        private String numero;
        private String codigoCiudad;
        private String codigoPais;
        private Integer activo;
        private LocalDateTime diaCreado;
        private LocalDateTime diaModificado;
        private LocalDateTime diaUltimoLogueado;
    }
}
