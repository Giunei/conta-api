# Desafio Conta API

Este é um projeto de API RESTful desenvolvido em Java com Spring Boot, que gerencia contas e usuários. A aplicação oferece funcionalidades para cadastro, atualização, busca e cálculo de valores relacionados às contas.

## Funcionalidades

### Conta
- Cadastrar uma nova conta.
- Atualizar dados de uma conta existente.
- Alterar a situação de uma conta.
- Listar contas a pagar com filtros de data de vencimento e descrição.
- Obter uma conta por ID.
- Calcular o valor total pago em um determinado intervalo de tempo.

### Usuário
- Cadastrar um novo usuário.

## Tecnologias Utilizadas

- Java 17
- JWT
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Swagger para documentação da API
- Lombok para simplificação de código

## Pré-requisitos

Antes de começar, você precisará ter instalado:

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Documentação

Documentação da API
A documentação da API está disponível no Swagger. Acesse http://localhost:8080/swagger-ui/index.html após iniciar a aplicação para visualizar e interagir com os endpoints.

## Autenticação

É necessário criar um usuário na rota *POST /usuarios* e em seguida gerar o token de autenticação para acesssar as demais rotas. 