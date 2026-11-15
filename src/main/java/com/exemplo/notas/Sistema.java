package com.exemplo.notas;

import java.util.*;

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
        if (listaAlunos.isEmpty()) {
            throw new IllegalArgumentException("Não há alunos cadastrados.");
        }

        List<Aluno> ordenados = listaAlunos.stream().sorted(Comparator.comparingDouble((Aluno a) -> calcularMedia(a)).reversed()).toList();

        List<ItemClassificacao> classificacao = new ArrayList<>();
        for (int i = 0; i < ordenados.size(); i++) {
            Aluno aluno = ordenados.get(i);
            classificacao.add(new ItemClassificacao(i + 1, aluno.getNome(), aluno.getMatricula(), calcularMedia(aluno)));
        }
        return classificacao;
    }

    public RelatorioIndividual gerarRelatorio(String matricula) {
        return montarRelatorio(buscarPorMatricula(matricula));
    }

    public static final String MSG_ALUNO_NAO_ENCONTRADO = "Aluno não encontrado.";

    private Aluno buscarPorMatricula(String matricula) {
        return listaAlunos.stream()
                .filter(aluno -> aluno.getMatricula().equals(matricula))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(MSG_ALUNO_NAO_ENCONTRADO));
    }

    private RelatorioIndividual montarRelatorio(Aluno aluno) {
        Map<String, Double> notas = new LinkedHashMap<>();
        for (Nota nota : aluno.getListaNotas()) {
            notas.put(nota.getMateria(), nota.getValor());
        }
        double media = calcularMedia(aluno);
        return new RelatorioIndividual(
                aluno.getNome(), notas, media, avaliacao.verificarSituacao(media));
    }

    private double calcularMedia(Aluno aluno) {
        List<Double> valores = aluno.getListaNotas().stream()
                .map(Nota::getValor)
                .toList();
        return avaliacao.calcularMedia(valores);
    }

}
