package com.ricoh.test.dto.vacante;

import com.ricoh.test.dto.request.OnCreate;
import com.ricoh.test.dto.request.OnUpdate;
import com.ricoh.test.model.enums.Status;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class VacanteRequest {
	@NotNull(message = "es obligatorio", groups = OnCreate.class)
	@NotEmpty(message = "no debe ser vacío", groups = OnCreate.class)
	@Size(min =1, max =50, message = "la cantidad de caracteres de estar entre 1 y 50", groups = {OnCreate.class, OnUpdate.class})
	private String destacado;
	@NotNull(message = "es obligatorio", groups = OnCreate.class)
	@NotEmpty(message = "no debe ser vacío", groups = OnCreate.class)
	@Size(min =1, max =50, message = "la cantidad de caracteres de estar entre 1 y 50", groups = {OnCreate.class, OnUpdate.class})
	private String nombre;
	@NotNull(message = "es obligatorio", groups = OnCreate.class)
	@NotEmpty(message = "no debe ser vacío", groups = OnCreate.class)
	@Size(min =1, max =15, message = "la cantidad de caracteres de estar entre 1 y 15", groups = {OnCreate.class, OnUpdate.class})
	private String descripcion;
	@NotNull(message = "es obligatorio", groups = OnCreate.class)
	private LocalDateTime fecha;
	@NotNull(message = "es obligatorio", groups = OnCreate.class)
	@NotEmpty(message = "no debe ser vacío", groups = OnCreate.class)
	@Size(min =10, max =500, message = "la cantidad de caracteres de estar entre 10 y 500", groups = {OnCreate.class, OnUpdate.class})
	private String imagen;
	@NotNull(message = "es obligatorio", groups = OnCreate.class)
	@NotEmpty(message = "no debe ser vacío", groups = OnCreate.class)
	@Size(min =10, max =50, message = "la cantidad de caracteres de estar entre 10 y 50", groups = {OnCreate.class, OnUpdate.class})
	private String detalles;
	private UUID categoriaId;
	private Status estatus;
}
