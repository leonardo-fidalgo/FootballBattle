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
        super("Sérgio Ramos - O Xerixe", 6, 10, 150, 150, "A muralha", 0);
        }
        
    @Override
    public void usarPoderEspecial(Personagem alvo){
        int novaDefesa = 2* this.getNivelDefesa();
        this.setNivelDefesa(novaDefesa);
        atacar(alvo);
    }
}
