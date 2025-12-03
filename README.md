HP App - (Harry Potter API)

O app deve consumir a HP-API (https://hp-api.onrender.com/)

utilizando corrotinas e web services. Ele será composto por uma Activity Principal e outras telas para listar informações dos personagens.

📌 Funcionalidades obrigatórias
1. Activity Principal (Dashboard)

-Tela inicial com botões que levam às seguintes ações:

-Buscar personagem por ID

-Listar professores (Hogwarts Staff)

-Listar estudantes por casa

-Sair do aplicativo

Os botões devem estar organizados de forma equilibrada na interface.

📌 2. Buscar personagem específico

-Usuário informa um ID.

-O app consulta o endpoint 'Specific Character by ID'.

-Mostra em um TextView: nome e casa/escola

📌 3. Listar professores

-Usa o endpoint 'Hogwarts Staff'.

-Mostra, em um TextView, todos os nomes dos professores.

📌 4. Listar estudantes de uma casa

-Usuário escolhe uma casa através de RadioButtons (apenas 1 pode ser selecionada).

-Usa o endpoint 'Characters in a House'.

-Apresenta, em um TextView, todos os nomes dos alunos da casa escolhida.

📌 5. Sair

Fecha o aplicativo.
