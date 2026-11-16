# language: pt
Funcionalidade: Desempenho acadêmico

  Cenário: Classificar alunos pela média em ordem decrescente
    Dado os seguintes alunos com notas:
      | nome       | matrícula | matéria    | nota |
      | Ana Souza  | 2024001   | Matemática | 8    |
      | Ana Souza  | 2024001   | Português  | 6    |
      | Bruno Lima | 2024002   | Matemática | 9    |
      | Bruno Lima | 2024002   | Português  | 10   |
      | Carla Dias | 2024003   | Matemática | 5    |
      | Carla Dias | 2024003   | Português  | 6    |
    Quando classifico os alunos pela média
    Então a classificação deve ser:
      | posição | nome       | média |
      | 1       | Bruno Lima | 9,5   |
      | 2       | Ana Souza  | 7     |
      | 3       | Carla Dias | 5,5   |

  Cenario: Classificar sem alunos cadastrados
    Dado nenhum aluno cadastrado
    Quando tento classificar os alunos pela média
    Então devo ser informado que não há alunos cadastrados

  Cenário: Gerar relatório individual com notas, média e situação acadêmica
    Dado os seguintes alunos com notas:
      | nome       | matrícula | matéria    | nota |
      | Ana Souza  | 2024001   | Matemática | 8    |
      | Ana Souza  | 2024001   | Português  | 6    |
      | Bruno Lima | 2024002   | Matemática | 9    |
      | Bruno Lima | 2024002   | Português  | 10   |
      | Carla Dias | 2024003   | Matemática | 5    |
      | Carla Dias | 2024003   | Português  | 6    |
    Quando gero o relatório individual do aluno de matrícula "2024001"
    Então o relatório deve ser:
      | nome      | matemática | português | média | situação |
      | Ana Souza | 8          | 6         | 7.0   | Aprovado |

  Cenário: Gerar relatório de aluno inexistente
    Dado os seguintes alunos com notas:
      | nome      | matrícula | matéria    | nota |
      | Ana Souza | 2024001   | Matemática | 8    |
    Quando gero o relatório individual do aluno de matrícula "9999"
    Então devo ser informado que "Aluno não encontrado."
