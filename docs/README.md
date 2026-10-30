# 🛒 ControleVendasBazarGomes

> Sistema desktop para gerenciamento e controle de vendas, estoque e cadastro de clientes desenvolvido em Java (Swing) integrado ao banco de dados relacional MySQL.

---

## 📌 Visão Geral

O **ControleVendasBazarGomes** foi desenvolvido para modernizar e automatizar o controle operacional de vendas do Bazar Gomes. A aplicação oferece uma interface intuitiva para cadastro de clientes, busca automática de endereços via CEP, gerenciamento de produtos, funcionários e registro de vendas com baixa dinâmica de estoque.

---

## 🏗️ Arquitetura e Tecnologias

O projeto foi construído seguindo o padrão arquitetural **MVC (Model-View-Controller)** e o padrão **DAO (Data Access Object)** para persistência de dados.

| Camada / Tecnologia | Descrição |
| :--- | :--- |
| **Java JDK 11+** | Linguagem principal do projeto |
| **Java Swing** | Construção da Interface Gráfica de Usuário (GUI) |
| **MySQL Server** | Banco de dados relacional (`BDVENDAS`) |
| **JDBC / PreparedStatements** | Conexão segura contra SQL Injection |
| **WebService (ViaCEP)** | Consulta automática de endereço por CEP |
| **Apache NetBeans IDE** | Ambiente de desenvolvimento integrado |

---

## 📁 Estrutura do Projeto

```text
ControleVendasBazarGomes/
│
├── docs/                             <-- Central de Documentação Técnica
│   └── DOCUMENTACAO_CLIENTES.md      <-- Detalhes completos do Módulo de Clientes
│
├── src/
│   └── br/com/projeto/
│       ├── dao/                      <-- Data Access Objects (SQL)
│       ├── jdbc/                     <-- Fabrica de Conexões e Testes
│       ├── model/                    <-- Entidades e Regras de Negócio
│       └── view/                     <-- Formulários e Interface Swing (Frames)
│
├── README.md                         <-- Este arquivo (Visão Geral)
└── build.xml

```

---

## 📚 Documentação dos Módulos

A documentação detalhada passo a passo de cada tela e módulo do sistema está disponível na pasta `docs/`:

* 👤 **[Módulo 01: Cadastro e Consulta de Clientes](https://www.google.com/search?q=docs/DOCUMENTACAO_CLIENTES.md)**
* *Status:* **Concluído** ✅
* *Funcionalidades:* CRUD completo, busca dinâmica por nome (`LIKE`), integração com Web Service de CEP e preenchimento automático na tabela (`JTable`).


* 👔 **Módulo 02: Funcionários e Controle de Acesso** *(Em Breve)*
* 📦 **Módulo 03: Produtos e Fornecedores** *(Em Breve)*
* 💰 **Módulo 04: Fluxo de Vendas e PDV** *(Em Breve)*

---

## 🛠️ Requisitos e Configuração do Ambiente

1. **Java Development Kit (JDK 11 ou superior):** Certifique-se de ter o JDK instalado.
2. **SGBD MySQL:** Instale o MySQL Server e o Workbench.
3. **Esquema de Banco de Dados:** Execute o script de criação do banco `BDVENDAS` e suas respectivas tabelas (`tb_clientes`, `tb_produtos`, etc.).
4. **Driver JDBC:** Adicione o arquivo `mysql-connector-j-8.x.x.jar` às bibliotecas do seu projeto no NetBeans.

---

## ✒️ Autor
Desenvolvido por **Mateus Ferreira Gomes & Marcos Ferreira  Gomes** como parte da evolução tecnológica do Bazar Gomes.

```

---

