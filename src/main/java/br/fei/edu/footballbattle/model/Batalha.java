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
public class Batalha {
    private Protagonista protagonista;
    private Inimigo inimigo;
    private boolean turnoProtagonista;
    private boolean batalhaFinalizada;
    private int nivelAtaqueOriginalProtagonista;
    private int nivelDefesaOriginalProtagonista;
    private int nivelAtaqueOriginalInimigo;
    private int nivelDefesaOriginalInimigo;
    private boolean efeitoProtagonistaPendente = false;
    private boolean efeitoInimigoPendente = false;
    private String ultimaAcaoInimigo = "";
    
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
    
    private void verificarFimBatalha() {
    if (!batalhaFinalizada 
            &&(!protagonista.estaVivo() || !inimigo.estaVivo())) {
        batalhaFinalizada = true;
        restaurarNiveisInimigo();
        restaurarNiveisProtagonista();
        inimigo.setDefendendo(false); 
        protagonista.setDefendendo(false); 
        if(!inimigo.estaVivo() && protagonista.estaVivo()){
        protagonista.adicionarInimigo(inimigo);
        }
    }
}
 
    private void alternarTurno() {
        if (!batalhaFinalizada) {
            turnoProtagonista = !turnoProtagonista;
        }
    }
    
    public void ataqueProtagonista(){
        if(!batalhaFinalizada && turnoProtagonista){
            protagonista.atacar(inimigo);
        
            finalizarEfeitoInimigo();
        
            verificarFimBatalha();
            if(!batalhaFinalizada){
                alternarTurno();
            }
        }
    }
   
    public void ataqueInimigo(){
        if(!batalhaFinalizada && !turnoProtagonista){
            
            inimigo.atacar(protagonista);
            
            finalizarEfeitoProtagonista();
            
            
            verificarFimBatalha();
            if(!batalhaFinalizada){
                alternarTurno();
            }
        }
    }
    
    public void defesaProtagonista(){
        if(!batalhaFinalizada && turnoProtagonista){
            protagonista.defender();
            
            finalizarEfeitoInimigo();
            alternarTurno();
        }
    }
    
    public void defesaInimigo(){
        if(!batalhaFinalizada  && !turnoProtagonista){
            inimigo.defender();
            
            finalizarEfeitoProtagonista();
            alternarTurno();
        }
    }
    
    
    public void restaurarNiveisProtagonista(){
        protagonista.setNivelAtaque(nivelAtaqueOriginalProtagonista);
        protagonista.setNivelDefesa(nivelDefesaOriginalProtagonista);
    }
    
    private void restaurarNiveisInimigo(){
        inimigo.setNivelAtaque(nivelAtaqueOriginalInimigo);
        inimigo.setNivelDefesa(nivelDefesaOriginalInimigo);
    }
    
    
    private void finalizarEfeitoInimigo(){
        if(efeitoInimigoPendente){
            restaurarNiveisInimigo();
            restaurarNiveisProtagonista();
            efeitoInimigoPendente =  false;
        }
    
    
    }
    
    private void finalizarEfeitoProtagonista(){
        if(efeitoProtagonistaPendente){
            if(protagonista instanceof Xerife){
                restaurarNiveisProtagonista();
            }else if(protagonista instanceof Bruxo){
                restaurarNiveisProtagonista();
                restaurarNiveisInimigo();
            }
                efeitoProtagonistaPendente = false;
        }
    
    }
    
    
    public void poderEspecialProtagonista(){
        if(!batalhaFinalizada && turnoProtagonista 
                && protagonista.getEnergia() >= 100
                && !efeitoInimigoPendente){
           
            protagonista.usarPoderEspecial(inimigo);
                
            finalizarEfeitoInimigo();
            
            if(protagonista instanceof Alienigena){
                restaurarNiveisInimigo();
            }else if(protagonista instanceof Rei){
                restaurarNiveisProtagonista();
            }else if(protagonista instanceof Xerife){
                efeitoProtagonistaPendente = true;
            }else if(protagonista instanceof Bruxo){
                protagonista.setNivelAtaque(nivelAtaqueOriginalProtagonista);
                inimigo.setNivelDefesa(nivelDefesaOriginalInimigo);
                efeitoProtagonistaPendente = true;
                
            }
            
            protagonista.setPoderEspecialAtivado(false);
            
            verificarFimBatalha();
            
            if(!batalhaFinalizada){
                alternarTurno();
            }
        }
    }
    
    public void poderEspecialInimigo(){
            
        if(!batalhaFinalizada && !turnoProtagonista
                && !efeitoProtagonistaPendente){
            
            inimigo.usarPoderEspecial(protagonista);
            
            finalizarEfeitoProtagonista();
            
            if(inimigo instanceof Titan 
                    || inimigo instanceof Neuer
                    || inimigo instanceof Mito){
                
                inimigo.setNivelAtaque(nivelAtaqueOriginalInimigo);
            }
            efeitoInimigoPendente = true;
            
            verificarFimBatalha();
            if(!batalhaFinalizada){
                alternarTurno();
            }
        }
    }
    
    public void executarTurnoInimigo() {
    if (!batalhaFinalizada && !turnoProtagonista) {

        Random random = new Random();
        int acao = random.nextInt(3);

        switch (acao) {
            case 0:
                ataqueInimigo();
                break;

            case 1:
                defesaInimigo();
                break;

            case 2:
                if (!efeitoProtagonistaPendente) {
                    poderEspecialInimigo();
                } else {
                    ataqueInimigo();
                }
                break;
        }
    }
}
    
    public boolean isBatalhaFinalizada(){
        return batalhaFinalizada;
    }

    public Protagonista getProtagonista() {
        return protagonista;
    }

    public Inimigo getInimigo() {
        return inimigo;
    }

    public boolean isTurnoProtagonista() {
        return turnoProtagonista;
    }
    
    public boolean isEfeitoInimigoPendente() {
        return efeitoInimigoPendente;
    }
}
