package com.ricoh.test.dto.usuario;

import com.ricoh.test.dto.categoria.CategoriaRequest;
import com.ricoh.test.model.Categoria;
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
public interface UsuarioMapper {
	
	UsuarioDto toDto(Usuario usuario);
	Usuario toModel(UsuarioRequest usuarioRequest);
	void updateModel(UsuarioRequest usuarioRequest, @MappingTarget Usuario usuario);

}
