package br.com.tech4me.locacaoms.httpclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import br.com.tech4me.locacaoms.model.Livro;

@FeignClient("cadastros-ms")
public interface CadastrosmsClient {
    @RequestMapping(method = RequestMethod.GET, value = "/livros/{id}")
    Livro obterLivros(@PathVariable String id);
}
