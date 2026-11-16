package br.edu.pucpr.notas;

import java.util.ArrayList;
import java.util.List;

/**
 * Etapa de Consulta e Manutenção: listar e excluir alunos.
 */
public class GerenciadorAlunos {

    private final List<Aluno> alunos = new ArrayList<>();

    /**
     * Ponto de integração com a funcionalidade de cadastro do grupo.
     * Versão mínima, usada aqui apenas para preparar os testes.
     */
    public void cadastrarAluno(Aluno aluno) {

        if (aluno == null) {
            throw new IllegalArgumentException(
                    "O aluno não pode ser nulo.");
        }

        alunos.add(aluno);
    }

    /**
     * Retorna todos os alunos na ordem de cadastro.
     * A lista é somente leitura, para que ninguém altere o cadastro por fora.
     */
    public List<Aluno> listarAlunos() {
        return List.copyOf(alunos);
    }

    /**
     * Remove o aluno com a matrícula informada.
     */
    public void excluirAluno(String matricula) {

        if (matricula == null || matricula.isBlank()) {
            throw new IllegalArgumentException(
                    "A matrícula deve ser informada.");
        }

        boolean removido = alunos.removeIf(
                aluno -> aluno.getMatricula().equals(matricula.trim()));

        if (!removido) {
            throw new IllegalArgumentException(
                    "Aluno não encontrado.");
        }
    }
}
