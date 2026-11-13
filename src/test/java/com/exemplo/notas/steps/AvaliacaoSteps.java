package com.exemplo.notas.steps;

import com.exemplo.notas.AvaliacaoAcademica;
import io.cucumber.java.pt.*;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AvaliacaoSteps {

    private final AvaliacaoAcademica avaliacao = new AvaliacaoAcademica();

    private List<Double> notas = List.of();
    private double media;
    private String situacao;
    private Exception erro;

    @Dado("as notas {string}")
    public void asNotas(String texto) {
        notas = converter(texto);
    }

    @Quando("calculo a média")
    public void calculoAMedia() {
        try {
            media = avaliacao.calcularMedia(notas);
        } catch (IllegalArgumentException e) {
            erro = e;
        }
    }

    @Quando("verifico a situação")
    public void verificoASituacao() {
        situacao = avaliacao.verificarSituacao(media);
    }

    @Então("a média deve ser {double}")
    public void aMediaDeveSer(double esperada) {
        assertEquals(esperada, media, 0.001);
    }

    @Então("a situação deve ser {string}")
    public void aSituacaoDeveSer(String esperada) {
        assertEquals(esperada, situacao);
    }

    @Então("o sistema deve informar o erro {string}")
    public void erroInformado(String mensagem) {
        assertNotNull(erro, "Nenhum erro foi lançado");
        assertEquals(mensagem, erro.getMessage());
    }

    private static List<Double> converter(String texto) {
        if (texto == null || texto.isBlank()) return List.of();
        return Arrays.stream(texto.split(";"))
                .map(String::trim)
                .map(s -> Double.parseDouble(s.replace(',', '.')))
                .toList();
    }
}
