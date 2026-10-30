# 🖥️ Módulo de Cadastro e Consulta de Clientes (Java Swing)

Documentação detalhada do desenvolvimento da interface gráfica e das regras de persistência para o gerenciamento de clientes no sistema de controle de vendas utilizando **Java Swing**, arquitetura em camadas e banco de dados relacional MySQL.

---

## 📋 1. Configuração Inicial do Formulário

1. **Criar o Formulário:** No ambiente de desenvolvimento, clique com o botão direito no projeto, vá em **New > JFrame Form** e nomeie o formulário como `frmCliente`.
2. **Ajustar Tamanho:** Redimensione a janela principal (`JFrame`) para o tamanho desejado para acomodar o layout do sistema.

---

## 🎨 2. Construção do Topo (Cabeçalho)

1. **Painel de Fundo (Banner):**
   - Arraste um componente **JPanel** para o topo do formulário e ajuste o tamanho dele cobrindo a largura da tela.
   - Defina a cor de fundo (`Background`) utilizando um tom de azul padrão (ex: inserindo o código hexadecimal da cor escolhida nas propriedades).
2. **Título da Tela:**
   - Adicione um componente **JLabel** dentro do painel azul.
   - Altere o texto para **"Cadastro de Clientes"**.
   - Personalize a fonte para a cor **branca**, com tamanho **24** e negrito.

---

## 📑 3. Criação do Sistema de Abas (JTabbedPane)

1. **Painel com Guias:** Arraste o componente **Tabbed Pane** (Painel com Guias) para o corpo principal abaixo do cabeçalho.
2. **Configuração das Abas:** Renomeie as abas clicando com o botão direito nelas (ou pelo navegador de componentes):
   - **Aba 1:** `Dados Pessoais`
   - **Aba 2:** `Consulta de Clientes`

---

## 👤 4. Aba 01: Dados Pessoais (Campos e Máscaras)

Dê um duplo clique na aba **Dados Pessoais** para focar nela, defina o fundo como branco e organize os campos utilizando **JLabel** (rótulos) e **JTextField** (campos de texto) ou **JFormattedTextField** (para campos formatados):

- **Código:** `JLabel` ("Código") + `JTextField` (tamanho reduzido, desativado para edição automática).
- **Nome & E-mail:** `JTextField` comuns (duplicados utilizando o atalho de cópia ou `Ctrl + D`).
- **Celular & Telefone Fixo (`JFormattedTextField`):**
  - Para aplicar a máscara, clique com o botão direito no campo formatado > **Propriedades** > **formatterFactory** > **Máscara Personalizada**.
  - **Máscara Celular:** `(##) # ####-####`
  - **Máscara Fixo:** `(##) ####-####`
- **Endereço, Número, Complemento & Bairro:** `JTextField` para entrada de texto livre.
- **CEP (`JFormattedTextField`):**
  - **Máscara CEP:** `#####-###`
- **UF (Estado):** Utilizar um componente **JComboBox** (Caixa de Combinação) para seleção da sigla do estado.
- **RG & CPF (`JFormattedTextField`):**
  - **Máscara RG:** `##.###.###-#`
  - **Máscara CPF:** `###.###.###-##`

---

## 🔍 5. Aba 02: Consulta de Clientes

1. **Painel de Pesquisa:** Adicione um rótulo e um campo de texto para o filtro de busca por nome, acompanhado de um botão **Pesquisar**.
2. **Tabela de Dados (`JTable`):**
   - Arraste o componente **Table** para a aba de consulta e ajuste seu tamanho.
   - **Configuração das Colunas:** Clique com o botão direito na tabela > **Conteúdo da Tabela (Columns)** e defina as colunas correspondentes aos atributos do banco de dados (ex: *Código, Nome, E-mail, Celular, CPF*, etc.). Remova as linhas de exemplo pré-existentes, pois os dados serão populados dinamicamente pelo código Java.

---

## 🔘 6. Botões de Ação Inferiores

