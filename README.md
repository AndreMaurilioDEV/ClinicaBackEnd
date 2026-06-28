# Clinica Back-End V2

API REST para gerenciamento de uma clinica, desenvolvida em Java com Spring Boot. O sistema centraliza recursos de autenticacao, cadastro de pacientes, cadastro de medicos, agendamento de consultas e recuperacao de senha por e-mail.

## Sumario

- [Sobre o projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Arquitetura do projeto](#arquitetura-do-projeto)
- [Requisitos](#requisitos)
- [Configuracao do ambiente](#configuracao-do-ambiente)
- [Como executar](#como-executar)
- [Documentacao da API](#documentacao-da-api)
- [Endpoints principais](#endpoints-principais)
- [Observacoes importantes](#observacoes-importantes)

## Sobre o projeto

Este back-end foi criado para atender os principais fluxos operacionais de uma clinica:

- controle de pacientes;
- controle de medicos;
- criacao e acompanhamento de consultas;
- autenticacao via JWT;
- cadastro de colaboradores;
- recuperacao e alteracao de senha;
- documentacao interativa com Swagger/OpenAPI.

A aplicacao utiliza Spring Security com sessao stateless, persistencia com Spring Data JPA e banco de dados MySQL.

## Funcionalidades

- Login com emissao de token JWT.
- Criacao automatica de usuario administrador na inicializacao.
- Cadastro, listagem, consulta, edicao, exclusao e atualizacao de status de pacientes.
- Cadastro, listagem, consulta, edicao e exclusao de medicos.
- Cadastro, listagem, consulta, exclusao e atualizacao de status de consultas.
- Cadastro de colaboradores por usuario administrador.
- Recuperacao de senha por e-mail.
- Validacao de token de redefinicao de senha.
- Tratamento centralizado de excecoes.
- Documentacao da API via Swagger UI.

## Tecnologias

- Java 17
- Spring Boot 3.3.2
- Spring Web
- Spring Data JPA
- Spring Security
- JWT com `java-jwt`
- MySQL
- Spring Mail
- Bean Validation
- Springdoc OpenAPI
- Maven
- Dotenv Java
- JUnit/Spring Boot Test

## Arquitetura do projeto

```text
src/main/java/com/betrybe/Clinica
|-- advice/          # Tratamento global de excecoes
|-- controller/      # Controllers REST
|-- controller/dto/  # DTOs de entrada e saida
|-- doc/             # Configuracao do OpenAPI/Swagger
|-- entity/          # Entidades JPA e enums de dominio
|-- repository/      # Repositorios Spring Data JPA
|-- security/        # Configuracoes de seguranca, roles e filtro JWT
|-- service/         # Regras de negocio
|-- AdminInitializer.java
|-- HospitalExampleApplication.java
```

## Requisitos

Antes de executar o projeto, tenha instalado:

- Java 17 ou superior
- Maven ou Maven Wrapper incluido no projeto
- MySQL em execucao local

Por padrao, a aplicacao usa o banco:

```text
hospitaldb
```

A URL configurada cria o banco automaticamente caso ele ainda nao exista:

```properties
jdbc:mysql://localhost:3306/hospitaldb?createDatabaseIfNotExist=true
```

## Configuracao do ambiente

Crie um arquivo `.env` na raiz do projeto com as variaveis abaixo:

```env
SPRING_USERNAME=seu_usuario_mysql
SPRING_PASSWORD=sua_senha_mysql

JWT_SECRET=sua_chave_secreta_jwt

MAIL_USERNAME=seu_email@gmail.com
MAIL_PASSWORD=sua_senha_ou_app_password

ADMIN_NAME=Administrador
ADMIN_EMAIL=admin@clinica.com
ADMIN_PASSWORD=senha_admin
```

Essas variaveis sao carregadas na inicializacao da aplicacao por meio do `Dotenv`.

## Como executar

Na raiz do projeto, execute:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

Depois de iniciar, a API ficara disponivel em:

```text
http://localhost:8080
```

## Documentacao da API

Com a aplicacao em execucao, acesse:

```text
http://localhost:8080/swagger-ui.html
```

ou:

```text
http://localhost:8080/swagger-ui/index.html
```

A especificacao OpenAPI fica disponivel em:

```text
http://localhost:8080/v3/api-docs
```

## Endpoints principais

### Autenticacao

| Metodo | Endpoint | Descricao | Acesso |
| --- | --- | --- | --- |
| POST | `/auth/login` | Autentica usuario e retorna token JWT | Publico |
| POST | `/auth/forgot-password` | Solicita redefinicao de senha por e-mail | Publico |
| POST | `/auth/reset-password` | Redefine senha usando token | Publico |
| POST | `/auth/validate-reset-token` | Valida token de redefinicao | Publico |
| PUT | `/auth/change-password` | Altera senha do usuario | Autenticado |

### Pessoas e colaboradores

| Metodo | Endpoint | Descricao | Acesso |
| --- | --- | --- | --- |
| GET | `/persons` | Lista usuarios cadastrados | Autenticado |
| GET | `/persons/user-details/{username}` | Busca usuario por e-mail/username | `ROLE_EMPLOYEE` |
| POST | `/persons/employees` | Cadastra novo colaborador | `ROLE_ADMIN` |
| DELETE | `/persons/{idPerson}` | Remove usuario | Autenticado |

### Pacientes

| Metodo | Endpoint | Descricao | Acesso |
| --- | --- | --- | --- |
| GET | `/pacientes` | Lista todos os pacientes | Autenticado |
| GET | `/pacientes/{idPaciente}` | Busca paciente por ID | Autenticado |
| POST | `/pacientes` | Cadastra paciente | Autenticado |
| PUT | `/pacientes/{idPaciente}` | Atualiza paciente | Autenticado |
| DELETE | `/pacientes/{idPaciente}` | Remove paciente | Autenticado |
| PUT | `/pacientes/status-update/{idPaciente}` | Atualiza status do paciente | Autenticado |

### Medicos

| Metodo | Endpoint | Descricao | Acesso |
| --- | --- | --- | --- |
| GET | `/medicos` | Lista todos os medicos | Autenticado |
| GET | `/medicos/{idMedico}` | Busca medico por ID | Autenticado |
| POST | `/medicos` | Cadastra medico | Autenticado |
| PUT | `/medicos/{id}` | Atualiza medico | Autenticado |
| DELETE | `/medicos/{id}` | Remove medico | Autenticado |

### Consultas

| Metodo | Endpoint | Descricao | Acesso |
| --- | --- | --- | --- |
| GET | `/consultas` | Lista todas as consultas | Autenticado |
| GET | `/consultas/{consultaId}` | Busca consulta por ID | Autenticado |
| POST | `/consultas` | Agenda nova consulta | Autenticado |
| DELETE | `/consultas/{idConsulta}` | Remove consulta | Autenticado |
| PUT | `/consultas/status-consulta/{idConsulta}` | Atualiza status da consulta | Autenticado |

## Exemplos de payload

### Login

```json
{
  "username": "admin@clinica.com",
  "password": "senha_admin"
}
```

### Cadastro de paciente

```json
{
  "nome": "Maria Silva",
  "cpf": "12345678909",
  "telefone": "11999999999",
  "date": "1990-05-20",
  "endereco": "Rua Exemplo, 100",
  "estado": "SP",
  "cidade": "Sao Paulo",
  "cep": "01001000",
  "email": "maria@email.com",
  "planoDeSaude": "Plano Clinico",
  "numeroPlano": "ABC123",
  "ativo": true,
  "genero": "FEMININO"
}
```

### Cadastro de medico

```json
{
  "nome": "Dr. Carlos Souza",
  "crm": "123456",
  "cpf": "98765432100",
  "especialidadeMedica": "CARDIOLOGIA",
  "date": "1985-08-15"
}
```

### Agendamento de consulta

```json
{
  "horario": "14:30:00",
  "date": "2026-07-10",
  "medicoIds": 1,
  "pacienteIds": 1,
  "status": "AGENDADA",
  "tipoAtendimento": "CONSULTA"
}
```

## Variaveis de configuracao

| Variavel | Descricao |
| --- | --- |
| `SPRING_USERNAME` | Usuario do banco MySQL |
| `SPRING_PASSWORD` | Senha do banco MySQL |
| `JWT_SECRET` | Chave usada para assinar tokens JWT |
| `MAIL_USERNAME` | Conta de e-mail usada pelo Spring Mail |
| `MAIL_PASSWORD` | Senha ou app password do e-mail |
| `ADMIN_NAME` | Nome do administrador inicial |
| `ADMIN_EMAIL` | E-mail do administrador inicial |
| `ADMIN_PASSWORD` | Senha do administrador inicial |

## Observacoes importantes

- O projeto esta configurado para aceitar requisicoes CORS de `http://localhost:5173`, porta comum de projetos front-end com Vite.
- A configuracao atual usa `spring.jpa.hibernate.ddl-auto=create`. Isso recria as tabelas ao iniciar a aplicacao e pode apagar dados existentes. Para ambientes com dados importantes, altere para `update`, `validate` ou use migrations.
- A maioria das rotas exige token JWT no header:

```http
Authorization: Bearer seu_token_jwt
```

- O banco configurado no projeto e MySQL.
- O usuario administrador e criado automaticamente na inicializacao a partir das variaveis `ADMIN_NAME`, `ADMIN_EMAIL` e `ADMIN_PASSWORD`.

## Testes

Para executar os testes:

```bash
./mvnw test
```

No Windows:

```bash
mvnw.cmd test
```

## Status do projeto

Projeto em desenvolvimento, com foco em evoluir os fluxos de gestao clinica, seguranca, agendamento e integracao com o front-end em pt-BR.
