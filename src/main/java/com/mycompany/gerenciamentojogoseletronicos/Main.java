/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gerenciamentojogoseletronicos;

import model.Cliente;
import model.Jogo;

/**
 *
 * @author kevin
 */
public class Main {

    public static void main(String[] args) {
        Jogo jogo1 = new Jogo("Jogo 1", "Jogo faz tal coisa", "PS2", "Açao", 12, true);
        Cliente cliente1 = new Cliente("Joçao", "123@gmail.com");
        
        System.out.println(cliente1);
    }
}
