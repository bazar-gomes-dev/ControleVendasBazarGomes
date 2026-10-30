/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Cliente;
import br.com.projeto.model.WebServiceCep;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Classe DAO (Data Access Object) responsável pelas operações de banco de dados
 * da entidade Clientes.
 *
 * @author Mateus&Marcos
 */
public class ClientesDAO {

    // Atributo para armazenar a conexão com o banco de dados (declarado corretamente em sua própria linha)
    private Connection con;

    // Construtor da classe: inicializa a conexão ao instanciar o DAO
    public ClientesDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    /**
     * Método responsável por cadastrar um novo cliente no banco de dados.
     *
     * @param obj Objeto da classe Clientes contendo os dados recolhidos da
     * View.
     */
    public void cadastrarCliente(Cliente obj) {
        try {
            // 1º Passo - Criar o comando SQL com parâmetros (?) para evitar SQL Injection
            String sql = "insert into tb_clientes (nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // 2º Passo - Conectar ao banco de dados e organizar o comando SQL usando PreparedStatement
            PreparedStatement stmt = con.prepareStatement(sql);

            // Atribuindo os valores obtidos do objeto Model para cada parâmetro (?) da query SQL
            stmt.setString(1, obj.getNome());       // Define o nome
            stmt.setString(2, obj.getRg());         // Define o RG
            stmt.setString(3, obj.getCpf());        // Define o CPF
            stmt.setString(4, obj.getEmail());      // Define o e-mail
            stmt.setString(5, obj.getTelefone());   // Define o telefone fixo
            stmt.setString(6, obj.getCelular());    // Define o celular
            stmt.setString(7, obj.getCep());        // Define o CEP
            stmt.setString(8, obj.getEndereco());   // Define o endereço
            stmt.setInt(9, obj.getNumero());        // Define o número do endereço (inteiro)
            stmt.setString(10, obj.getComplemento());// Define o complemento
            stmt.setString(11, obj.getBairro());    // Define o bairro
            stmt.setString(12, obj.getCidade());    // Define a cidade
            stmt.setString(13, obj.getEstado());        // Define o estado (UF)

            // 3º Passo - Executar o comando SQL e fechar a instrução para liberar recursos
            stmt.execute();
            stmt.close();

            // Exibe uma mensagem de sucesso na tela caso a inserção ocorra sem problemas
            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");

        } catch (SQLException erro) {
            // Captura e exibe qualquer erro relacionado ao SQL ou à persistência
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }
    }

    //Metodo AlterarCliente
    public void alterarCliente(Cliente obj) {

        try {
            // 1º Passo - Criar o comando SQL com parâmetros (?) para evitar SQL Injection
            String sql = "update tb_clientes set nome=?, rg=?, cpf=?, email=?, telefone=?, celular=?, cep=?, "
                    + "endereco=?, numero=?, complemento=?, bairro=?, cidade=?, estado=? where id=?";

            // 2º Passo - Conectar ao banco de dados e organizar o comando SQL usando PreparedStatement
            PreparedStatement stmt = con.prepareStatement(sql);

            // Atribuindo os valores obtidos do objeto Model para cada parâmetro (?) da query SQL
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

            stmt.setInt(14, obj.getId());

            // 3º Passo - Executar o comando SQL e fechar a instrução para liberar recursos
            stmt.execute();
            stmt.close();

            // Exibe uma mensagem de sucesso na tela caso a inserção ocorra sem problemas
            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

        } catch (SQLException erro) {
            // Captura e exibe qualquer erro relacionado ao SQL ou à persistência
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }

    }

    //Metodo ExcluirCliente
    public void excluirCliente(Cliente obj) {

        try {
            // 1º Passo - Criar o comando SQL com parâmetros (?) para evitar SQL Injection
            String sql = " delete from tb_clientes where id = ?";

            // 2º Passo - Conectar ao banco de dados e organizar o comando SQL usando PreparedStatement
            PreparedStatement stmt = con.prepareStatement(sql);

            // Atribuindo os valores obtidos do objeto Model para cada parâmetro (?) da query SQL
            stmt.setInt(1, obj.getId());

            // 3º Passo - Executar o comando SQL e fechar a instrução para liberar recursos
            stmt.execute();
            stmt.close();

            // Exibe uma mensagem de sucesso na tela caso a inserção ocorra sem problemas
            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");

        } catch (SQLException erro) {
            // Captura e exibe qualquer erro relacionado ao SQL ou à persistência
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }

    }

    //Metodo Listar Todos Clientes
    public List<Cliente> listarClientes() {
        try {

            //1 passo criar a lista 
            List<Cliente> lista = new ArrayList<>();

            //2 passo - criar o sql, organizar e execultar 
            String sql = "select * from  tb_clientes";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

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
                obj.setEstado(rs.getString("estado"));

                lista.add(obj);

            }
            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }

    //metodo consultaCliente por Nome
    public Cliente consultaPorNome(String nome) {
        try {
            //1 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_clientes where nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Cliente obj = new Cliente();

            if (rs.next()) {
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
                obj.setEstado(rs.getString("estado"));
            }

            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
        }
        return null;
    }

    //Metodo buscarClientePorNome
    public List<Cliente> buscaClientePorNome(String nome) {
        try {

            //1 passo criar a lista 
            List<Cliente> lista = new ArrayList<>();

            //2 passo - criar o sql, organizar e execultar 
            String sql = "select * from  tb_clientes where nome like ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

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
                obj.setEstado(rs.getString("estado"));

                lista.add(obj);

            }
            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }

    // Buscar Cep
    public Cliente buscaCep(String cep) {

        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);

        Cliente obj = new Cliente();

        if (webServiceCep.wasSuccessful()) {
            obj.setEndereco(webServiceCep.getLogradouroFull());
            obj.setCidade(webServiceCep.getCidade());
            obj.setBairro(webServiceCep.getBairro());
            obj.setEstado(webServiceCep.getUf());
            return obj;
        } else {
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
            return null;
        }

    }
}
