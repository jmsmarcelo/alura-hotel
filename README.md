# Hotel Alura
## Challenge ONE Java [Alura](https://www.alura.com.br/)
## Desafio:
- Criar uma aplicação para Desktop com conexão ao Banco de Dados
## Requisitos do desafio:
1. Sistema de autenticação de usuários para que somente usuários pertencentes ao hotel possam acessar o sistema
2. Permitir criar, editar e excluir uma reserva para clientes
3. Pesquisar na base de dados toda a informação de clientes e reservas
4. Registrar, editar e excluir os dados dos hóspedes
5. Calcule o valor da reserva com base no número de dias da reserva e uma taxa diária com o valor atribuído por você na moeda Real. Por exemplo, se tivermos uma reserva de 3 dias e o valor de nossa diária for de R$20 devemos multiplicar esses 3 dias pelo valor da diária, totalizando R$60. Tudo isso deve ser feito automaticamente e mostrado ao usuário antes de salvar a reserva
6. Banco de dados para armazenar todos os dados solicitados anteriormente

## 🖥️ Tecnologias utilizadas:

- Java
- Eclipse
- MySQL

# 🖥️ Requisitos para utilizar a aplicação:

- Java
- MySQL Server (rodando na porta 3306)

## 🔐 Usuário e senha:
- Os mesmos utilizados no Banco de Dados (que foram inseridos na instalação do MySQL)

## 💾 Download do aplicativo: \>>[Hotel Alura](https://github.com/jmsmarcelo/alura-hotel/releases/latest)<<

## ⚙️ Configurando o Banco de Dados:

- Criando o Banco de Dados (O nome deve ser **alura_hotel**)
```sql
CREATE DATABASE alura_hotel;
```
- Acessando o Banco de Dados criado

```sql
USE alura_hotel;
```
- Criando a Tabela de preços da diária

```sql
CREATE TABLE prices(id VARCHAR(50) NOT NULL,
	price DECIMAL(10, 2) NOT NULL, PRIMARY KEY(id)) ENGINE InnoDB;
```
- Inserindo o valor da diária (Pode ser o valor que desejar)
```sql
INSERT INTO prices(id, price) VALUES('day', 349.90);
```
- Criando a Tabela de reservas
```sql
CREATE TABLE reservations(id BIGINT NOT NULL AUTO_INCREMENT,
	check_in DATE, check_out DATE, price DECIMAL(10, 2), pay_method VARCHAR(50),
	PRIMARY KEY(id)) ENGINE InnoDB;
```
- Criando a Tabela de Hóspedes
```sql
CREATE TABLE guests(id BIGINT NOT NULL AUTO_INCREMENT, first_name VARCHAR(50), last_name VARCHAR(50),
	birth_date DATE, country VARCHAR(50), phone VARCHAR(50), reserve_id BIGINT,
	PRIMARY KEY(id), FOREIGN KEY(reserve_id) REFERENCES reservations(id)) ENGINE InnoDB;
```

![alura-hotel-preview](https://github.com/jmsmarcelo/alura-hotel/assets/32857346/65a1cc8d-e68a-4507-b6a2-dae72292c59a)

https://github.com/jmsmarcelo/alura-hotel/assets/32857346/51cc0e3a-6cc7-4af1-b2eb-8314b0b0f933

![badge](https://github.com/jmsmarcelo/alura-hotel/assets/32857346/e749d6de-34da-43dd-8962-7ac4596c5587)



