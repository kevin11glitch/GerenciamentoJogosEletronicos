/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.Reader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import java.nio.file.Paths;

import model.Jogo;

/**
 *
 * @author kevin
 */
public class JogoRepository {
    Gson gson = new Gson();
    
    public List<Jogo> lerJogos() throws IOException {
        //cria a definição do tipo parametrizado
        TypeToken<List<Jogo>> tipoLista = new TypeToken<List<Jogo>>(){};
        
        //caminho do arquivo JSON
        Path path = Paths.get("src", "main", "data", "jogos.json");
        
        //lê o conteudo JSON do arquivo
        try(Reader reader = Files.newBufferedReader(path)){
            //desserializa o JSON para List<Jogo>
            List<Jogo> listaJogos = gson.fromJson(reader, tipoLista.getType());
        return listaJogos;
        }

    }
    
    public void cadastrarJogo(Jogo novoJogo) throws IOException {
        //carrega da lista de jogos os jogos existentes no JSON
        List<Jogo> listaJogos = lerJogos();
        //adição do jogo novo à lista
        listaJogos.add(novoJogo);
        //caminho JSON
        Path path = Paths.get("src", "main", "data", "jogos.json");
       
        Files.createDirectories(path.getParent());
        //ajustar as configurações padrões do gson
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        try(Writer writer = Files.newBufferedWriter(path)){
            gson.toJson(listaJogos, writer); //serialização e gravação da nova lista no arquivo
        }

    }

}
