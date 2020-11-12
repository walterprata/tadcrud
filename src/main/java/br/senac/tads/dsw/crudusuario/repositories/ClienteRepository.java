package br.senac.tads.dsw.crudusuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senac.tads.dsw.crudusuario.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
