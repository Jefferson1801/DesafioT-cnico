package com.prueba.demo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.demo.service.DemoService;
import com.prueba.demo.support.dto.UsuarioDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/demo")
@Api(value = "Recurso hola mundo", description = "muestra hola mundo")
public class PruebaController {

	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private DemoService demoService;

	@ApiOperation(value = "Registrar  usuario")
	@RequestMapping(value = "/agregarUsuario", method = RequestMethod.POST)
	public ResponseEntity<Object> agregarUsuario(@Valid @RequestBody UsuarioDto dto) {

		try {
			return ResponseEntity.ok(demoService.agregarUsuario(dto));

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new org.springframework.http.ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "lista Usuario")
	@RequestMapping(value = "/obtenerListaUsuario", method = RequestMethod.GET)
	public ResponseEntity<Object> obtenerListaParametro() {

		try {
			return ResponseEntity.ok(demoService.obtenerListaUsuario());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.ok(e);
		}
	}

	@ApiOperation(value = "Iniciar sesion")
	@RequestMapping(value = "/iniciarSesion", method = RequestMethod.POST)
	public ResponseEntity<Object> login(@RequestBody UsuarioDto dto) {

		try {
			return ResponseEntity.ok(demoService.login(dto));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.ok(e);
		}
	}

}
