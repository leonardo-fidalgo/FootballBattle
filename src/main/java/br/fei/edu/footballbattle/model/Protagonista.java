/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.model;

/**
 *
 * @author SAMSUNG
 */
public abstract class Protagonista extends Personagem {
        private int energia;
        
    public  Protagonista(String nome, int nivelAtaque, int nivelDefesa, 
            int vidaAtual, int vidaMaxima, String nomepoderEspecial, 
            int Energia) {
        super(nome, nivelAtaque, nivelDefesa, vidaAtual, vidaMaxima, 
                nomepoderEspecial);
        
        this.energia = energia;
    }
    
}

