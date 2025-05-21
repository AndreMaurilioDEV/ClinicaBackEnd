# Gerenciamento de Clínica - Back-End  
**Repositório do Back-End:** [https://github.com/AndreMaurilioDEV/ClinicaBackEnd](https://github.com/AndreMaurilioDEV/ClinicaBackEnd)  

---

## 📌 Descrição
Este projeto é uma API desenvolvida em Java com Spring Boot para gerenciar um sistema hospitalar. A API permite o cadastro, edição e exclusão de pacientes, médicos e consultas, além de fornecer autenticação segura e documentação interativa com Swagger.

---

## 📂 Arquitetura e Organização  

### 🔹 Módulos Funcionais  

#### 📌 Pacientes  
- **dtos** → Possui DTOs de request e response para utilizar nos endpoints da Controller.  
- **PacienteController.java** → Controla endpoints para criar, acessar, editar e deletar pacientes.  
- **PacienteService.java** → Contém a lógica de negócios para manipular pacientes.  
- **PacienteRepository.java** → Interface que interage com o banco de dados para salvar e recuperar pacientes.  

#### 📌 Médicos  
- **dtos** → Possui DTOs de request e response para utilizar nos endpoints da Controller.  
- **MedicoController.java** → Gerencia os médicos da clínica.  
- **MedicoService.java** → Processa os dados dos médicos e realiza operações como agendamento de consultas.  
- **MedicoRepository.java** → Interface para acesso ao banco de dados.  

#### 📌 Consultas  
- **dtos** → Possui DTOs de request e response para utilizar nos endpoints da Controller.  
- **ConsultaController.java** → Permite operações de CRUD sobre consultas.  
- **ConsultaService.java** → Processa as consultas e as associa a pacientes e médicos.  
- **ConsultaRepository.java** → Interage com o banco de dados para armazenar as consultas.  

#### 📌 Usuários  
- **dtos** → Possui DTOs de request e response para utilizar nos endpoints da Controller.  
- **UsuarioController.java** → Gerencia o cadastro, autenticação e recuperação de senha.  
- **UsuarioService.java** → Lida com a lógica de criação e edição de usuários.  
- **UsuarioRepository.java** → Repositório de persistência dos dados dos usuários.  

#### 📌 Infraestrutura e Utilitários  
- **application.properties** → Arquivo de configuração contendo informações sobre banco de dados, segurança e outras propriedades da aplicação.  
- **CustomizedResponseEntityException.java** e **ExceptionResponse.java** → Controla o tratamento de exceções e respostas personalizadas de erro.  
- **EmailService.java** → Serviço para envio de e-mails automáticos, incluindo confirmação de conta e recuperação de senha.  

---

### 🔹 Segurança  
A segurança da aplicação é garantida por meio de autenticação JWT (JSON Web Token) e configurações personalizadas no Spring Security. Abaixo estão os principais componentes e funcionalidades:  

#### 📌 Token JWT  
- **TokenService.java**: Responsável por gerar e validar tokens JWT.  
  - **Métodos Principais**:  
    - `generateToken`: Gera um token JWT com base nos dados do usuário.  
    - `validateToken`: Valida um token JWT e retorna o nome de usuário (subject) contido no token.  
  - **Link do Código**: [TokenService.java](https://github.com/AndreMaurilioDEV/ClinicaBackEnd/blob/main/src/main/java/com/betrybe/Clinica/service/TokenService.java)  

#### 📌 Configurações de Segurança  
- **Pacote Security**: Contém as configurações de segurança da aplicação.  
  - **SecurityConfig.java**: Configura as permissões de acesso, autenticação JWT e filtros de segurança.  
  - **SecurityFilter.java**: Filtro personalizado para validar tokens JWT em cada requisição.  
  - **Link do Código**: [Pacote Security](https://github.com/AndreMaurilioDEV/ClinicaBackEnd/tree/main/src/main/java/com/betrybe/Clinica/security)  

#### 📌 Autenticação e Autorização  
- **Autenticação**:  
  - Os usuários se autenticam fornecendo credenciais (e-mail e senha) no endpoint `/auth/login`.  
  - Um token JWT é gerado e retornado para o cliente após a autenticação bem-sucedida.  
- **Autorização**:  
  - O token JWT deve ser enviado no cabeçalho `Authorization` das requisições para acessar endpoints protegidos.  
  - O filtro de segurança valida o token e permite ou nega o acesso com base nas permissões configuradas.  

#### 📌 Fluxo de Segurança  
1. O usuário faz login no endpoint `/auth/login`.  
2. O sistema gera um token JWT e o retorna ao cliente.  
3. O cliente envia o token no cabeçalho `Authorization` das requisições subsequentes.  
4. O filtro de segurança valida o token e permite o acesso aos recursos protegidos.  

## 📋 Requisitos do Sistema  

### ✅ Requisitos Funcionais  
1. **Autenticação e autorização**: O sistema deve possuir autenticação de usuários via JWT.  
2. **Cadastro e gerenciamento de usuários**:  
   - Criar um novo usuário manualmente pelo sistema.  
   - Recuperar a senha utilizando um código enviado por e-mail.  
   - Alterar a senha com base no código de recuperação.  
3. **Cadastro de pacientes**: Criar, editar e excluir pacientes.  
4. **Cadastro de médicos**: Criar, editar e excluir médicos, com especialidades e horários de atendimento.  
5. **Agendamento de consultas**: Permitir o agendamento de consultas entre pacientes e médicos.  
6. **Relatórios**: Gerar relatórios de consultas por médico, paciente ou período.  
7. **Tratamento de exceções**: O sistema deve possuir tratamento de exceções com respostas personalizadas.  
8. **Testes de integração**: Deve realizar testes automatizados para garantir a estabilidade do sistema.  

---

### 🔒 Requisitos Não Funcionais  
1. **Linguagem**: O sistema será feito em Java utilizando o framework Spring Boot.  
2. **Banco de dados**: O banco de dados será em PostgreSQL.   
3. **Desempenho**: O sistema deve responder rapidamente às solicitações dos usuários.  
4. **Segurança**:  
   - Uso de JWT para autenticação.  
   - Senhas devem ser armazenadas criptografadas utilizando BCrypt ou equivalente.  
5. **Testes Automatizados**: O sistema deve ser validado através de testes automatizados utilizando:  
   - JUnit → Para testes unitários das regras de negócio.  
   - Mockito → Para criação de objetos mock e simulação de dependências durante os testes.  

---

## 🚀 Como Executar  

### 🔧 Pré-requisitos  
- Java 11+  
- Maven  
- PostgreSQL  

### 📌 Passos  
1. Configure as variáveis de ambiente criando um arquivo **.env** na raiz do projeto e preenchendo os valores conforme necessário:  
   ```plaintext
   # Configuração do Banco de Dados
   DB_USERNAME=postgres
   DB_PASSWORD=sua_senha

   # Chaves JWT para autenticação
   JWT_PUBLIC_KEY=
   JWT_PRIVATE_KEY=

   # Chave de API para segurança
   API_KEY=

   # Configuração de E-mail (Envio de notificações)
   ADMIN_EMAIL=
   EMAIL_COMUNICACAO=
   SENHA_EMAIL_COMUNICACAO=

   # Execute os comandos
   ./mvnw spring-boot:run


## 🌐 Endpoints Principais  

### 🔹 Autenticação
| Método  | Endpoint             | Descrição                          |  
|---------|----------------------|------------------------------------|  
| POST    | `/auth/login`        | Autentica um usuário               |  
| POST    | `/auth/register`     | Registra um novo usuário           |  

### 🔹 Pacientes  
| Método  | Endpoint             | Descrição                          |  
|---------|----------------------|------------------------------------|  
| POST    | `/paciente`          | Cria um novo paciente              |  
| GET     | `/paciente/{id}`     | Retorna um paciente específico     |  
| GET     | `/paciente/`         | Lista todos os pacientes           |  
| PUT     | `/paciente/{id}`     | Edita um paciente                  |  
| DELETE  | `/paciente/`         | Deleta um paciente                 |  

### 🔹 Médicos  
| Método  | Endpoint            | Descrição                          |  
|---------|---------------------|------------------------------------|  
| POST    | `/medico/criar`     | Cria um novo médico                |  
| GET     | `/medico/acessar`   | Retorna um médico específico       |  
| GET     | `/medico/listar`    | Lista todos os médicos             |  
| PUT     | `/medico/editar`    | Edita um médico                    |  
| DELETE  | `/medico/deletar`   | Deleta um médico                   |  

### 🔹 Consultas  
| Método  | Endpoint             | Descrição                          |  
|---------|----------------------|------------------------------------|  
| POST    | `/consulta/criar`    | Agenda uma nova consulta           |  
| GET     | `/consulta/acessar`  | Retorna uma consulta específica    |  
| GET     | `/consulta/listar`   | Lista todas as consultas           |  
| PUT     | `/consulta/editar`   | Edita uma consulta                 |  
| DELETE  | `/consulta/deletar`  | Deleta uma consulta                |  

### 🔹 Usuários  
| Método  | Endpoint             | Descrição                          |  
|---------|----------------------|------------------------------------|  
| GET     | `/pessoas`           | Lista todos os usuários            |  
| POST    | `/pessoas`           | Cria um novo usuário               |  
| GET     | `/pessoas/{id}`      | Obtém detalhes de um usuário pelo ID |  
| PUT     | `/pessoas/{id}`      | Atualiza um usuário existente       |  
| DELETE  | `/pessoas/{id}`      | Remove um usuário                  |   

---

## 📜 Tecnologias Utilizadas  

### 🔹 Back-End  
- **Linguagem:** Java  
- **Framework:** Spring Boot  
- **Gerenciamento de Dependências:** Maven  

### 🔹 Banco de Dados  
- **Banco de Dados Relacional:** PostgreSQL  
- **ORM:** Spring Data JPA  

### 🔹 Segurança  
- **Autenticação:** Spring Security  
- **Autenticação via Terceiros:** JWT  
- **Token de Segurança:** JWT (JSON Web Token)  
- **Criptografia de Senhas:** BCrypt  

### 🔹 Infraestrutura e DevOps  
- **Containerização:** Docker  
- **Gerenciamento de Containers:** Docker Compose  
- **Administração do Banco de Dados:** Admin   

### 🔹 Testes Automatizados  
- **Testes Unitários e de Integração:** JUnit  
- **Mock de Dependências:** Mockito  
- **Testes de Segurança:** Spring Security Test  

### 🔹 Outras Bibliotecas e Utilitários   
- **Envio de E-mails:** Spring Mail  
- **Logs e Monitoramento:** Spring Boot Actuator

---

Criado por André Maurilio

