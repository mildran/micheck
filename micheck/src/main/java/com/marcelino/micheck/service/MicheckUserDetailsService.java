package com.marcelino.micheck.service;

import com.marcelino.micheck.UsuarioDetails;
import com.marcelino.micheck.model.Usuario;
import com.marcelino.micheck.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MicheckUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public MicheckUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        if (!usuario.isAprobado()) {
            throw new UsernameNotFoundException("Usuario pendiente de aprobación");
        }

        return new UsuarioDetails(usuario);
    }
}