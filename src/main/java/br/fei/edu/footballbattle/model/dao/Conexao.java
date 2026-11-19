/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.model.dao;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author SAMSUNG
 */
public class Conexao {
    private Connection conn;

    public Connection getConnection() throws SQLException{
        Dotenv dotenv = Dotenv.load();
        
        Connection conexao = DriverManager.getConnection(
                dotenv.get("jdbc:postgresql://localhost:5432/FootballBattle"),
                dotenv.get("postgres"),
                dotenv.get("Srcoxinha3#")                
        );
        
        this.conn = conexao;
        return this.conn;
    }
}
