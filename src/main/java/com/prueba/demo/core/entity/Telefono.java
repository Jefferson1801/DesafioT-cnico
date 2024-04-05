package com.prueba.demo.core.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TELEFONO")
@Setter
@Getter
public class Telefono {
    
     @Column(name = "id")
     @Id
     private String id;

     @Column(name = "id_usuario")
     private String idUsuario;

     @Column(name = "numero_telefono")
     private String numero;

     @Column(name = "codigo_ciudad")
     private String codigoCiudad;

     @Column(name = "codigo_pais")
     private String codigoPais;

     @Column(name = "activo")
     private Integer activo;

     @Column(name = "dia_creado")
     private LocalDateTime diaCreado;    

     @Column(name = "dia_modificado")
     private LocalDateTime diaModificado;    

     @Column(name = "dia_ultimo_logueado")
     private LocalDateTime diaUltimoLogueado;
     
}
