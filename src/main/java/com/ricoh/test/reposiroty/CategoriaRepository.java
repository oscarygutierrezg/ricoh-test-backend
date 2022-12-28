package com.ricoh.test.reposiroty;

import java.util.UUID;

import com.ricoh.test.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricoh.test.model.Perfil;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
	

}
