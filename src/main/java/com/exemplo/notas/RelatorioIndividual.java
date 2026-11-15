package com.exemplo.notas;

import java.util.Map;

public record RelatorioIndividual(String nome,
                                  Map<String, Double> notas,
                                  double media,
                                  String situacao) {
}