/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Marcos&Mateus
 */
public class ConnectionFactory {

    // Método responsável por estabelecer e retornar a conexão com o banco de dados
    public Connection getConnection(){

        try {
            // Tenta retornar a conexão usando o driver JDBC, o endereço do servidor, o nome do banco, usuário e senha
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1/BDVENDAS", "bd_bazar_gomes", "123");

        } catch (Exception erro) {
            // Caso ocorra alguma falha na conexão, captura a exceção e lança um erro em tempo de execução
            throw new RuntimeException(erro);
        }

    }

}
