package com.ricoh.test.service;

import com.ricoh.test.dto.vacante.VacanteDto;
import com.ricoh.test.dto.vacante.VacanteRequest;
import com.ricoh.test.model.Vacante;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;


public interface VacanteService {

	VacanteDto create(VacanteRequest vacanteRequest);

	VacanteDto update(UUID uuid, VacanteRequest vacanteRequest);

	Page<VacanteDto> index(Example<Vacante> example, Pageable pageable);

	VacanteDto show(UUID uuid);

	List<VacanteDto> all();
	
	void delete(UUID uuid);


}
