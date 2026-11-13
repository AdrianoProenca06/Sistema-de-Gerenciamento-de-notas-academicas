import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AvaliacaoAcademicaTest {

    @Test
    void deveCalcularMediaDeDuasNotas() {

        AvaliacaoAcademica avaliacao =
                new AvaliacaoAcademica();

        List<Double> notas = List.of(8.0, 6.0);

        double media = avaliacao.calcularMedia(notas);

        assertEquals(7.0, media, 0.001);
    }


    @Test
    void deveCalcularMediaDeTresNotas() {

        AvaliacaoAcademica avaliacao =
                new AvaliacaoAcademica();

        List<Double> notas = List.of(7.0, 8.0, 9.0);

        double media = avaliacao.calcularMedia(notas);

        assertEquals(8.0, media, 0.001);
    }


    @Test
    void naoDeveCalcularMediaSemNotas() {

        AvaliacaoAcademica avaliacao =
                new AvaliacaoAcademica();

        List<Double> notas = List.of();

        assertThrows(IllegalArgumentException.class, () -> avaliacao.calcularMedia(notas));
    }


    @Test
    void deveAprovarAlunoComMediaOito() {

        AvaliacaoAcademica avaliacao =
                new AvaliacaoAcademica();

        double media = 8.0;

        String situacao = avaliacao.verificarSituacao(media);

        assertEquals("Aprovado", situacao);
    }


    @Test
    void deveReprovarAlunoComMediaCinco() {

        AvaliacaoAcademica avaliacao =
                new AvaliacaoAcademica();

        double media = 5.0;

        String situacao = avaliacao.verificarSituacao(media);

        assertEquals("Reprovado", situacao);
    }

    @Test
    void deveAprovarAlunoComMediaSete() {

        AvaliacaoAcademica avaliacao =
                new AvaliacaoAcademica();

        double media = 7.0;

        String situacao = avaliacao.verificarSituacao(media);

        assertEquals("Aprovado", situacao);
    }

    @Test
    void naoDeveAceitarNotaMaiorQueDez() {

        AvaliacaoAcademica avaliacao =
                new AvaliacaoAcademica();

        List<Double> notas = List.of(8.0, 11.0);

        assertThrows(IllegalArgumentException.class,
                () -> avaliacao.calcularMedia(notas));
    }


    @Test
    void naoDeveAceitarNotaNegativa() {

        AvaliacaoAcademica avaliacao =
                new AvaliacaoAcademica();

        List<Double> notas = List.of(8.0, -2.0);

        assertThrows(IllegalArgumentException.class,
                () -> avaliacao.calcularMedia(notas));
    }






}
