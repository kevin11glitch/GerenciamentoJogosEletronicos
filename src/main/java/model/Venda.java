/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class Venda {
    private int id;
    private Cliente cliente;
    private List<ItemVenda> itens;
    private LocalDateTime dataVenda;
    
    public Venda(Cliente cliente){
        if(cliente == null){
            throw new IllegalArgumentException(
                "O jogo precisa ser informado."
            );
        }
        
        this.cliente = cliente;
        this.dataVenda = LocalDateTime.now();
        this.itens = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void adicionarItem(Jogo jogo, int quantidade){
        if(jogo == null){
            throw new IllegalArgumentException(
                "O jogo precisa ser informado."
            );
        }
        
        if(!jogo.isAtivo()){
            throw new IllegalArgumentException(
                "O jogo não está ativo no catalogo."
            );
            
        }
        
        ItemVenda item = new ItemVenda(jogo, quantidade);
        
        itens.add(item);
    }
    
    public double calcularTotal(){
        double total = 0;
        
        for(ItemVenda item: itens){
            total += item.calcularSubtotal();
        }
        
        return total;
    }

}
