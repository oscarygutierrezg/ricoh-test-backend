package com.ricoh.test.dto.usuario;

import com.ricoh.test.dto.request.OnCreate;
import com.ricoh.test.dto.request.OnLogin;
import com.ricoh.test.dto.request.OnUpdate;
import com.ricoh.test.model.enums.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioRequest {

	@NotNull(message = "es obligatorio" , groups = OnCreate.class)
	@NotEmpty(message = "no debe ser vacío" , groups = OnCreate.class)
	@Email(message = "no es un email valido" , groups = OnCreate.class)
	private String email;
	@NotNull(message = "es obligatorio", groups = {OnCreate.class, OnLogin.class})
	@NotEmpty(message = "no debe ser vacío", groups = {OnCreate.class, OnLogin.class})
	@Size(min =6, max =20, message = "la cantidad de caracteres de estar entre 6 y 20", groups = {OnCreate.class, OnUpdate.class})
	private String password;
	@NotNull(message = "es obligatorio", groups = OnCreate.class)
	@NotEmpty(message = "no debe ser vacío", groups = OnCreate.class)
	@Size(min =1, max =15, message = "la cantidad de caracteres de estar entre 1 y 15", groups = {OnCreate.class, OnUpdate.class})
	private String nombre;
	@NotNull(message = "es obligatorio", groups = {OnCreate.class, OnLogin.class})
	@NotEmpty(message = "no debe ser vacío", groups ={OnCreate.class, OnLogin.class})
	@Size(min =1, max =50, message = "la cantidad de caracteres de estar entre 1 y 50", groups = {OnCreate.class, OnUpdate.class})
	private String username;
	private Status estatus;

}
