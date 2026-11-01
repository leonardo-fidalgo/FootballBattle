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
    private int nivelAtaqueOriginalProtagonista;
    private int nivelDefesaOriginalProtagonista;
    private int nivelAtaqueOriginalInimigo;
    private int nivelDefesaOriginalInimigo;
    
    public Batalha(Protagonista protagonista, Inimigo inimigo) {
        this.protagonista = protagonista;
        this.inimigo = inimigo;
        this.turnoProtagonista = true;
        this.batalhaFinalizada = false;
        this.nivelAtaqueOriginalProtagonista = protagonista.getNivelAtaque();
        this.nivelDefesaOriginalProtagonista = protagonista.getNivelDefesa();
        this.nivelAtaqueOriginalInimigo = inimigo.getNivelAtaque();
        this.nivelDefesaOriginalInimigo = inimigo.getNivelDefesa();
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
    
    public void defesaProtagonista(){
        if(!batalhaFinalizada && turnoProtagonista){
            protagonista.defender();
            alternarTurno();
        }
    }
    
    public void defesaInimigo(){
        if(!batalhaFinalizada && turnoProtagonista){
            inimigo.defender();
            alternarTurno();
        }
    }
    
    
    public void restaurarNiveisProtagonista(){
        protagonista.setNivelAtaque(nivelAtaqueOriginalProtagonista);
        protagonista.setNivelDefesa(nivelDefesaOriginalProtagonista);
    }
    
    public void restaurarNiveisInimigo(){
        inimigo.setNivelAtaque(nivelAtaqueOriginalInimigo);
        inimigo.setNivelDefesa(nivelDefesaOriginalInimigo);
    }
    
    public void poderEspecialProtagonista(){
        if(!batalhaFinalizada && turnoProtagonista 
                && protagonista.getEnergia() >= 100){
            protagonista.usarPoderEspecial(inimigo);
            verificarFimBatalha();
            if(!batalhaFinalizada){
                alternarTurno();
            }
        }
    }

    public void poderEspecialInimigo(){
        if(!batalhaFinalizada && !turnoProtagonista){
            inimigo.usarPoderEspecial(protagonista);
            verificarFimBatalha();
            if(!batalhaFinalizada){
                alternarTurno();
            }
        }
    }
}
