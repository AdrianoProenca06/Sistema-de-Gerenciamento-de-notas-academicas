# language: pt
Funcionalidade: Cálculo de médias e situação acadêmica
  Como responsável pelas notas acadêmicas
  Quero calcular médias e verificar a aprovação
  Para obter resultados corretos e validar entradas inválidas

  Esquema do Cenário: Calcular média das notas
    Dado que o aluno possui as notas "<notas>"
    Quando calculo a média das notas
    Então a média calculada deve ser <media>

    Exemplos:
      | notas   | media |
      | 8,6     | 7.0   |
      | 7,8,9   | 8.0   |

  Cenário: Não calcular média sem notas
    Dado que o aluno não possui notas cadastradas
    Quando tento calcular a média das notas
    Então deve ocorrer um erro informando "O aluno não possui notas cadastradas."

  Esquema do Cenário: Verificar situação acadêmica
    Dado que a média do aluno é <media>
    Quando verifico a situação acadêmica
    Então a situação deve ser "<situacao>"

    Exemplos:
      | media | situacao  |
      | 8.0   | Aprovado  |
      | 5.0   | Reprovado |
      | 7.0   | Aprovado  |

  Esquema do Cenário: Rejeitar notas fora do intervalo permitido
    Dado que o aluno possui as notas "<notas>"
    Quando tento calcular a média das notas
    Então deve ocorrer um erro informando "A nota deve estar entre 0 e 10."

    Exemplos:
      | notas  |
      | 8,11   |
      | 8,-2   |
