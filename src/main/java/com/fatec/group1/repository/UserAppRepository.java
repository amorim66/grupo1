package com.fatec.group1.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatec.group1.model.UsuarioApp;

@Repository
public interface UserAppRepository extends JpaRepository<UsuarioApp, Long> {
	Optional<UsuarioApp> findByUserName(String usarName);
}