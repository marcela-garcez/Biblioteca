package com.biblioteca.service;

import com.biblioteca.domains.Autor;
import com.biblioteca.domains.dtos.AutorDTO;
import com.biblioteca.repositories.AutorRepository;
import com.biblioteca.service.exceptions.DataIntegrityViolationException;
import com.biblioteca.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepo;

    public List<AutorDTO> findAll() {
        return autorRepo.findAll().stream()
                .map(AutorDTO::new)
                .collect(Collectors.toList());
    }

    public Autor findbyId(long id) {
        return autorRepo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Autor não encontrado! Id: " + id));
    }

    public Autor create(AutorDTO dto) {
        dto.setId(null);
        validaAutor(dto);
        Autor obj = new Autor(dto);
        return autorRepo.save(obj);
    }

    private void validaAutor(AutorDTO dto) {
        Optional<Autor> obj = autorRepo.findByDocumentoPessoal(dto.getDocumentoPessoal());
        if (obj.isPresent() && !obj.get().getId().equals(dto.getId())) {
            throw new DataIntegrityViolationException("Documento pessoal já cadastrado!");
        }
    }

}
