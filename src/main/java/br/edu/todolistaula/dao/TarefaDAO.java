/*
 * Classe DAO (Data Access Object) responsável por acessar a tabela de tarefas no banco de dados.
 * Aqui temos o método para buscar todas as tarefas de um usuário específico.
 */
package br.edu.todolistaula.dao;

import br.edu.todolistaula.database.ConexaoDB;
import java.sql.Connection;
import java.util.ArrayList;
import br.edu.todolistaula.model.Tarefa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TarefaDAO {
    // Atributo que representa a conexão com o banco
    private Connection con;
    
    // Método que retorna uma lista de tarefas de um usuário pelo seu id
    public ArrayList<Tarefa> retornaListadeTarefas(int idUsuario)
            throws SQLException{
        
        // Abre a conexão com o banco
        con = ConexaoDB.getConexao();
        
        // SQL que busca todas as tarefas onde o campo usuario_id corresponde ao id passado
        String sql = "SELECT * FROM tb_tarefas WHERE usuario_id = ?";
        
        // Lista que vai armazenar as tarefas encontradas
        ArrayList<Tarefa> listaDeTarefas = new ArrayList<>();
        
        try {           
            // Prepara o comando SQL e injeta o parâmetro idUsuario
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            
            // Executa a consulta e guarda o resultado
            ResultSet rs = ps.executeQuery();
            
            // Percorre todas as linhas do resultado
            while(rs.next()){
                // Cria um objeto Tarefa e preenche com os dados do banco
                Tarefa t = new Tarefa();
                t.setId(rs.getInt("id"));                 // pega o id da tarefa
                t.setTitulo(rs.getString("titulo"));      // pega o título
                t.setDescriao(rs.getString("descricao")); // pega a descrição
                t.setConcluida(rs.getBoolean("concluida"));// pega se está concluída ou não
                
                // Adiciona a tarefa preenchida na lista
                listaDeTarefas.add(t);
            }
        } catch (SQLException e) {
            // Caso aconteça algum erro durante a execução do SQL
            System.out.println("ERRO, ao listar tarefas -> " + e);
        } finally {
            // Fecha a conexão com o banco
            con.close();
            System.out.println("Conexão fechada. (Listar tarefas)");
        }
        
        // Retorna a lista de tarefas encontradas
        return listaDeTarefas;
    }
}
