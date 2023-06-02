# Register - API
## API RESTful para cadastro

Este é um aplicativo back-end que possui uma API RESTful, que também consome a API externa ViaCep. As requisições funcionam com JSON (entrada e saída).

**Tecnologias**
- Java 17, Maven,
- Hibernate, H2 Database, postgreSQL
- Spring Boot, Spring Security 6, Spring Data JPA e Lombok
- Docker
- JWT
- CI/CD - Railway integrado com o GitHub

## Modelo de domínio

![Domain model](/screenshots/domain-model.jpeg?raw=true "Domain model")


Para rodar a aplicação basta fazer o clone dela e rodar o seguinte comando:
```
mvn clean package spring-boot:run
```

## Registro
Para iniciar, é preciso realizar um cadastro:
##### Exemplo: #####
```
URL: /v1/auth/register
método: POST
```
##### Body: #####
```json
{
    "username": "elwgomes",
    "email": "leonardo@leonardo.com",
    "cep": "57035180",
    "firstname": "Leonardo",
    "lastname": "Gomes",
    "password": "p4ssw0rd"
}
```
##### O endereço será preenchido automaticamente a partir do CEP inserido. #####

Isto retornará suas credenciais necessárias para fazer login e assim receber o token que dará acesso ao sistema.

*Obs.: O Token é válido por 10 minutos.*

![Consulta de usuário](/screenshots/register.png?raw=true "Consulta de usuário")


## Consulta de usuário
Com o token, é possível fazer todo o restante das operações de CRUD.
##### Esta requisição busca uma lista de usuários.  #####
```
URL: /v1/users
método: GET
```
##### Esta requisição busca um usuário por id.  #####
```
URL: /v1/users/{id}
método: GET
```
##### Esta requisição deleta um usuário por id.  #####
```
URL: /v1/users/{id}
método: DELETE
```
##### Esta requisição atualiza o CEP de um usuário por id. (Todo o endereço do usuário é atualizado com o novo CEP)  #####
```
URL: /v1/users/{id}
método: PUT
```
No header da requisição é necessário informar a key: `Authorization`

E no value é necessário informar `Bearer {token}`
Isso permitirá que sua requisição seja feita com sucesso.

![Registro](/screenshots/getusers.png?raw=true "Registro")
![Update](/screenshots/updatecep.png?raw=true "Registro")
## Login
Caso não possua o token e já tenha feito o cadastro, é possível realizar o login através da seguinte URL:
##### Exemplo: #####
```
URL: /v1/auth/login
método: POST
```
##### Body: #####
```json
{
   "username":"elwgomes",
   "password":"p4ssw0rd"
}
```
Isto retornará um JSON com o seu novo token que dará acesso ao sistema.

![Login](/screenshots/login.png?raw=true "Login")

**As mensagens de erro seguem o seguinte padrão:**
```json
{ "mensagem": "mensagem de erro" }
```