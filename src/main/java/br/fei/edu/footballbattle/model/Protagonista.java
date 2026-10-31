/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SAMSUNG
 */
public abstract class Protagonista extends Personagem {
    private int energia;
    private List<Inimigo>inimigosEnfrentados;
    public  Protagonista(String nome, int nivelAtaque, int nivelDefesa, 
            int vidaAtual, int vidaMaxima, String nomepoderEspecial, 
            int Energia) {
        super(nome, nivelAtaque, nivelDefesa, vidaAtual, vidaMaxima, 
                nomepoderEspecial);
        this.energia = energia;
        this.inimigosEnfrentados = new ArrayList<>();
    }
    
    @Override
    public void atacar(Personagem alvo){
        int dano = 2* this.getNivelAtaque() + 10 - alvo.getNivelDefesa();
        if(dano >= 0){
        alvo.aplicarDano(dano);
        }
    }   

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public List<Inimigo> getInimigosEnfrentados() {
        return inimigosEnfrentados;
    }

    public void setInimigosEnfrentados(List<Inimigo> inimigosEnfrentados) {
        this.inimigosEnfrentados = inimigosEnfrentados;
    }
    
    
}

