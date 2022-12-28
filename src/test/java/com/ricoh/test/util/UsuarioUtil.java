package com.ricoh.test.util;

import com.github.javafaker.Faker;
import com.ricoh.test.dto.usuario.UsuarioDto;
import com.ricoh.test.dto.usuario.UsuarioRequest;
import com.ricoh.test.model.Usuario;

import java.util.UUID;

public class UsuarioUtil {
	
	public Faker faker = new Faker();
	
	
	public Usuario creatModel(Usuario p) {
		p.setId(UUID.randomUUID());
		return p;
	}

	public Usuario toModel(UsuarioRequest usuarioDto) {

		return Usuario.builder()
				.email(usuarioDto.getEmail())
				.password(usuarioDto.getPassword())
				.nombre(usuarioDto.getNombre())
				.username(usuarioDto.getUsername())
				.build();
	}

	public UsuarioDto toDto(Usuario usuario) {
		return UsuarioDto.builder()
				.email(usuario.getEmail())
				.nombre(usuario.getNombre())
				.username(usuario.getUsername())
				.fechaRegistro(usuario.getFechaRegistro())
				.estatus(usuario.getEstatus())
				.id(usuario.getId())
				.build();
	}


	public Usuario createUsuario() {

		return  Usuario.builder()
				.email(faker.internet().emailAddress())
				.password(faker.internet().password())
				.nombre(faker.name().firstName())
				.username(faker.name().username())
				.id(UUID.randomUUID())
				.build();
	}

	public UsuarioRequest createUsuarioRequest() {
		return  UsuarioRequest.builder()
				.email(faker.internet().emailAddress())
				.password(faker.internet().password())
				.nombre(faker.name().firstName())
				.username(faker.name().username())
				.build();
	}

	public UsuarioRequest createUsuarioRequestLogin() {
		return  UsuarioRequest.builder()
				.username("test")
				.password("test")
				.build();
	}

}
