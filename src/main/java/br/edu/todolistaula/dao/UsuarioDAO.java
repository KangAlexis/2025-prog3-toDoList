/*
 * Classe DAO (Data Access Object) responsável por acessar a tabela de usuários no banco.
 * Ela contém métodos para: autenticar (checarUsuario), obter o id do usuário (retornarId)
 * e salvar um novo usuário (salvarUsuario).
 */
package br.edu.todolistaula.dao;

import br.edu.todolistaula.database.ConexaoDB;
import br.edu.todolistaula.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    // Estes campos guardam estado dentro do DAO.
    private boolean usuarioLogado = false;
    private Connection con;
    private int id = 0;
    
    /**
     * checarUsuario: verifica se existe um usuário com o email e a senha informados.
     * Retorna true se encontrou (login válido) e false caso contrário.
     */
    public boolean checarUsuario(String email, String senha) throws SQLException{
        
        // 1) Pega a conexão com o banco
        con = ConexaoDB.getConexao();
        
        // 2) Monta a consulta SQL com parâmetros (placeholders ?)
        //    Usar parâmetros evita SQL Injection
        String sql = "SELECT id, nome, email, senha " +
                     "FROM tb_usuarios WHERE email = ? AND senha = ?";
        
        try {
            // 3) Cria um PreparedStatement e injeta os valores
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, senha);
            
            // 4) Executa a consulta
            ResultSet rs = ps.executeQuery();
            
            // 5) Se houver pelo menos um registro, então o usuário existe
            while(rs.next()){
                usuarioLogado = true;
            }
        } catch (SQLException e) {
            // Mensagem de erro
            System.out.println("ERRO, nao encontrei a tabela -> " + e);
        } finally{
            // 6) Fecha a conexão (boa prática!)
            con.close();
            System.out.println("Encerrando conexão");
        }
        
        return usuarioLogado;
    }
    
    /**
     * retornarId: busca o id do usuário a partir do email e senha.
     * Retorna 0 se não encontrar.
     */
    public int retornarId(String email, String senha) throws SQLException{
        con = ConexaoDB.getConexao();
        
        String sql = "SELECT id FROM tb_usuarios WHERE email = ? AND senha = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, senha);
            
            ResultSet rs = ps.executeQuery();
            // Se a consulta retornar resultado, pega o id
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
    
    /**
     * salvarUsuario: insere um novo usuário na tabela.
     */
    public void salvarUsuario(Usuario u) throws SQLException{
        con = ConexaoDB.getConexao();
        
        String sql = "INSERT INTO tb_usuarios (id, nome, email, senha) VALUES (?, ?, ?, ?)";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, u.getId());          
            ps.setString(2, u.getNome());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getSenha());    
            
            ps.executeUpdate();               // Executa INSERT/UPDATE/DELETE
        } catch (SQLException e){
            System.out.println("ERRO, não foi possível salvar no banco -> " + e);
        } finally{
            con.close();
            System.out.println("Conexão fechada (Cadastrar usuário)");
        }
    }
}
