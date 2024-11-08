package com.biblioteca.domains;

import com.biblioteca.domains.dtos.EditoraDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "editora")
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_editora")
    private Integer id;

    @NotNull @NotBlank
    @Column(unique = true)
    private String cnpj;


    @NotNull @NotBlank
    private String razaoSocial;

    @JsonIgnore
    @OneToMany(mappedBy = "editora")
    private List<Livro> livros = new ArrayList<>();

    public List<Livro> getLivros(){
        return livros;
    }

    public Editora() {
    }

    public Editora(Integer id, String cnpj, String razaoSocial) {
        this.id = id;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }
    public Editora(EditoraDTO dto) {
        this.id = dto.getId();
        this.cnpj = dto.getCnpj();
        this.razaoSocial = dto.getRazaoSocial();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull @NotBlank String getCnpj() {
        return cnpj;
    }

    public void setCnpj(@NotNull @NotBlank String cnpj) {
        this.cnpj = cnpj;
    }

    public @NotNull @NotBlank String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(@NotNull @NotBlank String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Editora editora = (Editora) o;
        return id == editora.id && Objects.equals(cnpj, editora.cnpj) && Objects.equals(razaoSocial, editora.razaoSocial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cnpj, razaoSocial);
    }
}