package br.com.univali.apiteste.repositories;

import br.com.univali.apiteste.domain.User;
import br.com.univali.apiteste.domain.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
