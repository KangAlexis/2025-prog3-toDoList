package br.edu.todolistaula.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private static final String URL = 
            "jdbc:mysql://localhost:3306/db_to_do_list";
    private static final String USUARIO = 
            "root";
    private static final String SENHA = 
            "root";
    private static final String DRIVER = 
            "com.mysql.jdbc.Driver";
    
    private static Connection con = null;
    
    public static Connection getConexao(){
        
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(
                    URL, USUARIO, SENHA);
            System.out.println("Conexao com o banco bem sucedido!");
            return con;
        } catch (ClassNotFoundException e) {
            System.out.println("ERRO driver -> " + e);
        } catch (SQLException e){
            System.out.println("ERRO SQL -> " + e);
        }
        
        return con;
    }
}
