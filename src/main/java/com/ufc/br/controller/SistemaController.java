package com.ufc.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Prato;

@Controller
public class SistemaController {
	
	@Autowired
	PratoController pratoController;
	
	@RequestMapping("/")
	public ModelAndView mostrarPaginaInicial() {
		List<Prato> pratos = pratoController.listarPratos();
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("listaPratos", pratos);
		return mv;
	}

}
