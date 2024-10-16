package com.biblioteca.service;

import com.biblioteca.domains.Editora;
import com.biblioteca.domains.Livro;
import com.biblioteca.domains.dtos.EditoraDTO;
import com.biblioteca.repositories.EditoraRepository;
import com.biblioteca.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository editoraRepo;

    public List<EditoraDTO> findAll(){
        //retorna uma lista de GrupoProdutoDRO
        return editoraRepo.findAll().stream()
                .map(obj -> new EditoraDTO(obj))
                .collect(Collectors.toList());
    }
    public Editora findbyId (int id){
        Optional<Editora> obj = editoraRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Editora não encontrada! Id: "+id));
    }
    public Editora findbycnpj(String cnpj){
        Optional<Editora> obj = editoraRepo.findBycnpj(cnpj);
        return obj.orElseThrow(() -> new ObjectNotFoundException("CNPJ não encontrado! Cnpj: "+cnpj));
    }
}
