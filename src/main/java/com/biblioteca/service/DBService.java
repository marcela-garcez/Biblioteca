package com.biblioteca.service;

import com.biblioteca.domains.Autor;
import com.biblioteca.domains.Editora;
import com.biblioteca.domains.Livro;
import com.biblioteca.domains.enums.Conservacao;
import com.biblioteca.domains.enums.Status;
import com.biblioteca.repositories.AutorRepository;
import com.biblioteca.repositories.EditoraRepository;
import com.biblioteca.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DBService {

    @Autowired
    private AutorRepository autorRepo;

    @Autowired
    private EditoraRepository editoraRepo;

    @Autowired
    private LivroRepository livroRepo;

    public void initDB(){
        Autor autor01 = new Autor(null,"Machado de Assis","01");
        Autor autor02 = new Autor(null,"Monteiro Lobato","02");

        Editora editora01 = new Editora(null,"0001-01","Pé da Letra");
        Editora editora02 = new Editora(null,"0002-02","Tipografia Nacional");

        Livro livro01 = new Livro(null, "O Saci", "0001", 30, LocalDate.now(),new BigDecimal("100.0"),autor02,editora01, Status.LENDO, Conservacao.EXCELENTE);
        Livro livro02 = new Livro(null, "Sitio do Pica pau amarelo", "0002", 56, LocalDate.now(),new BigDecimal("130."),autor02,editora01, Status.LENDO, Conservacao.EXCELENTE);
        Livro livro03 = new Livro(null, "Memórias Póstumas de Brás Cubas", "0003", 41, LocalDate.now(),new BigDecimal("110.0"),autor01,editora02, Status.LENDO, Conservacao.BOM);
        Livro livro04 = new Livro(null, "Dom Casmurro", "0004", 84, LocalDate.now(),new BigDecimal("110.0"),autor01,editora02, Status.LENDO, Conservacao.BOM);

        autorRepo.save(autor01);
        autorRepo.save(autor02);

        editoraRepo.save(editora01);
        editoraRepo.save(editora02);

        livroRepo.save(livro01);
        livroRepo.save(livro02);
        livroRepo.save(livro03);
        livroRepo.save(livro04);

    }


}
