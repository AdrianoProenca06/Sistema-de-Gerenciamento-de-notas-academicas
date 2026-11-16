# language: pt
Funcionalidade: Listar alunos cadastrados
  Como professor
  Quero listar todos os alunos cadastrados
  Para consultar quem está registrado no sistema

  Cenário: Listar alunos quando não há nenhum cadastrado
    Dado que não há alunos cadastrados
    Quando eu listar os alunos
    Então a lista de alunos deve estar vazia

  Cenário: Listar todos os alunos cadastrados na ordem de cadastro
    Dado que os seguintes alunos estão cadastrados:
      | matricula | nome         |
      | 2024001   | Ana Souza    |
      | 2024002   | Bruno Lima   |
      | 2024003   | Carla Mendes |
    Quando eu listar os alunos
    Então a lista deve conter 3 alunos
    E os alunos devem aparecer na seguinte ordem:
      | nome         |
      | Ana Souza    |
      | Bruno Lima   |
      | Carla Mendes |

  Cenário: Listagem atualizada após um novo cadastro
    Dado que os seguintes alunos estão cadastrados:
      | matricula | nome       |
      | 2024001   | Ana Souza  |
      | 2024002   | Bruno Lima |
    E o aluno de matrícula "2024003" e nome "Carla Mendes" é cadastrado
    Quando eu listar os alunos
    Então a lista deve conter 3 alunos
    E o aluno "Carla Mendes" deve aparecer na lista
