package com.exemplo.notas;

import java.util.ArrayList;

public class Sistema {
    private ArrayList<Aluno> listaAlunos = new ArrayList<>();

    public Sistema() {}

    public Aluno cadastrarAluno(String nome, String matricula) {
        Aluno aluno = new Aluno(nome, matricula);
        listaAlunos.add(aluno);
        return aluno;
    }

    public ArrayList<ItemClassificacao> classificarPorMedia() {
        return new ArrayList<>();
    }

}
