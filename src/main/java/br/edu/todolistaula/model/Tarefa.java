package br.edu.todolistaula.model;
// Define o pacote onde essa classe está. Serve para organizar o código em pastas (módulos).

public class Tarefa {
    // Declaração da classe Tarefa, que representa uma tarefa da lista.

    private int id;
    // 'id' é o identificador único da tarefa. Geralmente usado para diferenciar uma tarefa da outra.

    private String titulo;
    // 'titulo' é o nome ou título da tarefa. Exemplo: "Estudar Java".

    private String descriao;
    // 'descriao' (provavelmente seria 'descricao') guarda detalhes adicionais da tarefa.
    // Exemplo: "Ler o capítulo 3 do livro".

    private boolean concluida;
    // 'concluida' indica se a tarefa já foi feita (true) ou não (false).

    private int usuarioId;
    // 'usuarioId' guarda o ID do usuário dono dessa tarefa. 
    // Isso ajuda a saber qual usuário criou cada tarefa.

    // Construtor vazio (sem parâmetros).
    // Usado quando queremos criar um objeto Tarefa e depois preencher os dados com os setters.
    public Tarefa() {
    }

    // Construtor com parâmetros.
    // Permite criar uma Tarefa já preenchida, passando todos os valores na criação.
    public Tarefa(int id, String titulo, String descriao, boolean concluida, int usuarioId) {
        this.id = id; // 'this' se refere ao atributo da classe.
        this.titulo = titulo;
        this.descriao = descriao;
        this.concluida = concluida;
        this.usuarioId = usuarioId;
    }

    // Métodos getters e setters.
    // Getters: servem para "pegar" o valor de um atributo.
    // Setters: servem para "alterar" o valor de um atributo.

    public int getId() {
        return id; // Retorna o valor do atributo 'id'.
    }

    public void setId(int id) {
        this.id = id; // Define um novo valor para o atributo 'id'.
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescriao() {
        return descriao;
    }

    public void setDescriao(String descriao) {
        this.descriao = descriao;
    }

    public boolean isConcluida() {
        // Para atributos booleanos, o padrão do Java é usar "is" no getter.
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    // Método toString()
    // Define como o objeto será exibido quando for transformado em texto.
    // Exemplo: se imprimir a Tarefa no console, vai aparecer algo como:
    // Tarefa{id=1, titulo=Estudar, descriao=Ler capítulo, concluida=false, usuarioId=10}
    @Override
    public String toString() {
        return "Tarefa{" + "id=" + id + ", titulo=" + titulo + ", descriao=" + descriao + ", concluida=" + concluida + ", usuarioId=" + usuarioId + '}';
    }
}
