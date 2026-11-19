/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.fei.edu.footballbattle;

import br.fei.edu.footballbattle.controller.UsuarioController;
import br.fei.edu.footballbattle.model.dao.Conexao;
import br.fei.edu.footballbattle.view.JFrameTelaCadastro;
import br.fei.edu.footballbattle.view.JFrameTelaInicial;
import java.sql.SQLException;

/**
 *
 * @author SAMSUNG
 */
public class FootballBattle {

    public static void main(String[] args) {
        Conexao c = new Conexao();
        try{
            c.getConnection();
            System.out.println("Conexao com BD bem sucedida");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
        
        JFrameTelaInicial telaInicial = new JFrameTelaInicial();
        JFrameTelaCadastro telaCadastro = new JFrameTelaCadastro();
        
        UsuarioController usuarioController = new UsuarioController(telaInicial,
            telaCadastro, c);
        
        telaInicial.setUsuarioController(usuarioController);
        telaCadastro.setUsuarioController(usuarioController);
        
        telaInicial.setVisible(true);
    }
}
