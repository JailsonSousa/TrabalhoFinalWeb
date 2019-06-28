package com.ufc.br.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ufc.br.controller.PedidoController;
import com.ufc.br.model.Role;
import com.ufc.br.model.Usuario;
import com.ufc.br.repository.RoleRepository;
import com.ufc.br.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PedidoController pedidoController;
	
	public void cadastrar(Usuario cliente) {
		List<Role> roles = new ArrayList<Role>();
		Role role = roleRepository.findByRole("ROLE_CLIENTE");
		roles.add(role);
		cliente.setRoles(roles);
		cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
		
		usuarioRepository.save(cliente);
	}
	
	public void criarPedido(String usuarioLogado) {
		Usuario usuario = usuarioRepository.findByEmail(usuarioLogado);
		pedidoController.criarPedido(usuario);
	}

	public void fecharPedido(Long idPedido) {
		pedidoController.fecharPedido(idPedido);
	}

	public void adicionarPrato(Usuario usuario, Long codigo) {
		pedidoController.adcionarPratoPedido(usuario,codigo);
	}

	public void removerPrato(Usuario usuario, Long codigo) {
		pedidoController.removerPratoDoPedido(usuario,codigo);
	}
	
	public Usuario buscarClienteUsername(String username) {
		return usuarioRepository.findByEmail(username);
	}
}
