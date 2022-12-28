package com.ricoh.test.controller;

import com.ricoh.test.dto.request.OnCreate;
import com.ricoh.test.dto.vacante.VacanteDto;
import com.ricoh.test.dto.vacante.VacanteRequest;
import com.ricoh.test.model.Vacante;
import com.ricoh.test.model.enums.Status;
import com.ricoh.test.service.VacanteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;


@Tag(name = "vacantes")
@RestController
@RequestMapping(value = "/v1/vacante")
@CrossOrigin
@Validated
@AllArgsConstructor
public class VacanteController {

	
	private VacanteService vacanteService;

	@PostMapping
	public ResponseEntity<VacanteDto> createVacante(
					@Validated(OnCreate.class) @RequestBody VacanteRequest personRequest) {

		VacanteDto dto = vacanteService.create(personRequest);
		return  ResponseEntity.created(URI.create("/v1/vacante/" + dto.getId()))
				.body(dto);
	}

	@PutMapping(value = "/{uuid}")
	public ResponseEntity<VacanteDto>  updateVacante(@PathVariable(value = "uuid") UUID uuid,
			@Validated @RequestBody  VacanteRequest personRequest) {
		return  ResponseEntity.ok().body(vacanteService.update(uuid, personRequest));
	}

	@GetMapping
	public ResponseEntity<Page<VacanteDto>>  getAllVacantes(
			@RequestParam(required = false) String destacado,
			@RequestParam(required = false) String nombre,
			@RequestParam(required = false) String descripcion,
			@RequestParam(required = false) String detalles,
			@RequestParam(required = false) Status estatus,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size
	) {
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("destacado", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("nombre", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("detalles", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("estatus", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

		Example<Vacante> example = Example.of(
				Vacante.builder().destacado(destacado).descripcion(descripcion).estatus(estatus).detalles(detalles).nombre(nombre).build(),
				exampleMatcher);
		return ResponseEntity.ok().body(vacanteService.index(example, PageRequest.of(page,size)));
	}

	@GetMapping(value = "/all")
	public ResponseEntity<List<VacanteDto>>  getAllVacantes() {
		return ResponseEntity.ok().body(vacanteService.all());
	}

	@GetMapping(value = "/{uuid}")
	public  ResponseEntity<VacanteDto>  showVacante(
			@PathVariable(value = "uuid") UUID uuid) {
		return  ResponseEntity.ok().body(vacanteService.show(uuid));
	}
	
	@DeleteMapping(value = "/{uuid}")
	public  ResponseEntity<VacanteDto>  deleteVacante(
			@PathVariable(value = "uuid") UUID uuid) {
		vacanteService.delete(uuid);
		return  ResponseEntity.noContent().build();
	}
	
	


}
