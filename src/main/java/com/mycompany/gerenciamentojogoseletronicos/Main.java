/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gerenciamentojogoseletronicos;

//import model.Cliente;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Jogo;
import model.Cliente;
import model.Venda;
import model.ItemVenda;

import repository.JogoRepository;
import java.util.List;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Jogo;

/**
 *
 * @author kevin
 */
public class Main {

    public static void main(String[] args) {
        
        Gson gson = new Gson();
        
        
        int opcao = -1;
        
        while(opcao != 0){
            opcao = Integer.parseInt(JOptionPane.showInputDialog("Gerenciamento de Venda de Jogos Eletronicos\n"
                    + "1 - Cadastrar Jogo\n"
                    + "2 - Pesquisar Jogo\n"
                    + "3 - Visualizar Jogos\n"
                    + "4 - Atualizar Jogo\n"
                    + "5 - Vender Jogo\n"
                    + "6 - Deletar Jogo\n"
                    + "0 - Sair\n\n"
            ));
            switch(opcao){
                case 1:
                    
                    break;

                case 2:
                    break;
                case 3:
                    JogoRepository jogoRepository = new JogoRepository();
        
                    try {
                        List<Jogo> jogos = jogoRepository.lerJogos();

                        for (Jogo jogo : jogos) {
                            JOptionPane.showMessageDialog(null, jogo);
                        }

                    } catch (IOException e) {
                        System.out.println("Erro ao ler os jogos: " + e.getMessage());
                    }
                    break;
            }
        }
        
        

        
         
    }
}