Na parte inferior do formulário, adicione quatro botões (**JButton**) para o gerenciamento dos registros:
- **Novo**
- **Salvar**
- **Editar**
- **Excluir**
- *Dica de Layout:* Selecione todos os botões, clique com o botão direito no último selecionado e escolha **Mesmo Tamanho > Mesma Largura** para padronizar o design da interface.

---

## 🛠️ 7. Ajuste de Look and Feel (Tema Visual)

Por padrão, o Java Swing pode aplicar um visual nativo antigo dependendo do sistema operacional. Para atualizar a aparência para o padrão moderno do Windows:
1. Abra o código-fonte do formulário (`.java`).
2. Vá até o método `main` (ou bloco responsável pela inicialização do Look and Feel).
3. Localize a linha que define o tema (geralmente contendo `"Nimbus"` ou o padrão cross-platform) e altere para **`"Windows"`**:
   ```java
   // Exemplo de alteração no Look and Feel
   UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

```

4. Salve o arquivo e execute o projeto (`Shift + F6`) para validar o design final com a estilização aplicada.

---

# 📦 Passo a Passo: Criação da Camada Model (Classe `Clientes`)

Esta etapa documenta o desenvolvimento da camada de modelo (`Model`) da aplicação, responsável por estruturar os objetos que encapsulam os dados capturados na interface (`View`) antes de serem enviados para o banco de dados através da camada DAO.

## 🏗️ 1. Criação da Classe Model

1. **Localizar o Pacote:** No projeto, navegue até o pacote responsável pelos modelos (ex: `model`).
2. **Criar a Classe Java:** Clique com o botão direito no pacote **model** > **New > Java Class** e defina o nome como `Clientes`.

## 🧬 2. Definição dos Atributos

Os atributos da classe `Clientes` devem espelhar as colunas da tabela de clientes do banco de dados (mapeando os tipos do SQL para os tipos equivalentes em Java, como `String` para `VARCHAR`, `int` para inteiros, etc.).

Adicione os seguintes atributos privados à classe:

```java
private int id;
private String nome;
private String rg;
private String cpf;
private String email;
private String telefone;
private String celular;
private String cep;
private String endereco;
private int numero;
private String complemento;
private String bairro;
private String cidade;
private String estado;

```

> **💡 Dica de Produtividade (Atalho):** No editor (como NetBeans ou IntelliJ), você pode duplicar linhas rapidamente utilizando o atalho **`Ctrl + Shift + Seta para Baixo`** (ou `Ctrl + D` dependendo do ambiente) para acelerar a criação e depois apenas ajustar os tipos e nomes dos atributos.

## ⚙️ 3. Geração de Getters e Setters

Os métodos **Getters** e **Setters** são os responsáveis por permitir o acesso e a modificação segura dos atributos privados (princípio do encapsulamento da Orientação a Objetos):

1. Clique com o botão direito dentro da classe `Clientes`.
2. Vá em **Inserir Código** (ou utilize o atalho de geração de código do IDE, como `Alt + Insert`).
3. Selecione a opção **Getter and Setter**.
4. Selecione todos os atributos listados e clique em **Gerar**.

O IDE gerará automaticamente os métodos:

* `getAtributo()`: Retorna o valor armazenado no atributo.
* `setAtributo(tipo valor)`: Atribui ou altera o valor dentro do atributo.

---

# 🔌 Passo a Passo: Criação da Camada de Conexão (`ConnectionFactory`)

Esta etapa documenta a implementação da classe responsável por gerenciar a conexão da aplicação Java com o banco de dados relacional utilizando a API JDBC.

## 🧭 1. Dinâmica de Interação com o Banco de Dados

Em operações que exigem persistência de dados (como cadastrar um cliente), o fluxo padrão consiste em três etapas principais:

1. **Abrir a conexão** com o banco de dados.
2. **Executar o comando SQL** (responsabilidade da camada DAO).
3. **Fechar a conexão** para liberar os recursos do sistema.

## 📦 2. Criação do Pacote JDBC

