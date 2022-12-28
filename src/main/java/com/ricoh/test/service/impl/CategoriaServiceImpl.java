package com.ricoh.test.service.impl;

import com.ricoh.test.dto.categoria.CategoriaDto;
import com.ricoh.test.dto.categoria.CategoriaMapper;
import com.ricoh.test.dto.categoria.CategoriaRequest;
import com.ricoh.test.model.Categoria;
import com.ricoh.test.model.Categoria;
import com.ricoh.test.model.enums.Status;
import com.ricoh.test.reposiroty.CategoriaRepository;
import com.ricoh.test.reposiroty.CategoriaRepository;
import com.ricoh.test.service.CategoriaService;
import com.ricoh.test.service.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    public CategoriaDto create(CategoriaRequest categoriaRequest) {
        var categoria = categoriaMapper.toModel(categoriaRequest);
        return categoriaMapper.toDto(categoriaRepository.save(categoria));
    }

    @Override
    public CategoriaDto update(UUID uuid, CategoriaRequest categoriaRequest) {
        var categoria = findCategoriaById(uuid);
        categoriaMapper.updateModel(categoriaRequest,categoria);
        return categoriaMapper.toDto(categoriaRepository.save(categoria));

    }

    @Override
    public CategoriaDto show(UUID uuid) {
        return categoriaMapper.toDto(findCategoriaById(uuid)) ;
    }

    @Override
    public List<CategoriaDto> all() {
        return categoriaRepository.findAll().stream().map(categoriaMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(UUID uuid) {
        var categoria = findCategoriaById(uuid);
        categoriaRepository.delete(categoria);
    }

    private Categoria findCategoriaById(UUID uuid) {
        if(uuid == null)
            return null;
        return categoriaRepository.findById(uuid).orElseThrow(
                () -> new  EntityNotFoundException(
                        "Categoria con UUID "+uuid+" no existe."
                ));
    }



}
