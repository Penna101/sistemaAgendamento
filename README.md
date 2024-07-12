# Sistema de Agendamentos

## A aplicação está separada em dois serviços:
- backend
- frontend

## Execução
### Backend
Acesse o diretório do backend e certifique-se de estar utilizando a JDK 17 (java -version).
```bash
java --version
```
Compile o projeto:
```bash
./mvnw clean install
```
E então execute:
```bash
java -jar target/sistemaAgendamento-backend-0.0.1-SNAPSHOT.jar
```
A aplicação será executada na porta local 8080.

### Frontend
Acesse o diretório do frontend e certifique-se de possuir yarn instalado.
```bash
yarn --version
```
Compile o projeto:
```bash
yarn install
```
E então execute:
```bash
yarn dev
```
A aplicação será executada na porta local 3000.

### Banco de dados
Para acessar o banco de dados via console acesse a url:
* [Console H2](http://localhost:8080/h2-console/)
* E acesse com as credencias:
  * sa
  * password

# Especificações do projeto
Ao compilar e rodar o projeto o backend irá criar a pasta data, 
nele o banco de dados h2 será construido.

Por se tratar de um projeto simples feito em página única 
são poucas as decisões marcantes de projeto que tiveram de ser tomadas.
Todavia vale notar:
* Vuetify foi escolhido como o framework que usa VueJs como base para o
frontend. Se trata de um framework leve de fácil utilização e componentes elegantes
* O backend utiliza SpringBoot 3.3.1, com plugins de conexão ao banco de dados H2,
framework JPA e camada Rest
* O frontend possui validação em formulário para não permitir o envio de informações
incorretas. Da mesma forma o backend em sua camada de serviço também visa previnir 
dados incorretos e manter uma base de dados limpa
* Foi decidido por uma divisão clara nas camadas do backend entre elas:
  * Services (Regras de negócio)
  * Controllers (API REST)
  * Entities (Entidades JPA que mapeam o banco)
  * Repositories (Camada de acesso ao banco)
