package com.generation.lojadegames.repository;


import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.lojadegames.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long >{
	
	
	List<Produto> findAllByNomeContainingIgnoreCase(@Param("nome")String nome);
	List<Produto> findByPrecoLessThan(@Param("preco")double preco);
	List<Produto> findByPrecoGreaterThan(@Param("preco")double preco);
}
