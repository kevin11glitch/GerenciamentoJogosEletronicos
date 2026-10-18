/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kevin
 */
public class ItemVenda {
    private Jogo jogo;
    private int quantidade;
    private double precoUn;
    
    public ItemVenda(Jogo jogo, int quantidade){
        if(jogo == null){
            throw new IllegalArgumentException(
                "O jogo deve ser informado."
            );
        }
        
        if (quantidade <= 0) {
            throw new IllegalArgumentException(
                "A quantidade deve ser maior que zero."
            );
        }
        
        this.jogo = jogo;
        this.quantidade = quantidade;
        this.precoUn = jogo.getPreco();
    }

    public Jogo getJogo() {
        return jogo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUn() {
        return precoUn;
    }

    public double calcularSubtotal(){
        return precoUn * quantidade;
    }
    
    
}
