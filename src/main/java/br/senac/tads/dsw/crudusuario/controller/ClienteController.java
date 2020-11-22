package br.senac.tads.dsw.crudusuario.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
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
	
	//CADASTRO
	@GetMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv= new ModelAndView("CadastroCliente");
		
		List<Papel> listaPapel = papelRepository.findAll();
		mv.addObject("status", StatusCliente.values());
		mv.addObject("papeis", listaPapel);
		mv.addObject("cliente", new Cliente());
		return mv;
	}
	
	//SALVAR
	@PostMapping(value = "/salvar")
	public String salvar( @ModelAttribute Cliente cliente, @RequestParam(value = "cargo[]", required = false) Integer[] cargo,
			RedirectAttributes redirAttr, @RequestParam(value = "userName") String username ) {
		
		try {
			cliente.setDataCadastro(MetodosUtilitarios.getDataHora());
			
			List<Papel> papeis = new ArrayList<>();
			
			if(cargo != null) {
				for(int i = 0; i <= cargo.length-1; i++) {
					Papel p = papelRepository.findById(cargo[i]).get();
					papeis.add(p);
				}
				
				cliente.setPapeis(papeis);
			} 
				
			
			if(cliente != null) {
				clienteRepository.save(cliente);
				redirAttr.addFlashAttribute("mensagem","Cliente salvo com sucesso!");
			} 
			
		}catch(DataIntegrityViolationException e) {
			redirAttr.addFlashAttribute("mensagemError","O nome de usuário: \"" + username  + "\"" + " já existe!");
			return "redirect:/cliente/novo";
		}
		
		
		return "redirect:/cliente";
	}
	
	//LISTAR CLIENTES
	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("Lista");
		List<Cliente> clientes = clienteRepository.findAll();
		mv.addObject("listaClientes", clientes);
		return mv;
	}
	
	//ALTERAR CLIENTE
	@GetMapping(value = "/editar/{id}")
	public ModelAndView editar(@PathVariable Integer id) {
		Cliente cliente = clienteRepository.findById(id).orElse(null);
		List<Papel> papeis = papelRepository.buscarPapelPorID(id);
		List<Papel> list = papelRepository.findAll();
		
			
		ModelAndView mv= new ModelAndView("CadastroCliente");
		mv.addObject("status", StatusCliente.values());
		mv.addObject("papeis", papeis);
		mv.addObject("cliente", cliente);
		
		return mv;
	}
	
	// REMOVER CLIENTE
	@PostMapping("/{id}/remover")
    public ModelAndView remover(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		
		clienteRepository.deleteById(id);
		
        
		redirectAttributes.addFlashAttribute("msgSucesso",
                "Produto ID " + id + " removido com sucesso");
        return new ModelAndView("redirect:/cliente");
    }

}
