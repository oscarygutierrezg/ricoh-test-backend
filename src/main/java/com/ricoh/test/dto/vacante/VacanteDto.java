package com.ricoh.test.dto.vacante;

import com.ricoh.test.dto.categoria.CategoriaDto;
import com.ricoh.test.model.Categoria;
import com.ricoh.test.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class VacanteDto {
	private UUID id;
	private String destacado;
	private String nombre;
	private String descripcion;
	private LocalDateTime fecha;
	private String imagen;
	private String detalles;
	private Status estatus;
	private CategoriaDto categoria;
}
