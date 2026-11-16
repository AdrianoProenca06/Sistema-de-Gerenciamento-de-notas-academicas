package br.edu.pucpr.notas;

import java.util.Objects;

public class Aluno {

    private final String matricula;
    private final String nome;

    public Aluno(String matricula, String nome) {

        if (matricula == null || matricula.isBlank()) {
            throw new IllegalArgumentException(
                    "A matrícula do aluno é obrigatória.");
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do aluno é obrigatório.");
        }

        this.matricula = matricula.trim();
        this.nome = nome.trim();
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object outro) {
        if (this == outro) {
            return true;
        }
        if (!(outro instanceof Aluno aluno)) {
            return false;
        }
        return matricula.equals(aluno.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public String toString() {
        return matricula + " - " + nome;
    }
}
