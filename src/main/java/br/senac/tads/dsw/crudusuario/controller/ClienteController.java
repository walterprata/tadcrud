package br.senac.tads.dsw.crudusuario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@RequestMapping("/novo")
	public String novo() {
		return "CadastroCliente";
	}
}
