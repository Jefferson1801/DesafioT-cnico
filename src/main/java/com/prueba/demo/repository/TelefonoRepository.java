package com.prueba.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.demo.core.entity.Telefono;


 
public interface TelefonoRepository extends JpaRepository<Telefono, String>{

    List<Telefono> findByIdUsuario(String idUsuario);
}