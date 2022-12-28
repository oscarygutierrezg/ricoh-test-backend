package com.ricoh.test.dto.categoria;

import com.ricoh.test.dto.vacante.VacanteRequest;
import com.ricoh.test.model.Categoria;
import com.ricoh.test.model.Vacante;
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
public interface CategoriaMapper {
	
	CategoriaDto toDto(Categoria categoria);
	Categoria toModel(CategoriaRequest categoriaRequest);
	void updateModel(CategoriaRequest categoriaRequest, @MappingTarget Categoria categoria);
}
