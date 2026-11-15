package com.exemplo.notas;

public class GerenciamentoNotas {

    public void registrarNota(Aluno aluno, String materia, Double valor) {

        validarNota(valor);

        if (buscarNota(aluno, materia) != null) {
            throw new IllegalArgumentException(
                    "Já existe uma nota registrada nesta matéria.");
        }

        aluno.getListaNotas().add(new Nota(materia, valor));
    }

    public void alterarNota(Aluno aluno, String materia, Double novoValor) {

        Nota nota = buscarNota(aluno, materia);

        if (nota == null) {
            throw new IllegalArgumentException(
                    "A nota informada ainda não foi registrada.");
        }

        validarNota(novoValor);

        nota.setValor(novoValor);
    }

    public Nota buscarNota(Aluno aluno, String materia) {

        for (Nota nota : aluno.getListaNotas()) {
            if (nota.getMateria().equals(materia)) {
                return nota;
            }
        }

        return null;
    }

    private void validarNota(Double valor) {

        if (valor == null || valor < 0 || valor > 10) {
            throw new IllegalArgumentException(
                    "A nota deve estar entre 0 e 10.");
        }
    }
}
