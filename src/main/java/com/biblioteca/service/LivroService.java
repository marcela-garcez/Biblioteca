package com.biblioteca.service;

import com.biblioteca.domains.Autor;
import com.biblioteca.domains.Editora;
import com.biblioteca.domains.Livro;
import com.biblioteca.domains.dtos.EditoraDTO;
import com.biblioteca.domains.dtos.LivroDTO;
import com.biblioteca.repositories.LivroRepository;
import com.biblioteca.service.exceptions.DataIntegrityViolationException;
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
        // retorna uma lista de LivroDTO
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

    public Livro create(LivroDTO dto){
        dto.setId(null);
        validaLivro(dto);
        Livro obj = new Livro(dto); // Usando o DTO para criar o objeto Livro
        return livroRepo.save(obj); // Corrigido para "livroRepo"
    }
    private void validaLivro(LivroDTO dto){
        Optional<Livro> obj = livroRepo.findByisbn(dto.getIsbn());
        if (obj.isPresent() && obj.get().getIdLivro() != dto.getId()){
            throw new DataIntegrityViolationException("ISBN já cadastrado!");
        }
        Optional<Livro> livro = livroRepo.findByisbn(dto.getIsbn());
        if (!livro.isPresent()){
            throw new DataIntegrityViolationException("Editora - " +dto.getIsbn()+ "" +
                    "não está cadastrado!");
        }
    }
}


