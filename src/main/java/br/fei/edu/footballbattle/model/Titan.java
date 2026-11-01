/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.model;

/**
 *
 * @author SAMSUNG
 */
public class Titan extends Inimigo{
    
    public Titan(){
        super("Oliver Kahn", 6, 7, 120, 120, "Furia");
    }
    
    @Override
    public void usarPoderEspecial(Personagem alvo){
        this.setNivelAtaque(this.getNivelAtaque() + 4);
        this.setNivelDefesa(this.getNivelDefesa() -2);
    
        atacar(alvo);
    
    
    }
}
