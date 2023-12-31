package com.parkauto.parkauto.repository;

import com.parkauto.parkauto.entity.Client;
import com.parkauto.parkauto.entity.Role;
import com.parkauto.parkauto.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);
    User findUserByUsername(String username);

    User findByRole(Role role);
}
