/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.controller;

import br.fei.edu.footballbattle.model.Alienigena;
import br.fei.edu.footballbattle.model.Bruxo;
import br.fei.edu.footballbattle.model.Jogo;
import br.fei.edu.footballbattle.model.Protagonista;
import br.fei.edu.footballbattle.model.Rei;
import br.fei.edu.footballbattle.model.Save;
import br.fei.edu.footballbattle.model.Xerife;
import br.fei.edu.footballbattle.model.dao.Conexao;
import br.fei.edu.footballbattle.model.dao.SaveDAO;
import br.fei.edu.footballbattle.view.JFrameBatalha;
import br.fei.edu.footballbattle.view.JFrameMenuPrincipal;
import br.fei.edu.footballbattle.view.JFrameNovoJogo;
import br.fei.edu.footballbattle.view.JFrameResultado;
import br.fei.edu.footballbattle.view.JFrameSaves;
import br.fei.edu.footballbattle.view.JFrameTelaInicial;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private SaveDAO saveDAO;
    private String nomeUsuario;
    private Conexao con;
    private JFrameSaves telaSaves;
    
    public JogoController (JFrameMenuPrincipal menu, JFrameTelaInicial inicio, 
            String nomeUsuario,Conexao con) throws SQLException{
        this.telaMenu = menu;
        this.telaInicio = inicio;
        this.nomeUsuario = nomeUsuario;
        this.saveDAO = new SaveDAO(con.getConnection());
        this.con = con;
    }
    
    public void sair(){ 
        this.telaMenu.setVisible(false);
        this.telaInicio.setVisible(true);
    
    }
    
    public void continuarJogo()throws SQLException{
        
        JFrameSaves saves = new JFrameSaves();
        this.telaSaves = saves;
        SaveController sc = new SaveController(saves, this.nomeUsuario, 
                this.telaMenu, this.con, this);
        
        saves.setSaveController(sc);
        sc.mostrarSaves();
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
        this.save.setNomeUsuario(nomeUsuario);
        
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
    
        this.save.setProtagonista(protagonista);
        this.save.setNivelAtual(this.jogo.getNivelAtual());
        
        try {
            this.saveDAO.inserir(this.save);
        } catch (SQLException e) {
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(
                this.telaSelecao,
                "Erro ao criar o save!",
                "ERRO",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        this.jogo.comecarBatalha();
        this.telaBatalha = new JFrameBatalha();
        
        BatalhaController bc = new BatalhaController(this.jogo, 
                this.telaBatalha, this);
        this.telaBatalha.setBatalhaController(bc);
        
        alterarImagemProt(protagonista);
        alterarImagemInim();
        
        this.telaSelecao.setVisible(false);
        this.telaBatalha.setVisible(true);
    }
    
    public void carregarJogo(int idSave) throws SQLException {
        ResultSet resultado = saveDAO.consultarPorId(idSave, nomeUsuario);
        
        if (resultado.next()) {
            int nivel = resultado.getInt("nivelAtual");
            String nomeProtagonista = resultado.getString("nomeProtagonista");
            String nomeSave = resultado.getString("nomeSave");
            
            Protagonista protagonista = null;
                
            switch (nomeProtagonista) {
                case "Messi - O ET":
                    protagonista = new Alienigena();
                    break;
                    
                case "Pelé - O Rei":
                    protagonista = new Rei();
                    break;
                    
                case "R10 - O Bruxo":
                    protagonista = new Bruxo();
                    break;
                    
                case "Sérgio Ramos - O Xerife":
                    protagonista = new Xerife();
                    break;
            }
            
            if (protagonista == null) {
                throw new IllegalArgumentException("Protagonista desconhecido!");
            }
        
            this.save = new Save();
            this.save.setIdSave(idSave);
            this.save.setNomeSave(nomeSave);
            this.save.setNomeUsuario(nomeUsuario);
            this.save.setProtagonista(protagonista);
            this.save.setNivelAtual(nivel);
        
            this.jogo = new Jogo(protagonista);
            this.jogo.restaurarProgresso(nivel);
            this.jogo.comecarBatalha();
            
            this.telaBatalha = new JFrameBatalha();

            BatalhaController bc = new BatalhaController(this.jogo, 
                    this.telaBatalha, this);
            this.telaBatalha.setBatalhaController(bc);
            
            alterarImagemProt(protagonista);
            alterarImagemInim();
            
            this.telaSaves.setVisible(false);
            this.telaBatalha.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(telaMenu, "Save não encontrado!");
        }
        
        resultado.getStatement().close();
    }
    
    public void finalizarBatalha() {

    boolean venceu = !jogo.getBatalha().getInimigo().estaVivo()
            && jogo.getBatalha().getProtagonista().estaVivo();

    boolean ultimaFase = false;

    if (venceu) {
        ultimaFase = jogo.verificarFimJogo();

        if (!ultimaFase) {
            save.setNivelAtual(jogo.getNivelAtual());
        }

        try {
            saveDAO.atualizar(save);

        } catch (SQLException e) {
            e.printStackTrace();

            JOptionPane.showMessageDialog(telaBatalha,
                    "Erro ao atualizar o progresso!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    this.telaResultado = new JFrameResultado();
    this.telaResultado.setJogoController(this);
    this.telaResultado.configurarResultado(venceu, ultimaFase);

    this.telaBatalha.setVisible(false);
    this.telaResultado.setVisible(true);
    }
    
    
    public void proximaFase() {
        
        if (!jogo.verificarFimJogo()) {
            jogo.comecarBatalha();
            
            this.telaBatalha = new JFrameBatalha();
            
            BatalhaController bc = new BatalhaController(
                    this.jogo, this.telaBatalha, this);
            
            this.telaBatalha.setBatalhaController(bc);
            
            alterarImagemProt(jogo.getProtagonista());
            alterarImagemInim();
            
            this.telaResultado.setVisible(false);
            this.telaBatalha.setVisible(true);
        }
    }

  
    public void sairResultado() {
        this.telaResultado.setVisible(false);
        this.telaMenu.setVisible(true);
    }
    
    public void tentarNovamente() {
            
        Protagonista protagonista = jogo.getProtagonista();
        int nivel = jogo.getNivelAtual();
        
        this.jogo = new Jogo(protagonista);
        this.jogo.restaurarProgresso(nivel);
        this.jogo.comecarBatalha();
        
        this.telaBatalha = new JFrameBatalha();
        
        BatalhaController bc = new BatalhaController(
                this.jogo, this.telaBatalha, this);
        
        this.telaBatalha.setBatalhaController(bc);
        
        alterarImagemProt(protagonista);
        alterarImagemInim();
        
        this.telaResultado.setVisible(false);
        this.telaBatalha.setVisible(true);
    }
    
    public void acaoResultado() {

    if (jogo.getBatalha().getProtagonista().estaVivo()) {
        proximaFase();
    } else {
        tentarNovamente();
    }
}
}
