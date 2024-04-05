package com.prueba.demo.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.demo.core.entity.Telefono;
import com.prueba.demo.core.entity.Usuario;
import com.prueba.demo.repository.TelefonoRepository;
import com.prueba.demo.repository.UsuarioRepository;
import com.prueba.demo.service.DemoService;
import com.prueba.demo.support.dto.Answer;
import com.prueba.demo.support.dto.DtoResponse;
import com.prueba.demo.support.dto.JwtUtil;
import com.prueba.demo.support.dto.UsuarioDetalle;
import com.prueba.demo.support.dto.UsuarioDto;
import com.prueba.demo.support.dto.UsuarioDto.TelefonoDto;

@Service
public class DemoServiceImpl implements DemoService {
	private static final Logger log = LoggerFactory.getLogger(DemoServiceImpl.class);

	private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	TelefonoRepository telefonoRepository;

	@Autowired
	JwtUtil jwtUtil;

	@Override
	public Answer<?> agregarUsuario(UsuarioDto dto) throws Exception {

		DtoResponse respuesta = null;
		try {

			if (dto.getId() == null || dto.getId().equals("")) {
				List<Usuario> validarCorreo = usuarioRepository.findByCorreo(dto.getCorreo());

				if (validarCorreo != null && !validarCorreo.isEmpty()) {
					return new Answer<>(false, null, "El correo ya fue registrado");
				}
				UUID uuid = null;
				uuid = UUID.randomUUID();
				String idUsuarioGenerado = uuid.toString();
				dto.setId(idUsuarioGenerado);

				Usuario usuario = new Usuario();
				usuario.setId(dto.getId());
				usuario.setNombre(dto.getNombre());
				usuario.setCorreo(dto.getCorreo());
				usuario.setContrasena(dto.getContrasena());
				usuario.setActivo(dto.getActivo());
				usuario.setDiaCreado(LocalDateTime.now());
				usuario.setDiaModificado(dto.getDiaModificado());
				usuario.setDiaUltimoLogueado(LocalDateTime.now());
				Usuario usuarioSave = usuarioRepository.save(usuario);

				Telefono telefono = new Telefono();
				String idTelefonoGenerado = "";

				if (dto.getTelefono() != null && !dto.getTelefono().isEmpty()) {
					for (TelefonoDto telefonos : dto.getTelefono()) {
						telefono = new Telefono();
						idTelefonoGenerado = "";

						uuid = UUID.randomUUID();
						idTelefonoGenerado = uuid.toString();
						telefono.setId(idTelefonoGenerado);

						telefono.setId(telefono.getId());
						telefono.setIdUsuario(usuarioSave.getId());
						telefono.setNumero(telefonos.getNumero());
						telefono.setCodigoCiudad(telefonos.getCodigoCiudad());
						telefono.setCodigoPais(telefonos.getCodigoPais());
						telefono.setActivo(telefonos.getActivo());
						telefono.setDiaCreado(usuarioSave.getDiaCreado());
						telefono.setDiaModificado(usuarioSave.getDiaModificado());
						telefono.setDiaUltimoLogueado(usuarioSave.getDiaUltimoLogueado());
						telefonoRepository.save(telefono);
					}
				}

				UsuarioDetalle detalle = new UsuarioDetalle();
				detalle.setCorreo(dto.getCorreo());
				detalle.setNombre(dto.getNombre());
				detalle.setId(usuarioSave.getId());

				String token = jwtUtil.createToken(usuarioSave.getId());

				DtoResponse response = new DtoResponse(usuarioSave.getId(),
						formatLocalDate(usuarioSave.getDiaCreado()),
						usuarioSave.getActivo(),
						usuarioSave.getDiaModificado() != null ? (formatLocalDate(usuarioSave.getDiaModificado())) : null,
						formatLocalDate(usuarioSave.getDiaUltimoLogueado()), token);

				respuesta = response;
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return new Answer<>(true, respuesta, respuesta != null ? "Se registró correctamente" : "Hubo un error al registrar al usuario");
	}

	@Override
	public Answer<?> actualizarUsuario(String idUsuario, UsuarioDto body) throws Exception {

		DtoResponse respuesta = null;
		try {
			if (idUsuario != null) {
				List<Usuario> validarCorreo = usuarioRepository.findByCorreo(body.getCorreo());
				Boolean mismoUsuario = false;
				for (Usuario usuario : validarCorreo) {
					if (body.getId().equals(usuario.getId())) {
						mismoUsuario = true;
					}
				}
				if (!mismoUsuario && validarCorreo != null && !validarCorreo.isEmpty()) {
					return new Answer<>(false, null, "El correo ya esta registrado");
				}

				UUID uuid = null;

				Usuario usu = new Usuario();
				Optional<Usuario> usuario = usuarioRepository.findById(body.getId());
				usu = usuario.get();
				usu.setId(body.getId());
				usu.setNombre(body.getNombre());
				usu.setCorreo(body.getCorreo());
				usu.setContrasena(body.getContrasena());
				usu.setActivo(body.getActivo());
				usu.setDiaModificado(LocalDateTime.now());
				Usuario usuarioSave = usuarioRepository.save(usu);

				Telefono telefono = new Telefono();
				String idTelefonoGenerado = "";

				if (body.getTelefono() != null && !body.getTelefono().isEmpty()) {
					for (TelefonoDto tel : body.getTelefono()) {
						telefono = new Telefono();
						idTelefonoGenerado = "";
						if (tel.getId() == null || tel.getId().equals("")) {
							uuid = UUID.randomUUID();
							idTelefonoGenerado = uuid.toString();
							tel.setId(idTelefonoGenerado);
						}
						telefono.setId(tel.getId());
						telefono.setIdUsuario(usuarioSave.getId());
						telefono.setNumero(tel.getNumero());
						telefono.setCodigoCiudad(tel.getCodigoCiudad());
						telefono.setCodigoPais(tel.getCodigoPais());
						telefono.setActivo(tel.getActivo());
						telefono.setDiaCreado(usuarioSave.getDiaCreado());
						telefono.setDiaModificado(usuarioSave.getDiaModificado());
						telefono.setDiaUltimoLogueado(usuarioSave.getDiaUltimoLogueado());
						telefonoRepository.save(telefono);
					}
				}

				String token = jwtUtil.createToken(usuarioSave.getId());

				DtoResponse response = new DtoResponse(usuarioSave.getId(),
						formatLocalDate(usuarioSave.getDiaCreado()),
						usuarioSave.getActivo(), formatLocalDate(usuarioSave.getDiaModificado()),
						formatLocalDate(usuarioSave.getDiaUltimoLogueado()), token);

				respuesta = response;
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return new Answer<>(true, respuesta, respuesta != null ? "Se actualizó correctamente" : "Hubo un error al actualizar el registro");
	}

	@Override
	public Answer<?> obtenerListaUsuario() throws Exception {

		List<Usuario> listUsuario = usuarioRepository.findAll();
		List<Telefono> listTelefono = new ArrayList<>();

		List<UsuarioDto> listUsuarioDto = new ArrayList<>();
		UsuarioDto usuarioDto = new UsuarioDto();

		List<UsuarioDto.TelefonoDto> listTelefonoDto = new ArrayList<>();
		UsuarioDto.TelefonoDto telefonoDto = new TelefonoDto();

		for (Usuario usuario : listUsuario) {
			listTelefono = new ArrayList<>();
			usuarioDto = new UsuarioDto();
			listTelefonoDto = new ArrayList<>();

			usuarioDto.setId(usuario.getId());
			usuarioDto.setNombre(usuario.getNombre());
			usuarioDto.setCorreo(usuario.getCorreo());
			usuarioDto.setContrasena(usuario.getContrasena());
			usuarioDto.setActivo(usuario.getActivo());
			usuarioDto.setDiaCreado(usuario.getDiaCreado());
			usuarioDto.setDiaModificado(usuario.getDiaModificado());
			usuarioDto.setDiaUltimoLogueado(usuario.getDiaUltimoLogueado());
			listTelefono = telefonoRepository.findByIdUsuario(usuario.getId());
			for (Telefono telefono : listTelefono) {
				telefonoDto = new TelefonoDto();
				telefonoDto.setId(telefono.getId());
				telefonoDto.setIdUsuario(telefono.getIdUsuario());
				telefonoDto.setNumero(telefono.getNumero());
				telefonoDto.setCodigoCiudad(telefono.getCodigoCiudad());
				telefonoDto.setCodigoPais(telefono.getCodigoPais());
				telefonoDto.setActivo(telefono.getActivo());
				telefonoDto.setDiaCreado(telefono.getDiaCreado());
				telefonoDto.setDiaModificado(telefono.getDiaModificado());
				telefonoDto.setDiaUltimoLogueado(telefono.getDiaUltimoLogueado());
				listTelefonoDto.add(telefonoDto);
			}
			usuarioDto.setTelefono(listTelefonoDto);
			listUsuarioDto.add(usuarioDto);
		}

		return new Answer<>(true, listUsuarioDto);
	}

	@Override
	public Answer<?> login(UsuarioDto dto) throws Exception {

		Usuario user = new Usuario();
		Optional<Usuario> usuario = usuarioRepository.findById(dto.getId());
		user = usuario.get();
		user.setDiaUltimoLogueado(LocalDateTime.now());
		Usuario actualizarUsuario = usuarioRepository.save(user);

		String token = jwtUtil.createToken(actualizarUsuario.getId());

		DtoResponse response = new DtoResponse(actualizarUsuario.getId(), formatLocalDate(actualizarUsuario.getDiaUltimoLogueado()),
				token);
		return new Answer<>(true, response, "Se inició sesión correctamente");
	}

	private static String formatLocalDate(LocalDateTime localDateTime) {

		String fechaFormateada = "";

		if (localDateTime != null) {
			fechaFormateada = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN, Locale.GERMANY).format(localDateTime);
		}

		return fechaFormateada;
	}

}