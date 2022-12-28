package com.ricoh.test.dto.perfil;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PerfilDto {
	private UUID id;
	private String perfil;
}
