package com.cassimiro.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cassimiro.enumerado.EnumStatusTitulo;
import com.cassimiro.model.Titulo;
import com.cassimiro.repository.TituloRepository;

@Controller
@RequestMapping("/rest/titulo")
public class TituloController {

	private static final String REST_LIST = "/titulo/titulo";
	public static final String REST_EDIT = "/titulo/tituloEdit";
	public static final String REST_REDIRECT_NEW = "redirect:/rest/titulo/novo";
	public static final String REST_REDIRECT_LIST = "redirect:/rest/titulo";
	
	@Autowired
	private TituloRepository tituloRepository;
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView(REST_EDIT);
		mv.addObject(new Titulo());
		return mv;
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes){
		if(errors.hasErrors()){
			return REST_EDIT;
		}
		tituloRepository.save(titulo);
		attributes.addFlashAttribute("mensagem", "Registro salvo com sucesso!");
		return REST_REDIRECT_NEW;
	}
	
	@RequestMapping
	public ModelAndView listar(){
		ModelAndView mv = new ModelAndView(REST_LIST);
		mv.addObject("titulos", tituloRepository.findAll());
		return mv;
	}
	
	@RequestMapping("/editar/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Titulo titulo){
		ModelAndView mv = new ModelAndView(REST_EDIT);
		mv.addObject("titulo", titulo);
		return mv;
	}
	
	@RequestMapping(value = "/remover/{codigo}", method = RequestMethod.DELETE)
	public String remover(@PathVariable("codigo") Long codigo, RedirectAttributes attributes){
		this.tituloRepository.delete(codigo);;
		attributes.addFlashAttribute("mensagem", "Registro excluído com sucesso!");
		return REST_REDIRECT_LIST;
	}
	
	@ModelAttribute("listaStatusTitulo")
	public List<EnumStatusTitulo> listarStatusTitulo(){
		return Arrays.asList(EnumStatusTitulo.values());
	}
}
