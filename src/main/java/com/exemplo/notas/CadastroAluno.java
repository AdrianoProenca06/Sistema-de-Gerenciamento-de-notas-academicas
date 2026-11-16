package com.exemplo.notas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CadastroAluno {
    private final List<Aluno> alunos = new ArrayList<>();

    public Aluno cadastrarAluno(String nome, String matricula) {
        // Refatoração: validação de nome e matrícula
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do aluno é obrigatório.");
        }
        if (matricula == null || matricula.isBlank()) {
            throw new IllegalArgumentException("A matrícula do aluno é obrigatória.");
        }

        String nomeNormalizado = nome.trim();
        String matriculaNormalizada = matricula.trim();

        if (buscarPorMatricula(matriculaNormalizada).isPresent()) {
            throw new IllegalArgumentException(
                    "Já existe um aluno cadastrado com esta matrícula.");
        }

        Aluno aluno = new Aluno(nomeNormalizado, matriculaNormalizada);
        alunos.add(aluno);
        return aluno;
    }

    public List<Aluno> buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            return List.of();
        }

        String nomeNormalizado = nome.trim();
        return alunos.stream()
                .filter(aluno -> aluno.getNome().equals(nomeNormalizado))
                .toList();
    }

    public Optional<Aluno> buscarPorMatricula(String matricula) {
        if (matricula == null || matricula.isBlank()) {
            return Optional.empty();
        }

        String matriculaNormalizada = matricula.trim();
        return alunos.stream()
                .filter(aluno -> aluno.getMatricula().equals(matriculaNormalizada))
                .findFirst();
    }
}
