package com.parkauto.parkauto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkauto.parkauto.entity.Client;

public interface IClientRepository extends JpaRepository<Client, Long>{

}
