package com.ricoh.test.dto.inscripcion;

import com.ricoh.test.dto.request.OnCreate;
import com.ricoh.test.dto.request.OnDelete;
import com.ricoh.test.dto.request.OnLogin;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class InscripcionRequest {
	@NotNull(message = "es obligatorio", groups = {OnCreate.class, OnDelete.class})
	private UUID vacanteId;
	@NotNull(message = "es obligatorio", groups = {OnCreate.class, OnDelete.class})
	private UUID usuarioId;
	@NotNull(message = "es obligatorio", groups = {OnCreate.class})
	@PastOrPresent(message = "no es valida", groups = {OnCreate.class})
	private LocalDateTime fechaInscripcion;
}
