package com.ricoh.test.dto.perfil;

import com.ricoh.test.dto.request.OnCreate;
import com.ricoh.test.dto.request.OnLogin;
import com.ricoh.test.dto.request.OnUpdate;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class PerfilRequest {
	@NotNull(message = "es obligatorio", groups = OnCreate.class)
	@NotEmpty(message = "no debe ser vacío", groups = OnCreate.class)
	@Size(min =1, max =20, message = "la cantidad de caracteres de estar entre 1 y 20", groups = {OnCreate.class, OnUpdate.class})
	private String perfil;
}
