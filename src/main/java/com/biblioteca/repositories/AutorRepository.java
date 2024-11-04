package com.biblioteca.repositories;

import com.biblioteca.domains.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {

    Optional<Autor> findByDocumentoPessoal(String documentoPessoal);
}
