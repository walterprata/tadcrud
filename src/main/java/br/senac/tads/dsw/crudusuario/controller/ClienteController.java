package br.senac.tads.dsw.crudusuario.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.senac.tads.dsw.crudusuario.enums.StatusCliente;
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
	PapelRepository papelRepository;
	
	@GetMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv= new ModelAndView("CadastroCliente");
		List<Papel> listaPapel = papelRepository.findAll();
		mv.addObject("status", StatusCliente.values());
		mv.addObject("papeis", listaPapel);
		return mv;
	}
	
	
	@PostMapping(value = "/salvar")
	public ModelAndView salvar(@ModelAttribute Cliente cliente, @RequestParam(value = "cargo", required = false) Integer[] cargo ,RedirectAttributes redirAttr) {
		cliente.setDataCadastro(MetodosUtilitarios.getDataHora());
		
		List<Papel> papeis = new ArrayList<>();
		
		if(cargo != null) {
			for(int i = 0; i <= cargo.length-1; i++) {
				
				Papel p = papelRepository.findById(cargo[i]).get();
				
				papeis.add(p);
			}
			
			cliente.setPapeis(papeis);
		} 
			cliente.setPapeis(Collections.emptyList());
		
		ModelAndView mv= new ModelAndView("Lista");
		clienteRepository.save(cliente);
		mv.addObject("mensagem", "Cliente salvo com sucesso!");
		
		return mv;
	}
	
	@ModelAttribute("papeis")
    public List<Papel> getPapeis() {
        return papelRepository.findAll();
    }
}
