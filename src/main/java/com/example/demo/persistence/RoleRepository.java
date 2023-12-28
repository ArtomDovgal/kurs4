package com.example.demo.persistence;

import com.example.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query("from Role c where c.typeRole = :type")
    Optional<Role> findByTypeRole(@Param("type") Role.TypeRole type);
}
