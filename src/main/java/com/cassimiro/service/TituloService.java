package com.cassimiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cassimiro.enumerado.EnumStatusTitulo;
import com.cassimiro.model.Titulo;
import com.cassimiro.repository.TituloRepository;

@Service
public class TituloService {

	@Autowired
	TituloRepository tituloRepository;
	
	public void salvar(Titulo titulo){
		try {
			tituloRepository.save(titulo);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		}
	}

	public void excluir(Long codigo) {
		this.tituloRepository.delete(codigo);
	}

	public List<Titulo> listar() {
		return tituloRepository.findAll();
	}
	
	public String receber(Long codigo) {
		Titulo titulo = tituloRepository.findOne(codigo);
		titulo.setStatus(EnumStatusTitulo.RECEBIDO);
		tituloRepository.save(titulo);
		return titulo.getStatus().getDescricao();
	}
}
