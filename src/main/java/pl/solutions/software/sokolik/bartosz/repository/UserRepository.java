package pl.solutions.software.sokolik.bartosz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.solutions.software.sokolik.bartosz.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
