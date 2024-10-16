package com.biblioteca.resources;

import com.biblioteca.domains.Autor;
import com.biblioteca.domains.dtos.AutorDTO;
import com.biblioteca.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
