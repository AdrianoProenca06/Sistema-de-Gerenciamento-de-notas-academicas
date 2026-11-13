import java.util.List;

public class AvaliacaoAcademica {

    public double calcularMedia(List<Double> notas) {

        if (notas == null || notas.isEmpty()) {
            throw new IllegalArgumentException(
                    "O aluno não possui notas cadastradas.");
        }

        double somaDasNotas = 0;

        for (Double nota : notas) {

            if (nota == null || nota < 0 || nota > 10) {
                throw new IllegalArgumentException(
                        "A nota deve estar entre 0 e 10.");
            }

            somaDasNotas += nota;
        }

        return somaDasNotas / notas.size();
    }

    public String verificarSituacao(double media) {
        return media >= 7.0 ? "Aprovado" : "Reprovado";
    }
}
