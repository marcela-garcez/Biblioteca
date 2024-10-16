package com.biblioteca.service;

import com.biblioteca.domains.Livro;
import com.biblioteca.domains.dtos.LivroDTO;
import com.biblioteca.repositories.LivroRepository;
import com.biblioteca.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepo;

    public List<LivroDTO> findAll(){
        //retorna uma lista de GrupoProdutoDRO
        return livroRepo.findAll().stream()
                .map(obj -> new LivroDTO(obj))
                .collect(Collectors.toList());
    }
    public Livro findbyId(int id){
        Optional<Livro> obj = livroRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Livro não encontrado! Id: "+id));
    }
    public Livro findbyisbn(String isbn){
        Optional<Livro> obj = livroRepo.findByisbn(isbn);
        return obj.orElseThrow(() -> new ObjectNotFoundException("ISBN não encontrado! Isbn: "+isbn));
    }
}
