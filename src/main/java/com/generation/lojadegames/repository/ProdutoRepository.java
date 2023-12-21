package com.generation.lojadegames.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.lojadegames.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long >{
	
	//select from * tb_postagem where psotagem like "%?%";
		List<Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
		List<Produto> findByPrecoLessThan(@Param("menor") BigDecimal preco);
		List<Produto> findByPrecoGreaterThan(@Param("maior") BigDecimal preco);
}
