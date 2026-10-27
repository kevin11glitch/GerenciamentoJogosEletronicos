package view;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;
import model.Jogo;
import repository.JogoRepository;

/**
 * Interação com o usuário para as operações do catálogo de jogos. A leitura e a
 * gravação do JSON continuam na JogoRepository.
 */
public class JogoMenu {

    private final JogoRepository repository;

    public JogoMenu(JogoRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    public void exibirMenu() {
        while (true) {
            String entrada = JOptionPane.showInputDialog(
                    "GERENCIAR JOGOS\n"
                    + "1 - Cadastrar jogo\n"
                    + "2 - Pesquisar jogo\n"
                    + "3 - Visualizar jogos\n"
                    + "4 - Atualizar jogo\n"
                    + "5 - Desativar jogo\n"
                    + "0 - Voltar ao menu principal\n\n"
            );

            if (entrada == null) {
                return; // Usuário fechou o menu
            }

            try {
                int opcao = Integer.parseInt(entrada.trim());
                switch (opcao) {
                    case 1: {
                        cadastrarJogo();
                        break;
                    }
                    case 2: {
                        pesquisarJogo();
                        break;
                    }
                    case 3: {
                        visualizarJogos();
                        break;
                    }
                    case 4: {
                        atualizarJogo();
                        break;
                    }
                    case 5: {
                        desativarJogo();
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

    private void cadastrarJogo() {
        String titulo = JOptionPane.showInputDialog("Título do jogo:");
        if (titulo == null) {
            return;
        }

        String descricao = JOptionPane.showInputDialog("Descrição do jogo:");
        if (descricao == null) {
            return;
        }

        String plataforma = JOptionPane.showInputDialog("Plataforma compatível:");
        if (plataforma == null) {
            return;
        }

        String genero = JOptionPane.showInputDialog("Gênero do jogo:");
        if (genero == null) {
            return;
        }

        String entradaPreco = JOptionPane.showInputDialog("Preço do jogo:");
        if (entradaPreco == null) {
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(
                null,
                "O jogo está ativo no catálogo?",
                "Status do jogo",
                JOptionPane.YES_NO_CANCEL_OPTION
        );
        if (confirmacao != JOptionPane.YES_OPTION
                && confirmacao != JOptionPane.NO_OPTION) {
            return;
        }

        try {
            double preco = Double.parseDouble(entradaPreco.trim().replace(',', '.'));
            if (!Double.isFinite(preco)) {
                throw new IllegalArgumentException("O preço precisa ser um número finito.");
            }
            boolean ativo = confirmacao == JOptionPane.YES_OPTION;
            Jogo novoJogo = new Jogo(titulo, descricao, plataforma, genero, preco, ativo);

            repository.cadastrarJogo(novoJogo);
            JOptionPane.showMessageDialog(null, "Jogo cadastrado com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar jogo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Dados inválidos: " + e.getMessage());
        }
    }

    private void pesquisarJogo() {
        String titulo = JOptionPane.showInputDialog("Digite o título do jogo que deseja pesquisar:");
        if (titulo == null) {
            return;
        }

        try {
            Jogo encontrado = repository.pesquisarJogo(titulo);
            if (encontrado != null) {
                JOptionPane.showMessageDialog(null, encontrado);
            } else {
                JOptionPane.showMessageDialog(null, "Jogo não encontrado: " + titulo);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar jogo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Dados inválidos: " + e.getMessage());
        }
    }

    private void visualizarJogos() {
        try {
            List<Jogo> jogos = repository.lerJogos();
            if (jogos == null || jogos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum jogo cadastrado.");
                return;
            }

            for (Jogo jogo : jogos) {
                JOptionPane.showMessageDialog(null, jogo);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar jogos: " + e.getMessage());
        }
    }

    private void atualizarJogo() {
        String tituloOriginal = JOptionPane.showInputDialog(
                "Digite o título do jogo que deseja atualizar:");
        if (tituloOriginal == null) {
            return;
        }

        try {
            // O objeto retornado é editado em memória até o usuário escolher salvar.
            Jogo jogoEditado = repository.pesquisarJogo(tituloOriginal);
            if (jogoEditado == null) {
                JOptionPane.showMessageDialog(null, "Jogo não encontrado no catálogo.");
                return;
            }

            while (true) {
                String entrada = JOptionPane.showInputDialog(
                        "ESCOLHA O CAMPO QUE DESEJA ALTERAR\n"
                        + "1 - Título\n"
                        + "2 - Descrição\n"
                        + "3 - Plataforma\n"
                        + "4 - Gênero\n"
                        + "5 - Preço\n"
                        + "6 - Status\n"
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
                            String valor = JOptionPane.showInputDialog("Novo título:");
                            if (valor != null) {
                                jogoEditado.setTitulo(valor);
                            }
                            break;
                        }
                        case 2: {
                            String valor = JOptionPane.showInputDialog("Nova descrição:");
                            if (valor != null) {
                                jogoEditado.setDescricao(valor);
                            }
                            break;
                        }
                        case 3: {
                            String valor = JOptionPane.showInputDialog("Nova plataforma:");
                            if (valor != null) {
                                jogoEditado.setPlataforma(valor);
                            }
                            break;
                        }
                        case 4: {
                            String valor = JOptionPane.showInputDialog("Novo gênero:");
                            if (valor != null) {
                                jogoEditado.setGenero(valor);
                            }
                            break;
                        }
                        case 5: {
                            String valor = JOptionPane.showInputDialog("Novo preço:");
                            if (valor != null) {
                                double preco = Double.parseDouble(valor.trim().replace(',', '.'));
                                if (!Double.isFinite(preco)) {
                                    throw new IllegalArgumentException("O preço precisa ser um número finito.");
                                }
                                jogoEditado.setPreco(preco);
                            }
                            break;
                        }
                        case 6: {
                            int status = JOptionPane.showConfirmDialog(
                                    null,
                                    "O jogo está ativo no catálogo?",
                                    "Status do jogo",
                                    JOptionPane.YES_NO_CANCEL_OPTION
                            );
                            if (status == JOptionPane.YES_OPTION) {
                                jogoEditado.setAtivo(true);
                            } else if (status == JOptionPane.NO_OPTION) {
                                jogoEditado.setAtivo(false);
                            }
                            break;
                        }
                        case 0: {
                            boolean atualizado = repository.atualizarJogo(tituloOriginal, jogoEditado);
                            JOptionPane.showMessageDialog(null,
                                    atualizado ? "Jogo atualizado com sucesso!" : "Jogo não encontrado.");
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
            JOptionPane.showMessageDialog(null, "Erro ao atualizar jogo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Dados inválidos: " + e.getMessage());
        }
    }

    private void desativarJogo() {
        String titulo = JOptionPane.showInputDialog("Qual jogo deseja desativar do catálogo?");
        if (titulo == null) {
            return;
        }

        if (titulo.isBlank()) {
            JOptionPane.showMessageDialog(null, "Digite um título válido.");
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(
                null,
                "Deseja desativar o jogo '" + titulo.trim() + "' do catálogo?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );
        if (confirmacao != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            boolean desativado = repository.desativarJogo(titulo);
            JOptionPane.showMessageDialog(null,
                    desativado ? "Jogo desativado com sucesso!"
                            : "Jogo não encontrado ou já desativado.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao desativar jogo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Dados inválidos: " + e.getMessage());
        }
    }
}
