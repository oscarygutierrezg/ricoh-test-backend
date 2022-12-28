package com.ricoh.test.dto.categoria;

import com.ricoh.test.dto.request.OnCreate;
import com.ricoh.test.dto.request.OnLogin;
import com.ricoh.test.dto.request.OnUpdate;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class CategoriaRequest {
	@NotNull(message = "es obligatorio", groups = OnCreate.class)
	@NotEmpty(message = "no debe ser vacío", groups = OnCreate.class)
	@Size(min =1, max =15, message = "la cantidad de caracteres de estar entre 1 y 15", groups = {OnCreate.class,OnUpdate.class})
	@NotEmpty(message = "no debe ser vacío", groups = OnCreate.class)
	private String nombre;
	@NotNull(message = "es obligatorio", groups = OnCreate.class)
	@NotEmpty(message = "no debe ser vacío", groups = OnCreate.class)
	@Size(min =5, max =50, message = "la cantidad de caracteres de estar entre 5 y 50", groups = {OnCreate.class,OnUpdate.class})
	private String descripcion;
}
