package br.edu.todolistaula.model;
// Define o pacote onde a classe está localizada. 
// Ajuda a organizar o projeto em pastas/módulos.

public class Usuario {
    // Declaração da classe Usuario.
    // Essa classe representa um usuário do sistema.

    private int id;
    // 'id' é o identificador único de cada usuário. 
    // Exemplo: usuário 1, usuário 2, etc.

    private String nome;
    // 'nome' é o nome do usuário. Exemplo: "Maria Silva".

    private String email;
    // 'email' é o endereço de e-mail do usuário. 
    // Normalmente usado para login ou contato.

    private String senha;
    // 'senha' é a senha do usuário, utilizada para autenticação (login).
    // OBS: em sistemas reais, a senha nunca deve ser armazenada em texto puro.
    // O correto é salvar uma versão criptografada (hash).

    // Construtor vazio.
    // Permite criar um objeto Usuario sem precisar passar os dados imediatamente.
    public Usuario() {
    }

    // Construtor com parâmetros.
    // Permite criar um usuário já preenchendo todos os atributos ao mesmo tempo.
    public Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Métodos getters e setters.
    // Getters: usados para acessar (ler) os atributos privados.
    // Setters: usados para alterar (escrever) os atributos privados.

    public int getId() {
        return id; // retorna o valor de 'id'.
    }

    public void setId(int id) {
        this.id = id; // define um novo valor para 'id'.
    }

    public String getNome() {
        return nome; // retorna o valor de 'nome'.
    }

    public void setNome(String nome) {
        this.nome = nome; // define um novo valor para 'nome'.
    }

    public String getEmail() {
        return email; // retorna o valor de 'email'.
    }

    public void setEmail(String email) {
        this.email = email; // define um novo valor para 'email'.
    }

    public String getSenha() {
        return senha; // retorna o valor de 'senha'.
    }

    public void setSenha(String senha) {
        this.senha = senha; // define um novo valor para 'senha'.
    }

    // Método toString().
    // Define como o objeto Usuario será exibido quando convertido para texto.
    // Exemplo: Usuario{id=1, nome=Maria, email=maria@email.com, senha=123}
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + '}';
    }
}
