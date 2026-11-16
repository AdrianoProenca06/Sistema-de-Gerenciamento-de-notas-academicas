package com.exemplo.notas.steps;

import com.exemplo.notas.Aluno;
import com.exemplo.notas.CadastroAluno;
import io.cucumber.java.pt.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CadastroAlunoSteps {

    private final CadastroAluno cadastro = new CadastroAluno();
    private Aluno alunoCadastrado;
    private List<Aluno> alunosEncontrados = List.of();
    private Optional<Aluno> alunoEncontrado = Optional.empty();
    private Exception erro;

    @Dado("que o cadastro de alunos está vazio")
    public void queOCadastroDeAlunosEstaVazio() {
        assertTrue(cadastro.buscarPorNome("Matheus Alberti").isEmpty());
    }

    @Quando("cadastro o aluno {string} com matrícula {string}")
    public void cadastroOAlunoComMatricula(String nome, String matricula) {
        try {
            alunoCadastrado = cadastro.cadastrarAluno(nome, matricula);
        } catch (IllegalArgumentException e) {
            erro = e;
        }
    }

    @Dado("que o aluno {string} com matrícula {string} já está cadastrado")
    public void queOAlunoJaEstaCadastrado(String nome, String matricula) {
        cadastro.cadastrarAluno(nome, matricula);
    }

    @Quando("busco alunos pelo nome {string}")
    public void buscoAlunosPeloNome(String nome) {
        alunosEncontrados = cadastro.buscarPorNome(nome);
    }

    @Quando("busco o aluno pela matrícula {string}")
    public void buscoOAlunoPelaMatricula(String matricula) {
        alunoEncontrado = cadastro.buscarPorMatricula(matricula);
    }

    @Então("o aluno cadastrado deve ter nome {string} e matrícula {string}")
    public void oAlunoCadastradoDeveTerNomeEMatricula(String nome, String matricula) {
        assertNotNull(alunoCadastrado);
        assertEquals(nome, alunoCadastrado.getNome());
        assertEquals(matricula, alunoCadastrado.getMatricula());
    }

    @Então("o sistema deve informar o erro de matrícula duplicada")
    public void oSistemaDeveInformarErroDeMatriculaDuplicada() {
        assertNotNull(erro, "Nenhum erro foi lançado");
        assertEquals("Já existe um aluno cadastrado com esta matrícula.",
                erro.getMessage());
    }

    @Então("devo encontrar {int} aluno com nome {string} e matrícula {string}")
    public void devoEncontrarAlunoComNomeEMatricula(
            int quantidade, String nome, String matricula) {
        assertEquals(quantidade, alunosEncontrados.size());
        assertTrue(alunosEncontrados.stream().anyMatch(aluno ->
                aluno.getNome().equals(nome)
                        && aluno.getMatricula().equals(matricula)));
    }

    @Então("devo encontrar o aluno {string} com matrícula {string}")
    public void devoEncontrarOAlunoComMatricula(String nome, String matricula) {
        assertTrue(alunoEncontrado.isPresent());
        assertEquals(nome, alunoEncontrado.orElseThrow().getNome());
        assertEquals(matricula, alunoEncontrado.orElseThrow().getMatricula());
    }

    @Então("não devo encontrar o aluno pela matrícula")
    public void naoDevoEncontrarOAlunoPelaMatricula() {
        assertTrue(alunoEncontrado.isEmpty());
    }
}
