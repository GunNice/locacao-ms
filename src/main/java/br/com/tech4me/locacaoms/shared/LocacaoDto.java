package br.com.tech4me.locacaoms.shared;

import java.time.LocalDate;

import br.com.tech4me.locacaoms.model.Livro;

public record LocacaoDto(LocalDate data_Locacao, String IdLivro, Livro dadosLivro) {
    
}
