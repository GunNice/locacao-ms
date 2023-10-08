package br.com.tech4me.locacaoms.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.tech4me.locacaoms.shared.DevolucaoCompletoDto;
import br.com.tech4me.locacaoms.shared.DevolucaoDto;
@Document("devolucao")
public class Devolucao {
    @Id
    private String id;
    private LocalDate data_Devolucao;
    private String IdLivro;

   
    public Devolucao(){}

    public Devolucao(DevolucaoCompletoDto dto) {
        this.id = dto.id();
       this.data_Devolucao = dto.data_Devolucao();
        this.IdLivro = dto.IdLivro();
        
    }

    public Devolucao(DevolucaoDto dto) {
        this.data_Devolucao = dto.data_Devolucao();
        this.IdLivro = dto.IdLivro();

    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
   
    public LocalDate getData_Devolucao() {
        return data_Devolucao;
    }
    public void setData_Devolucao(LocalDate data_Devolucao) {
        this.data_Devolucao = data_Devolucao;
    }
      public String getIdLivro() {
        return IdLivro;
    }

    public void setIdLivro(String idLivro) {
        IdLivro = idLivro;
    }




}
