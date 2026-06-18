package com.marcelino.micheck.service;

import com.marcelino.micheck.model.Usuario;
import com.marcelino.micheck.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.marcelino.micheck.repository.CategoriaRepository;
import com.marcelino.micheck.repository.EntradaRepository;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoriaRepository categoriaRepository;
    private final EntradaRepository entradaRepository;


    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
                          CategoriaRepository categoriaRepository, EntradaRepository entradaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.categoriaRepository = categoriaRepository;
        this.entradaRepository = entradaRepository;
    }

    public void registrar(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRol("ROLE_USER");
        usuario.setAprobado(false);
        usuarioRepository.save(usuario);
    }

    public List<Usuario> getPendientes() {
        return usuarioRepository.findByAprobadoFalse();
    }

    public List<Usuario> getAprobados() {
        return usuarioRepository.findByAprobadoTrue();
    }

    public void aprobar(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        usuario.setAprobado(true);
        usuarioRepository.save(usuario);
    }

    @org.springframework.transaction.annotation.Transactional
    public void rechazar(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        entradaRepository.deleteAll(entradaRepository.findAll().stream()
                .filter(e -> e.getUsuario() != null && e.getUsuario().getId().equals(id))
                .toList());
        categoriaRepository.deleteAll(categoriaRepository.findByUsuario(usuario));
        usuarioRepository.deleteById(id);
    }

    public void hacerAdmin(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        usuario.setRol("ROLE_ADMIN");
        usuarioRepository.save(usuario);
    }

    public Usuario getByEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    public long getTotalUsuarios() {
        return usuarioRepository.count();
    }

    public void crearAdmin(String nombre, String email, String password) {
        Usuario admin = new Usuario();
        admin.setNombre(nombre);
        admin.setEmail(email);
        admin.setPassword(passwordEncoder.encode(password));
        admin.setRol("ROLE_ADMIN");
        admin.setAprobado(true);
        usuarioRepository.save(admin);
    }
}