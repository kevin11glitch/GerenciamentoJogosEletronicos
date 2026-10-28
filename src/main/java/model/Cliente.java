/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import util.ValidarEmail;

/**
 *
 * @author kevin
 */
public class Cliente {
    private String id;
    private String nome;
    private String email;
    
    public Cliente(
        String nome, String email
    ){
        setNome(nome);
        setEmail(email);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException(
                "O campo Nome não pode estar em branco ou conter caracteres nulos."
            );
        }
        
        String nomeTemp = nome.trim();
        
        if(nomeTemp.length() < 3){
            throw new IllegalArgumentException(
                "O nome deve ser pelo menos 3 caracteres."
            );
        }
        
        if (nomeTemp.matches(".*\\d.*")) { //regex para identificar numeros 
            throw new IllegalArgumentException(
                "O nome nao pode conter numeros."
            );
        }
        this.nome = nomeTemp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(!ValidarEmail.validar(email)){
            throw new IllegalArgumentException(
                "Digite um Email valido."
            );
        }
        this.email = email.trim();
    }
    
    @Override
    public String toString() {
        return "Nome: " + this.nome + 
                "\nEmail: " + this.email;
        
    }
    
    
}
