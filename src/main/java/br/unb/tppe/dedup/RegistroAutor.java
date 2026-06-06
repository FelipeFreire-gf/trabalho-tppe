package br.unb.tppe.dedup;

public class RegistroAutor {

    private final Integer id;
    private final String nome;

    public RegistroAutor(Integer id, String nome) {
        if (id == null) {
            throw new IllegalArgumentException("id nao pode ser nulo");
        }
        if (id < 0) {
            throw new IllegalArgumentException("id invalido: " + id);
        }
        if (nome == null) {
            throw new IllegalArgumentException("nome nao pode ser nulo");
        }
        if (nome.trim().isEmpty()) {
            throw new IllegalArgumentException("nome nao pode ser vazio");
        }
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
