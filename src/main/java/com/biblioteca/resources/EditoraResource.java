package com.biblioteca.resources;


import com.biblioteca.domains.Autor;
import com.biblioteca.domains.Editora;
import com.biblioteca.domains.Livro;
import com.biblioteca.domains.dtos.AutorDTO;
import com.biblioteca.domains.dtos.EditoraDTO;
import com.biblioteca.domains.dtos.LivroDTO;
import com.biblioteca.service.EditoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/editora")
public class EditoraResource {

    @Autowired
    private EditoraService editoraService;

    @GetMapping
    public ResponseEntity<List<EditoraDTO>> findAll(){

        return  ResponseEntity.ok().body(editoraService.findAll());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<EditoraDTO> findById(@PathVariable Integer id){
        Editora obj = this.editoraService.findbyId(id);
        return ResponseEntity.ok().body(new EditoraDTO(obj));
    }
    @GetMapping(value = "/cnpj/{cnpj}")
    public ResponseEntity<EditoraDTO> findById(@PathVariable String cnpj){
        Editora obj =this.editoraService.findbycnpj(cnpj);
        return ResponseEntity.ok().body(new EditoraDTO(obj));
    }
}
