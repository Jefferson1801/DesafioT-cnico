package com.prueba.demo.service;
 
import com.prueba.demo.support.dto.Answer;
import com.prueba.demo.support.dto.UsuarioDto;
 

public interface DemoService {

	Answer<?> agregarUsuario(UsuarioDto dto) throws Exception;
	Answer<?> actualizarUsuario(String idUsuario, UsuarioDto body) throws Exception;
	Answer<?> obtenerListaUsuario() throws Exception;
	Answer<?> login(UsuarioDto dto) throws Exception;
}