1. No projeto, clique com o botão direito no diretório de pacotes fontes (`Source Packages`) e crie um novo pacote chamado **`br.com.projeto.jdbc`**.
2. Dentro deste pacote, crie uma nova classe Java chamada **`ConnectionFactory`**.

## 🛠️ 3. Implementação do Método de Conexão

A classe utiliza um método estático ou público (`getConnection`) que retorna um objeto do tipo `java.sql.Connection`. Abaixo está a estrutura base da classe com o tratamento de exceções adequado (`try-catch`):

```java
package br.com.projeto.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection getConnection() {
        try {
            // URL de conexão com o MySQL, usuário e senha
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1/BDVENDAS", "bd_bazar_gomes", "123");
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
}

```

## 🔑 4. Detalhes da String de Conexão (Data Source / Connection String)

A string de conexão utilizada no `DriverManager.getConnection` é composta pelas seguintes informações essenciais para a comunicação:

* **Driver e Protocolo:** `jdbc:mysql://` indicando o uso do driver JDBC para MySQL.
* **Endereço do Servidor (Host):** `127.0.0.1` (representa o `localhost`, ou seja, o banco rodando na própria máquina).
* **Nome do Banco de Dados:** `BDVENDAS` (banco de dados alvo da aplicação).
* **Usuário:** `bd_bazar_gomes` (usuário criado no banco de dados para autenticação).
* **Senha:** `123` (credencial de acesso do usuário).

## ⚠️ 5. Tratamento de Erros

* O método utiliza um bloco `try-catch` capturando uma exceção do tipo `SQLException`.
* Caso ocorra qualquer falha de conexão (como servidor offline, credenciais inválidas ou banco inexistente), a exceção é tratada e relançada como uma `RuntimeException` para notificar o desenvolvedor em tempo de execução.

---

# 🧪 Passo a Passo: Testando a Conexão com o Banco de Dados

Esta etapa documenta a criação da classe de teste para validar se a aplicação Java consegue estabelecer comunicação com o banco de dados através da `ConnectionFactory`.

## 🧭 1. Criação da Classe de Teste (`TestaConexao`)

1. No mesmo pacote da conexão (`br.com.projeto.jdbc`), crie uma nova classe Java chamada **`TestaConexao`**.
2. Adicione o método principal (`main`) para permitir a execução direta do script de teste.

## 💻 2. Implementação do Código de Teste

A classe utiliza um bloco `try-catch` em conjunto com caixas de diálogo (`JOptionPane`) para informar visualmente o sucesso ou a falha na tentativa de conexão:

```java
package br.com.projeto.jdbc;

import javax.swing.JOptionPane;

public class TestaConexao {

    public static void main(String[] args) {
        
        try {
            // Tenta obter a conexão através da ConnectionFactory
            new ConnectionFactory().getConnection();
            JOptionPane.showMessageDialog(null, "Conectado com sucesso!");
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Ops aconteceu o erro: " + erro);
        }
    }
}

```

---

# 🚀 Desenvolvimento da Camada DAO e Método de Cadastro

Nesta etapa, criamos a classe responsável por realizar a persistência e a comunicação com a tabela de clientes no banco de dados.

## 🧭 1. Criação da Classe `ClientesDAO`

1. No pacote `br.com.projeto.dao`, clique com o botão direito e crie uma nova classe Java chamada **`ClientesDAO`**.
2. Esta classe centralizará os métodos SQL referentes à tabela de clientes, mantendo o código organizado por entidade.

## 💻 2. Código da Classe e Construtor com Conexão

A classe possui um atributo privado de conexão e um construtor que instancia a fábrica de conexões automaticamente:

```java
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Classe DAO responsável pelas operações de banco de dados da entidade Clientes.
 * 
 * @author Caio Duarte
 */
public class ClientesDAO {

    private Connection con;

    // Construtor: inicializa a conexão automaticamente ao instanciar a classe
    public ClientesDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

```

## ✍️ 3. Implementação do Método `cadastrarCliente`

