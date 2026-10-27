/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.Reader;
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
    private final Gson gson = new Gson();
    private final Path path = Paths.get("src", "main", "data", "clientes.json");
    
    public void inicializarArquivo() throws IOException{ 
        Files.createDirectories(path.getParent());

        // Verifica se o arquivo não existe
        if (Files.notExists(path)) {
            Files.writeString(path, "[]");
        }
    }
    
    public List<Cliente> lerClientes() throws IOException{
        //verificar se o JSON existe e cria caso necessário
        inicializarArquivo();
        TypeToken<List<Cliente>> tipoLista = new TypeToken<List<Cliente>>(){};      

        //lê o conteudo JSON do arquivo
        try(Reader reader = Files.newBufferedReader(path)){
            //desserializa o JSON para List<Jogo>
            List<Cliente> listaClientes = gson.fromJson(reader, tipoLista.getType());
            //garantir uma lista não nula se o JSON estiver vazio
            return listaClientes != null ? listaClientes : new ArrayList<>();
        }
    }
}
