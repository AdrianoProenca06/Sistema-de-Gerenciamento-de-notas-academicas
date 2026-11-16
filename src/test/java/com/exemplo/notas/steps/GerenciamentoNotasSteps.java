package com.exemplo.notas.steps;

import com.exemplo.notas.Aluno;
import com.exemplo.notas.GerenciamentoNotas;
import com.exemplo.notas.Nota;
import io.cucumber.java.pt.*;

import static org.junit.jupiter.api.Assertions.*;

public class GerenciamentoNotasSteps {

    private final GerenciamentoNotas gerenciamento = new GerenciamentoNotas();
    private final Aluno aluno = new Aluno("Ana", "2026001");
    private Exception erro;

    @Dado("que o aluno não possui nota na matéria {string}")
    public void queOAlunoNaoPossuiNotaNaMateria(String materia) {
        assertNull(gerenciamento.buscarNota(aluno, materia));
    }

    @Dado("que o aluno possui a nota {string} na matéria {string}")
    public void queOAlunoPossuiANotaNaMateria(String nota, String materia) {
        gerenciamento.registrarNota(aluno, materia, Double.parseDouble(nota));
    }

    @Quando("registro a nota {string} na matéria {string}")
    public void registroANotaNaMateria(String nota, String materia) {
        gerenciamento.registrarNota(aluno, materia, Double.parseDouble(nota));
    }

    @Quando("tento registrar a nota {string} na matéria {string}")
    public void tentoRegistrarANotaNaMateria(String nota, String materia) {
        try {
            gerenciamento.registrarNota(aluno, materia, Double.parseDouble(nota));
        } catch (IllegalArgumentException e) {
            erro = e;
        }
    }

    @Quando("altero a nota da matéria {string} para {string}")
    public void alteroANotaDaMateriaPara(String materia, String novaNota) {
        gerenciamento.alterarNota(aluno, materia, Double.parseDouble(novaNota));
    }

    @Quando("tento alterar a nota da matéria {string} para {string}")
    public void tentoAlterarANotaDaMateriaPara(String materia, String novaNota) {
        try {
            gerenciamento.alterarNota(aluno, materia, Double.parseDouble(novaNota));
        } catch (IllegalArgumentException e) {
            erro = e;
        }
    }

    @Então("a nota do aluno na matéria {string} deve ser {string}")
    public void aNotaDoAlunoNaMateriaDeveSer(String materia, String esperada) {
        Nota nota = gerenciamento.buscarNota(aluno, materia);
        assertNotNull(nota);
        assertEquals(Double.parseDouble(esperada), nota.getValor(), 0.001);
    }

    @Então("o sistema deve informar o erro de nota fora do intervalo")
    public void oSistemaDeveInformarErroDeNotaForaDoIntervalo() {
        assertNotNull(erro, "Nenhum erro foi lançado");
        assertEquals("A nota deve estar entre 0 e 10.", erro.getMessage());
    }

    @E("o aluno não deve possuir nota na matéria {string}")
    public void oAlunoNaoDevePossuirNotaNaMateria(String materia) {
        assertNull(gerenciamento.buscarNota(aluno, materia));
    }

    @E("a nota do aluno na matéria {string} deve continuar {string}")
    public void aNotaDoAlunoNaMateriaDeveContinuar(String materia, String esperada) {
        aNotaDoAlunoNaMateriaDeveSer(materia, esperada);
    }
}
