package com.ricoh.test.dto.perfil;

import com.ricoh.test.dto.categoria.CategoriaRequest;
import com.ricoh.test.model.Categoria;
import com.ricoh.test.model.Perfil;
import com.ricoh.test.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


@Mapper(
	    componentModel = "spring",
	    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
	    unmappedTargetPolicy = ReportingPolicy.IGNORE,
	    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
	)
public interface PerfilMapper {
	
	PerfilDto toDto(Perfil perfil);
	Perfil toModel(PerfilRequest perfilRequest);
	void updateModel(PerfilRequest perfilRequest, @MappingTarget Perfil perfil);

}