O método recebe um objeto da camada Model preenchido, monta a instrução SQL com parâmetros (`?`) para evitar *SQL Injection*, preenche os campos e executa a persistência:

```java
    // Método cadastrarCliente
    public void cadastrarCliente(Cliente obj) {
        try {
            // 1º Passo - Criar o comando SQL
            String sql = "insert into tb_clientes (nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
                       + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // 2º Passo - Conectar ao banco de dados e organizar o comando SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setInt(9, obj.getNumero());
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getEstado());

            // 3º Passo - Executar o comando SQL e fechar o statement
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }
    }
}

```

---

# 📝 Implementação do Botão Cadastrar na View

Nesta etapa, conectamos o evento de clique do botão "Salvar" na interface gráfica (`JFrame`) para resgatar os dados digitados pelo usuário, instanciar a camada Model, passá-los para o `ClientesDAO` e efetivar o cadastro no banco de dados.

## 💻 1. Programação do Evento do Botão Salvar

No design da tela de clientes, dê um duplo clique no botão **Salvar** para gerar o método de ação. Em seguida, implemente a captura dos campos de texto (`JTextField`), preenchimento do objeto `Cliente` e chamada do método DAO:

```java
    // Evento de clique do botão Salvar
    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        
        //Botão Salvar
        
            Cliente obj = new Cliente();
            obj.setNome(txtNome.getText());
            obj.setRg(txtRg.getText());
            obj.setCpf(txtCpf.getText());
            obj.setEmail(txtEmail.getText());
            obj.setTelefone(fttTelefoneFixo.getText());
            obj.setCelular(fttCelular.getText());
            obj.setCep(fttCep.getText());
            obj.setEndereco(txtEndereco.getText());
            obj.setNumero(Integer.parseInt(txtNumero.getText()));
            obj.setComplemento(txtComplemento.getText());
            obj.setBairro(txtBairro.getText());
            obj.setCidade(txtCidade.getText());
            obj.setEstado(cbxUf.getSelectedItem().toString());
            
            ClientesDAO dao = new ClientesDAO(); 
            
            dao.cadastrarCliente(obj);
        
    }                                         

```

## 🔍 2. Validação e Testes Práticos

Após implementar o código, execute a aplicação (`Shift + F6`) para realizar os testes de cadastro:

* **Preenchimento dos Campos:** Insira os dados nos componentes de texto (Nome, RG, CPF, E-mail, Telefones, CEP, Endereço, Número, Complemento, Bairro, Cidade) e selecione o estado correspondente na caixa de seleção (`JComboBox`).
* **Validação do Tamanho do Campo (UF):** Caso ocorra um erro de SQL informando que o dado é muito longo para a coluna `estado`, verifique a estrutura da tabela no banco de dados (`tb_clientes`) ou ajuste o componente para enviar apenas a sigla com 2 caracteres (ex: `SP`).
* **Confirmação de Sucesso:** Ao clicar em salvar sem erros, uma mensagem de **"Cadastrado com Sucesso!"** será exibida em uma caixa de diálogo (`JOptionPane`), e os dados poderão ser verificados diretamente no banco de dados via comando `SELECT * FROM tb_clientes`.

---

# 📋 Implementação do Método `listarClientes` na Camada DAO

Nesta etapa, criamos o método na classe `ClientesDAO` responsável por executar a consulta (`SELECT`) na tabela de clientes do banco de dados, percorrer os registros retornados e preencher uma lista para exibição posterior na interface gráfica.

## 💻 1. Código do Método de Listagem

O método retorna uma estrutura do tipo `List<Cliente>`, executando a query e mapeando cada linha do `ResultSet` para um objeto da classe Model:

```java
    // Método Listar Todos os Clientes
    public List<Cliente> listarClientes() {
        try {
            // 1º Passo - Criar a lista
            List<Cliente> lista = new ArrayList<>();

            // 2º Passo - Criar o SQL, organizar e executar
            String sql = "select * from tb_clientes";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // 3º Passo - Percorrer o resultado e preencher o objeto Cliente
            while (rs.next()) {
                Cliente obj = new Cliente();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("uf"));

                // Adiciona o objeto preenchido à lista
                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }
    }

```

