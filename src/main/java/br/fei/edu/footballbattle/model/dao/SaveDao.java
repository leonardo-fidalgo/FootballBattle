/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.model.dao;

import br.fei.edu.footballbattle.model.Save;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author SAMSUNG
 */
public class SaveDAO {
    private Connection conn;

    public SaveDAO(Connection conn) {
        this.conn = conn;
    }
    
    public void inserir(Save save) throws SQLException{
            String sql = "INSERT INTO save "
                    + "(\"nomeSave\", \"nomeUsuario\", \"nivelAtual\", "
                    + "\"nomeProtagonista\") "
                    + "VALUES (?,?,?,?)";
            
            
        PreparedStatement statement = conn.prepareStatement(sql); 
        
        statement.setString(1, save.getNomeSave());
        statement.setString(2, save.getNomeUsuario());
        statement.setInt(3, save.getNivelAtual());
        statement.setString(4, save.getProtagonista().getNome());

        statement.execute();
        statement.close();
        }
    
    public ResultSet consultar(String nomeUsuario) throws SQLException{
        String sql = "SELECT * FROM save WHERE \"nomeUsuario\" = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, nomeUsuario);
        
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        
        return resultado;
    }
    
    public void excluir(int idSave) throws SQLException {
    String sql = "DELETE FROM Save WHERE id_save = ?";

    PreparedStatement statement = conn.prepareStatement(sql);

    statement.setInt(1, idSave);

    statement.execute();
    statement.close();
    }

    public void atualizar(Save save) throws SQLException {
    String sql = "UPDATE Save "
               + "SET nivelAtual = ?, nomeProtagonista = ? "
               + "WHERE id_save = ?";

    PreparedStatement statement = conn.prepareStatement(sql);

    statement.setInt(1, save.getNivelAtual());
    statement.setString(2, save.getProtagonista().getNome());
    statement.setInt(3, save.getIdSave());

    statement.execute();
    statement.close();
}




}

