package br.senac.tads.dsw.crudusuario;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.senac.tads.dsw.crudusuario.model.Papel;
import br.senac.tads.dsw.crudusuario.repositories.PapelRepository;

@SpringBootApplication
public class CrudUsuarioApplication implements CommandLineRunner {

	@Autowired
	PapelRepository papelRepository;
	
    public static void main(String[] args) {
        SpringApplication.run(CrudUsuarioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
    	List<Papel> lista = papelRepository.findAll();
    	
    	if(lista.isEmpty()) {
    		Papel p1 = new Papel(null, "ADM");
            Papel p2 = new Papel(null, "PAPEL1");
            Papel p3 = new Papel(null, "PAPEL2");
            Papel p4 = new Papel(null, "PAPEL3");
            papelRepository.saveAll(Arrays.asList(p1,p2,p3,p4));
    	}
    	
    }

}
