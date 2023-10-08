package br.com.tech4me.locacaoms.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.tech4me.locacaoms.shared.LocacaoCompletoDto;
import br.com.tech4me.locacaoms.shared.LocacaoDto;
@Document("locacao")
public class Locacao {
    @Id
    private String id;
    private String livro_Id;
    private LocalDate data_Locacao;
    private String IdLivro;


    public Locacao(){}

    public Locacao(LocacaoCompletoDto dto) {
        this.id = dto.id();
        this.data_Locacao = dto.data_Locacao();
        this.IdLivro = dto.IdLivro();
    }

    public Locacao(LocacaoDto dto) {
       
       
        this.data_Locacao = dto.data_Locacao();
        this.IdLivro = dto.IdLivro();
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
 
    public LocalDate getData_Locacao() {
        return data_Locacao;
    }
    public void setData_Locacao(LocalDate data_Locacao) {
        this.data_Locacao = data_Locacao;
    }
        public String getIdLivro() {
        return IdLivro;
    }

    public void setIdLivro(String idLivro) {
        IdLivro = idLivro;
    }


}
