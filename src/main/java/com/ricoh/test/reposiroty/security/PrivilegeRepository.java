package com.ricoh.test.reposiroty.security;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ricoh.test.model.security.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, UUID> {

    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);

}
