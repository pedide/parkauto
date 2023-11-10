package com.parkauto.parkauto.repository;

import com.parkauto.parkauto.entity.Client;
import com.parkauto.parkauto.entity.Role;
import com.parkauto.parkauto.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String userName);

    User findByRole(Role role);
}
