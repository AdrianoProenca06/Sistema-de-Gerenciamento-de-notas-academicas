package com.exemplo.notas;
import java.util.ArrayList;

public class Aluno {
    private String nome;
    private String matricula;
    private ArrayList<Nota> listaNotas = new ArrayList<>();

    public Aluno(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

}
