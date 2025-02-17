# Card Transaction API

## Descrição
Este projeto é uma API de transações de cartão que gerencia saldos de contas e transações.
Ele utiliza Kotlin, Spring Boot, JPA e H2 Database para persistência de dados.

## Funcionalidades
* Autorizar e Listar Transações MCC
* Criar e Listar Saldos de Contas

## Estrutura do Projeto
### Clean Architecture
Este projeto segue os princípios da Clean Architecture para garantir uma separação clara de responsabilidades e facilitar a manutenção e escalabilidade do código.
- **application**: Contém os casos de uso e gateways da aplicação.
- **domain**: Define as entidades de domínio.
- **infraestructure**: Contém a persistência e as implementações de gateways.

## Pré-requisitos
* Java (openjdk 21)
* SpringBoot 3.4.2
* Gradle
* Intellij Community **(com suporte para Kotlin)**

## Executando o Projeto
Para executar o projeto, utilize o seguinte comando:
````bash
./gradlew bootRun
````
### Executando os testes da API
````bash
./gradlew test
````

## Documentação SWAGGER
Para acessar a documentação da api após inicializar o projeto, acesse [Card Transaction API](http://localhost:8080/swagger-ui/index.html).
![image](https://github.com/user-attachments/assets/a68b982a-c0e6-4826-92d6-5b234a218f8c)

## Dependências
### As principais dependências utilizadas no projeto são:
- **Spring Boot**
- **Spring Data JPA**
- **H2 Database**
- **MockK**
- **Springdoc OpenAPI** (para documentação Swagger)
