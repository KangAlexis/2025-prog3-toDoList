/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.todolistaula.dao;

import br.edu.todolistaula.database.ConexaoDB;
import com.mysql.cj.xdevapi.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class UsuarioDAO {
    private boolean usuarioLogado = false;
    private Connection con;
    
    public boolean checarUsuario(
            String email, String senha) throws SQLException{
        
        //Iniciando a conexão
        con = ConexaoDB.getConexao();
        
        //Criando a SQL
        String sql = "SELECT id, nome, email, senha "+
                "FROM tb_usuarios WHERE email = ? AND "+
                "senha = ?";
        
        try {
            //Injetando SQL
            PreparedStatement ps = con.prepareStatement(sql);
        
            ps.setString(1, email);
            ps.setString(2, senha);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                usuarioLogado = true;
            }
        } catch (SQLException e) {
            System.out.println("ERRO, "
                    + "nao encontrei a tabela -> " + e);
        } finally{
            con.close();
            System.out.println("Encerrando conexão");
        }
        
        return usuarioLogado;
    }
}
