package br.com.fernandamullerb.mudi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fernandamullerb.mudi.model.User;

public interface UserRepository extends JpaRepository<User, String> {

	User findByUsername(String username);

}