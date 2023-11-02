package com.parkauto.parkauto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkauto.parkauto.entity.Voiture;

public interface IVoitureRepository extends JpaRepository<Voiture, Long>{

}
