package br.edu.pucpr.notas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListarAlunosTest {

    private GerenciadorAlunos gerenciador;

    @BeforeEach
    void prepararGerenciador() {
        gerenciador = new GerenciadorAlunos();
    }


    @Test
    void deveRetornarListaVaziaQuandoNaoHaAlunosCadastrados() {

        List<Aluno> alunos = gerenciador.listarAlunos();

        assertTrue(alunos.isEmpty());
    }


    @Test
    void deveListarTodosOsAlunosCadastrados() {

        gerenciador.cadastrarAluno(new Aluno("2024001", "Ana Souza"));
        gerenciador.cadastrarAluno(new Aluno("2024002", "Bruno Lima"));
        gerenciador.cadastrarAluno(new Aluno("2024003", "Carla Mendes"));

        List<Aluno> alunos = gerenciador.listarAlunos();

        assertEquals(3, alunos.size());
    }


    @Test
    void deveListarAlunosNaOrdemDeCadastro() {

        Aluno ana = new Aluno("2024001", "Ana Souza");
        Aluno bruno = new Aluno("2024002", "Bruno Lima");
        Aluno carla = new Aluno("2024003", "Carla Mendes");

        gerenciador.cadastrarAluno(ana);
        gerenciador.cadastrarAluno(bruno);
        gerenciador.cadastrarAluno(carla);

        List<Aluno> alunos = gerenciador.listarAlunos();

        assertEquals(List.of(ana, bruno, carla), alunos);
    }


    @Test
    void naoDevePermitirAlterarAListaRetornada() {

        gerenciador.cadastrarAluno(new Aluno("2024001", "Ana Souza"));

        List<Aluno> alunos = gerenciador.listarAlunos();

        assertThrows(UnsupportedOperationException.class,
                () -> alunos.add(new Aluno("2024099", "Intruso")));

        assertEquals(1, gerenciador.listarAlunos().size());
    }
}
