package com.prueba.demo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.demo.core.entity.Usuario;


 
public interface UsuarioRepository extends JpaRepository<Usuario, String>{

   Optional<Usuario> findById(String id);
   List<Usuario> findByCorreo(String correo);
}