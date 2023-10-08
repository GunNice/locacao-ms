package br.com.tech4me.locacaoms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.locacaoms.httpclient.CadastrosmsClient;
import br.com.tech4me.locacaoms.model.Devolucao;
import br.com.tech4me.locacaoms.model.Livro;
import br.com.tech4me.locacaoms.repository.DevolucaoRepository;
import br.com.tech4me.locacaoms.shared.DevolucaoCompletoDto;
import br.com.tech4me.locacaoms.shared.DevolucaoDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class DevolucaoServicoImpl implements DevolucaoServico {

    @Autowired
    private DevolucaoRepository repositorio;

    @Autowired
    private CadastrosmsClient cadastrosmsClient;

    @Override
    public DevolucaoCompletoDto CadastrarDevolucao(DevolucaoCompletoDto dto) {
        Devolucao devolucao = new Devolucao(dto);
        repositorio.save(devolucao);
        return new DevolucaoCompletoDto(devolucao.getId(), devolucao.getIdLivro(), devolucao.getData_Devolucao());
    }

    @Override
    public List<DevolucaoCompletoDto> obterDevolucao() {
        return repositorio.findAll()
            .stream()
            .map(p -> new DevolucaoCompletoDto(p.getId(), p.getIdLivro(), p.getData_Devolucao()))
            .toList();
    }

    @CircuitBreaker(name = "obterLivro", fallbackMethod = "fallbackdevolucaoPorId")
    @Override
    public Optional<DevolucaoDto> obterDevolucaoPorId(String id) {
        Optional<Devolucao> devolucao = repositorio.findById(id);

        if (devolucao.isPresent()) {
            Livro livro = cadastrosmsClient.obterLivros(devolucao.get().getId());
            DevolucaoDto devolucaoComLivro = new DevolucaoDto(devolucao.get().getData_Devolucao(), devolucao.get().getIdLivro(), livro);
            return Optional.of(devolucaoComLivro);
        } else {
            return Optional.empty();
        }
    }

    public Optional<DevolucaoDto> fallbackPedidosPorId(String id, Exception e) {
        Optional<Devolucao> devolucao = repositorio.findById(id);

        if (devolucao.isPresent()) {
           DevolucaoDto devolucaoComLivro = new DevolucaoDto(devolucao.get().getData_Devolucao(), devolucao.get().getIdLivro(), null);
            return Optional.of(devolucaoComLivro);
        } else {
            return Optional.empty();
        }
    }
    

    @Override
    public void excluirDevolucao(String id) {
        repositorio.deleteById(id);
    }

    @Override
    public Optional<DevolucaoDto> atualizarDevolucaoPorId(String id, DevolucaoDto dto) {
        Optional<Devolucao> devolucao = repositorio.findById(id);
        
        if (devolucao.isPresent()) {
            Devolucao devolucaoAtualizar = new Devolucao(dto);
            devolucaoAtualizar.setId(id);
            repositorio.save(devolucaoAtualizar);
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }
    
}
