package com.ricoh.test.service;

import com.ricoh.test.dto.categoria.CategoriaDto;
import com.ricoh.test.dto.categoria.CategoriaRequest;
import com.ricoh.test.model.Categoria;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;


public interface CategoriaService {

	CategoriaDto create(CategoriaRequest categoriaRequest);

	CategoriaDto update(UUID uuid, CategoriaRequest categoriaRequest);

	CategoriaDto show(UUID uuid);

	List<CategoriaDto> all();
	
	void delete(UUID uuid);


}
