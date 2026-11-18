/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.controller;

import br.fei.edu.footballbattle.model.Alienigena;
import br.fei.edu.footballbattle.model.AranhaNegra;
import br.fei.edu.footballbattle.model.Batalha;
import br.fei.edu.footballbattle.model.Bruxo;
import br.fei.edu.footballbattle.model.Inimigo;
import br.fei.edu.footballbattle.model.Jogo;
import br.fei.edu.footballbattle.model.Mito;
import br.fei.edu.footballbattle.model.Neuer;
import br.fei.edu.footballbattle.model.Protagonista;
import br.fei.edu.footballbattle.model.Rei;
import br.fei.edu.footballbattle.model.Superman;
import br.fei.edu.footballbattle.model.Titan;
import br.fei.edu.footballbattle.model.Xerife;
import br.fei.edu.footballbattle.view.JFrameBatalha;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author SAMSUNG
 */
public class BatalhaController {
    
    private Batalha batalha;
    private JFrameBatalha telaBatalha;
    private JogoController jogoController;
    private Timer timerInimigo;
    private boolean aguardandoInimigo = false;
    
    public BatalhaController(Jogo jogo, JFrameBatalha telaBatalha,
            JogoController jogoController) {
        this.batalha = jogo.getBatalha();
        this.telaBatalha = telaBatalha;
        this.jogoController = jogoController;        
    }
    
    public void atacar() {
        if (podeExecutarAcao()) {
 
            Inimigo inimigo = batalha.getInimigo();
            int vidaAnterior = inimigo.getVidaAtual();

            batalha.ataqueProtagonista();

            int dano = vidaAnterior - inimigo.getVidaAtual();

            telaBatalha.mostrarAcao(
                    "Você atacou " + inimigo.getNome()
                    + " e causou " + dano + " de dano!");
            
            atualizarInterface();
            atualizarMensagemEfeito();
            prepararTurnoInimigo();
        }
    }

    public void defender() {
        if (podeExecutarAcao()) {
            batalha.defesaProtagonista();
    
            telaBatalha.mostrarAcao(
                    "Você se defendeu! O próximo dano recebido "
                    + "será reduzido pela metade.");
            
            atualizarInterface();
            atualizarMensagemEfeito();
            prepararTurnoInimigo();
        }
    }

    public void usarPoderEspecial() {
        if (podeExecutarAcao()) {
            Protagonista protagonista = batalha.getProtagonista();
            Inimigo inimigo = batalha.getInimigo();
            
            if (protagonista.getEnergia() < 100) {
                JOptionPane.showMessageDialog(telaBatalha,
                        "Energia insuficiente!");
                return;
            }
            
            if (batalha.isEfeitoInimigoPendente()) {
                return;
            }
            
            int vidaAnterior = inimigo.getVidaAtual();
            
            batalha.poderEspecialProtagonista();
            
            int dano = vidaAnterior - inimigo.getVidaAtual();
            
            telaBatalha.mostrarAcao(
                    protagonista.getNome() + " utilizou "
                    + protagonista.getNomepoderEspecial()
                    + " e causou " + dano + " de dano!");
            
            telaBatalha.mostrarEfeito(descricaoPoderProtagonista(protagonista));
            atualizarInterface();
            prepararTurnoInimigo();
        }
    }
    
    private boolean podeExecutarAcao() {
        return !aguardandoInimigo && !batalha.isBatalhaFinalizada()
                && batalha.isTurnoProtagonista();
    }
    
    private void prepararTurnoInimigo() {
        if (batalha.isBatalhaFinalizada()) {
            atualizarInterface();
            verificarResultado();
            return;
        }
        
        aguardandoInimigo = true;
        atualizarInterface();

        timerInimigo = new Timer(5000, e -> {
            executarAcaoInimigo();
        });

        timerInimigo.setRepeats(false);
        timerInimigo.start();
    }
    
