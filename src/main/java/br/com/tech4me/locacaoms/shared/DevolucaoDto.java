package br.com.tech4me.locacaoms.shared;

import java.time.LocalDate;

import br.com.tech4me.locacaoms.model.Livro;

public record DevolucaoDto( LocalDate data_Devolucao, String IdLivro, Livro dadosLivro) {
    
}