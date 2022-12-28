package com.ricoh.test.dto.categoria;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CategoriaDto {
	private UUID id;
	private String nombre;
	private String descripcion;
}
