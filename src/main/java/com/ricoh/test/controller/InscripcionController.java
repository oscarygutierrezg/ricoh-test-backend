package com.ricoh.test.controller;

import com.ricoh.test.dto.inscripcion.InscripcionDto;
import com.ricoh.test.dto.inscripcion.InscripcionRequest;
import com.ricoh.test.dto.request.OnCreate;
import com.ricoh.test.dto.usuario.UsuarioDto;
import com.ricoh.test.dto.vacante.VacanteDto;
import com.ricoh.test.service.InscripcionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;


@Tag(name = "inscripciones")
@RestController
@RequestMapping(value = "/v1/inscripcion")
@CrossOrigin
@Validated
@AllArgsConstructor
public class InscripcionController {

	
	private InscripcionService inscripcionService;

	@PostMapping
	public ResponseEntity<InscripcionDto> createInscripcion(
					@Validated(OnCreate.class) @RequestBody InscripcionRequest inscripcionRequest) {

		InscripcionDto dto = inscripcionService.create(inscripcionRequest);
		return  ResponseEntity.created(URI.create("/v1/inscripcion/" + dto.getId()))
				.body(dto);
	}

	@PutMapping(value = "/{uuid}")
	public ResponseEntity<InscripcionDto>  updateInscripcion(@PathVariable(value = "uuid") UUID uuid,
			 @RequestBody  InscripcionRequest inscripcionRequest) {
		return  ResponseEntity.ok().body(inscripcionService.update(uuid, inscripcionRequest));
	}

	@GetMapping(value = "/all")
	public ResponseEntity<List<InscripcionDto>>  getAllInscripcions() {
		return ResponseEntity.ok().body(inscripcionService.all());
	}

	@GetMapping(value = "/{uuid}")
	public  ResponseEntity<InscripcionDto>  showInscripcion(
			@PathVariable(value = "uuid") UUID uuid) {
		return  ResponseEntity.ok().body(inscripcionService.show(uuid));
	}

	@GetMapping(value = "/vacantes/{usuarioId}")
	public  ResponseEntity<List<VacanteDto>>  getVacantes(
			@PathVariable(value = "usuarioId") UUID usuarioId) {
		return  ResponseEntity.ok().body(inscripcionService.allVacanteByUsuarioId(usuarioId));
	}

	@GetMapping(value = "/usuarios/{vacanteId}")
	public  ResponseEntity<List<UsuarioDto>>  getUsuarios(
			@PathVariable(value = "vacanteId") UUID vacanteId) {
		return  ResponseEntity.ok().body(inscripcionService.allyUsuarioByVacanteId(vacanteId));
	}

	
	@DeleteMapping(value = "/{uuid}")
	public  ResponseEntity<InscripcionDto>  deleteInscripcion(
			@PathVariable(value = "uuid") UUID uuid) {
		inscripcionService.delete(uuid);
		return  ResponseEntity.noContent().build();
	}
	
	


}