## 🔍 2. Explicação do Processo

* **Estrutura de Repetição (`while`):** O comando `rs.next()` avança registro por registro no resultado obtido do banco de dados, permitindo extrair e mapear os valores de cada coluna para os atributos correspondentes na classe Model.
* **Retorno da Lista:** Após iterar por todas as linhas da tabela, o método devolve a coleção completa preenchida (`lista`), que posteriormente será integrada à tabela (`JTable`) da interface gráfica (`View`).

---

# 🛠️ Métodos de Alteração e Exclusão na Camada DAO

Nesta etapa, implementamos na classe `ClientesDAO` os métodos necessários para atualizar os dados de um cliente existente e remover um registro do banco de dados MySQL.

## 🗑️ 1. Método `excluirCliente`

O método de exclusão utiliza a instrução SQL `DELETE` com o filtro `WHERE id = ?`. Recebe o objeto contendo o ID do cliente e executa a remoção:

```java
    // Método Excluir Cliente
    public void excluirCliente(Cliente obj) {
        try {
            // 1º Passo - Criar o comando SQL
            String sql = "delete from tb_clientes where id = ?";

            // 2º Passo - Conectar ao banco e organizar o comando SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            // 3º Passo - Executar o comando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + erro);
        }
    }

```

## ✏️ 2. Método `alterarCliente`

O método de alteração utiliza a instrução SQL `UPDATE` para atualizar todos os campos do cliente com base no seu código único (`id`):

```java
    // Método Alterar Cliente
    public void alterarCliente(Cliente obj) {
        try {
            // 1º Passo - Criar o comando SQL
            String sql = "update tb_clientes set nome=?, rg=?, cpf=?, email=?, telefone=?, "
                       + "celular=?, cep=?, endereco=?, numero=?, complemento=?, bairro=?, "
                       + "cidade=?, estado=? where id=?";

            // 2º Passo - Conectar ao banco e organizar o comando SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setInt(9, obj.getNumero());
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getEstado());
            
            // ID para a cláusula WHERE (14º parâmetro)
            stmt.setInt(14, obj.getId());

            // 3º Passo - Executar o comando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar: " + erro);
        }
    }

```

## 🔍 3. Observações Importantes

* **Cláusula `WHERE`:** Tanto no `UPDATE` quanto no `DELETE`, é essencial incluir o filtro `where id = ?` para garantir que apenas o registro selecionado seja alterado ou removido.
* **Índice dos Parâmetros:** No `alterarCliente`, preste atenção à contagem das interrogações (`?`). O parâmetro do `id` deve ser configurado na posição **14** (`stmt.setInt(14, obj.getId())`), logo após o estado.

---

# 🖱️ Evento de Clique na Tabela (`mouseClicked`) e Ações de CRUD (Editar e Excluir)

Nesta etapa, implementamos a interatividade na tabela de consulta (`tblClientes`). Quando o usuário clica em um registro da tabela, os dados são automaticamente transferidos para a aba de "Dados Pessoais" (mudando o índice do painel de abas) e preenchem os respectivos campos de texto, permitindo realizar operações de alteração ou exclusão de forma simples.

## ⚙️ 1. Configuração do Evento `mouseClicked` na Tabela

Na interface gráfica (`Frmcliente`), clique com o botão direito sobre a tabela (`tblClientes`), vá em **Events > mouse > mouseClicked**, e adicione o código para alternar a aba ativa e resgatar os valores das colunas:

