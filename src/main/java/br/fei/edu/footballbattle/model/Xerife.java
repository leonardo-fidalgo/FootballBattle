/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.model;

/**
 *
 * @author SAMSUNG
 */
public class Xerife extends Protagonista{
        public Xerife(){
        super("Sérgio Ramos - O Xerife", 6, 10, 150, 150, "A muralha", 0);
        }
        
    @Override
    public void usarPoderEspecial(Personagem alvo){
        super.usarPoderEspecial(alvo);
        int novaDefesa = 2* this.getNivelDefesa();
        double novoAtaque = 1.5 * this.getNivelAtaque();
        int novoAtaqueI = (int) novoAtaque;
        this.setNivelDefesa(novaDefesa);
        this.setNivelAtaque(novoAtaqueI);
        atacar(alvo);
    }
}
