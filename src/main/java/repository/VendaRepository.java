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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import model.Venda;

/**
 *
 * @author kevin
 */
public class VendaRepository {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Path path = Paths.get("src", "main", "data", "vendas.json");
    
    private void inicializarArquivo() throws IOException {
        Files.createDirectories(path.getParent());

        //verifica se o arquivo não existe
        if (Files.notExists(path)) {
            Files.writeString(path, "[]");
        }
    }
    
    public List<Venda> lerVendas() throws IOException {
        inicializarArquivo();
        TypeToken<List<Venda>> tipoLista = new TypeToken<List<Venda>>(){};
        
        //lê o conteudo JSON do arquivo
        try(Reader reader = Files.newBufferedReader(path)){
            //desserializa o JSON para List<Jogo>
            List<Venda> listaJogos = gson.fromJson(reader, tipoLista.getType());
            return listaJogos != null ? listaJogos : new ArrayList<>();
        }
    }

    
//    public void registrarVenda(Venda venda)
//        throws IOException {
//        // Validar, gerar ID e persistir
//    }



//    public void salvarVendas(List<Venda> vendas)
//            throws IOException {
//        // Serializar e gravar no JSON
//    }
//

//    public Venda pesquisarVenda(int id)
//            throws IOException {
//        // Localizar venda pelo ID
//    }
}
