/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.model;

/**
 *
 * @author SAMSUNG
 */
public class Rei extends Protagonista{
    public Rei(){
        super("Pelé - O Rei", 10, 8, 130, 130, "A força do trono", 0);
    }
    
    @Override
    public void usarPoderEspecial(Personagem alvo){

        int novoAtaque = 2* this.getNivelAtaque();
        this.setNivelAtaque(novoAtaque);
        atacar(alvo);
    }
}
