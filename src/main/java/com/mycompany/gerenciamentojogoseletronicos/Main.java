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
import view.JogoMenu;

/**
 *
 * @author kevin
 */
public class Main {

    public static void main(String[] args) throws IOException {
        JogoRepository jogoRepository = new JogoRepository();
        JogoMenu jogoMenu = new JogoMenu(jogoRepository);

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
                        JOptionPane.showMessageDialog(null,
                                "Menu de clientes ainda não implementado.");
                        break;
                    }
                    case 3: {
                        JOptionPane.showMessageDialog(null,
                                "Menu de vendas ainda não implementado.");
                        break;
                    }
                    case 0: {
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
