package com.ricoh.test.controller;

import com.ricoh.test.dto.request.OnUpdate;
import com.ricoh.test.dto.usuario.UsuarioDto;
import com.ricoh.test.dto.usuario.UsuarioRequest;
import com.ricoh.test.model.Usuario;
import com.ricoh.test.model.enums.Status;
import com.ricoh.test.service.UsuarioService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@Tag(name = "usuarios")
@RestController
@RequestMapping(value = "/v1/usuario")
@CrossOrigin
@Validated
@AllArgsConstructor
public class UsuarioController {

	
	private UsuarioService usuarioService;
	
	@PutMapping(value = "/{uuid}")
	public ResponseEntity<UsuarioDto>  updateUsuario(@PathVariable(value = "uuid") UUID uuid,
													 @Validated(OnUpdate.class)  @RequestBody  UsuarioRequest personRequest) {
		return  ResponseEntity.ok().body(usuarioService.update(uuid, personRequest));
	}

	@GetMapping
	public ResponseEntity<Page<UsuarioDto>>  getAllUsuarios(
			@RequestParam(required = false) String username,
			@RequestParam(required = false) String nombre,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) Status estatus,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size
	) {
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("nombre", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("estatus", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

		Example<Usuario> example = Example.of(
				Usuario.builder().username(username).email(email).estatus(estatus).nombre(nombre).build(),
				exampleMatcher);
		return ResponseEntity.ok().body(usuarioService.index(example, PageRequest.of(page,size)));
	}

	@GetMapping(value = "/all")
	public ResponseEntity<List<UsuarioDto>>  getAllUsuarios() {
		return ResponseEntity.ok().body(usuarioService.all());
	}

	@GetMapping(value = "/{uuid}")
	public  ResponseEntity<UsuarioDto>  showUsuario(
			@PathVariable(value = "uuid") UUID uuid) {
		return  ResponseEntity.ok().body(usuarioService.show(uuid));
	}
	
	@DeleteMapping(value = "/{uuid}")
	public  ResponseEntity<UsuarioDto>  deleteUsuario(
			@PathVariable(value = "uuid") UUID uuid) {
		usuarioService.delete(uuid);
		return  ResponseEntity.noContent().build();
	}
	
	


}
