/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.model;

/**
 *
 * @author SAMSUNG
 */
public class AranhaNegra extends Inimigo{

    public AranhaNegra() {
        super("Yashin", 7, 9, 130, 130, "Teia negra");
    }
    
    @Override
    public void usarPoderEspecial(Personagem alvo){
        int novoAtaqueAlvo = alvo.getNivelAtaque() - 4;
        alvo.setNivelAtaque(novoAtaqueAlvo);
    }
    
    
}
