package com.generation.lojadegames.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.lojadegames.model.Produto;
import com.generation.lojadegames.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*" ,allowedHeaders = "*" )
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository ;
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.ok(produtoRepository.findAll()); // select * from tb_produto;
		
	}
	
	//select * from tb_postagem where id = ?
		@GetMapping("/{id}")
		public ResponseEntity <Produto> getById(@PathVariable Long id){
			return produtoRepository.findById(id)
					.map(resposta -> ResponseEntity.ok(resposta))
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		}
		
		@GetMapping("/nome/{nome}")
		public ResponseEntity <List<Produto>> getByNome(@PathVariable String nome){
			return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
			}
		
		@GetMapping("/menor/{preco}")
		public ResponseEntity <List<Produto>> getByMenor(@PathVariable BigDecimal preco){
			return ResponseEntity.ok(produtoRepository.findByPrecoLessThan(preco));
			}
		
		@GetMapping("/maior/{preco}")
		public ResponseEntity <List<Produto>> getBymaior(@PathVariable BigDecimal preco){
			return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThan(preco));
			}
		
		
		@PostMapping
		public ResponseEntity<Produto> post (@Valid @RequestBody Produto produto){
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(produtoRepository.save(produto));
			// insert into tb_produto (titulo,texto)values (valores...);
		}
		
		@PutMapping
		public ResponseEntity<Produto> put (@Valid @RequestBody Produto produto){
			return produtoRepository.findById(produto.getId())
					.map(resposta -> ResponseEntity.ok(produtoRepository.save(produto)))
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
			// uptade into tb_produto  set titulo = ?, texto = ?  where id = ? ;
			
		}
		
		@DeleteMapping ("/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void delete( @PathVariable long id) {
			
			Optional<Produto> produto = produtoRepository.findById(id);

			if (produto.isEmpty())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			
			produtoRepository.deleteById(id);
		}
		
		
		

}
