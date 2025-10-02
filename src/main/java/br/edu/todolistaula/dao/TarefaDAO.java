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
                t.setId(rs.getInt("id"));                // pega o id da tarefa
                t.setTitulo(rs.getString("titulo"));     // pega o título
                t.setDescriao(rs.getString("descricao"));// pega a descrição
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
    
    // Método que adiciona uma nova tarefa no banco, vinculada a um usuário
    public void addTarefa(Tarefa t, int idUsuario) throws SQLException{
        con = ConexaoDB.getConexao();
        
        // SQL de inserção de uma nova tarefa
        String sql = "INSERT INTO tb_tarefas"
                + " (titulo, descricao, concluida, usuario_id)"
                + " VALUES (?,?,?,?)";
        
        try {
            // Prepara o comando SQL e injeta os parâmetros
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getTitulo());
            ps.setString(2, t.getDescriao());
            ps.setBoolean(3, t.isConcluida());
            ps.setInt(4, idUsuario);
            
            // Executa a inserção
            ps.executeUpdate();
        } catch (SQLException e) {
            // Caso aconteça algum erro durante a execução do SQL
            System.out.println("ERRO, ao tentar "
                    + "salvar uma tarefa ->" + e);
        } finally{
            // Fecha a conexão com o banco
            con.close();
            System.out.println("Banco fechado com "
                    + "sucesso (Salvar tarefa)");
        }
    }
    
    // Método que deleta uma tarefa pelo id
    public void deletarTarefa(int id) throws SQLException{
        con = ConexaoDB.getConexao();
        
        // SQL que deleta uma tarefa pelo id
        String sql = "DELETE FROM tb_tarefas WHERE id = ?";
        
        try {
            // Prepara o comando SQL e injeta o parâmetro id
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            // Executa a exclusão
            ps.executeUpdate();
        } catch (SQLException e) {
            // Caso aconteça algum erro durante a execução do SQL
            System.out.println("ERRO, ao deletar tarefa -> " + e);
        } finally{
            // Fecha a conexão com o banco
            con.close();
            System.out.println("Banco fechado com "
                    + "sucesso (Deletar tarefa)");
        }
    }
    
    // Método que edita (atualiza) uma tarefa existente
    public void editarTarefa(Tarefa t, int id) throws SQLException{
        con = ConexaoDB.getConexao();
        
        // SQL que atualiza os dados de uma tarefa pelo id
        String sql = "UPDATE tb_tarefas SET "
                + "titulo = ?, "
                + "descricao = ?, "
                + "concluida = ? "
                + "WHERE id = ?";
        
        try {
            // Prepara o comando SQL e injeta os parâmetros
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getTitulo());
            ps.setString(2, t.getDescriao());
            ps.setBoolean(3, t.isConcluida());
            ps.setInt(4, id);
            
            // Executa a atualização
            ps.executeUpdate();
        } catch (SQLException e) {
            // Caso aconteça algum erro durante a execução do SQL
            System.out.println("ERRO, ao editar "
                    + "tarefa -> " + e);
        } finally{
            // Fecha a conexão com o banco
            con.close();
            System.out.println("Banco fechado com "
                    + "sucesso (editar tarefa)");
        }
    }
    
    // Método que retorna uma lista de tarefas de um usuário
    // filtrando também pelo título (usando LIKE)
    public ArrayList<Tarefa> retornaListadeTarefas(int idUsuario, String titulo)
            throws SQLException{
        
        // Abre a conexão com o banco
        con = ConexaoDB.getConexao();
        
        // SQL que busca todas as tarefas de um usuário filtrando pelo título
        String sql = "SELECT * FROM tb_tarefas "
                + "WHERE usuario_id = ? AND titulo LIKE ?";
        
        // Lista que vai armazenar as tarefas encontradas
        ArrayList<Tarefa> listaDeTarefas = new ArrayList<>();
        
        try {
            // Prepara o comando SQL e injeta os parâmetros
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ps.setString(2,"%"+titulo+"%");
            
            // Executa a consulta
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
            System.out.println("ERRO, ao listar tarefas pesquisa-> " + e);
        } finally {
            // Fecha a conexão com o banco
            con.close();
            System.out.println("Conexão fechada. (Listar tarefas pesquisa)");
        }
        
        // Retorna a lista de tarefas encontradas
        return listaDeTarefas;
    }
}
