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
public class Jogo {
    private Protagonista protagonista;
    private List<Inimigo>inimigos;
    private int nivelAtual;
    private Batalha batalha;
    private boolean jogoFinalizado = false;
    private boolean jogoIniciado = false;
    
    public Jogo(Protagonista protagonista) {
        this.protagonista = protagonista;
        this.nivelAtual = 1;
    }
    
    private void verificarInimigoAtual(){
        nivelAtual = 1;
        for(Inimigo inimigo : protagonista.getInimigosEnfrentados()){
            for(Inimigo inimigos : this.inimigos)
            if(inimigo.getNome().equals(inimigos.getNome())){ 
            nivelAtual ++;
            break;
            }
        
        }  
    }   
    
    public void comecarBatalha(){
        if (verificarFimJogo() || 
                batalha != null && !batalha.isBatalhaFinalizada()) {
            return;
        }
        
        this.inimigos = new ArrayList<>(List.of(
                new Titan(), 
                new Superman(), 
                new AranhaNegra(), 
                new Neuer(), 
                new Mito()));
        
        jogoIniciado = true;
        
                
        verificarInimigoAtual();
        
        if (nivelAtual >= 1 && nivelAtual <= inimigos.size()){
            this.batalha = new Batalha(protagonista, 
                    inimigos.get(nivelAtual - 1));
            protagonista.setVidaAtual(protagonista.getVidaMaxima());
            protagonista.setEnergia(0);
            
        }else{
            jogoFinalizado = true;
        }
    }
 
    public boolean verificarFimJogo(){
        if(!jogoFinalizado && jogoIniciado){
            verificarInimigoAtual();
            if(nivelAtual > 5){
                jogoFinalizado = true;
            }
        }
        return jogoFinalizado;
    }
    
    public Batalha getBatalha() {
        return batalha;
    }

    public int getNivelAtual() {
        return nivelAtual;
    }

    public Protagonista getProtagonista() {
        return protagonista;
    }

    public void setNivelAtual(int nivelAtual) {
        this.nivelAtual = nivelAtual;
    }

    public void setProtagonista(Protagonista protagonista) {
        this.protagonista = protagonista;
    }
    
    //Necessário pois ao buscar o save um novo objeto será instanciado
    public void restaurarProgresso(int nivelSalvo) {
        if (nivelSalvo < 1 || nivelSalvo > 5) {
            throw new IllegalArgumentException("Nível inválido!");
        }
        
        List<Inimigo> inimigosDoJogo = List.of(
                new Titan(),
                new Superman(),
                new AranhaNegra(),
                new Neuer(),
                new Mito()
        );
        
        protagonista.getInimigosEnfrentados().clear();
        
        for (int i = 0; i < nivelSalvo - 1; i++) {
            protagonista.adicionarInimigo(inimigosDoJogo.get(i));
        }
        
        this.nivelAtual = nivelSalvo;
    }

}