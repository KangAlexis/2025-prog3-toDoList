package br.edu.todolistaula.database;
// Pacote onde está a classe de conexão.
// Usado para organizar o código em módulos.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// Importa classes da biblioteca JDBC (Java Database Connectivity),
// que permitem conectar e trabalhar com bancos de dados.

public class ConexaoDB {
    // Classe responsável por criar a conexão com o banco de dados.

    // URL de conexão. Define onde o banco está (localhost),
    // a porta (3306) e o nome do banco (db_to_do_list).
    private static final String URL = 
            "jdbc:mysql://localhost:3306/db_to_do_list";

    // Usuário do banco de dados.
    private static final String USUARIO = "root";

    // Senha do banco de dados.
    private static final String SENHA = "root";

    // Driver JDBC que será usado para conectar ao MySQL.
    // OBS: A partir do MySQL Connector/J 8, o driver correto é:
    // "com.mysql.cj.jdbc.Driver"
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    // Objeto que representa a conexão com o banco.
    private static Connection con = null;

    // Método estático para obter a conexão.
    // Pode ser chamado sem precisar criar um objeto ConexaoDB.
    public static Connection getConexao(){
        
        try {
            // Carrega o driver do MySQL.
            Class.forName(DRIVER);

            // Estabelece a conexão usando DriverManager.
            con = DriverManager.getConnection(URL, USUARIO, SENHA);

            System.out.println("Conexao com o banco bem sucedido!");
            return con; // retorna a conexão criada
        } catch (ClassNotFoundException e) {
            // Erro caso o driver JDBC não seja encontrado.
            System.out.println("ERRO driver -> " + e);
        } catch (SQLException e){
            // Erro caso haja problema com a URL, usuário, senha ou banco.
            System.out.println("ERRO SQL -> " + e);
        }
        
        return con; // retorna null se deu erro
    }
}
