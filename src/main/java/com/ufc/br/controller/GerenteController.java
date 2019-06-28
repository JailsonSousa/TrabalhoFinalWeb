package com.ufc.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Prato;

@RequestMapping("/gerenciar")
@Controller
public class GerenteController {
	
	@Autowired
	private PratoController pratoController;
	
	@RequestMapping("/formulario/cadastrarPrato")
	public String formularioCadastroPrato() {
		return "cadastrarPrato";
	}
	
	@RequestMapping("/cadastroPratoSucesso")
	public String cadastroSucesso() {
		return "pratoCadastroSucesso";
	}
	
	@RequestMapping("/cadastrarPrato")
	public ModelAndView salvarPrato(@Validated Prato prato, BindingResult result, @RequestParam(value="imagem") MultipartFile imagem) {
		if(result.hasErrors()) {
			return new ModelAndView("cadastrarPrato");
		}
		ModelAndView mv = new ModelAndView("redirect:/gerenciar/cadastroPratoSucesso");
		pratoController.cadastrarPrato(prato, imagem);
		//mv.addObject("mensagem", "prato cadastrado");
		return mv;
	}
	
	@RequestMapping("/listarPratos")
	public ModelAndView mostrarPratos() {
		List<Prato> pratos = pratoController.listarPratos();
		ModelAndView mv = new ModelAndView("gerenciarPratos");
		mv.addObject("listaPratos", pratos);
		return mv;
	}
	
	@RequestMapping("/excluirPrato/{codigo}")
	public ModelAndView excluirPrato(@PathVariable Long codigo) {
		pratoController.excluirPrato(codigo);
		ModelAndView mv = new ModelAndView("redirect:/gerenciar/listarPratos");
		return mv;
	}
	
}
