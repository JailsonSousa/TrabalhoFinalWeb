package com.ufc.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ufc.br.model.Prato;
import com.ufc.br.service.PratoService;

@Controller
@RequestMapping("/pratos")
public class PratoController {
	
	@Autowired
	private PratoService pratoService;
		
	public void cadastrarPrato(Prato prato, MultipartFile imagem) {
		pratoService.cadastrar(prato, imagem);
	}
	

	public List<Prato> listarPratos() {
		return pratoService.listarPratos();
	}
	
	public void excluirPrato(Long codigo) {
		pratoService.excluirPrato(codigo);		
	}
	
	public Prato buscarPrato(Long codigo) {
		return pratoService.buscarPrato(codigo);
	}
}
