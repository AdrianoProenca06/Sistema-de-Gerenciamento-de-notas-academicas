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
}