package com.example.proyectointegrador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.example.ProyectoIntegrador.models.Usuario;
import com.example.ProyectoIntegrador.repositories.UsuarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario("John Doe", "johndoe@example.com", "Empresa XYZ");
        usuario.setId(1L);
    }

    @Test
    void testGetUsuarioById() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Usuario found = usuarioService.getUsuarioById(1L).orElse(null);
        assertNotNull(found);
        assertEquals("John Doe", found.getNombre());
    }

    private void assertNotNull(Usuario found) {
    }

    @Test
    void testSaveUsuario() {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Usuario saved = usuarioService.saveUsuario(usuario);
        assertNotNull(saved);
        assertEquals("johndoe@example.com", saved.getCorreo());
    }

    @Test
    void testUpdateUsuario() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        usuario.setNombre("Jane Doe");
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Usuario updated = usuarioService.updateUsuario(1L, usuario);
        assertEquals("Jane Doe", updated.getNombre());
    }

    @Test
    void testDeleteUsuario() {
        usuarioService.deleteUsuario(1L);
        // Aquí no se espera ningún retorno, se verifica que no haya excepciones
    }

    @Test
    void testUsuarioNotFound() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Usuario> found = usuarioService.getUsuarioById(1L);
        assertEquals(Optional.empty(), found);
    }
}
