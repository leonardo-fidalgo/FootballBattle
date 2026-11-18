/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.controller;

import br.fei.edu.footballbattle.model.dao.Conexao;
import br.fei.edu.footballbattle.model.dao.SaveDAO;
import br.fei.edu.footballbattle.view.JFrameMenuPrincipal;
import br.fei.edu.footballbattle.view.JFrameSaves;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SAMSUNG
 */
public class SaveController {
    private JFrameSaves telaSave;
    private String nomeUsuario;
    private JFrameMenuPrincipal telaMenu;
    private SaveDAO saveDAO;
    private JogoController jogoController;
    
    public SaveController(JFrameSaves telaSave, String nomeUsario, 
        JFrameMenuPrincipal telaMenu, Conexao con, 
        JogoController jogoController) throws SQLException{
        this.telaSave = telaSave;
        this.nomeUsuario = nomeUsario;
        this.telaMenu = telaMenu;
        this.saveDAO = new SaveDAO(con.getConnection());
        this.jogoController = jogoController;
    }

    public SaveController() {
    }
    
    public void voltar(){
        
        telaSave.setVisible(false);
        telaMenu.setVisible(true);

    }
    
    public void mostrarSaves() throws SQLException{
        DefaultTableModel modelo = (DefaultTableModel) telaSave.getSavesTable()
                .getModel();

        modelo.setRowCount(0);
        
        ResultSet resultado = saveDAO.consultar(nomeUsuario);
        
        while (resultado.next()) {
            String nomeSave = resultado.getString("nomeSave");
            String protagonista = resultado.getString("nomeProtagonista");
            int nivel = resultado.getInt("nivelAtual");
            int idSave = resultado.getInt("id_save");
            modelo.addRow(new Object[]{nomeSave, protagonista, nivel, idSave});
        }
    }
    
    public void excluir() throws SQLException{
        int linhaSelecionada = telaSave.getSavesTable().getSelectedRow();
    
        if (linhaSelecionada != -1) {
            int linhaModelo = telaSave.getSavesTable()
                    .convertRowIndexToModel(linhaSelecionada);

            int idSave = (int) telaSave.getSavesTable().getModel()
                    .getValueAt(linhaModelo, 3);
            
            int resposta = JOptionPane.showConfirmDialog(telaSave, 
                    "Tem certeza que deseja excluir?",    "Confirmar exclusão",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if(resposta == JOptionPane.YES_OPTION){
                this.saveDAO.excluir(idSave);
            }
            mostrarSaves();
        }
        
    }
    
    public void jogar() throws SQLException {
        int linhaSelecionada = telaSave.getSavesTable().getSelectedRow();
    
        if (linhaSelecionada != -1) {
            int linhaModelo = telaSave.getSavesTable()
                    .convertRowIndexToModel(linhaSelecionada);

            int idSave = (int) telaSave.getSavesTable().getModel()
                    .getValueAt(linhaModelo, 3);
            
            jogoController.carregarJogo(idSave);
        }
    }
}
