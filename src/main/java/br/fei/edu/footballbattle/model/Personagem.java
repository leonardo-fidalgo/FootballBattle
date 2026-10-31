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
    private int VidaAtual;
    private int VidaMaxima;
    private String NomepoderEspecial;

    public Personagem(String nome, int nivelAtaque, int nivelDefesa, int VidaAtual, int VidaMaxima, String NomepoderEspecial) {
        this.nome = nome;
        this.nivelAtaque = nivelAtaque;
        this.nivelDefesa = nivelDefesa;
        this.VidaAtual = VidaAtual;
        this.VidaMaxima = VidaMaxima;
        this.NomepoderEspecial = NomepoderEspecial;
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
        return VidaAtual;
    }

    public int getVidaMaxima() {
        return VidaMaxima;
    }

    public String getNomepoderEspecial() {
        return NomepoderEspecial;
    }

    public void setVidaAtual(int VidaAtual) {
        this.VidaAtual = VidaAtual;
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
