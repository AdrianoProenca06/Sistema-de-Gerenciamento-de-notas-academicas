package br.edu.pucpr.notas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExcluirAlunoTest {

    private GerenciadorAlunos gerenciador;

    @BeforeEach
    void prepararAlunosCadastrados() {
        gerenciador = new GerenciadorAlunos();
        gerenciador.cadastrarAluno(new Aluno("2024001", "Ana Souza"));
        gerenciador.cadastrarAluno(new Aluno("2024002", "Bruno Lima"));
        gerenciador.cadastrarAluno(new Aluno("2024003", "Carla Mendes"));
    }


    @Test
    void deveExcluirAlunoCadastrado() {

        gerenciador.excluirAluno("2024002");

        List<Aluno> alunos = gerenciador.listarAlunos();

        assertFalse(alunos.contains(new Aluno("2024002", "Bruno Lima")));
    }


    @Test
    void deveManterOsDemaisAlunosAposExclusao() {

        gerenciador.excluirAluno("2024002");

        List<Aluno> alunos = gerenciador.listarAlunos();

        assertEquals(2, alunos.size());
        assertEquals("2024001", alunos.get(0).getMatricula());
        assertEquals("2024003", alunos.get(1).getMatricula());
    }


    @Test
    void naoDeveExcluirAlunoInexistente() {

        IllegalArgumentException erro = assertThrows(
                IllegalArgumentException.class,
                () -> gerenciador.excluirAluno("9999999"));

        assertEquals("Aluno não encontrado.", erro.getMessage());
        assertEquals(3, gerenciador.listarAlunos().size());
    }


    @Test
    void naoDeveExcluirAlunoComMatriculaEmBranco() {

        IllegalArgumentException erro = assertThrows(
                IllegalArgumentException.class,
                () -> gerenciador.excluirAluno("   "));

        assertEquals("A matrícula deve ser informada.", erro.getMessage());
    }


    @Test
    void naoDeveExcluirAlunoComMatriculaNula() {

        assertThrows(IllegalArgumentException.class,
                () -> gerenciador.excluirAluno(null));

        assertEquals(3, gerenciador.listarAlunos().size());
    }
}
