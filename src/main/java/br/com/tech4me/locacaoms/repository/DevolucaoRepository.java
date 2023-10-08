package br.com.tech4me.locacaoms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.tech4me.locacaoms.model.Devolucao;


public interface DevolucaoRepository extends MongoRepository<Devolucao, String> {
    
}
