package com.cassimiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cassimiro.model.Titulo;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	@RequestMapping("/novo")
	public String novo(){
		return "CadastroTitulo";
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvar(Titulo titulo){
		//TODO: salvar no DB
		System.out.println(titulo);
		
		return "CadastroTitulo";
		
	}
}
