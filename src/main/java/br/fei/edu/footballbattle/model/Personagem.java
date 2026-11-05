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
    private boolean defendendo = false;
    
    public Personagem(String nome, int nivelAtaque, int nivelDefesa, 
            int vidaAtual, int vidaMaxima, String nomepoderEspecial) {
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
    
    public abstract void atacar(Personagem alvo);
    
    public void defender(){
        defendendo = true;
    }

    public void aplicarDano(int dano){
        if(defendendo){
            dano = dano /2;
            defendendo = false;
        }
        int vidaAposAtaque = getVidaAtual() - dano;
        if(vidaAposAtaque > 0){
            setVidaAtual(vidaAposAtaque);
        }else{
            setVidaAtual(0);
        }
    }
    
    public void usarPoderEspecial(Personagem alvo){}
    
    public boolean estaVivo(){
        if(vidaAtual >0){
            return true;
        }
        
        return false;
    }

    public boolean isDefendendo() {
        return defendendo;
    }

    public void setDefendendo(boolean defendendo) {
        this.defendendo = defendendo;
    }
    
    
    
    
    
}
