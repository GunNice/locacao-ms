package br.com.tech4me.locacaoms.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.locacaoms.shared.DevolucaoCompletoDto;
import br.com.tech4me.locacaoms.shared.DevolucaoDto;

public interface DevolucaoServico {
    DevolucaoCompletoDto CadastrarDevolucao(DevolucaoCompletoDto dto);
    List<DevolucaoCompletoDto> obterDevolucao();
    Optional<DevolucaoDto> obterDevolucaoPorId(String id);
    void excluirDevolucao(String id);
    Optional<DevolucaoDto> atualizarDevolucaoPorId(String id, DevolucaoDto dto);
}
