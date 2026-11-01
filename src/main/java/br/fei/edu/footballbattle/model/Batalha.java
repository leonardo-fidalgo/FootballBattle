/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.model;

/**
 *
 * @author SAMSUNG
 */
public class Batalha {
    private Protagonista protagonista;
    private Inimigo inimigo;
    private boolean turnoProtagonista;
    private boolean batalhaFinalizada;

    public Batalha(Protagonista protagonista, Inimigo inimigo) {
        this.protagonista = protagonista;
        this.inimigo = inimigo;
        this.turnoProtagonista = true;
        this.batalhaFinalizada = false;
    }
    
    public void verificarFimBatalha() {
    if (!protagonista.estaVivo() || !inimigo.estaVivo()) {
        batalhaFinalizada = true;
    }
}
 
    public void alternarTurno() {
    if (!batalhaFinalizada) {
        turnoProtagonista = !turnoProtagonista;
    }
}
    
    public void ataqueProtagonista(){
        if(!batalhaFinalizada && turnoProtagonista){
            protagonista.atacar(inimigo);
        
            verificarFimBatalha();
            if(!batalhaFinalizada){
                alternarTurno();
            }
        }
    }
   
    public void ataqueInimigo(){
        if(!batalhaFinalizada && !turnoProtagonista){
            inimigo.atacar(protagonista);
            verificarFimBatalha();
            if(!batalhaFinalizada){
                alternarTurno();
            }
        }
    }
    
    
}
