package com.exemplo.notas;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SistemaDesempenhoTest {

    private final Sistema sistema = new Sistema();

    private void cadastrar(String nome, double... valores) {
        Aluno aluno = sistema.cadastrarAluno(nome, nome);
        for (double valor : valores) {
            aluno.adicionarNota(new Nota("Matéria", valor));
        }
    }

    private void cadastrar(String nome, String matricula, Nota... notas) {
        Aluno aluno = sistema.cadastrarAluno(nome, matricula);
        for (Nota nota : notas) {
            aluno.adicionarNota(nota);
        }
    }

    @Test
    void deveClassificarPorMediaDecrescente() {
        cadastrar("Ana", 8, 6);
        cadastrar("Bruno", 9, 10);

        List<ItemClassificacao> resultado = sistema.classificarPorMedia();

        assertEquals(2, resultado.size());
        assertEquals("Bruno", resultado.get(0).nome());
        assertEquals(1, resultado.get(0).posicao());
        assertEquals(9.5, resultado.get(0).media(), 0.001);
        assertEquals("Ana", resultado.get(1).nome());
        assertEquals(2, resultado.get(1).posicao());
        assertEquals(7.0, resultado.get(1).media(), 0.001);
    }

    @Test
    void semAlunosCadastradosLancaExcecaoComMensagem() {
        IllegalArgumentException erro = assertThrows(
                IllegalArgumentException.class,
                () -> sistema.classificarPorMedia());

        assertEquals("Não há alunos cadastrados.", erro.getMessage());
    }

    private void cadastrar(String nome, Nota... notas) {
        Aluno aluno = sistema.cadastrarAluno(nome, nome);
        for (Nota nota : notas) {
            aluno.adicionarNota(nota);
        }
    }

    @Test
    void geraRelatorioDoAlunoComNotasMediaESituacao() {
        cadastrar("Ana Souza", "2024001", new Nota("Matemática", 8), new Nota("Português", 6));

        RelatorioIndividual ana = sistema.gerarRelatorio("2024001");

        assertEquals("Ana Souza", ana.nome());
        assertEquals(8.0, ana.notas().get("Matemática"), 0.001);
        assertEquals(6.0, ana.notas().get("Português"), 0.001);
        assertEquals(7.0, ana.media(), 0.001);
        assertEquals("Aprovado", ana.situacao());
    }

    @Test
    void geraRelatorioDoAlunoPedidoPelaMatricula() {
        cadastrar("Ana Souza", "2024001", new Nota("Matemática", 8), new Nota("Português", 6));
        cadastrar("Bruno Lima", "2024002", new Nota("Matemática", 9), new Nota("Português", 10));

        RelatorioIndividual bruno = sistema.gerarRelatorio("2024002");

        assertEquals("Bruno Lima", bruno.nome());
        assertEquals(9.5, bruno.media(), 0.001);
        assertEquals("Aprovado", bruno.situacao());
    }

    @Test
    void alunoComMediaAbaixoDeSeteFicaReprovado() {
        cadastrar("Carla Dias", "2024003", new Nota("Matemática", 5), new Nota("Português", 6));

        RelatorioIndividual carla = sistema.gerarRelatorio("2024003");

        assertEquals(5.5, carla.media(), 0.001);
        assertEquals("Reprovado", carla.situacao());
    }

    @Test
    void matriculaInexistenteLancaExcecaoComMensagem() {
        cadastrar("Ana Souza", "2024001", new Nota("Matemática", 8));

        IllegalArgumentException erro = assertThrows(
                IllegalArgumentException.class,
                () -> sistema.gerarRelatorio("9999"));

        assertEquals("Aluno não encontrado.", erro.getMessage());
    }


}