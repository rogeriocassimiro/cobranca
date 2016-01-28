package com.cassimiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cassimiro.model.Titulo;
import com.cassimiro.repository.TituloRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	@Autowired
	private TituloRepository tituloRepository;
	
	@RequestMapping("/novo")
	public String novo(){
		return "CadastroTitulo";
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvar(Titulo titulo){
		tituloRepository.save(titulo);
		return "CadastroTitulo";
	}
}
