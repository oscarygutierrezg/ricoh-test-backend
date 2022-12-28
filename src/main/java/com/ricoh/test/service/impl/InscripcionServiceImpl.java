package com.ricoh.test.service.impl;

import com.ricoh.test.dto.inscripcion.InscripcionDto;
import com.ricoh.test.dto.inscripcion.InscripcionRequest;
import com.ricoh.test.dto.usuario.UsuarioDto;
import com.ricoh.test.dto.usuario.UsuarioMapper;
import com.ricoh.test.dto.vacante.VacanteDto;
import com.ricoh.test.dto.vacante.VacanteMapper;
import com.ricoh.test.exceptions.InscripcionAlreadyExistException;
import com.ricoh.test.model.Inscripcion;
import com.ricoh.test.model.Usuario;
import com.ricoh.test.model.Vacante;
import com.ricoh.test.reposiroty.InscripcionRepository;
import com.ricoh.test.reposiroty.UsuarioRepository;
import com.ricoh.test.reposiroty.VacanteRepository;
import com.ricoh.test.service.InscripcionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InscripcionServiceImpl implements InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final UsuarioRepository usuarioRepository;
    private final VacanteRepository vacanteRepository;
    private final UsuarioMapper usuarioMapper;
    private final VacanteMapper vacanteMapper;

    @Override
    public InscripcionDto create(InscripcionRequest inscripcionRequest) {
       if (inscripcionRepository.findByUsuarioIdAndVacanteId(inscripcionRequest.getUsuarioId(),inscripcionRequest.getVacanteId()).isPresent()){
           throw  new InscripcionAlreadyExistException(
                   "Usuario con UUID "+inscripcionRequest.getUsuarioId()+" ya esta inscrito en la Vacante con UUID "+inscripcionRequest.getVacanteId());

       }
        var vacante = findVacanteById(inscripcionRequest.getVacanteId());
        var usuario = findUsuarioById(inscripcionRequest.getUsuarioId());
        var inscripcion =  inscripcionRepository.save(
                Inscripcion.builder().
                fechaInscripcion(inscripcionRequest.getFechaInscripcion()).
                usuarioId(usuario.getId()).
                vacanteId(vacante.getId()).
                build());
        return toDto(vacante,usuario, inscripcion);
    }

    private InscripcionDto toDto(Vacante vacante, Usuario usuario, Inscripcion inscripcion) {
        return InscripcionDto.builder().
                fechaInscripcion(inscripcion.getFechaInscripcion()).
                id(inscripcion.getId()).
                usuario(usuarioMapper.toDto(usuario)).
                vacante(vacanteMapper.toDto(vacante)).
                build();
    }

    @Override
    public InscripcionDto update(UUID uuid, InscripcionRequest inscripcionRequest) {
        var inscripcion =  findInscripcionById(uuid);
        var vacante = findVacanteById(inscripcionRequest.getVacanteId());
        var usuario = findUsuarioById(inscripcionRequest.getUsuarioId());
        if(inscripcionRequest.getFechaInscripcion()!= null) {
            inscripcion.setFechaInscripcion(inscripcionRequest.getFechaInscripcion());
        }
        return toDto(vacante,usuario, inscripcionRepository.save(inscripcion));
    }

    @Override
    public InscripcionDto show(UUID uuid) {
        var inscripcion = findInscripcionById(uuid);
        var vacante = findVacanteById(inscripcion.getVacanteId());
        var usuario = findUsuarioById(inscripcion.getUsuarioId());
        return toDto(vacante, usuario,inscripcion ) ;
    }

    @Override
    public List<InscripcionDto> all() {
        return inscripcionRepository.findAll().stream().map(inscripcion -> {
                    var vacante = findVacanteById(inscripcion.getVacanteId());
                    var usuario = findUsuarioById(inscripcion.getUsuarioId());
                    return  toDto(vacante, usuario, inscripcion);
                }
        ).collect(Collectors.toList());
    }

    @Override
    public List<VacanteDto> allVacanteByUsuarioId(UUID usuarioId) {
        return  inscripcionRepository.findVacanteByUsuarioId(usuarioId).stream().map(vacanteMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<UsuarioDto> allyUsuarioByVacanteId(UUID vacanteId) {
        return  inscripcionRepository.findUsuarioByVacanteId(vacanteId).stream().map(usuarioMapper::toDto).collect(Collectors.toList());

    }

    @Override
    public void delete(UUID uuid) {
        var inscripcion = findInscripcionById(uuid);
        inscripcionRepository.delete(inscripcion);
    }

    private Inscripcion findInscripcionById(UUID uuid) {
        return inscripcionRepository.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException(
                        "Inscripcion con UUID "+uuid+" no existe."
                ));
    }


    private Usuario findUsuarioById(UUID uuid) {
        return usuarioRepository.findById(uuid).orElseThrow(
                () -> new  EntityNotFoundException(
                        "Usuario con UUID "+uuid+" no existe."
                ));
    }


    private Vacante findVacanteById(UUID uuid) {
        return vacanteRepository.findById(uuid).orElseThrow(
                () -> new  EntityNotFoundException(
                        "Vacante con UUID "+uuid+" no existe."
                ));
    }
}
