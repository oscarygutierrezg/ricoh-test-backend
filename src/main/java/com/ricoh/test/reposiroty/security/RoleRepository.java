package com.ricoh.test.reposiroty.security;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ricoh.test.model.security.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}
