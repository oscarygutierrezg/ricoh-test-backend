package com.ricoh.test.service;


import com.ricoh.test.dto.usuario.UsuarioDto;
import com.ricoh.test.dto.usuario.UsuarioRequest;
import com.ricoh.test.model.Usuario;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;


public interface UsuarioService {

	UsuarioDto update(UUID uuid, UsuarioRequest usuarioRequest);

	Page<UsuarioDto> index(Example<Usuario> example, Pageable pageable);

	UsuarioDto show(UUID uuid);

	List<UsuarioDto> all();
	
	void delete(UUID uuid);


}
