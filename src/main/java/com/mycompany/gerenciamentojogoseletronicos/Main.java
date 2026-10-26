/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gerenciamentojogoseletronicos;

//import model.Cliente;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
               
        
        int opcao = -1;
        JogoRepository jogoRepository = new JogoRepository();
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
                case 1:{
                    String titulo = JOptionPane.showInputDialog("Titulo do Jogo:");
                    String descricao = JOptionPane.showInputDialog("Descrição do Jogo:");
                    String plataforma = JOptionPane.showInputDialog("Plataforma comatível com o Jogo:");
                    String genero = JOptionPane.showInputDialog("Gênero do Jogo:");
                    double preco = Double.parseDouble(JOptionPane.showInputDialog("Preço do Jogo:"));
                    int ativo = JOptionPane.showConfirmDialog(null, "O Jogo está ativo no catálogo?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    
                    boolean resposta = (ativo == JOptionPane.YES_OPTION);
                    Jogo novoJogo = new Jogo(titulo, descricao, plataforma, genero, preco, resposta);
                   
                    try {
                        jogoRepository.cadastrarJogo(novoJogo);
                        JOptionPane.showMessageDialog(null,"Jogo cadastrado com sucesso!");
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar jogo: " + e.getMessage());
                    }

                    break;
                }
                
                case 2:{
                    String tituloPesquisa = JOptionPane.showInputDialog("Digite o titulo do jogo que deseja pesquisar:");
                    if(tituloPesquisa == null){
                        break;
                    }
                    
                    try{
                        Jogo jogoPesquisado = jogoRepository.pesquisarJogo(tituloPesquisa);
                        if(jogoPesquisado != null){
                            JOptionPane.showMessageDialog(null, jogoPesquisado);
                        }else{
                            JOptionPane.showMessageDialog(null, "'" + tituloPesquisa + "'" + " não encontrado em nosso estoque.");
                        }
                    } catch(IOException e){
                        JOptionPane.showMessageDialog(null, "Erro ao pesquisar jogo: " + e.getMessage());
                    }
                    break;
                }
                case 3:{

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
}
