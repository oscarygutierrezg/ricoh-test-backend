package com.ricoh.test.dto.vacante;

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
public interface VacanteMapper {
	
	VacanteDto toDto(Vacante vacante);
	Vacante toModel(VacanteRequest vacanteRequest);
	void updateModel(VacanteRequest vacanteRequest, @MappingTarget Vacante vacante);

}
