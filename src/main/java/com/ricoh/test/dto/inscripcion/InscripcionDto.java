package com.ricoh.test.dto.inscripcion;

import com.ricoh.test.dto.usuario.UsuarioDto;
import com.ricoh.test.dto.vacante.VacanteDto;
import com.ricoh.test.model.Usuario;
import com.ricoh.test.model.Vacante;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionDto {
	private UUID id;
	private VacanteDto vacante;
	private UsuarioDto usuario;
	private LocalDateTime fechaInscripcion;

}
