package com.ricoh.test.service.impl;

import com.ricoh.test.dto.vacante.VacanteDto;
import com.ricoh.test.dto.vacante.VacanteMapper;
import com.ricoh.test.dto.vacante.VacanteRequest;
import com.ricoh.test.model.Categoria;
import com.ricoh.test.model.Vacante;
import com.ricoh.test.model.enums.Status;
import com.ricoh.test.reposiroty.CategoriaRepository;
import com.ricoh.test.reposiroty.VacanteRepository;
import com.ricoh.test.service.VacanteService;
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
public class VacanteServiceImpl implements VacanteService {

    private final VacanteRepository vacanteRepository;
    private final CategoriaRepository categoriaRepository;
    private final VacanteMapper vacanteMapper;

    @Override
    public VacanteDto create(VacanteRequest vacanteRequest) {
        var vacante = vacanteMapper.toModel(vacanteRequest);
        vacante.setEstatus(Status.CREATED);
        var categoria = findCategoriaById(vacanteRequest.getCategoriaId());
        vacante.setCategoria(categoria);
        return vacanteMapper.toDto(vacanteRepository.save(vacante));
    }

    @Override
    public VacanteDto update(UUID uuid, VacanteRequest vacanteRequest) {
        var vacante = findVacanteById(uuid);
        var categoria = findCategoriaById(vacanteRequest.getCategoriaId());
        vacante.setCategoria(categoria);
        vacanteMapper.updateModel(vacanteRequest,vacante);
        return vacanteMapper.toDto(vacanteRepository.save(vacante));

    }

    @Override
    public Page<VacanteDto> index(Example<Vacante> example, Pageable pageable) {
        return vacanteRepository.findAll(example, pageable).map(vacanteMapper::toDto);
    }

    @Override
    public VacanteDto show(UUID uuid) {
        return vacanteMapper.toDto(findVacanteById(uuid)) ;
    }

    @Override
    public List<VacanteDto> all() {
        return vacanteRepository.findAll().stream().map(vacanteMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(UUID uuid) {
        var vacante = findVacanteById(uuid);
        vacante.setEstatus(Status.DELETED);
        vacanteRepository.delete(vacante);
    }

    private Vacante findVacanteById(UUID uuid) {
        return vacanteRepository.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException(
                        "Vacante con UUID "+uuid+" no existe."
                ));
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
