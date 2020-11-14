package br.senac.tads.dsw.crudusuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.senac.tads.dsw.crudusuario.model.Cliente;
import br.senac.tads.dsw.crudusuario.model.Papel;
import br.senac.tads.dsw.crudusuario.repositories.ClienteRepository;
import br.senac.tads.dsw.crudusuario.repositories.PapelRepository;
import br.senac.tads.dsw.crudusuario.utils.MetodosUtilitarios;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	PapelRepository PapelRepository;
	
	@RequestMapping("/novo")
	public String novo() {
		
		return "CadastroCliente";
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST )
	public String salvar(Cliente cliente, List<Papel> papeis) {
		cliente.setDataCadastro(MetodosUtilitarios.getDataHora());
		clienteRepository.save(cliente);
		return "CadastroCliente";
	}
}
