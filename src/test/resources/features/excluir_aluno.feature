# language: pt
Funcionalidade: Excluir aluno do sistema
  Como professor
  Quero excluir alunos do sistema
  Para manter o cadastro de alunos atualizado

  Contexto:
    Dado que os seguintes alunos estão cadastrados:
      | matricula | nome         |
      | 2024001   | Ana Souza    |
      | 2024002   | Bruno Lima   |
      | 2024003   | Carla Mendes |

  Cenário: Excluir um aluno cadastrado
    Quando eu excluir o aluno de matrícula "2024002"
    Então o aluno de matrícula "2024002" não deve aparecer na lista
    E a lista deve conter 2 alunos

  Cenário: Tentar excluir um aluno que não existe
    Quando eu excluir o aluno de matrícula "9999999"
    Então o sistema deve exibir a mensagem "Aluno não encontrado."
    E a lista deve conter 3 alunos

  Cenário: Tentar excluir sem informar a matrícula
    Quando eu excluir o aluno de matrícula ""
    Então o sistema deve exibir a mensagem "A matrícula deve ser informada."
    E a lista deve conter 3 alunos

  Cenário: Tentar excluir um aluno que já foi excluído
    Dado que o aluno de matrícula "2024001" já foi excluído
    Quando eu excluir o aluno de matrícula "2024001"
    Então o sistema deve exibir a mensagem "Aluno não encontrado."
    E a lista deve conter 2 alunos
