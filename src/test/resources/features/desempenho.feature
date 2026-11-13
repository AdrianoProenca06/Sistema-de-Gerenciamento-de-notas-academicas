# language: pt
Funcionalidade: Desempenho acadêmico

  Cenário: Classificar alunos pela média em ordem decrescente
    Dado os seguintes alunos com notas:
      | nome       | matrícula | notas |
      | Ana Souza  | 2024001   | 8; 6  |
      | Bruno Lima | 2024002   | 9; 10 |
      | Carla Dias | 2024003   | 5; 6  |
    Quando classifico os alunos pela média
    Então a classificação deve ser:
      | posição | nome       | média |
      | 1       | Bruno Lima | 9,5   |
      | 2       | Ana Souza  | 7     |
      | 3       | Carla Dias | 5,5   |