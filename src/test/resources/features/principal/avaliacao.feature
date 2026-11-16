# language: pt
Funcionalidade: Avaliação acadêmica

  Cenário: Calcular a média aritmética
    Dado as notas "8; 6"
    Quando calculo a média
    Então a média deve ser 7

  Cenário: Média sem notas
    Dado as notas ""
    Quando calculo a média
    Então o sistema deve informar o erro "O aluno não possui notas cadastradas."

  Cenário: Nota fora do intervalo
    Dado as notas "8; 11"
    Quando calculo a média
    Então o sistema deve informar o erro "A nota deve estar entre 0 e 10."

  Esquema do Cenário: Situação conforme a média
    Dado as notas "<notas>"
    Quando calculo a média
    E verifico a situação
    Então a situação deve ser "<situação>"

    Exemplos:
      | notas  | situação  |
      | 8; 6   | Aprovado  |
      | 6; 6,5 | Reprovado |
      | 5      | Reprovado |
      | 10; 10 | Aprovado  |