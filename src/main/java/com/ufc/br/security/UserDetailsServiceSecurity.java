package com.ufc.br.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import com.ufc.br.model.Usuario;
import com.ufc.br.repository.UsuarioRepository;

@Repository
@Transactional
public class UserDetailsServiceSecurity implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(email);

		if (usuario == null) {
			throw new UsernameNotFoundException("usuario não encontrado");
		}
		return new User(usuario.getEmail(), usuario.getSenha(), true, true, true, true, usuario.getAuthorities());
	}

}