```java
    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {                                         
        // 1º Passo - Alternar para a aba de Dados Pessoais (Índice 0)
        tbpAbas.setSelectedIndex(0);

        // 2º Passo - Preencher os campos de texto com os dados da linha selecionada
        txtCodigo.setText(tblClientes.getValueAt(tblClientes.getSelectedRow(), 0).toString());
        txtNome.setText(tblClientes.getValueAt(tblClientes.getSelectedRow(), 1).toString());
        txtRg.setText(tblClientes.getValueAt(tblClientes.getSelectedRow(), 2).toString());
        txtCpf.setText(tblClientes.getValueAt(tblClientes.getSelectedRow(), 3).toString());
        txtEmail.setText(tblClientes.getValueAt(tblClientes.getSelectedRow(), 4).toString());
        fttTelefoneFixo.setText(tblClientes.getValueAt(tblClientes.getSelectedRow(), 5).toString());
        fttCelular.setText(tblClientes.getValueAt(tblClientes.getSelectedRow(), 6).toString());
        fttCep.setText(tblClientes.getValueAt(tblClientes.getSelectedRow(), 7).toString());
        txtEndereco.setText(tblClientes.getValueAt(tblClientes.getSelectedRow(), 8).toString());
        txtNumero.setText(tblClientes.getValueAt(tblClientes.getSelectedRow(), 9).toString());
        txtComplemento.setText(tblClientes.getValueAt(tblClientes.getSelectedRow(), 10).toString());
        txtBairro.setText(tblClientes.getValueAt(tblClientes.getSelectedRow(), 11).toString());
        txtCidade.setText(tblClientes.getValueAt(tblClientes.getSelectedRow(), 12).toString());
        cbxUf.setSelectedItem(tblClientes.getValueAt(tblClientes.getSelectedRow(), 13).toString());
    }

```

## ✏️ 2. Implementação do Botão Editar (`btnEditar`)

No evento de clique do botão editar, capturamos os novos valores preenchidos nos campos do formulário (incluindo o ID) e acionamos o método `alterarCliente` da camada DAO:

```java
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        //Botão Editar

        Cliente obj = new Cliente();
        obj.setNome(txtNome.getText());
        obj.setRg(txtRg.getText());
        obj.setCpf(txtCpf.getText());
        obj.setEmail(txtEmail.getText());
        obj.setTelefone(fttTelefoneFixo.getText());
        obj.setCelular(fttCelular.getText());
        obj.setCep(fttCep.getText());
        obj.setEndereco(txtEndereco.getText());
        obj.setNumero(Integer.parseInt(txtNumero.getText()));
        obj.setComplemento(txtComplemento.getText());
        obj.setBairro(txtBairro.getText());
        obj.setCidade(txtCidade.getText());
        obj.setEstado(cbxUf.getSelectedItem().toString());

        obj.setId(Integer.parseInt(txtCodigo.getText()));

        ClientesDAO dao = new ClientesDAO();
        dao.alterarCliente(obj);
    }

```

## 🗑️ 3. Implementação do Botão Excluir (`btnExcluir`)

No evento de clique do botão excluir, utilizamos o código (ID) do cliente selecionado para efetuar a remoção via camada DAO:

```java
    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // Botão Excluir
        Cliente obj = new Cliente();
        
        // Passa apenas o ID para realizar a exclusão
        obj.setId(Integer.parseInt(txtCodigo.getText()));

        ClientesDAO dao = new ClientesDAO();
        dao.excluirCliente(obj);
    }

```

---

# 🔍 Implementação da Busca de Clientes por Nome

Nesta etapa, implementamos a funcionalidade de pesquisa na aplicação. Criamos o método de consulta filtrada na camada DAO utilizando o operador `LIKE` com caracteres coringas (`%`) e configuramos a interface para atualizar a tabela de forma dinâmica conforme o usuário digita.

## 💻 1. Criação do Método `buscarClientePorNome` na Camada DAO

Na classe `ClientesDAO`, criamos um método semelhante ao `listarClientes`, mas adaptado para receber um parâmetro de texto (`nome`) e filtrá-lo no banco de dados:

```java
    // Método Buscar Cliente por Nome
    public List<Cliente> buscarClientePorNome(String nome) {
        try {
            // 1º Passo - Criar a lista
            List<Cliente> lista = new ArrayList<>();

            // 2º Passo - Criar o SQL, organizar e executar (utilizando LIKE para busca aproximada)
            String sql = "select * from tb_clientes where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            // ...

```