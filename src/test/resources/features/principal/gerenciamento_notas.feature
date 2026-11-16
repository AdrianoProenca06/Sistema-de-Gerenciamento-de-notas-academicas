# language: pt
Funcionalidade: Registro e alteração de notas

  Cenário: Registrar nota válida entre 0 e 10
    Dado que o aluno não possui nota na matéria "Matemática"
    Quando registro a nota "8.0" na matéria "Matemática"
    Então a nota do aluno na matéria "Matemática" deve ser "8.0"

  Cenário: Recusar registro de nota fora do intervalo
    Dado que o aluno não possui nota na matéria "Matemática"
    Quando tento registrar a nota "11" na matéria "Matemática"
    Então o sistema deve informar o erro de nota fora do intervalo
    E o aluno não deve possuir nota na matéria "Matemática"

  Cenário: Alterar nota registrada com valor válido
    Dado que o aluno possui a nota "6.0" na matéria "Matemática"
    Quando altero a nota da matéria "Matemática" para "8.5"
    Então a nota do aluno na matéria "Matemática" deve ser "8.5"

  Cenário: Recusar alteração de nota com valor inválido
    Dado que o aluno possui a nota "6.0" na matéria "Matemática"
    Quando tento alterar a nota da matéria "Matemática" para "11"
    Então o sistema deve informar o erro de nota fora do intervalo
    E a nota do aluno na matéria "Matemática" deve continuar "6.0"
