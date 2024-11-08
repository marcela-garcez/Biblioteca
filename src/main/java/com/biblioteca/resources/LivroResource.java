package com.biblioteca.resources;

import com.biblioteca.domains.Livro;
import com.biblioteca.domains.dtos.LivroDTO;
import com.biblioteca.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    @PostMapping
    public ResponseEntity<LivroDTO> create(@Valid @RequestBody LivroDTO dto) {  // Adicionado @PostMapping
        Livro livro = livroService.create(dto);  // Usando livroService para criar o Livro
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(livro.getIdLivro()).toUri();
        return ResponseEntity.created(uri).body(new LivroDTO(livro));  // Retorna o DTO no corpo da resposta
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<LivroDTO> update(@PathVariable Long id, @Valid @RequestBody LivroDTO objDto){
        Livro Obj = livroService.update(id, objDto);
        return ResponseEntity.ok().body(new LivroDTO(Obj));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<LivroDTO> delete(@PathVariable Long id){
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}