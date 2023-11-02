package com.parkauto.parkauto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkauto.parkauto.entity.Commande;

public interface ICommandeRepository extends JpaRepository<Commande, Long>{

}
