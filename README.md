# Desafio Votação

[![Run in Postman](https://run.pstmn.io/button.svg)]()

## Descrição

Esta API REST foi desenvolvida para gerenciar e participar de sessões de votação no contexto do cooperativismo. A API permite a criação de pautas, a abertura de sessões de votação, o registro de votos e a obtenção dos resultados das votações. A aplicação foi desenvolvida em **Java + Spring Boot** e utiliza um banco de dados **PostgreSQL**.
A API também integra-se com um serviço externo para verificação da aptidão de CPFs para participar da votação, além de suportar testes automatizados e a documentação interativa via Swagger UI.

## CPF Validos e Invalidos para teste
CPF válido é o CPF em situação regular

| CPF            | Situação                     |
|----------------|------------------------------|
| 40442820135    | Regular                      |
| 63017285995    | Regular                      |
| 47123586964    | Suspensa                     |
| 07691852312    | Pendente de Regularização    |
| 01648527949    | Cancelada por Multiplicidade |
| 98302514705    | Nula                         |

## Funcionalidades

- **Criação de Pauta:** Permite o cadastro de novas pautas no sistema.
- **Abertura de Sessão de Votação:** Possibilita iniciar uma sessão de votação para uma pauta cadastrada.
- **Registro de Votos:** Garante que os usuários possam votar em uma sessão ativa.
- **Obter Resultado da Votação:** Permite visualizar o resultado da votação.
- **Validação de CPF:** Integração com serviço externo para verificar a aptidão de CPFs para votar.

## Tecnologias Utilizadas

- **Java 17+**: Linguagem de programação moderna e estável.
- **Spring Boot**: Framework para construção da aplicação, com:
    - **Spring Data JPA**: Integração com PostgreSQL.
    - **Spring Web**: Criação de endpoints RESTful.
    - **Spring Validation**: Validação de dados.
    - **Spring Cloud OpenFeign**: Comunicação entre microserviços.
- **PostgreSQL**: Banco de dados relacional.
- **Docker**: Conteinerização da aplicação e banco de dados.
- **Swagger UI**: Documentação interativa da API.
- **Postman**: Testes dos endpoints da API.
- **Maven**: Gerenciamento de dependências e automação de build, com dependências como:
    - **springdoc-openapi**: Geração automática de documentação da API.
    - **Lombok**: Redução de código boilerplate.
    - **spring-boot-starter-test**: Testes automatizados.

## Como Executar

1. Certifique-se de ter o Java 17+ instalado.
2. Clone este repositório:
   ```bash
   git clone https://github.com/thaa0/CoopVote.git
   ```
3. Navegue até o diretório do projeto:
   ```bash
   cd coopvote
   ```
4. Inicie o banco de dados com Docker:
5. ```bash
   docker-compose up -d
   ```
5. Compile e inicie a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```
6. A API estará disponível em `http://localhost:8080/coopvote`.

## Testes

Execute os testes com o Maven:
```bash
./mvnw test
```



