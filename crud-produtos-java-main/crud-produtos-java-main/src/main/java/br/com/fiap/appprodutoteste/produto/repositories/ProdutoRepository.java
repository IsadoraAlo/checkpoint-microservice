package br.com.fiap.appprodutoteste.produto.repositories;


import br.com.fiap.appprodutoteste.produto.model.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {
}
