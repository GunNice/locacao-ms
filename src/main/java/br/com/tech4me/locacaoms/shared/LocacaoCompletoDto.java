package br.com.tech4me.locacaoms.shared;

import java.time.LocalDate;

public record LocacaoCompletoDto(String id, String IdLivro, LocalDate data_Locacao) {
    
}