package br.com.tech4me.locacaoms.shared;

import java.time.LocalDate;

public record DevolucaoCompletoDto(String id, String IdLivro, LocalDate data_Devolucao) {
    
}
