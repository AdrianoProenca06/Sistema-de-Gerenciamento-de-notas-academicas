import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AvaliacaoAcademicaSteps {

    private final AvaliacaoAcademica avaliacao = new AvaliacaoAcademica();
    private List<Double> notas;
    private double media;
    private String situacao;
    private IllegalArgumentException erro;

    @Dado("que o aluno possui as notas {string}")
    public void alunoPossuiNotas(String valores) {
        notas = new ArrayList<>();
        for (String valor : valores.split(",")) {
            notas.add(Double.parseDouble(valor.trim()));
        }
    }

    @Dado("que o aluno não possui notas cadastradas")
    public void alunoSemNotas() {
        notas = List.of();
    }

    @Dado("que a média do aluno é {string}")
    public void mediaDoAluno(String valor) {
        this.media = Double.parseDouble(valor);
    }

    @Quando("calculo a média das notas")
    public void calcularMedia() {
        media = avaliacao.calcularMedia(notas);
    }

    @Quando("tento calcular a média das notas")
    public void tentarCalcularMedia() {
        erro = assertThrows(
                IllegalArgumentException.class,
                () -> avaliacao.calcularMedia(notas)
        );
    }

    @Quando("verifico a situação acadêmica")
    public void verificarSituacao() {
        situacao = avaliacao.verificarSituacao(media);
    }

    @Então("a média calculada deve ser {string}")
    public void conferirMedia(String mediaEsperada) {
        assertEquals(
                Double.parseDouble(mediaEsperada),
                media,
                0.001
        );
    }

    @Então("a situação deve ser {string}")
    public void conferirSituacao(String esperado) {
        assertEquals(esperado, situacao);
    }

    @Então("deve ocorrer um erro informando {string}")
    public void conferirErro(String mensagem) {
        assertNotNull(erro);
        assertEquals(mensagem, erro.getMessage());
    }
}
