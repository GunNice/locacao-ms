package br.com.tech4me.locacaoms.model;

public class Livro {
   
    private String titulo;
    private boolean locado;
    
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public boolean isLocado() {
        return locado;
    }
    public void setLocado(boolean locado) {
        this.locado = locado;
    }

}
