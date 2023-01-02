package com.ricoh.test.reposiroty;


import java.util.Optional;
import java.util.UUID;

import com.ricoh.test.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

	Optional<Usuario> findByUsername(String username);
}
