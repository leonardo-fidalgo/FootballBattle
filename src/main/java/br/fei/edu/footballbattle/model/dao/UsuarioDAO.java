/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.model.dao;

import br.fei.edu.footballbattle.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author SAMSUNG
 */
public class UsuarioDAO {
        private Connection conn;

    public UsuarioDAO(Connection conn) {
        this.conn = conn;
    }

        public void inserir(Usuario usuario) throws SQLException{
            String sql = "INSERT INTO usuario"
                    + "(nome,sobrenome,nome_usuario, senha) "
                    + "VALUES (?,?,?,?)";
            
            
        PreparedStatement statement = conn.prepareStatement(sql); 
        
        statement.setString(1, usuario.getNome());
        statement.setString(2, usuario.getSobrenome());
        statement.setString(3, usuario.getNome_usuario());
        statement.setString(4, usuario.getSenha());

        statement.execute();
        }
        
        
        public ResultSet consultar(String nome_usuario, String senha) throws SQLException{
            String sql = "SELECT * FROM usuario WHERE nome_usuario = ? AND senha = ?";
                    
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, nome_usuario);
            statement.setString(2, senha);
            
            statement.execute();
            ResultSet resultado = statement.getResultSet();
            return resultado;
        }
}
