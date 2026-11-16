package com.exemplo.notas.steps;

import com.exemplo.notas.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DesempenhoSteps {
    private final Sistema sistema = new Sistema();
    private List<ItemClassificacao> classificacao;
    private Exception erro;
    private static final Set<String> COLUNAS_FIXAS = Set.of("nome", "média", "situação");

    private RelatorioIndividual relatorio;

    @Dado("os seguintes alunos com notas:")
    public void alunosComNotas(DataTable tabela) {
        Map<String, Aluno> porMatricula = new LinkedHashMap<>();
        for (Map<String, String> linha : tabela.asMaps()) {
            String matricula = linha.get("matrícula");
            Aluno aluno = porMatricula.get(matricula);
            if (aluno == null) {
                aluno = sistema.cadastrarAluno(linha.get("nome"), matricula);
                porMatricula.put(matricula, aluno);
            }
            aluno.adicionarNota(new Nota(linha.get("matéria"), converter(linha.get("nota"))));
        }
    }

    @Quando("classifico os alunos pela média")
    public void classificoOsAlunosPelaMedia() {
        classificacao = sistema.classificarPorMedia();
    }

    @Então("a classificação deve ser:")
    public void aClassificacaoDeveSer(DataTable tabela) {
        List<Map<String, String>> esperado = tabela.asMaps();
        assertEquals(esperado.size(), classificacao.size());
        for (int i = 0; i < esperado.size(); i++) {
            ItemClassificacao item = classificacao.get(i);
            assertEquals(Integer.parseInt(esperado.get(i).get("posição")), item.posicao());
            assertEquals(esperado.get(i).get("nome"), item.nome());
            assertEquals(converter(esperado.get(i).get("média")), item.media(), 0.001);
        }
    }

    @Dado("nenhum aluno cadastrado")
    public void nenhumAlunoCadastrado() {

    }

    @Quando("tento classificar os alunos pela média")
    public void tentoClassificarOsAlunosPelaMedia() {
        try {
            classificacao = sistema.classificarPorMedia();
        } catch (IllegalArgumentException e) {
            erro = e;
        }
    }

    @Então("devo ser informado que não há alunos cadastrados")
    public void devoSerInformadoQueNaoHaAlunos() {
        assertNotNull(erro, "Nenhum erro foi lançado");
        assertEquals("Não há alunos cadastrados.", erro.getMessage());
    }

    @Quando("gero o relatório individual do aluno de matrícula {string}")
    public void geroORelatorioIndividualDoAluno(String matricula) {
        try {
            relatorio = sistema.gerarRelatorio(matricula);
        } catch (IllegalArgumentException e) {
            erro = e;
        }
    }

    @Então("o relatório deve ser:")
    public void oRelatorioDeveSer(DataTable tabela) {
        assertNotNull(relatorio, "Nenhum relatório foi gerado");
        Map<String, String> esperado = tabela.asMaps().get(0);

        assertEquals(esperado.get("nome"), relatorio.nome());
        assertEquals(converter(esperado.get("média")), relatorio.media(), 0.001);
        assertEquals(esperado.get("situação"), relatorio.situacao());

        for (Map.Entry<String, String> coluna : esperado.entrySet()) {
            if (COLUNAS_FIXAS.contains(coluna.getKey())) {
                continue;
            }
            Double nota = notaDaMateria(relatorio, coluna.getKey());
            assertNotNull(nota, "O relatório não tem a matéria " + coluna.getKey());
            assertEquals(converter(coluna.getValue()), nota, 0.001);
        }
    }

    @Então("devo ser informado que {string}")
    public void devoSerInformadoQue(String mensagem) {
        assertNotNull(erro, "Nenhum erro foi lançado");
        assertEquals(mensagem, erro.getMessage());
    }

    private static Double notaDaMateria(RelatorioIndividual relatorio, String materia) {
        for (Map.Entry<String, Double> entrada : relatorio.notas().entrySet()) {
            if (entrada.getKey().equalsIgnoreCase(materia)) {
                return entrada.getValue();
            }
        }
        return null;
    }



    private static double converter(String texto) {
        return Double.parseDouble(texto.trim().replace(',', '.'));
    }
}
