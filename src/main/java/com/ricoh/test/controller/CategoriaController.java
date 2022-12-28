package com.ricoh.test.controller;

import com.ricoh.test.dto.categoria.CategoriaDto;
import com.ricoh.test.dto.categoria.CategoriaRequest;
import com.ricoh.test.dto.request.OnCreate;
import com.ricoh.test.service.CategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
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


@Tag(name = "categorias")
@RestController
@RequestMapping(value = "/v1/categoria")
@CrossOrigin
@Validated
@AllArgsConstructor
public class CategoriaController {

	
	private CategoriaService categoriaService;

	@PostMapping
	public ResponseEntity<CategoriaDto> createCategoria(
					@Validated(OnCreate.class) @RequestBody CategoriaRequest personRequest) {

		CategoriaDto dto = categoriaService.create(personRequest);
		return  ResponseEntity.created(URI.create("/v1/categoria/" + dto.getId()))
				.body(dto);
	}

	@PutMapping(value = "/{uuid}")
	public ResponseEntity<CategoriaDto>  updateCategoria(@PathVariable(value = "uuid") UUID uuid,
			@Validated @RequestBody  CategoriaRequest personRequest) {
		return  ResponseEntity.ok().body(categoriaService.update(uuid, personRequest));
	}

	@GetMapping(value = "/all")
	public ResponseEntity<List<CategoriaDto>>  getAllCategorias() {
		return ResponseEntity.ok().body(categoriaService.all());
	}

	@GetMapping(value = "/{uuid}")
	public  ResponseEntity<CategoriaDto>  showCategoria(
			@PathVariable(value = "uuid") UUID uuid) {
		return  ResponseEntity.ok().body(categoriaService.show(uuid));
	}
	
	@DeleteMapping(value = "/{uuid}")
	public  ResponseEntity<CategoriaDto>  deleteCategoria(
			@PathVariable(value = "uuid") UUID uuid) {
		categoriaService.delete(uuid);
		return  ResponseEntity.noContent().build();
	}
	
	


}
