package com.prueba.demo.core.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USUARIO")
@Setter
@Getter
public class Usuario {

     @Column(name = "id")
     @Id
     private String id;

     @Column(name = "nombre")
     private String nombre;

     @Column(name = "correo")
     private String correo;

     @Column(name = "activo")
     private Integer activo;
     
     @Column(name = "contrasena")
     private String contrasena;

     @Column(name = "dia_creado")
     private LocalDateTime diaCreado;    

     @Column(name = "dia_modificado")
     private LocalDateTime diaModificado;    

     @Column(name = "dia_ultimo_logueado")
     private LocalDateTime diaUltimoLogueado;
     
     
}
