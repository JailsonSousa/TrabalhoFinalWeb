package com.ufc.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ufc.br.model.Pedido;
import com.ufc.br.model.Prato;
import com.ufc.br.model.Usuario;
import com.ufc.br.service.PedidoService;

@Controller
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private PratoController pratoController;

	public void criarPedido(Usuario usuario) {
		pedidoService.criarPedido(usuario);
	}

	public List<Pedido> listarPedidos(Usuario usuario) {
		return pedidoService.listarPedido(usuario);
	}

	public void fecharPedido(Long codigo) {
		Pedido pedido = pedidoService.buscarPedido(codigo);
		if (pedido != null) {
			pedido.setStatus(false);
		}
		pedidoService.atualizarPedido(pedido);
	}

	public void adcionarPratoPedido(Usuario usuario, Long codigoPrato) {
		Pedido pedido = pedidoService.buscarPedidoAberto(usuario);
		Prato prato = pratoController.buscarPrato(codigoPrato);
		if (pedido != null) {
			pedido.setValorTotal(pedido.getValorTotal() + prato.getValor());
			pedidoService.adcionarPratoPedido(pedido, prato);
		} else {
			this.criarPedido(usuario);
			this.adcionarPratoPedido(usuario, codigoPrato);
		}
	}

	public void removerPratoDoPedido(Usuario usuario, Long codigoPrato) {
		Pedido pedido = pedidoService.buscarPedidoAberto(usuario);
		Prato prato = pratoController.buscarPrato(codigoPrato);
		pedido.getPratos().remove(prato);
		pedido.setValorTotal(pedido.getValorTotal() - prato.getValor());
		pedidoService.atualizarPedido(pedido);
	}

	public Pedido buscarPedidoAberto(Usuario usuario) {
		return pedidoService.buscarPedidoAberto(usuario);
	}
	
	public Pedido buscarPedidoPorCodigo(Long codigo) {
		return pedidoService.buscarPedido(codigo);
	}

}
