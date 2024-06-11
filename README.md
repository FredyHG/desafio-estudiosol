# Desafio Estúdio Sol

Projeto desenvolvido por FredyHG [GitHub](https://github.com/FredyHG)

## Problema

Joaquim é um jovem estudante que deseja ajudar seus colegas de classe a resolver um problema de contagem de pontuações em jogos de futebol americano. Ele pretende construir uma API web que resolva o problema passado pela professora e retorne o resultado para seus colegas utilizarem. A sua tarefa será ajudar Joaquim nessa missão.

Dado um resultado de placar de jogo de futebol americano, Joaquim precisa calcular o maior número de combinações de pontuações possíveis para o resultado daquela partida. A ordem das pontuações não importa, apenas os tipos de pontuações diferentes de cada time. Os pontos podem ser marcados da seguinte forma:
- **Touchdown**: 6 pontos
- **Extra touchdown**: 0, 1 ou 2 pontos (só pode ser marcado após um touchdown)
- **Field goal**: 3 pontos

Por exemplo, para o placar de 3 x 15, há quatro combinações possíveis:
- O time com 3 pontos só pode ter marcado através de um field goal.
- O time com 15 pontos pode ter feito 4 tipos diferentes de jogadas:
  - Um touchdown seguido de outro touchdown e um field goal
  - Um touchdown seguido de três field goals
  - Cinco field goals
  - Um touchdown seguido de um extra touchdown (1 ponto) e outro touchdown seguido de um extra touchdown (2 pontos)

### Exemplo
- **Placar**: 3 x 15
- **Combinações possíveis**: 4

Um exemplo de resultado não possível seria 8 x 5, nesse caso é impossível um time marcar 5 pontos.

### Exemplo
- **Placar**: 8 x 5
- **Combinações possíveis**: 0

## Solução

Para resolver esse problema, utilizei Java e Spring Web. A lógica de solução foi implementada usando recursão e o algoritmo de busca em profundidade (Depth-First Search - DFS) com retrocesso (backtracking).

### Tecnologias Utilizadas
- **Java**
- **Spring Web**
- **Swagger** para documentação da API
- **JUnit 5** para testes unitários

### Algoritmo
O algoritmo utilizado é uma variante de busca em profundidade (DFS) com retrocesso (backtracking). Ele explora todas as combinações possíveis de pontuações para um placar alvo, garantindo que todas as combinações válidas sejam encontradas.

### Estrutura do Projeto
- **ScoreCombinationService**: Serviço principal que contém a lógica de cálculo das combinações de pontuações.
- **ScoreProperties**: Classe de configuração que define os valores das pontuações.
- **VerifyRequest**: Classe que representa a requisição para verificar as combinações.
- **VerifyResponse**: Classe que representa a resposta com o número de combinações possíveis.

### Como Executar com Docker Compose

Para executar a aplicação usando Docker Compose, siga os passos abaixo:

1. **Entre na pasta docker**

Ela está localizada na raiz do projeto.

2. **Inicie os containers usando Docker Compose**

Execute o comando abaixo para construir e iniciar os containers:

```sh
docker compose up --build
```

3. **Testar a API**

Para testar a API, envie uma requisição POST para `http://localhost:8080/verify` com o corpo da requisição contendo o placar a ser verificado. Você também pode rodar os testes de integração do controlador usando o comando de teste do Gradle:

```sh
gradle test
```

### Documentação da API

A API está documentada usando Swagger. Após iniciar a aplicação, você pode acessar a documentação interativa em:

```
http://localhost:8080/swagger-ui.html
```

### Contato

Para mais informações, visite o [GitHub](https://github.com/FredyHG).