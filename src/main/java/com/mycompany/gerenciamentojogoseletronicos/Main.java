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
import model.Jogo;

/**
 *
 * @author kevin
 */
public class Main {

    public static void main(String[] args) {
        
        Gson gson = new Gson();
        
        JogoRepository jogoRepository = new JogoRepository();
        
        try {
            List<Jogo> jogos = jogoRepository.lerJogos();

            for (Jogo jogo : jogos) {
                System.out.println(jogo);
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler os jogos: " + e.getMessage());
        }

        
         
    }
}
