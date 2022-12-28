package com.ricoh.test.service;

import com.ricoh.test.dto.inscripcion.InscripcionDto;
import com.ricoh.test.dto.inscripcion.InscripcionRequest;
import com.ricoh.test.dto.usuario.UsuarioDto;
import com.ricoh.test.dto.vacante.VacanteDto;
import com.ricoh.test.model.Vacante;

import java.util.List;
import java.util.UUID;


public interface InscripcionService {

	InscripcionDto create(InscripcionRequest inscripcionRequest);

	InscripcionDto update(UUID uuid, InscripcionRequest inscripcionRequest);

	InscripcionDto show(UUID uuid);

	List<InscripcionDto> all();

	List<VacanteDto> allVacanteByUsuarioId(UUID usuarioId);

	List<UsuarioDto> allyUsuarioByVacanteId(UUID vacanteId);
	
	void delete(UUID uuid);


}
