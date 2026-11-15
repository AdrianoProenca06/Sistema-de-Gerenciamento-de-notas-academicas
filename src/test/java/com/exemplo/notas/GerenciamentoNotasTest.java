package com.exemplo.notas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GerenciamentoNotasTest {

    @Test
    void deveRegistrarNotaValida() {
        GerenciamentoNotas gerenciamento = new GerenciamentoNotas();
        Aluno aluno = new Aluno("Bernardo", "2026001");

        gerenciamento.registrarNota(aluno, "Matemática", 8.0);

        assertEquals(8.0, gerenciamento.buscarNota(aluno, "Matemática").getValor(), 0.001);
    }

    @Test
    void deveAceitarNotasNosLimitesZeroEDez() {
        GerenciamentoNotas gerenciamento = new GerenciamentoNotas();
        Aluno aluno = new Aluno("Bernardo", "2026001");

        gerenciamento.registrarNota(aluno, "Matemática", 0.0);
        gerenciamento.registrarNota(aluno, "Português", 10.0);

        assertEquals(0.0, gerenciamento.buscarNota(aluno, "Matemática").getValor(), 0.001);
        assertEquals(10.0, gerenciamento.buscarNota(aluno, "Português").getValor(), 0.001);
    }

    @Test
    void naoDeveRegistrarNotaMaiorQueDez() {
        GerenciamentoNotas gerenciamento = new GerenciamentoNotas();
        Aluno aluno = new Aluno("Bernardo", "2026001");

        assertThrows(IllegalArgumentException.class,
                () -> gerenciamento.registrarNota(aluno, "Matemática", 10.1));

        assertNull(gerenciamento.buscarNota(aluno, "Matemática"));
    }

    @Test
    void naoDeveRegistrarNotaNegativa() {
        GerenciamentoNotas gerenciamento = new GerenciamentoNotas();
        Aluno aluno = new Aluno("Bernardo", "2026001");

        assertThrows(IllegalArgumentException.class,
                () -> gerenciamento.registrarNota(aluno, "Matemática", -0.1));
    }

    @Test
    void deveAlterarNotaComValorValido() {
        GerenciamentoNotas gerenciamento = new GerenciamentoNotas();
        Aluno aluno = new Aluno("Bernardo", "2026001");
        gerenciamento.registrarNota(aluno, "Matemática", 6.0);

        gerenciamento.alterarNota(aluno, "Matemática", 8.5);

        assertEquals(8.5, gerenciamento.buscarNota(aluno, "Matemática").getValor(), 0.001);
    }

    @Test
    void naoDeveAlterarNotaParaValorInvalidoEDeveManterNotaAnterior() {
        GerenciamentoNotas gerenciamento = new GerenciamentoNotas();
        Aluno aluno = new Aluno("Bernardo", "2026001");
        gerenciamento.registrarNota(aluno, "Matemática", 6.0);

        assertThrows(IllegalArgumentException.class,
                () -> gerenciamento.alterarNota(aluno, "Matemática", 11.0));

        assertEquals(6.0, gerenciamento.buscarNota(aluno, "Matemática").getValor(), 0.001);
    }

    @Test
    void naoDeveAlterarNotaNaoRegistrada() {
        GerenciamentoNotas gerenciamento = new GerenciamentoNotas();
        Aluno aluno = new Aluno("Bernardo", "2026001");

        assertThrows(IllegalArgumentException.class,
                () -> gerenciamento.alterarNota(aluno, "Matemática", 7.0));
    }
}
