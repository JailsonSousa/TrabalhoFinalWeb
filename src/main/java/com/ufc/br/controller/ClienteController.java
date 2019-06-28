package com.ufc.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Pedido;
import com.ufc.br.model.Usuario;
import com.ufc.br.service.UsuarioService;


@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PedidoController pedidoController;
	
	@RequestMapping("/formulario/cadastrarCliente")
	public String formularioCadastroCliente() {
		return "cadastrarCliente";
	}
	
	@RequestMapping("/cadastroClienteSucesso")
	public String cadastroSucesso() {
		return "clienteCadastroSucesso";
	}
	
	@RequestMapping("/cadastrarCliente")
	public ModelAndView cadastraCliente(@Validated Usuario usuario, BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("cadastrarCliente");
		}
		
		ModelAndView mv = new ModelAndView("redirect:/cliente/cadastroClienteSucesso");
		usuarioService.cadastrar(usuario);
		return mv;
	}
	
	@RequestMapping("/fazerPedido")
	public ModelAndView criarPedido() {
		String usuarioLogado = this.getUserName();
		usuarioService.criarPedido(usuarioLogado);
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
	@RequestMapping("/fecharPedido/{codigo}")
	public ModelAndView fecharPedido(@PathVariable Long codigoPedido) {
		pedidoController.fecharPedido(codigoPedido);
		ModelAndView mv = new ModelAndView("redirect:/cliente/listarPedidos");
		return mv;
		
	}
	
	@RequestMapping("/adicionarPrato/{codigo}")
	public ModelAndView pedirPrato(@PathVariable Long codigo) {
		Usuario usuario = usuarioService.buscarClienteUsername(this.getUserName());
		usuarioService.adicionarPrato(usuario, codigo);
		ModelAndView mv = new ModelAndView("redirect:/cliente/listarPedidos");
		return mv;
	}
	
	@RequestMapping("/listaPratosPedido/{idPedido}")
	public ModelAndView mostrarPratosDoPedido(@PathVariable Long codigo) {
		Pedido pedido = pedidoController.buscarPedidoPorCodigo(codigo);
		ModelAndView mv = new ModelAndView("listaPratosPedidos");
		mv.addObject("pedido", pedido);
		mv.addObject("listaDePratos", pedido.getPratos());
		return mv;
	}
	
	@RequestMapping("/removerPratoDoPedido/{idPrato}")
	public ModelAndView removerPratoDoPedido(@PathVariable Long codigo) {
		Usuario usuario = usuarioService.buscarClienteUsername(this.getUserName());
		usuarioService.removerPrato(usuario,codigo);
		Pedido pedido = pedidoController.buscarPedidoAberto(usuario);
		ModelAndView mv = new ModelAndView("redirect:/cliente/listaPratosPedido/"+pedido.getCodigo());
		return mv;
	}
		
	public String getUserName() {
		UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuario.getUsername();
	}
}
