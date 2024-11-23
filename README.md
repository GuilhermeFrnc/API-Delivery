# API de Gerenciamento de Delivery

## Descrição
Esta API foi desenvolvida em Java 17 com Spring Boot e utiliza MySQL como banco de dados. A aplicação tem como objetivo gerenciar entregas de forma eficiente, permitindo criar, atualizar, consultar e excluir registros de entregas, bem como acompanhar o status e os detalhes de cada entrega.


## Funcionalidades da API

### Criar uma Entrega
- **ENDPOINT:** ``` POST /api/delivery```
  - **Descrição:** Cria uma nova entrega no sistema.
  - **Exemplo de JSON para requisição:**
  ```
  {
    "nameClient": "Carlos",
    "nameStore": "SopaSopa",
    "address": {
        "street": "Afonso Pena",
        "city": "Metropolis",
        "number": "457",
        "complement": "31",
        "zipCode": "12345"
    }
  }
  ```

  
### Consultar Entregas
- **ENDPOINT:** ``` GET /api/delivery/last-week```
  - **Descrição:**  Retorna todas as entregas realizadas na última semana.

- **ENDPOINT:** ``` GET /api/delivery/pending```
  - **Descrição:**  Retorna todas as entregas pendentes.
 
- **ENDPOINT:** ``` GET /api/delivery/completed```
  - **Descrição:**  Retorna todas as entregas realizadas durante a semana.

- **ENDPOINT:** ``` GET /api/delivery/{id}```
  - **Descrição:** Retorna todos os dados de uma entrega específica.


### Atualizar Status de uma Entrega
- **ENDPOINT:** ``` PUT /api/delivery/update-status/{id}```
  - **Descrição:** Atualiza o status de uma entrega para DELIVERED ou PENDING.


### Excluir uma Entrega
- **ENDPOINT:** ``` DELETE /api/delivery/delete/{id}```
- **Descrição:** Deleta uma entrega com base no id especificado.


## Requisitos do Sistema

Certifique-se de ter as seguintes ferramentas instaladas:

- Java Development Kit (JDK) 17
- MySQL versão 8.0.39


## Configuração do Banco de Dados
1. **Criar o Banco de Dados**

   Acesse o cliente de linha de comando do MySQL:
   ```bash
   mysql -u root -p
   ```
   Digite a senha (no exemplo, 1234567) quando solicitado.

   Crie o banco de dados:
   ```bash
   CREATE DATABASE deliveries_db;
   ```

   Selecione o banco de dados:

    ```bash
   USE deliveries_db;
   ```

2. **Executar os Scripts de Configuração**

   Certifique-se de ter os arquivos schema.sql e data.sql com a estrutura e os dados iniciais do banco.

   No terminal, execute os comandos abaixo, apontando para o caminho dos arquivos:
   ```bash
    SOURCE /caminho/para/schema.sql;
    SOURCE /caminho/para/data.sql;
   ```
   Substitua /caminho/para/ pelo caminho completo onde os arquivos estão localizados.

## Configuração do Projeto
Siga os passos abaixo para configurar o projeto no seu ambiente:
1. **Clone o repositório**
   ```bash
   git clone https://github.com/GuilhermeFrnc/API-Delivery.git
   ```

2. **Configure as Credenciais do Banco**

   Atualize o arquivo application.yml com as credenciais do seu banco de dados:
   ```bash
     spring:
        datasource:
            url: jdbc:mysql://localhost:3306/deliveries_db?useSSL=false&serverTimezone=UTC
            username: root
            password: 1234567
   ```
   
3. **Compile e Execute o Projeto**

   Use o Maven para compilar e rodar a aplicação:
  
  ```
  mvn clean install
  mvn spring-boot:run
  ```
4. **Teste a API**

   Após iniciar a aplicação, a API estará disponível no seguinte endereço:

   ```
    http://localhost:8080/api/delivery
    ```
