/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.model;

/**
 *
 * @author SAMSUNG
 */
public class Superman extends Inimigo{
    
    public Superman(){
        super("Buffon", 6, 8, 130, 130, "Muralha Italiana");
    }
    
    @Override
    public void usarPoderEspecial(Personagem alvo){
        int novaDefesa = this.getNivelDefesa() * 2;
        this.setNivelDefesa(novaDefesa);
        atacar(alvo);
    }
}
