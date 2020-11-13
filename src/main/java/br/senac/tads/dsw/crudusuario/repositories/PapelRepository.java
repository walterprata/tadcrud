package br.senac.tads.dsw.crudusuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.senac.tads.dsw.crudusuario.model.Papel;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Integer>{

}
