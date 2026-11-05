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
    private List<Inimigo>Inimigos;

    public Jogo(Protagonista protagonista) {
        this.protagonista = protagonista;
//        Inimigos = new ArrayList<>(List.of(Titan, ));
    }
    
        
    
    
    private void verificarInimigoAtual(){
        for(Inimigo inimigo : protagonista.getInimigosEnfrentados()){
            
        
        }    
    }   
   
}
