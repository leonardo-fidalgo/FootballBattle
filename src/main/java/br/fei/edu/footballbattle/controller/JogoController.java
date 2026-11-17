/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.controller;

import br.fei.edu.footballbattle.model.Bruxo;
import br.fei.edu.footballbattle.model.Jogo;
import br.fei.edu.footballbattle.model.Protagonista;
import br.fei.edu.footballbattle.model.Rei;
import br.fei.edu.footballbattle.model.Save;
import br.fei.edu.footballbattle.model.Xerife;
import br.fei.edu.footballbattle.view.JFrameBatalha;
import br.fei.edu.footballbattle.view.JFrameMenuPrincipal;
import br.fei.edu.footballbattle.view.JFrameNovoJogo;
import br.fei.edu.footballbattle.view.JFrameResultado;
import br.fei.edu.footballbattle.view.JFrameSaves;
import br.fei.edu.footballbattle.view.JFrameTelaInicial;
import javax.swing.JOptionPane;

/**
 *
 * @author SAMSUNG
 */
public class JogoController {
    private JFrameMenuPrincipal telaMenu;
    private JFrameNovoJogo telaSelecao;
    private JFrameResultado telaResultado;
    private JFrameTelaInicial telaInicio;
    private JFrameBatalha telaBatalha;
    private Save save;
    private Jogo jogo;
    public JogoController(JFrameMenuPrincipal menu, JFrameTelaInicial inicio) {
        this.telaMenu = menu;
        this.telaInicio = inicio;
    }
    
    public void sair(){ 
        this.telaMenu.setVisible(false);
        this.telaInicio.setVisible(true);
    
    }
    
    public void continuarJogo(){
        JFrameSaves saves = new JFrameSaves();
        
        SaveController sc = new SaveController(saves);
        
        saves.setSaveController(sc);
        this.telaMenu.setVisible(false);
        saves.setVisible(true);
    }
    
    public void novoJogo(){
        String nomeSave = JOptionPane.showInputDialog(null, "Nome do save:");
        
        if(nomeSave == null || nomeSave.isBlank()){
            JOptionPane.showMessageDialog(telaMenu, "Nenhum nome informado");
            return;
        }
        this.save = new Save();
        this.save.setNomeSave(nomeSave);
        
        this.telaSelecao  = new JFrameNovoJogo();

        this.telaSelecao.setJogoController(this);

        
        this.telaMenu.setVisible(false);
        this.telaSelecao.setVisible(true);
    }
    
    public void alterarImagemProt(Protagonista protagonista){
        if(protagonista instanceof Bruxo){
            this.telaBatalha.carregarImagem(this.telaBatalha.getLblProt(),
                "/imagens/Ronaldinho.png", 160, 160);
        }else if(protagonista instanceof Rei){
            this.telaBatalha.carregarImagem(this.telaBatalha.getLblProt(),
                "/imagens/Pele.png", 160, 160);
        }else if(protagonista instanceof Xerife){
            this.telaBatalha.carregarImagem(this.telaBatalha.getLblProt(),
                "/imagens/SergioRamos.png", 160, 160);
        }
    }
    
    public void alterarImagemInim(){
        switch(jogo.getNivelAtual()){
            case 2:
                this.telaBatalha.carregarImagem(this.telaBatalha.getLblInim(),
                "/imagens/Buffon.png", 160, 160);
                break;
            
            case 3:
                this.telaBatalha.carregarImagem(this.telaBatalha.getLblInim(),
                "/imagens/Yashin.png", 160, 160);
                break;
               
            case 4:
                this.telaBatalha.carregarImagem(this.telaBatalha.getLblInim(),
                "/imagens/Neuer.png", 160, 160);
                break;
            
            case 5:
                this.telaBatalha.carregarImagem(this.telaBatalha.getLblInim(),
                "/imagens/Rogerio.png", 160, 160);
                break;
        }
    }
    
    
    public void iniciarPrimeiraBatalha(Protagonista protagonista){
        this.jogo = new Jogo(protagonista);
        this.telaBatalha = new JFrameBatalha();
        
        BatalhaController bc = new BatalhaController();
        this.telaBatalha.setBatalhaController(bc);
        
        alterarImagemProt(protagonista);
        alterarImagemInim();
        
        this.telaSelecao.setVisible(false);
        this.telaBatalha.setVisible(true);
    
        //falta salvar no banco
    }
}
