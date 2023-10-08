package br.com.tech4me.locacaoms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.locacaoms.service.LocacaoServico;
import br.com.tech4me.locacaoms.shared.LocacaoCompletoDto;
import br.com.tech4me.locacaoms.shared.LocacaoDto;



@RestController
@RequestMapping("/locacao")
public class LocacaoController {
    @Autowired
    private LocacaoServico servico;

    @PostMapping
    public ResponseEntity<LocacaoCompletoDto> cadastrarDevolucao(@RequestBody LocacaoCompletoDto locacao) {
        return new ResponseEntity<>(servico.CadastrarLocacao(locacao), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LocacaoCompletoDto>> obterLocacao() {
        return new ResponseEntity<>(servico.obterLocacao(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocacaoDto> obterLocacaoPorId(@PathVariable String id) {
        Optional<LocacaoDto> retorno = servico.obterLocacaoPorId(id);

        if (retorno.isPresent()) {
            return new ResponseEntity<>(retorno.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirLocacao(@PathVariable String id) {
        servico.excluirLocacao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocacaoDto> atualizarLocacao(@PathVariable String id, @RequestBody LocacaoDto locacao) {
        Optional<LocacaoDto> retorno = servico.atualizarLocacaoPorId(id, locacao);

        if (retorno.isEmpty()) {
            return new ResponseEntity<>(retorno.get(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}