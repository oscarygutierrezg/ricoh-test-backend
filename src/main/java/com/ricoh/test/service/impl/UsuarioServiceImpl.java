package com.ricoh.test.service.impl;

import com.ricoh.test.dto.usuario.UsuarioDto;
import com.ricoh.test.dto.usuario.UsuarioMapper;
import com.ricoh.test.dto.usuario.UsuarioRequest;
import com.ricoh.test.model.Usuario;
import com.ricoh.test.model.enums.Status;
import com.ricoh.test.reposiroty.CategoriaRepository;
import com.ricoh.test.reposiroty.UsuarioRepository;
import com.ricoh.test.service.UsuarioService;
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
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final UsuarioMapper usuarioMapper;
    

    @Override
    public UsuarioDto update(UUID uuid, UsuarioRequest usuarioRequest) {
        var usuario = findUsuarioById(uuid);
        usuarioMapper.updateModel(usuarioRequest, usuario);
        return usuarioMapper.toDto(usuarioRepository.save(usuario));

    }

    @Override
    public Page<UsuarioDto> index(Example<Usuario> example, Pageable pageable) {
        return usuarioRepository.findAll(example, pageable).map(usuarioMapper::toDto);
    }

    @Override
    public UsuarioDto show(UUID uuid) {
        return usuarioMapper.toDto(findUsuarioById(uuid)) ;
    }

    @Override
    public List<UsuarioDto> all() {
        return usuarioRepository.findAll().stream().map(usuarioMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(UUID uuid) {
        var usuario = findUsuarioById(uuid);
        usuario.setEstatus(Status.DELETED);
        usuarioRepository.delete(usuario);
    }

    private Usuario findUsuarioById(UUID uuid) {
        return usuarioRepository.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException(
                        "Usuario con UUID "+uuid+" no existe."
                ));
    }
}
