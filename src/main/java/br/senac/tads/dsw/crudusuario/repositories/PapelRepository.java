package br.senac.tads.dsw.crudusuario.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.senac.tads.dsw.crudusuario.model.Papel;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Integer>{

	@Query(value = "SELECT  papel.id, cargo FROM CLIENTE_PAPEL \r\n"
			+ "inner join papel on cliente_papel.id_papel = papel.id\r\n"
			+ "inner join cliente on cliente_papel.id_cliente = cliente.id\r\n"
			+ "where id_cliente = ?", nativeQuery = true)
	
	List<Papel> buscarPapelPorID(Integer id);
}
