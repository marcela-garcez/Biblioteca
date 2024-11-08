package com.biblioteca.service;

import com.biblioteca.domains.Autor;
import com.biblioteca.domains.Editora;
import com.biblioteca.domains.Livro;
import com.biblioteca.domains.dtos.AutorDTO;
import com.biblioteca.domains.dtos.EditoraDTO;
import com.biblioteca.repositories.EditoraRepository;
import com.biblioteca.service.exceptions.DataIntegrityViolationException;
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
    public Editora create(EditoraDTO dto){
        dto.setId(null);
        validaEditora(dto);
        Editora obj = new Editora(dto);
        return editoraRepo.save(obj);
    }
    private void validaEditora(EditoraDTO dto){
        Optional<Editora> obj = editoraRepo.findBycnpj(dto.getCnpj());
        if (obj.isPresent() && obj.get().getId() != dto.getId()){
            throw new DataIntegrityViolationException("CNPJ já cadastrado!");
        }

    }
    public Editora update(Integer id, EditoraDTO objDto){
        objDto.setId(id);
        Editora oldObj = findbyId(id);
        oldObj = new Editora(objDto);
        return editoraRepo.save(oldObj);
    }
    public void delete(Integer id){
        Editora obj = findbyId(id);
        if(obj.getLivros().size()>0){
            throw new DataIntegrityViolationException("Editora não pode ser deletada, pois possui livros vinculados!");
        }
        editoraRepo.deleteById(id);
    }
}