/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kevin
 */
public class Jogo {
    private String id;
    private String titulo;
    private String descricao;
    private String plataforma;
    private String genero;
    private double preco;
    private boolean ativo; //jogo está ativo no catálogo
    
    public Jogo(
        String titulo, String descricao, String plataforma, String genero, double preco, boolean ativo
    ){
        setTitulo(titulo);
        setDescricao(descricao);
        setPlataforma(plataforma);
        setGenero(genero);
        setPreco(preco);
        this.ativo = ativo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        //validação para o titulo não estar vazio nem em branco
        if(titulo == null || titulo.isBlank()){
            throw new IllegalArgumentException(
                "O campo Titulo precisa ser preenchido."
            );
        }
        this.titulo = titulo.trim(); //remove espaços desnecessários no início e no final
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if(descricao == null || descricao.isBlank()){
            throw new IllegalArgumentException(
                "O campo Descricao precisa ser preenchido."
            );
        }
        this.descricao = descricao.trim();
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if(preco < 0){
            throw new IllegalArgumentException(
                "O preco deve ser um numero valido e nao negativo."
            );
        }
        this.preco = preco;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        if(plataforma == null || plataforma.isBlank()){
            throw new IllegalArgumentException(
                "O campo Plataforma precisa ser preenchido."
            );
        }
        this.plataforma = plataforma.trim();
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        if(genero == null || genero.isBlank()){
            throw new IllegalArgumentException(
                "O campo Genero precisa ser preenchido."
            );
        }
        this.genero = genero.trim();
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
   @Override
    public String toString() {
        return "Id: " + this.id +
                "\nTitulo: " + this.titulo + 
                "\nDescricao: " + this.descricao +
                "\nPlataforma: " + this.plataforma +
                "\nGenero: " + this.genero +
                "\nPreco: " + this.preco +
                "\nAtivo: " + (this.ativo ? "Sim" : "Nao");
        
    }
    
}
