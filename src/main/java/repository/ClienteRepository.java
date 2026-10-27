/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Jogo;

/**
 *
 * @author kevin
 */
public class ClienteRepository {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Path path = Paths.get("src", "main", "data", "clientes.json");

    public void inicializarArquivo() throws IOException {
        Files.createDirectories(path.getParent());

        // Verifica se o arquivo não existe
        if (Files.notExists(path)) {
            Files.writeString(path, "[]");
        }
    }

    public List<Cliente> lerClientes() throws IOException {
        //verificar se o JSON existe e cria caso necessário
        inicializarArquivo();
        TypeToken<List<Cliente>> tipoLista = new TypeToken<List<Cliente>>() {
        };

        //lê o conteudo JSON do arquivo
        try (Reader reader = Files.newBufferedReader(path)) {
            //desserializa o JSON para List<Jogo>
            List<Cliente> listaClientes = gson.fromJson(reader, tipoLista.getType());
            //garantir uma lista não nula se o JSON estiver vazio
            return listaClientes != null ? listaClientes : new ArrayList<>();
        }
    }

    public void salvarCliente(List<Cliente> listaCliente) throws IOException {
        //cria os diretórios do caminho do arquivo, caso ainda não existam
        Files.createDirectories(path.getParent());
        //configurações Gson
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = Files.newBufferedWriter(path)) {
            gson.toJson(listaCliente, writer); //serialização e gravação da nova lista no arquivo
        }
    }

    public void cadastrarCliente(Cliente cliente) throws IOException {
        List<Cliente> listaClientes = lerClientes();
        for (Cliente clienteCadastrado : listaClientes) {
            if (clienteCadastrado.getEmail().equalsIgnoreCase(cliente.getEmail())) {
                throw new IllegalArgumentException(
                        "Já existe um cliente cadastrado com este e-mail."
                );
            }
        }
        listaClientes.add(cliente);
        salvarCliente(listaClientes);
    }

    public Cliente pesquisarCliente(String email) throws IOException {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "Digite um email valido."
            );
        }
        List<Cliente> listaClientes = lerClientes();

        for (Cliente cliente : listaClientes) {
            if (cliente.getEmail().equalsIgnoreCase(email.trim())) {
                return cliente;
            }
        }
        return null;
    }

    public boolean atualizarCliente(String email, Cliente clienteEditado) throws IOException {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "Digite um e-mail valido."
            );
        }
        List<Cliente> listaClientes = lerClientes();

        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente cliente = listaClientes.get(i);
            //compara o email do indice pelo email do parametro
            if (cliente.getEmail().equalsIgnoreCase(email.trim())) {
                for (int j = 0; j < listaClientes.size(); j++) {

                    if (j != i
                            && listaClientes.get(j).getEmail()
                                    .equalsIgnoreCase(clienteEditado.getEmail())) {

                        throw new IllegalArgumentException(
                                "Este e-mail já pertence a outro cliente."
                        );
                    }
                }
                //atualiza as informações do cliente original pelas do cliente editado
                listaClientes.set(i, clienteEditado);
                //persistir as alterações no JSON
                salvarCliente(listaClientes);
                return true;
            }
        }
        return false;
    }

}
