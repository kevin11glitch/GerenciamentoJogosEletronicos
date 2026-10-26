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
                //cadastrar
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
                //pesquisar
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
                //vizualizar
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
                //atualizar
                case 4:{
                    String tituloPesquisa = JOptionPane.showInputDialog("Digite o titulo do jogo que deseja atualizar:");
                    //validação para titulo da pesquisa estar vazio
                    if (tituloPesquisa == null) {
                        break;
                    }
                    try{
                        Jogo jogoPesquisado = jogoRepository.pesquisarJogo(tituloPesquisa);
                        if(jogoPesquisado != null){
                            int opcaoJogo = -1;
                            while(opcaoJogo != 0){
                                String entrada = JOptionPane.showInputDialog("Escolha uma opção que deseja atualizar:\n"
                                    + "1 - Titulo\n"
                                    + "2 - Descrição\n"
                                    + "3 - Plataforma\n"
                                    + "4 - Gênero\n"
                                    + "5 - Preço\n"
                                    + "6 - Status\n"
                                    + "0 - Salvar alterações\n\n"
                                );
                                
                                if(entrada == null){
                                    break;
                                }
                                opcaoJogo = Integer.parseInt(entrada);
                                switch(opcaoJogo){
                                    case 1:{
                                        String novoTitulo = JOptionPane.showInputDialog("Novo titulo:");
                                        jogoPesquisado.setTitulo(novoTitulo);
                                        JOptionPane.showMessageDialog(null, "Titulo alterado! Salve para confirmar as alterações.");
                                        break;
                                    }
                                    case 2:{
                                        String novaDescricao = JOptionPane.showInputDialog("Nova descrição:");
                                        jogoPesquisado.setDescricao(novaDescricao);
                                        JOptionPane.showMessageDialog(null, "Descrição alterada! Salve para confirmar as alterações.");
                                        break;
                                    }
                                    case 3:{
                                        String novaPlataforma = JOptionPane.showInputDialog("Nova plataforma:");
                                        jogoPesquisado.setPlataforma(novaPlataforma);
                                        JOptionPane.showMessageDialog(null, "Plataforma alterada! Salve para confirmar as alterações.");
                                        break;
                                    }
                                    case 4:{
                                        String novoGenero = JOptionPane.showInputDialog("Novo gênero:");
                                        jogoPesquisado.setGenero(novoGenero);
                                        JOptionPane.showMessageDialog(null, "Gênero alterado! Salve para confirmar as alterações.");
                                        break;
                                    }
                                    case 5:{
                                        double novoPreco = Double.parseDouble(JOptionPane.showInputDialog("Novo preço:"));
                                        jogoPesquisado.setPreco(novoPreco);
                                        JOptionPane.showMessageDialog(null, "Preço alterado! Salve para confirmar as alterações.");
                                        break;
                                    }
                                    case 6:{
                                        int novoStatus = JOptionPane.showConfirmDialog(
                                                null, 
                                                "O Jogo ainda está ativo no catálogo?", 
                                                "Confirmação", 
                                                JOptionPane.YES_NO_OPTION
                                        );
                                        //validação para o caso de cancelar ou fechar o dialog
                                        if (novoStatus == JOptionPane.YES_OPTION) {
                                            jogoPesquisado.setAtivo(true);
                                        } else if (novoStatus == JOptionPane.NO_OPTION) {
                                            jogoPesquisado.setAtivo(false);
                                        }
                                        boolean resposta = (novoStatus == JOptionPane.YES_OPTION);
                                        jogoPesquisado.setAtivo(resposta);
                                        break;
                                    }
                                    case 0:{
                                        boolean atualizado = jogoRepository.atualizarJogo(tituloPesquisa, jogoPesquisado);
                                        
                                        if(atualizado){
                                            JOptionPane.showMessageDialog(null, "Jogo atualizado com sucesso!");
                                        }else{
                                            JOptionPane.showMessageDialog(null, "Jogo não encontrado.");
                                        }
                                        break;
                                    }
                                    default:
                                        JOptionPane.showMessageDialog(null, "Digite uma opção válida.");
                                        break;
                                }
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Jogo não encontrado no catálogo.");
                        }
                        //tratamento para erro de leitura e escrita
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(
                            null,
                            "Erro ao atualizar jogo: " + e.getMessage()
                        );
                        //tratamento para erro de valores inválidos
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(
                            null,
                            "Dados inválidos: " + e.getMessage()
                        );
                    }
                    
                    break;
                }
                //vender
                case 5:{
                    
                }
                //deletar
                case 6:{
                    
                }
                case 0:{
                }
            }
        }
        
        

        
         
    }
}
