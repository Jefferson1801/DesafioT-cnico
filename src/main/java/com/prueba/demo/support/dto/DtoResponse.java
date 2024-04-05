package com.prueba.demo.support.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DtoResponse {
    
    private String id;
    private String creado;
    private Integer activo;
    private String modificado;
    private String ultimo_logueado;
    private String token;
    
    public DtoResponse(String id, String creado, Integer activo, String modificado,
    String ultimo_logueado, String token) {
        this.id = id;
        this.creado = creado;
        this.activo = activo;
        this.modificado = modificado;
        this.ultimo_logueado = ultimo_logueado;
        this.token = token;
    }

    public DtoResponse(String id, String ultimo_logueado, String token) {
        this.id = id;
        this.ultimo_logueado = ultimo_logueado;
        this.token = token;
    }
    

    

    
}
