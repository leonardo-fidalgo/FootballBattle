/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.controller;

import br.fei.edu.footballbattle.model.Batalha;
import br.fei.edu.footballbattle.model.Inimigo;
import br.fei.edu.footballbattle.model.Jogo;
import br.fei.edu.footballbattle.model.Protagonista;
import br.fei.edu.footballbattle.view.JFrameBatalha;
import javax.swing.JOptionPane;

/**
 *
 * @author SAMSUNG
 */
public class BatalhaController {
    
    private Batalha batalha;
    private JFrameBatalha telaBatalha;
    private JogoController jogoController;
    
    public BatalhaController(Jogo jogo, JFrameBatalha telaBatalha,
            JogoController jogoController) {
        this.batalha = jogo.getBatalha();
        this.telaBatalha = telaBatalha;
        this.jogoController = jogoController;        
    }
    
    public void atacar() {
        if (!batalha.isBatalhaFinalizada()) {
            batalha.ataqueProtagonista();
            atualizarInterface();
            verificarResultado();
        }
    }

    public void defender() {
        if (!batalha.isBatalhaFinalizada()) {
        batalha.defesaProtagonista();
        atualizarInterface();
        verificarResultado();
        }
    }

    public void usarPoderEspecial() {
        if (!batalha.isBatalhaFinalizada()) {
            if (batalha.getProtagonista().getEnergia() < 100) {
            JOptionPane.showMessageDialog(telaBatalha,
                    "Energia insuficiente!");
            return;
            }

        batalha.poderEspecialProtagonista();
        atualizarInterface();
        verificarResultado();
        }

        
    }

    public void atualizarInterface() {
        Protagonista protagonista = batalha.getProtagonista();
        Inimigo inimigo = batalha.getInimigo();

        telaBatalha.atualizarInformacoes(
                protagonista.getVidaAtual(),
                protagonista.getVidaMaxima(),
                inimigo.getVidaAtual(),
                inimigo.getVidaMaxima(),
                protagonista.getEnergia()
        );

        boolean podeAgir = !batalha.isBatalhaFinalizada()
                && batalha.isTurnoProtagonista();

        boolean podeUsarPoder = podeAgir
                && protagonista.getEnergia() >= 100
                && !batalha.isEfeitoInimigoPendente();

        telaBatalha.atualizarBotoes(
                podeAgir, podeUsarPoder);
    }

    private void verificarResultado() {
        if (batalha.isBatalhaFinalizada()) {
            jogoController.finalizarBatalha();
        }
    }
}