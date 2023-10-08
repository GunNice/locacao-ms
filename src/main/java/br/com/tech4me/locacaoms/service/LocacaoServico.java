package br.com.tech4me.locacaoms.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.locacaoms.shared.LocacaoCompletoDto;
import br.com.tech4me.locacaoms.shared.LocacaoDto;

public interface LocacaoServico {
    LocacaoCompletoDto CadastrarLocacao(LocacaoCompletoDto dto);
    List<LocacaoCompletoDto> obterLocacao();
    java.util.Optional<LocacaoDto> obterLocacaoPorId(String id);
    void excluirLocacao(String id);
    java.util.Optional<LocacaoDto> atualizarLocacaoPorId(String id, LocacaoDto dto);
    
}
