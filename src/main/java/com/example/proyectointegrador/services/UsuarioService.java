package com.example.proyectointegrador.services;

import com.example.ProyectoIntegrador.models.Usuario;
import com.example.ProyectoIntegrador.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        usuario.setNombre(usuarioDetails.getNombre());
        usuario.setCorreo(usuarioDetails.getCorreo());
        usuario.setEmpresa(usuarioDetails.getEmpresa());
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
