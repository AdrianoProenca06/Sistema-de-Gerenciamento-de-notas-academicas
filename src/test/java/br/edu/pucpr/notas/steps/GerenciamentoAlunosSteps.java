package br.edu.pucpr.notas.steps;

import br.edu.pucpr.notas.Aluno;
import br.edu.pucpr.notas.GerenciadorAlunos;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GerenciamentoAlunosSteps {

    private GerenciadorAlunos gerenciador = new GerenciadorAlunos();
    private List<Aluno> alunosListados;
    private IllegalArgumentException erro;


    // ---------- Dado ----------

    @Dado("que não há alunos cadastrados")
    public void queNaoHaAlunosCadastrados() {
        gerenciador = new GerenciadorAlunos();
    }

    @Dado("que os seguintes alunos estão cadastrados:")
    public void queOsSeguintesAlunosEstaoCadastrados(DataTable tabela) {
        for (Map<String, String> linha : tabela.asMaps()) {
            gerenciador.cadastrarAluno(
                    new Aluno(linha.get("matricula"), linha.get("nome")));
        }
    }

    @Dado("o aluno de matrícula {string} e nome {string} é cadastrado")
    public void oAlunoECadastrado(String matricula, String nome) {
        gerenciador.cadastrarAluno(new Aluno(matricula, nome));
    }

    @Dado("que o aluno de matrícula {string} já foi excluído")
    public void queOAlunoJaFoiExcluido(String matricula) {
        gerenciador.excluirAluno(matricula);
    }


    // ---------- Quando ----------

    @Quando("eu listar os alunos")
    public void euListarOsAlunos() {
        alunosListados = gerenciador.listarAlunos();
    }

    @Quando("eu excluir o aluno de matrícula {string}")
    public void euExcluirOAluno(String matricula) {
        try {
            gerenciador.excluirAluno(matricula);
            erro = null;
        } catch (IllegalArgumentException e) {
            erro = e;
        }
    }


    // ---------- Então ----------

    @Então("a lista de alunos deve estar vazia")
    public void aListaDeAlunosDeveEstarVazia() {
        assertTrue(alunosListados.isEmpty());
    }

    @Então("a lista deve conter {int} alunos")
    public void aListaDeveConter(int quantidade) {
        assertEquals(quantidade, gerenciador.listarAlunos().size());
    }

    @Então("os alunos devem aparecer na seguinte ordem:")
    public void osAlunosDevemAparecerNaOrdem(DataTable tabela) {
        List<String> nomesEsperados = tabela.asMaps().stream()
                .map(linha -> linha.get("nome"))
                .toList();

        List<String> nomesListados = alunosListados.stream()
                .map(Aluno::getNome)
                .toList();

        assertEquals(nomesEsperados, nomesListados);
    }

    @Então("o aluno {string} deve aparecer na lista")
    public void oAlunoDeveAparecerNaLista(String nome) {
        assertTrue(alunosListados.stream()
                .anyMatch(aluno -> aluno.getNome().equals(nome)));
    }

    @Então("o aluno de matrícula {string} não deve aparecer na lista")
    public void oAlunoNaoDeveAparecerNaLista(String matricula) {
        assertNull(erro, "A exclusão não deveria ter gerado erro.");
        assertTrue(gerenciador.listarAlunos().stream()
                .noneMatch(aluno -> aluno.getMatricula().equals(matricula)));
    }

    @Então("o sistema deve exibir a mensagem {string}")
    public void oSistemaDeveExibirAMensagem(String mensagem) {
        assertNotNull(erro, "Era esperado um erro, mas a operação foi concluída.");
        assertEquals(mensagem, erro.getMessage());
    }
}
