package com.exemplo.notas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sistema {
    private ArrayList<Aluno> listaAlunos = new ArrayList<>();
    private final AvaliacaoAcademica avaliacao = new AvaliacaoAcademica();

    public Sistema() {}

    public Aluno cadastrarAluno(String nome, String matricula) {
        Aluno aluno = new Aluno(nome, matricula);
        listaAlunos.add(aluno);
        return aluno;
    }

    public List<ItemClassificacao> classificarPorMedia() {
        List<Aluno> ordenados = listaAlunos.stream().sorted(Comparator.comparingDouble((Aluno a) -> calcularMedia(a)).reversed()).toList();

        List<ItemClassificacao> classificacao = new ArrayList<>();
        for (int i = 0; i < ordenados.size(); i++) {
            Aluno aluno = ordenados.get(i);
            classificacao.add(new ItemClassificacao(i + 1, aluno.getNome(), aluno.getMatricula(), calcularMedia(aluno)));
        }
        return classificacao;
    }

    private double calcularMedia(Aluno aluno) {
        List<Double> valores = aluno.getListaNotas().stream()
                .map(Nota::getValor)
                .toList();
        return avaliacao.calcularMedia(valores);
    }

}
