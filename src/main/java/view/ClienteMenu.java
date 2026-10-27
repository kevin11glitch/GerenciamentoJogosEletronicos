/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;
import model.Cliente;
import repository.ClienteRepository;

/**
 *
 * @author kevin
 */
public class ClienteMenu {

    private final ClienteRepository repository;

    public ClienteMenu(ClienteRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    public void exibirMenu() {
        while (true) {
            String entrada = JOptionPane.showInputDialog(
                    "GERENCIAR CLIENTES\n"
                    + "1 - Cadastrar cliente\n"
                    + "2 - Pesquisar cliente\n"
                    + "3 - Visualizar clientes\n"
                    + "4 - Atualizar cliente\n"
                    + "0 - Voltar ao menu principal\n\n"
            );

            if (entrada == null) {
                return; // Usuário fechou o menu
            }

            try {
                int opcao = Integer.parseInt(entrada.trim());
                switch (opcao) {
                    case 1: {
                        cadastrarCliente();
                        break;
                    }
                    case 2: {
                        pesquisarCliente();
                        break;
                    }
                    case 3: {
                        visualizarClientes();
                        break;
                    }
                    case 4: {
                        atualizarCliente();
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

    private void cadastrarCliente() {
        String nome = JOptionPane.showInputDialog("Nome do cliente:");
        if (nome == null) {
            return;
        }

        String email = JOptionPane.showInputDialog("E-mail do cliente:");
        if (email == null) {
            return;
        }

        try {
            Cliente novoCliente = new Cliente(nome, email);
            repository.cadastrarCliente(novoCliente);
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Dados inválidos: " + e.getMessage());
        }
    }

    private void pesquisarCliente() {
        String email = JOptionPane.showInputDialog("E-mail do cliente:");
        if (email == null) {
            return;
        }

        try {
            Cliente encontrado = repository.pesquisarCliente(email);
            if (encontrado != null) {
                JOptionPane.showMessageDialog(null, encontrado);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado: " + email);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar cliente: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Dados inválidos: " + e.getMessage());
        }
    }

    private void visualizarClientes() {
        try {
            List<Cliente> clientes = repository.lerClientes();
            if (clientes == null || clientes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.");
                return;
            }

            for (Cliente cliente : clientes) {
                JOptionPane.showMessageDialog(null, cliente);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes: " + e.getMessage());
        }
    }

    private void atualizarCliente() {
        String emailOriginal = JOptionPane.showInputDialog(
                "Digite o E-mail do cliente que deseja atualizar:");
        if (emailOriginal == null) {
            return;
        }

        try {
            // O objeto retornado é editado em memória até o usuário escolher salvar.
            Cliente clienteEditado = repository.pesquisarCliente(emailOriginal);
            if (clienteEditado == null) {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado no catálogo.");
                return;
            }

            while (true) {
                String entrada = JOptionPane.showInputDialog(
                        "ESCOLHA O CAMPO QUE DESEJA ALTERAR\n"
                        + "1 - Nome\n"
                        + "2 - E-mail\n"
                        + "0 - Salvar alterações\n\n"
                        + "Cancelar - Descartar alterações"
                );

                if (entrada == null) {
                    return; // Descarta alterações ainda não salvas
                }

                try {
                    int opcao = Integer.parseInt(entrada.trim());
                    switch (opcao) {
                        case 1: {
                            String valor = JOptionPane.showInputDialog("Novo Nome:");
                            if (valor != null) {
                                clienteEditado.setNome(valor);
                            }
                            break;
                        }
                        case 2: {
                            String valor = JOptionPane.showInputDialog("Novo E-mail:");
                            if (valor != null) {
                                clienteEditado.setEmail(valor);
                            }
                            break;
                        }
                        case 0: {
                            boolean atualizado = repository.atualizarCliente(emailOriginal, clienteEditado);
                            JOptionPane.showMessageDialog(null,
                                    atualizado ? "Cliente atualizado com sucesso!" : "Cliente não encontrado.");
                            return;
                        }
                        default: {
                            JOptionPane.showMessageDialog(null, "Digite uma opção válida.");
                        }
                    }
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Dados inválidos: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null, "Erro ao atualizar cliente: " + e.getMessage()
            );
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Dados inválidos: " + e.getMessage());
        }
    }
}
