package com.cassimiro.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cassimiro.enumerado.EnumStatusTitulo;
import com.cassimiro.model.Titulo;
import com.cassimiro.repository.TituloRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	@Autowired
	private TituloRepository tituloRepository;
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		return mv;
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(Titulo titulo){
		tituloRepository.save(titulo);
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		return mv;
	}
	
	public ModelAndView listar(){
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		tituloRepository.findAll();
		return mv;
	}
	
	@ModelAttribute("listaStatusTitulo")
	public List<EnumStatusTitulo> listarStatusTitulo(){
		return Arrays.asList(EnumStatusTitulo.values());
	}
}
