package com.ricoh.test.service.impl;

import com.ricoh.test.dto.perfil.PerfilDto;
import com.ricoh.test.dto.perfil.PerfilMapper;
import com.ricoh.test.dto.perfil.PerfilRequest;
import com.ricoh.test.model.Categoria;
import com.ricoh.test.model.Perfil;
import com.ricoh.test.model.enums.Status;
import com.ricoh.test.reposiroty.CategoriaRepository;
import com.ricoh.test.reposiroty.PerfilRepository;
import com.ricoh.test.service.PerfilService;
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
public class PerfilServiceImpl implements PerfilService {

    private final PerfilRepository perfilRepository;
    private final CategoriaRepository categoriaRepository;
    private final PerfilMapper perfilMapper;

    @Override
    public PerfilDto create(PerfilRequest perfilRequest) {
        var perfil = perfilMapper.toModel(perfilRequest);
        return perfilMapper.toDto(perfilRepository.save(perfil));
    }

    @Override
    public PerfilDto update(UUID uuid, PerfilRequest perfilRequest) {
        var perfil = findPerfilById(uuid);
        perfilMapper.updateModel(perfilRequest, perfil);
        return perfilMapper.toDto(perfilRepository.save(perfil));

    }

    @Override
    public PerfilDto show(UUID uuid) {
        return perfilMapper.toDto(findPerfilById(uuid)) ;
    }

    @Override
    public List<PerfilDto> all() {
        return perfilRepository.findAll().stream().map(perfilMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(UUID uuid) {
        var perfil = findPerfilById(uuid);
        perfilRepository.delete(perfil);
    }

    private Perfil findPerfilById(UUID uuid) {
        return perfilRepository.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException(
                        "Perfil con UUID "+uuid+" no existe."
                ));
    }
}
