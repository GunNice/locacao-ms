package br.com.tech4me.locacaoms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.locacaoms.httpclient.CadastrosmsClient;
import br.com.tech4me.locacaoms.model.Livro;
import br.com.tech4me.locacaoms.model.Locacao;
import br.com.tech4me.locacaoms.repository.LocacaoRepository;
import br.com.tech4me.locacaoms.shared.LocacaoCompletoDto;
import br.com.tech4me.locacaoms.shared.LocacaoDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class LocacaoServicoImpl implements LocacaoServico {

    @Autowired
    private LocacaoRepository repositorio;

    @Autowired
    private CadastrosmsClient cadastrosmsClient;

    @Override
    public LocacaoCompletoDto CadastrarLocacao(LocacaoCompletoDto dto) {
        Locacao locacao = new Locacao(dto);
        repositorio.save(locacao);
        return new LocacaoCompletoDto(locacao.getId(), locacao.getIdLivro(), locacao.getData_Locacao());
    }

    @Override
    public List<LocacaoCompletoDto> obterLocacao() {
        return repositorio.findAll()
            .stream()
            .map(p -> new LocacaoCompletoDto(p.getId(), p.getIdLivro(), p.getData_Locacao()))
            .toList();
    }

    @CircuitBreaker(name = "obterLivro", fallbackMethod = "fallbackLocacaoPorId")
    @Override
    public Optional<LocacaoDto> obterLocacaoPorId(String id) {
        Optional<Locacao> locacao = repositorio.findById(id);

        if (locacao.isPresent()) {
            Livro livro = cadastrosmsClient.obterLivros(locacao.get().getIdLivro());
            LocacaoDto locacaoComLivro = new LocacaoDto(locacao.get().getData_Locacao(), locacao.get().getIdLivro(), livro);
            return Optional.of(locacaoComLivro);
        } else {
            return Optional.empty();
        }
    }

    public Optional<LocacaoDto> fallbackPedidosPorId(String id, Exception e) {
        Optional<Locacao> locacao = repositorio.findById(id);

        if (locacao.isPresent()) {
           LocacaoDto locacaoComLivro = new LocacaoDto(locacao.get().getData_Locacao(), locacao.get().getIdLivro(), null);
            return Optional.of(locacaoComLivro);
        } else {
            return Optional.empty();
        }
    }
    

    @Override
    public void excluirLocacao(String id) {
        repositorio.deleteById(id);
    }

    @Override
    public Optional<LocacaoDto> atualizarLocacaoPorId(String id, LocacaoDto dto) {
        Optional<Locacao> locacao = repositorio.findById(id);
        
        if (locacao.isPresent()) {
            Locacao locacaoAtualizar = new Locacao(dto);
            locacaoAtualizar.setId(id);
            repositorio.save(locacaoAtualizar);
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }
    
}

