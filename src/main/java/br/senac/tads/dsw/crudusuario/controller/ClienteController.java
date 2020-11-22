package br.senac.tads.dsw.crudusuario.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import br.senac.tads.dsw.crudusuario.enums.StatusCliente;
import br.senac.tads.dsw.crudusuario.model.Cliente;
import br.senac.tads.dsw.crudusuario.model.Papel;
import br.senac.tads.dsw.crudusuario.model.PapelViewModel;
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
	
	private final String CADASTRO_CLIENTE = "CadastroCliente";
	private final String LISTA = "Lista";
	
	//CADASTRO
	@GetMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv= new ModelAndView(CADASTRO_CLIENTE);
		
		List<Papel> listaPapel = papelRepository.findAll();
		List<PapelViewModel> papelViewModel = new ArrayList<>();
		
		listaPapel.forEach( x -> papelViewModel.add(new PapelViewModel(x, false)));
		
		mv.addObject("status", StatusCliente.values());
		mv.addObject("papeis", papelViewModel);
		mv.addObject("cliente", new Cliente());
		return mv;
	}
	
	//SALVAR
	@PostMapping(value = "/salvar")
	public ModelAndView salvar(  @ModelAttribute @Valid Cliente cliente,Errors error, @RequestParam(value = "cargo[]", required = false) Integer[] cargo,
			RedirectAttributes redirAttr, @RequestParam(value = "userName") String username) {
		ModelAndView mv = new ModelAndView("redirect:/cliente/novo");
		
		try {
			cliente.setDataCadastro(MetodosUtilitarios.getDataHora());
			
			
			
			if(error.hasErrors() || cargo == null ) {
				
				List<Papel> listaPapel = papelRepository.findAll();
				List<PapelViewModel> papelViewModel = new ArrayList<>();
				
				listaPapel.forEach( x -> papelViewModel.add(new PapelViewModel(x, false)));
				mv.addObject("status", StatusCliente.values());
				mv.addObject("papeis", listaPapel);
				redirAttr.addFlashAttribute("mensagemError","Todos os campos são obrigatórios!");
				return mv;
			}
			
			
			List<Papel> papeis = new ArrayList<>();
			
			
				for(int i = 0; i <= cargo.length-1; i++) {
					Papel p = papelRepository.findById(cargo[i]).get();
					papeis.add(p);
				}
				
				
				cliente.setPapeis(papeis);
			
			
			
			if(!Objects.isNull(cliente)) { 
				clienteRepository.save(cliente);
				redirAttr.addFlashAttribute("mensagem","Cliente salvo com sucesso!");
				return new ModelAndView("redirect:/cliente");
			} else {
				redirAttr.addFlashAttribute("mensagemError","Erro ao salvar cliente!");
			}
			
		}catch(DataIntegrityViolationException e) {
			redirAttr.addFlashAttribute("mensagemError","O nome de usuário: \"" + username  + "\"" + " já existe!");
			return new ModelAndView("redirect:/cliente/novo");
		}
		
		
		return mv;
	}
	
	//LISTAR CLIENTES
	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView(LISTA);
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
		
		List<PapelViewModel> papelViewModel = new ArrayList<>();
		
		
		list.forEach( x -> papelViewModel.add(new PapelViewModel(x, false)));
		
		
			for(Papel papel : papeis) {
				for(PapelViewModel pvm : papelViewModel) {
					boolean selecionado = pvm.getPapel().getCargo().equals(papel.getCargo());
					if(selecionado) {
						pvm.setSelecionado(selecionado);
					}
					
				}
				
			}
			
		
		ModelAndView mv= new ModelAndView(CADASTRO_CLIENTE);
		mv.addObject("status", StatusCliente.values());
		mv.addObject("papeis", papelViewModel);
		mv.addObject("cliente", cliente);
		
		return mv;
	}
	
	// REMOVER CLIENTE
	@PostMapping("/{id}/remover")
    public ModelAndView remover(@PathVariable("id") Integer id, RedirectAttributes redirAttr) {
		
		clienteRepository.deleteById(id);
		redirAttr.addFlashAttribute("mensagem","Cliente deletado com sucesso!");
        return new ModelAndView("redirect:/cliente");
    }

}
