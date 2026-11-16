/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.controller;

import br.fei.edu.footballbattle.model.Usuario;
import br.fei.edu.footballbattle.model.dao.Conexao;
import br.fei.edu.footballbattle.model.dao.UsuarioDAO;
import br.fei.edu.footballbattle.view.JFrameTelaCadastro;
import br.fei.edu.footballbattle.view.JFrameTelaInicial;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author SAMSUNG
 */
public class UsuarioController {
    private JFrameTelaInicial telaInicial;
    private JFrameTelaCadastro telaCadastro;
    private UsuarioDAO usuarioDAO;
    private Conexao conexao;
    
    
    public UsuarioController(JFrameTelaInicial telaInicial,
            JFrameTelaCadastro telaCadastro, Conexao conexao){
        this.telaInicial = telaInicial;
        this.telaCadastro = telaCadastro;
        this.conexao = conexao;
        
        try{
            this.usuarioDAO = new UsuarioDAO(
                    conexao.getConnection());
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void cadastrese(){
        telaInicial.setVisible(false);
        telaCadastro.setVisible(true);
    }
    
    
    public void cadastrar(){
        
        String nome = this.telaCadastro.getNomeTf().getText();
        String sobrenome = this.telaCadastro.getSobrenomeTf().getText();
        String nome_usuario = this.telaCadastro.getUserTf().getText();
        String senha = String.copyValueOf(this.telaCadastro
                .getSenhaPf().getPassword());
        
        if(nome.isEmpty() || sobrenome.isEmpty() || 
                nome_usuario.isEmpty() ||senha.isEmpty()){
            JOptionPane.showMessageDialog(this.telaCadastro, 
            "Todos os campos devem ser preenchidos",
            "AVISO", JOptionPane.INFORMATION_MESSAGE);
        }else{
            Usuario usuario = new Usuario(nome, sobrenome, nome_usuario, senha);
            
            try{
                this.usuarioDAO.inserir(usuario);
                
            }catch(SQLException e){
                e.printStackTrace();
            }
            
            telaCadastro.setVisible(false);
            telaInicial.setVisible(true);
        }
    }
    
    
    
    
    
}
