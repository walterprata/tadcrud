package br.senac.tads.dsw.crudusuario.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.senac.tads.dsw.crudusuario.model.Papel;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Integer>{

	@Query(value = "SELECT * FROM CLIENTE_PAPEL WHERE ID_CLIENTE = ?", nativeQuery = true)
	List<Papel> buscarPapelPorID(Integer id);
}
