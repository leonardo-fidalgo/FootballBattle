/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.model;

/**
 *
 * @author SAMSUNG
 */
public class Neuer extends Inimigo{
    
    public Neuer(){
        super("Manuel Neuer", 8, 8, 140, 140, "Kamikaze" );
    }
    
    @Override
    public void usarPoderEspecial(Personagem alvo){
        int novoAtaque = this.getNivelAtaque() * 2;
        this.setNivelAtaque(novoAtaque);
        this.setNivelDefesa(0);
        
    }
}
