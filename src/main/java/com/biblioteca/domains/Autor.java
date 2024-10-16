package com.biblioteca.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;


@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_autor")
    private int id;

    @NotNull @NotBlank
    private String nome;

    @NotNull @NotBlank
    private String documentoPessoal;

    public Autor() {
    }

    public Autor(int id, String nome, String documentoPessoal) {
        this.id = id;
        this.nome = nome;
        this.documentoPessoal = documentoPessoal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotNull @NotBlank String nome) {
        this.nome = nome;
    }

    public @NotNull @NotBlank String getDocumentoPessoal() {
        return documentoPessoal;
    }

    public void setDocumentoPessoal(@NotNull @NotBlank String documentoPessoal) {
        this.documentoPessoal = documentoPessoal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return id == autor.id && Objects.equals(nome, autor.nome) && Objects.equals(documentoPessoal, autor.documentoPessoal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, documentoPessoal);
    }
}
