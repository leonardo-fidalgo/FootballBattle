/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.model;

/**
 *
 * @author SAMSUNG
 */
public class Mito extends Inimigo{

    public Mito() {
        super("Rogerio Ceni", 9, 9, 150, 150, "Centésimo Gol");
    }
        
    @Override
        public void usarPoderEspecial(Personagem alvo){
        
            int novoAtaque = this.getNivelAtaque() *2;
            this.setNivelAtaque(novoAtaque);
            this.setNivelDefesa(0);
        
            atacar(alvo);
        }
    
}
