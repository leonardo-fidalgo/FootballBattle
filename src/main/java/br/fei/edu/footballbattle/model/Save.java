/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.fei.edu.footballbattle.model;

/**
 *
 * @author SAMSUNG
 */
public class Save {
    private String nomeSave;
    private int idSave;
    private String nomeUsuario;
    private int nivelAtual;
    private Protagonista protagonista;

    public Save(Jogo jogo, String nomeUsuario, String nomeSave){
        this.nivelAtual = jogo.getNivelAtual();
        this.protagonista = jogo.getProtagonista();
        this.nomeUsuario = nomeUsuario;
        this.nomeSave = nomeSave;
    }

    public int getIdSave() {
        return idSave;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public int getNivelAtual() {
        return nivelAtual;
    }

    public Protagonista getProtagonista() {
        return protagonista;
    }

    public String getNomeSave() {
        return nomeSave;
    }

    
}
