package com.prueba.demo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.demo.service.DemoService;
import com.prueba.demo.support.dto.UsuarioDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuario")
@Api(value = "Recurso hola mundo", description = "muestra hola mundo")
public class UsuarioController {

	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private DemoService demoService;

	@ApiOperation(value = "Actualizar usuario")
	@RequestMapping(value = "/actualizarUsuario/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<Object> updateUser(@PathVariable(value = "id") String id,
			@Valid @RequestBody UsuarioDto body) {

		try {
			return ResponseEntity.ok(demoService.actualizarUsuario(id, body));

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new org.springframework.http.ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}

	}

	

}
