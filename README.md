# README

## Descrição do Projeto

Este projeto é uma aplicação desenvolvida em Kotlin utilizando o Spring Boot (Java 17) com suporte a Webflux, JPA, e integração com PostgreSQL e Redis. O objetivo deste README é fornecer instruções para instalação e execução do projeto.

## Tecnologias Usadas

- **Kotlin**: Linguagem de programação moderna e concisa.
- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Java 17**: Versão da linguagem Java utilizada no projeto.
- **PostgreSQL**: Sistema de gerenciamento de banco de dados relacional.
- **Redis**: Armazenamento em memória para estruturas de dados.
- **Docker**: Plataforma para criar, implantar e executar aplicações em containers.
- **Gradle**: Ferramenta de automação de construção.
- **Swagger**: Documentação da API REST.

## Arquitetura sugestao
### Arquivo: arquitetura-loan-1.png
## Pré-requisitos

Antes de começar, verifique se você tem os seguintes pré-requisitos instalados em sua máquina:

- [Java 17](https://adoptium.net/)
- [Gradle](https://gradle.org/install/)
- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Instalação

1. **Clone o repositório:**

   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd <NOME_DO_DIRETORIO>
   
2. **Docker** (postgres/redis)
 docker-compose up -d
3. **Run** (via gradlew)
   ./gradlew bootRun
4. **Run Test**
   ./gradlew test
5. **Swagger API Docs**
   http://localhost:8080/swagger-ui/

6. docker-compose up -d
7. docker-compose up -d