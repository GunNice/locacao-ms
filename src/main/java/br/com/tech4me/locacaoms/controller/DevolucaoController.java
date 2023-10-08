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

import br.com.tech4me.locacaoms.service.DevolucaoServico;
import br.com.tech4me.locacaoms.shared.DevolucaoCompletoDto;
import br.com.tech4me.locacaoms.shared.DevolucaoDto;

@RestController
@RequestMapping("/devolucao")
public class DevolucaoController {
    @Autowired
    private DevolucaoServico servico;

    @PostMapping
    public ResponseEntity<DevolucaoCompletoDto> cadastrarDevolucao(@RequestBody DevolucaoCompletoDto devolucao) {
        return new ResponseEntity<>(servico.CadastrarDevolucao(devolucao), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DevolucaoCompletoDto>> obterDevolucao() {
        return new ResponseEntity<>(servico.obterDevolucao(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DevolucaoDto> obterDevolucaoPorId(@PathVariable String id) {
        Optional<DevolucaoDto> retorno = servico.obterDevolucaoPorId(id);

        if (retorno.isPresent()) {
            return new ResponseEntity<>(retorno.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDevolucao(@PathVariable String id) {
        servico.excluirDevolucao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DevolucaoDto> atualizarDevolucao(@PathVariable String id, @RequestBody DevolucaoDto devolucao) {
        Optional<DevolucaoDto> retorno = servico.atualizarDevolucaoPorId(id, devolucao);

        if (retorno.isEmpty()) {
            return new ResponseEntity<>(retorno.get(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}