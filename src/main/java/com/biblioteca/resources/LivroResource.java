package com.biblioteca.resources;

import com.biblioteca.domains.Livro;
import com.biblioteca.domains.dtos.LivroDTO;
import com.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/livro")
public class LivroResource {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public ResponseEntity<List<LivroDTO>> findAll(){
        return ResponseEntity.ok().body(livroService.findAll());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<LivroDTO> findById(@PathVariable Integer id){
        Livro obj = this.livroService.findbyId(id);
        return ResponseEntity.ok().body(new LivroDTO(obj));
    }
    @GetMapping(value = "/isbn/{isbn}")
    public ResponseEntity<LivroDTO> findById(@PathVariable String isbn){
    Livro obj =this.livroService.findbyisbn(isbn);
    return ResponseEntity.ok().body(new LivroDTO(obj));
    }
}
