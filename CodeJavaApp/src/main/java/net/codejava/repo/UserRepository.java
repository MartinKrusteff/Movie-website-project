package net.codejava.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import net.codejava.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