    private void executarAcaoInimigo() {
        if (batalha.isBatalhaFinalizada()) {
            aguardandoInimigo = false;
            return;
        }

        Protagonista protagonista = batalha.getProtagonista();
        Inimigo inimigo = batalha.getInimigo();

        int vidaAnterior = protagonista.getVidaAtual();

        batalha.executarTurnoInimigo();

        int dano = vidaAnterior - protagonista.getVidaAtual();

        String acao = batalha.getUltimaAcaoInimigo();

        switch (acao) {
            case "ATAQUE":
                telaBatalha.mostrarAcao(
                        inimigo.getNome() + " atacou você e causou "
                        + dano + " de dano!");
                break;

            case "DEFESA":
                telaBatalha.mostrarAcao(
                        inimigo.getNome()
                        + " se defendeu! O próximo dano recebido "
                        + "será reduzido pela metade.");
                break;

            case "PODER":
                telaBatalha.mostrarAcao(
                        inimigo.getNome() + " utilizou "
                        + inimigo.getNomepoderEspecial()
                        + " e causou " + dano + " de dano!");
                break;
        }

        aguardandoInimigo = false;

        atualizarInterface();

        if ("PODER".equals(acao)) {
            telaBatalha.mostrarEfeito(
                    descricaoPoderInimigo(inimigo));
        } else {
            atualizarMensagemEfeito();
        }

        verificarResultado();
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

        boolean podeAgir = !aguardandoInimigo && !batalha.isBatalhaFinalizada()
                && batalha.isTurnoProtagonista();

        boolean podeUsarPoder = podeAgir
                && protagonista.getEnergia() >= 100
                && !batalha.isEfeitoInimigoPendente();

        telaBatalha.atualizarBotoes(
                podeAgir, podeUsarPoder);
    }

    private void atualizarMensagemEfeito() {
        if (batalha.isEfeitoInimigoPendente()) {
            telaBatalha.mostrarEfeito(
                    descricaoPoderInimigo(batalha.getInimigo()));

        } else if (batalha.isEfeitoProtagonistaPendente()) {
            telaBatalha.mostrarEfeito(
                    descricaoPoderProtagonista(
                            batalha.getProtagonista()));

        } else {
            telaBatalha.mostrarEfeito(
                    "Nenhum efeito especial pendente.");
        }
    }

    private String descricaoPoderProtagonista(
            Protagonista protagonista) {

        if (protagonista instanceof Alienigena) {
            return "Abdução: defesa inimiga zerada durante o ataque.";
        }

        if (protagonista instanceof Rei) {
            return "Força do Trono: ataque duplicado neste golpe.";
        }

        if (protagonista instanceof Bruxo) {
            return "Magia Pura: atributos inimigos afetados "
                    + "até o fim do próximo turno.";
        }

        if (protagonista instanceof Xerife) {
            return "A Muralha: defesa duplicada "
                    + "até o fim do turno inimigo.";
        }

        return "Poder especial ativado!";
    }

    private String descricaoPoderInimigo(Inimigo inimigo) {

        if (inimigo instanceof Titan) {
            return "Fúria: +4 de ataque e -2 de defesa "
                    + "durante o efeito.";
        }

        if (inimigo instanceof Superman) {
            return "Muralha Italiana: defesa de Buffon duplicada.";
        }

        if (inimigo instanceof AranhaNegra) {
            return "Teia Negra: seu ataque foi reduzido em 4.";
        }

        if (inimigo instanceof Neuer) {
            return "Kamikaze: ataque duplicado e defesa zerada "
                    + "durante o golpe.";
        }

        if (inimigo instanceof Mito) {
            return "Centésimo Gol: ataque duplicado e "
                    + "própria defesa zerada durante o golpe.";
        }

        return "Poder especial inimigo ativado!";
    }
    
    private void verificarResultado() {
        if (batalha.isBatalhaFinalizada()) {
            jogoController.finalizarBatalha();
        }
    }
}