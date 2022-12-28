package com.ricoh.test.service;

import com.ricoh.test.dto.perfil.PerfilDto;
import com.ricoh.test.dto.perfil.PerfilRequest;
import com.ricoh.test.model.Perfil;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;


public interface PerfilService {

	PerfilDto create(PerfilRequest perfilRequest);

	PerfilDto update(UUID uuid, PerfilRequest perfilRequest);

	PerfilDto show(UUID uuid);

	List<PerfilDto> all();
	
	void delete(UUID uuid);


}
