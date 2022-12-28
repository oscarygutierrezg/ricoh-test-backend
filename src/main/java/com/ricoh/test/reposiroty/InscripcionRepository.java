package com.ricoh.test.reposiroty;

import com.ricoh.test.model.Inscripcion;
import com.ricoh.test.model.Usuario;
import com.ricoh.test.model.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, UUID> {

    @Query("SELECT u FROM Usuario u, Inscripcion i " +
            "WHERE i.vacanteId = :vacanteId " +
            "AND u.id=i.usuarioId")
    List<Usuario> findUsuarioByVacanteId(UUID vacanteId);


    @Query("SELECT v FROM Vacante v, Inscripcion i " +
            "WHERE i.usuarioId = :usuarioId " +
            "AND v.id=i.vacanteId")
    List<Vacante> findVacanteByUsuarioId(UUID usuarioId);

    Optional<Inscripcion> findByUsuarioIdAndVacanteId( UUID usuarioId , UUID vacanteId);

}
