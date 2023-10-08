package br.com.tech4me.locacaoms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.tech4me.locacaoms.model.Locacao;

public interface LocacaoRepository extends MongoRepository<Locacao, String> {
    
}
