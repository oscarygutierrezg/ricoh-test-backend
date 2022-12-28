package com.ricoh.test.dto.usuario;

import com.ricoh.test.model.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UsuarioDto {
	private UUID id;
	private String email;
	private String username;
	private String nombre;
	private Status estatus;
	private LocalDateTime fechaRegistro;
}
