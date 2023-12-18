<div align="center">
  <img src="https://github.com/alexandresjunior/taskify-api/assets/83607914/d0a78793-d53b-4d5e-a064-3c38c2fb5565" alt="Taskify" width="50%" />
</div>

## Descrição do Projeto
O Taskify é uma API REST desenvolvida com Spring Boot para servir como backend de uma aplicação de gerenciamento de tarefas. Ele oferece recursos para manipulação de usuários, tarefas e projetos, proporcionando uma interface para interação com o banco de dados MySQL.

## Configuração do Ambiente

### Requisitos
Certifique-se de ter as seguintes dependências instaladas em seu ambiente de desenvolvimento:

* [Java 11+](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html)
* [Maven](https://maven.apache.org/download.cgi)
* [MySQL](https://dev.mysql.com/downloads/installer/)

### Instalação
1. Clone o repositório:
```
git clone https://github.com/alexandresjunior/taskify-api.git
```
2. No terminal, navegue até o diretório do projeto:
```
cd taskify-api
```
3. Configure o banco de dados:
Certifique-se de que um servidor MySQL esteja em execução e crie um banco de dados chamado "taskify".
```
CREATE DATABASE taskify;
```
4. Configure as propriedades do banco de dados:
Se for o caso, edite o arquivo `src/main/resources/application.properties` e ajuste as configurações de conexão com o servidor MySQL:
```
spring.datasource.url=jdbc:mysql://localhost:3306/taskify
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
```
5. Execute a aplicação:
```
mvn spring-boot:run
```
O servidor estará acessível em `http://localhost:8080` por padrão.

## Estrutura do Projeto
O projeto é estruturado da seguinte forma:
* `com.taskify.api.constants`: Constantes utilizadas na aplicação.
* `com.taskify.api.controller`: Controladores para manipular as requisições HTTP.
* `com.taskify.api.model`: Modelos de dados para representar Usuários, Tarefas e Projetos.
* `com.taskify.api.repository`: Repositórios para interação com o banco de dados.

## Uso da API
A API possui os seguintes endpoints:

### Usuários:
* `POST /usuarios`: Cria um novo usuário.
* `GET /usuarios`: Lista todos os usuários.
* `GET /usuarios/{id}`: Obtém informações de um usuário específico.
* `PUT /usuarios/{id}`: Atualiza as informações de um usuário.
* `DELETE /usuarios/{id}`: Exclui um usuário.

### Tarefas:
* `POST /tarefas`: Cria uma nova tarefa.
* `GET /tarefas`: Lista todos as tarefas.
* `GET /tarefas/{id}`: Obtém informações de uma tarefa específica.
* `PUT /tarefas/{id}`: Atualiza as informações de uma tarefa.
* `DELETE /tarefas/{id}`: Exclui uma tarefa.

### Projetos:
* `POST /projetos`: Cria um novo projeto.
* `GET /projetos`: Lista todos os projetos.
* `GET /projetos/{id}`: Obtém informações de um projeto específico.
* `PUT /projetos/{id}`: Atualiza as informações de um projeto.
* `DELETE /projetos/{id}`: Exclui um projeto.

## Chamando os Endpoints via Swagger
Após iniciar a aplicação, você pode acessar em seu navegador a documentação interativa da API por meio do [Swagger UI](http://localhost:8080/swagger-ui.html).  
Lá, você encontrará uma interface fácil de usar para explorar e testar os endpoints da API.
