package com.ufc.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ufc.br.model.Prato;
import com.ufc.br.repository.PratoRepository;
import com.ufc.br.util.FileUpload;

@Service
public class PratoService {

	@Autowired
	private PratoRepository pratoRepository;

	public void cadastrar(Prato prato, MultipartFile imagem) {
		System.out.println(prato.getimagem());
		System.out.println(imagem);
		pratoRepository.save(prato);
		pratoRepository.findByNome(prato.getNome());
		String path = "img/" + prato.getCodigo() + ".png";
		FileUpload.salvarImagem(path, imagem);
	}

	public List<Prato> listarPratos() {
		return pratoRepository.findAll();
	}

	public void excluirPrato(Long codigo) {
		pratoRepository.deleteById(codigo);
	}

	public Prato buscarPrato(Long codigo) {
		return pratoRepository.getOne(codigo);
	}

}
