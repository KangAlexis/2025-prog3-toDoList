/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.todolistaula.dao;

import br.edu.todolistaula.database.ConexaoDB;
import java.sql.Connection;
import java.util.ArrayList;
import br.edu.todolistaula.model.Tarefa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class TarefaDAO {
    private Connection con;
    
    public ArrayList<Tarefa> retornaListadeTarefas(int idUsuario)
            throws SQLException{
        con = ConexaoDB.getConexao();
        String sql = "SELECT * FROM tb_tarefas WHERE usuario_id = ?";
        ArrayList<Tarefa> listaDeTarefas = new ArrayList<>();
        
        try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Tarefa t = new Tarefa();
                t.setId(rs.getInt("id"));
                t.setTitulo(rs.getString("titulo"));
                t.setDescriao(rs.getString("descricao"));
                t.setConcluida(rs.getBoolean("concluida"));
                
                listaDeTarefas.add(t);
            }
        } catch (SQLException e) {
            System.out.println("ERRO, ao listar tarefas -> " + e);
        } finally {
            con.close();
            System.out.println("Conexão fechada. (Listar tarefas)");
        }
        
        return listaDeTarefas;
        
        
    }
}
