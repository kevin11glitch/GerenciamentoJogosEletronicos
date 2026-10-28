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
import java.util.UUID;

import model.Jogo;

/**
 *
 * @author kevin
 */
public class JogoRepository {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Path path = Paths.get("src", "main", "data", "jogos.json");
    
    private void inicializarArquivo() throws IOException {
        Files.createDirectories(path.getParent());

        // Verifica se o arquivo não existe
        if (Files.notExists(path)) {
            Files.writeString(path, "[]");
        }
    }
    
    public List<Jogo> lerJogos() throws IOException {
        inicializarArquivo();
        //cria a definição do tipo parametrizado
        TypeToken<List<Jogo>> tipoLista = new TypeToken<List<Jogo>>(){};
        
        //lê o conteudo JSON do arquivo
        try(Reader reader = Files.newBufferedReader(path)){
            //desserializa o JSON para List<Jogo>
            List<Jogo> listaJogos = gson.fromJson(reader, tipoLista.getType());
        return listaJogos != null ? listaJogos : new ArrayList<>();
        }

    }
    
    public void salvarJogos(List<Jogo> listaJogos) throws IOException{
        //caminho 
        Path path = Paths.get("src", "main", "data", "jogos.json");
        //cria os diretórios do caminho do arquivo, caso ainda não existam
        Files.createDirectories(path.getParent());
        //configurações Gson
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(Writer writer = Files.newBufferedWriter(path)){
            gson.toJson(listaJogos, writer); //serialização e gravação da nova lista no arquivo
        }    
    }
    
    public void cadastrarJogo(Jogo novoJogo) throws IOException {
        //carrega da lista de jogos existentes no JSON
        List<Jogo> listaJogos = lerJogos();
        //geração de UUID
        novoJogo.setId(UUID.randomUUID().toString());
        //adição do jogo novo à lista
        listaJogos.add(novoJogo);
        //salvar informações
        salvarJogos(listaJogos);

    }
    
    public Jogo pesquisarJogo(String tituloJogo) throws IOException{
        if(tituloJogo == null || tituloJogo.isBlank()){
            throw new IllegalArgumentException(
                "Digite um titulo valido."
            );
        }
        //desserialização da lista de jogos "lerJogos()"
        List<Jogo> listaJogos = lerJogos();
        
        //percorrer a lista de jogos ate encontrar o jogo
        for(Jogo jogo : listaJogos){
            if(jogo.getTitulo().equalsIgnoreCase(tituloJogo.trim())){
                return jogo;
            }
        }
        return null;
        
    }
    
    
    public boolean atualizarJogo(String tituloJogo, Jogo jogoEditado) throws IOException{
        if(tituloJogo == null || tituloJogo.isBlank()){
            throw new IllegalArgumentException(
                "Digite um titulo valido."
            );
        }
        List<Jogo> listaJogos = lerJogos();
        
        for(int i = 0; i < listaJogos.size(); i++){
            Jogo jogo = listaJogos.get(i);
            //compara o titulo do indice pelo titulo do parametro
            if(jogo.getTitulo().equalsIgnoreCase(tituloJogo.trim())){
                //atualiza as informações do jogo original pelas do jogo editado
                listaJogos.set(i, jogoEditado);
                //persistir as alterações no JSON
                salvarJogos(listaJogos);
                return true;
            }
        }
        return false;
    }
    
    public boolean desativarJogo(String tituloJogo) throws IOException{
        if(tituloJogo == null || tituloJogo.isBlank()){
            throw new IllegalArgumentException(
                "Digite um titulo valido."
            );
        }

        List<Jogo> listaJogos = lerJogos();
        
        for (Jogo jogo : listaJogos) {
            
            if (jogo.getTitulo().equalsIgnoreCase(tituloJogo.trim())) {
                //verificação se o jogo ja esta desativado
                if (!jogo.isAtivo()) {
                    return false;
                }
                //desativa o jogo em memória
                jogo.setAtivo(false);
                //persiste a alteração no arquivo JSON
                salvarJogos(listaJogos);
                return true;
            }
        }
        
        return false;
    }

}
