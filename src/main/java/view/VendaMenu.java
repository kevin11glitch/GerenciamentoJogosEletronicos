/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;
import model.Venda;
import repository.ClienteRepository;
import repository.VendaRepository;

/**
 *
 * @author kevin
 */
public class VendaMenu {
    private final VendaRepository repository;

    public VendaMenu(VendaRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    public void exibirMenu() {
        while (true) {
            String entrada = JOptionPane.showInputDialog(
                    "GERENCIAR VENDAS\n"
                    + "1 - Registrar Venda\n"
                    + "2 - Pesquisar Venda\n"
                    + "3 - Visualizar Histórico de Vendas\n"
                    + "4 - Visualizar Vendas por Cliente\n"
                    + "0 - Voltar ao menu principal\n\n"
            );

            if (entrada == null) {
                return;
            }

            try {
                int opcao = Integer.parseInt(entrada.trim());
                switch (opcao) {
                    case 1: {
                        //cadastrarCliente();
                        break;
                    }
                    case 2: {
                        //pesquisarCliente();
                        break;
                    }
                    case 3: {
                        lerVendas();
                        break;
                    }
                    case 4: {
                        //atualizarCliente();
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
    private void lerVendas(){
            try {
                List<Venda> vendas = repository.lerVendas();
                if (vendas == null || vendas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhuma venda cadastrada.");
                    return;
                }

                for (Venda venda : vendas) {
                    JOptionPane.showMessageDialog(null, venda);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao listar vendas: " + e.getMessage());
            }
        }
}
