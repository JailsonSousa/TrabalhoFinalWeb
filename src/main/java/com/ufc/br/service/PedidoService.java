package com.ufc.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Pedido;
import com.ufc.br.model.Prato;
import com.ufc.br.model.Usuario;
import com.ufc.br.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	public void criarPedido(Usuario usuario) {
		Pedido pedido = new Pedido(usuario);
		pedidoRepository.save(pedido);
	}

	public void atualizarPedido(Pedido pedido) {
		pedidoRepository.save(pedido);
	}
	
	public List<Pedido> listarPedido(Usuario usuario) {
		return pedidoRepository.findAllByUsuario(usuario);
		
	}
	
	public Pedido buscarPedido(Long codigo) {
		return pedidoRepository.getOne(codigo);
	}

	public Pedido buscarPedidoAberto(Usuario usuario) {
		List<Pedido> pedidos = pedidoRepository.findAllByUsuario(usuario);
		for (Pedido pedido : pedidos) {
			if(pedido.isStatus()) {
				return pedido;
			}
		}
		return null;
	}

	public void adcionarPratoPedido(Pedido pedido, Prato prato) {
		List<Prato> pratos = pedido.getPratos();
		pratos.add(prato);
		pedido.setPratos(pratos);
		pedidoRepository.save(pedido);
	}
}