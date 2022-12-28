package com.ricoh.test.controller;

import com.ricoh.test.dto.request.OnCreate;
import com.ricoh.test.dto.perfil.PerfilDto;
import com.ricoh.test.dto.perfil.PerfilRequest;
import com.ricoh.test.model.Perfil;
import com.ricoh.test.model.enums.Status;
import com.ricoh.test.service.PerfilService;
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


@Tag(name = "perfiles")
@RestController
@RequestMapping(value = "/v1/perfil")
@CrossOrigin
@Validated
@AllArgsConstructor
public class PerfilController {

	
	private PerfilService perfilService;

	@PostMapping
	public ResponseEntity<PerfilDto> createPerfil(
					@Validated(OnCreate.class) @RequestBody PerfilRequest personRequest) {

		PerfilDto dto = perfilService.create(personRequest);
		return  ResponseEntity.created(URI.create("/v1/perfil/" + dto.getId()))
				.body(dto);
	}

	@PutMapping(value = "/{uuid}")
	public ResponseEntity<PerfilDto>  updatePerfil(@PathVariable(value = "uuid") UUID uuid,
			@Validated @RequestBody  PerfilRequest personRequest) {
		return  ResponseEntity.ok().body(perfilService.update(uuid, personRequest));
	}

	@GetMapping(value = "/all")
	public ResponseEntity<List<PerfilDto>>  getAllPerfils() {
		return ResponseEntity.ok().body(perfilService.all());
	}

	@GetMapping(value = "/{uuid}")
	public  ResponseEntity<PerfilDto>  showPerfil(
			@PathVariable(value = "uuid") UUID uuid) {
		return  ResponseEntity.ok().body(perfilService.show(uuid));
	}
	
	@DeleteMapping(value = "/{uuid}")
	public  ResponseEntity<PerfilDto>  deletePerfil(
			@PathVariable(value = "uuid") UUID uuid) {
		perfilService.delete(uuid);
		return  ResponseEntity.noContent().build();
	}
	
	


}
