package com.ricoh.test.reposiroty;

import com.ricoh.test.model.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VacanteRepository extends JpaRepository<Vacante, UUID>, QueryByExampleExecutor<Vacante> {

}
