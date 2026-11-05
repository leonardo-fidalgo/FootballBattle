/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.model;

import java.util.Random;

/**
 *
 * @author SAMSUNG
 */
public abstract class Inimigo extends Personagem {
    Random random = new Random();

    public Inimigo(String nome, int nivelAtaque, int nivelDefesa, 
            int VidaAtual, int VidaMaxima, String NomepoderEspecial) {
        super(nome, nivelAtaque, nivelDefesa, VidaAtual, VidaMaxima,
                NomepoderEspecial);
    }
    
    @Override
    public void atacar(Personagem alvo){
        int aleatorio = random.nextInt(7) -3; // numero aleatório entre -3 e 3
        int dano = 2 * this.getNivelAtaque() + 10 - alvo.getNivelDefesa() 
                + aleatorio;
        if(dano >= 0){
            alvo.aplicarDano(dano);

        }
    }
    
    @Override
    public String toString(){
        return this.getNome();
    
    
    }
    
}
