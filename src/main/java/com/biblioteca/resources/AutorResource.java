package com.biblioteca.resources;

import com.biblioteca.domains.Autor;
import com.biblioteca.domains.dtos.AutorDTO;
import com.biblioteca.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/autor")
public class AutorResource {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public ResponseEntity<List<AutorDTO>> findAll(){
        return ResponseEntity.ok().body(autorService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AutorDTO> findById(@PathVariable long id){
        Autor obj = this.autorService.findbyId(id);
        return ResponseEntity.ok().body(new AutorDTO(obj));
    }
    @PostMapping
    public ResponseEntity<AutorDTO> create( @RequestBody AutorDTO dto){
    Autor autor = autorService.create(dto);
    //cria o URI para o recurso criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(autor.getId()).toUri();
        //retorna a resposta com status 201 Created o local do recurso creado
        return ResponseEntity.created(uri).build();
    }
}
