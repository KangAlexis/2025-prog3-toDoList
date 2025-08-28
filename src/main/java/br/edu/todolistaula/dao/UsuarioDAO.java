/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.todolistaula.dao;

import br.edu.todolistaula.database.ConexaoDB;
import br.edu.todolistaula.model.Usuario;
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
    private int id = 0;
    
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
    
    public int retornarId(String email, String senha) 
            throws SQLException{
        con = ConexaoDB.getConexao();
        String sql = "SELECT id FROM tb_usuarios "
                + "WHERE email = ? AND senha = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, senha);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("ERRO, ao retornar id -> " + e);
        } finally{
            con.close();
            System.out.println("Conexão fechada. (Buscar id usuário)");
        }
        return id;
    }
    
    public void salvarUsuario(Usuario u) throws SQLException{
        con = ConexaoDB.getConexao();
        
        String sql = "INSERT INTO tb_usuarios (id, nome, "
                + "email, senha)"
                + " VALUES (?, ?, ?, ?)";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, u.getId());
            ps.setString(2, u.getNome());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getSenha());
            
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("ERRO, não foi possível "
                    + "salvar no banco -> " + e);
        }finally{
            con.close();
            System.out.println("Conexão fechada "
                    + "(Cadastrar usuário)");
        }
    }
}
