/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.controller;

import br.fei.edu.footballbattle.model.Batalha;
import br.fei.edu.footballbattle.model.Jogo;

/**
 *
 * @author SAMSUNG
 */
public class BatalhaController {
    
    private Batalha batalha;

    public BatalhaController(Jogo jogo) {
        this.batalha = jogo.getBatalha();
    }
}
