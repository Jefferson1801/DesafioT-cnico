package com.prueba.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.prueba.demo.core.entity.Usuario;
import com.prueba.demo.repository.TelefonoRepository;
import com.prueba.demo.repository.UsuarioRepository;
import com.prueba.demo.service.impl.DemoServiceImpl;
import com.prueba.demo.support.dto.UsuarioDto;
import com.prueba.demo.support.dto.Answer;
import com.prueba.demo.support.dto.JwtUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private TelefonoRepository telefonoRepository;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private DemoServiceImpl demoService;

    @Before
    public void setUp() {
       MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetListUsuario() throws Exception {

        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(new Usuario()));

        Answer<?> respuesta = demoService.obtenerListaUsuario();
        
        assertThat(respuesta.isSuccess(), is(true));
        assertNotNull(respuesta.getDato());
    }

    @Test
    public void testAddUser() throws Exception {

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setCorreo("registrar@gmail.com");
        usuarioDto.setNombre("Nombre prueba");
        usuarioDto.setContrasena("PassowrdPass1!");
        usuarioDto.setTelefono(new ArrayList<>());

        when(usuarioRepository.findByCorreo(usuarioDto.getCorreo())).thenReturn(Arrays.asList());
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(new Usuario());

        when(jwtUtil.createToken(anyString())).thenReturn("");

        Answer<?> respuesta = demoService.agregarUsuario(usuarioDto);

        
        assertThat(respuesta.isSuccess(), is(true));
        assertNotNull(respuesta.getMessage(), is("Se registró correctamente"));
    }


    @Test
    public void testActualizarUsuario() throws Exception {

        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setId("1");
        usuarioExistente.setCorreo("existente@gmail.com");
        usuarioExistente.setNombre("Nombre Existente");
        usuarioExistente.setContrasena("PasswordExistente1!");
        
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId("1");
        usuarioDto.setCorreo("actualizar@gmail.com");
        usuarioDto.setNombre("Nombre prueba");
        usuarioDto.setContrasena("PasswordPass2!");
        usuarioDto.setTelefono(new ArrayList<>());

        when(usuarioRepository.findByCorreo(usuarioDto.getCorreo())).thenReturn(Arrays.asList(usuarioExistente));
        when(usuarioRepository.findById(usuarioDto.getId())).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(new Usuario());
        when(jwtUtil.createToken(anyString())).thenReturn("");

        Answer<?> respuesta = demoService.actualizarUsuario(usuarioDto.getId(), usuarioDto);

        assertThat(respuesta.isSuccess(), is(true));
        assertNotNull(respuesta.getMessage(), is("Se actualizó correctamente"));
    }

}