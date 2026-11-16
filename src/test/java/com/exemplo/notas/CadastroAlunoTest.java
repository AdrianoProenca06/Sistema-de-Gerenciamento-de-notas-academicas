package com.exemplo.notas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CadastroAlunoTest {

    @Test
    void deveCadastrarAlunoComNomeEMatricula() {
        CadastroAluno cadastro = new CadastroAluno();

        Aluno aluno = cadastro.cadastrarAluno("Matheus Alberti", "2026001");

        assertEquals("Matheus Alberti", aluno.getNome());
        assertEquals("2026001", aluno.getMatricula());
    }

    @Test
    void naoDevePermitirMatriculaDuplicada() {
        CadastroAluno cadastro = new CadastroAluno();
        cadastro.cadastrarAluno("Matheus Alberti", "2026001");

        IllegalArgumentException erro = assertThrows(
                IllegalArgumentException.class,
                () -> cadastro.cadastrarAluno("Bruno Lima", "2026001"));

        assertEquals("Já existe um aluno cadastrado com esta matrícula.",
                erro.getMessage());
    }

    @Test
    void deveBuscarAlunoPorNome() {
        CadastroAluno cadastro = new CadastroAluno();
        cadastro.cadastrarAluno("Matheus Alberti", "2026001");

        assertEquals(1, cadastro.buscarPorNome("Matheus Alberti").size());

    }

    @Test
    void deveBuscarAlunoPorMatricula() {
        CadastroAluno cadastro = new CadastroAluno();
        cadastro.cadastrarAluno("Matheus Alberti", "2026001");

        assertTrue(cadastro.buscarPorMatricula("2026001").isPresent());
        assertEquals("Matheus Alberti",
                cadastro.buscarPorMatricula("2026001").orElseThrow().getNome());
    }

    @Test
    void deveRetornarVazioQuandoAlunoNaoForEncontrado() {
        CadastroAluno cadastro = new CadastroAluno();

        assertTrue(cadastro.buscarPorNome("Nome inexistente").isEmpty());
        assertTrue(cadastro.buscarPorMatricula("9999999").isEmpty());
    }
}
