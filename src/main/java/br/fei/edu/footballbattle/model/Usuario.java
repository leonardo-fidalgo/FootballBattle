/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.model;

/**
 *
 * @author SAMSUNG
 */
public class Usuario {
    private String nome;
    private String sobrenome;
    private String nome_usuario;
    private String senha;

    public Usuario(String nome, String sobrenome, String nome_usuario, String senha) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nome_usuario = nome_usuario;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    
}
