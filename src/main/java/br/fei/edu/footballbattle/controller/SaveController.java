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
    
    public SaveController(JFrameSaves telaSave, String nomeUusario, 
        JFrameMenuPrincipal telaMenu, Conexao con) throws SQLException{
        this.telaSave = telaSave;
        this.nomeUsuario = nomeUsuario;
        this.telaMenu = telaMenu;
        this.saveDAO = new SaveDAO(con.getConnection());
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

        modelo.addRow(new Object[]{nomeSave, protagonista, nivel});
    }
    }
}
