package com.parkauto.parkauto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkauto.parkauto.entity.Vehicule;

public interface IVehiculeRepository extends JpaRepository<Vehicule, Long>{

}
