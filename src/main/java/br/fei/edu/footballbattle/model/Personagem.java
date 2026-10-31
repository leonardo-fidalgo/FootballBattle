/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.model;

/**
 *
 * @author SAMSUNG
 */
public abstract class  Personagem {
    private String nome;
    private int nivelAtaque;
    private int nivelDefesa;
    private int vidaAtual;
    private int vidaMaxima;
    private String nomepoderEspecial;

    public Personagem(String nome, int nivelAtaque, int nivelDefesa, int vidaAtual, int vidaMaxima, String nomepoderEspecial) {
        this.nome = nome;
        this.nivelAtaque = nivelAtaque;
        this.nivelDefesa = nivelDefesa;
        this.vidaAtual = vidaAtual;
        this.vidaMaxima = vidaMaxima;
        this.nomepoderEspecial = nomepoderEspecial;
    }

      
    public String getNome() {
        return nome;
    }

    public int getNivelAtaque() {
        return nivelAtaque;
    }

    public int getNivelDefesa() {
        return nivelDefesa;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public String getNomepoderEspecial() {
        return nomepoderEspecial;
    }

    public void setVidaAtual(int VidaAtual) {
        this.vidaAtual = VidaAtual;
    }

    public void setNivelAtaque(int nivelAtaque) {
        this.nivelAtaque = nivelAtaque;
    }

    public void setNivelDefesa(int nivelDefesa) {
        this.nivelDefesa = nivelDefesa;
    }
    
    public void atacar(Personagem alvo){}
    
    public void defender(){}

    public void usarPoderEspecial(){}
    
    public void estaVivo(){}
    
    
    
    
    
}
