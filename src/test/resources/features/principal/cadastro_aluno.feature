# language: pt
Funcionalidade: Cadastro e busca de alunos

  Cenário: Cadastrar aluno com nome e matrícula
    Dado que o cadastro de alunos está vazio
    Quando cadastro o aluno "Matheus Alberti" com matrícula "2026001"
    Então o aluno cadastrado deve ter nome "Matheus Alberti" e matrícula "2026001"

  Cenário: Não permitir matrícula duplicada
    Dado que o aluno "Matheus Alberti" com matrícula "2026001" já está cadastrado
    Quando cadastro o aluno "Bruno Lima" com matrícula "2026001"
    Então o sistema deve informar o erro de matrícula duplicada

  Cenário: Buscar aluno pelo nome
    Dado que o aluno "Matheus Alberti" com matrícula "2026001" já está cadastrado
    Quando busco alunos pelo nome "Matheus Alberti"
    Então devo encontrar 1 aluno com nome "Matheus Alberti" e matrícula "2026001"

  Cenário: Buscar aluno pela matrícula
    Dado que o aluno "Matheus Alberti" com matrícula "2026001" já está cadastrado
    Quando busco o aluno pela matrícula "2026001"
    Então devo encontrar o aluno "Matheus Alberti" com matrícula "2026001"

  Cenário: Buscar matrícula inexistente
    Dado que o cadastro de alunos está vazio
    Quando busco o aluno pela matrícula "9999999"
    Então não devo encontrar o aluno pela matrícula
