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
import repository.ClienteRepository;
import repository.VendaRepository;
import view.ClienteMenu;
import view.JogoMenu;
import view.VendaMenu;

/**
 *
 * @author kevin
 */
public class Main {

    public static void main(String[] args) throws IOException {
        //instanciar Repository's
        JogoRepository jogoRepository = new JogoRepository();
        ClienteRepository clienteRepository = new ClienteRepository();
        VendaRepository vendaRepository = new VendaRepository();
        //instanciar Menu's
        JogoMenu jogoMenu = new JogoMenu(jogoRepository);
        ClienteMenu clienteMenu = new ClienteMenu(clienteRepository);
        VendaMenu vendaMenu = new VendaMenu(vendaRepository);
        
        while (true) {
            String entrada = JOptionPane.showInputDialog(
                    "GERENCIAMENTO DE VENDAS DE JOGOS ELETRÔNICOS\n"
                    + "1 - Gerenciar jogos\n"
                    + "2 - Gerenciar clientes\n"
                    + "3 - Gerenciar vendas\n"
                    + "0 - Sair\n\n"
            );

            if (entrada == null) {
                return;
            }

            try {
                int opcao = Integer.parseInt(entrada.trim());
                switch (opcao) {
                    case 1: {
                        jogoMenu.exibirMenu();
                        break;
                    }
                    case 2: {
                        clienteMenu.exibirMenu();
                        break;
                    }
                    case 3: {
                        vendaMenu.exibirMenu();
                        break;
                    }
                    case 0: {
                        JOptionPane.showMessageDialog(null, "Obrigado por usar o Gerenciamento de Vendas de Jogos Eletrônicos!");
                        return;
                        
                    }
                    default: {
                        JOptionPane.showMessageDialog(null, "Digite uma opção válida.");
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite o número de uma opção válida.");
            }
        }

    }
}
