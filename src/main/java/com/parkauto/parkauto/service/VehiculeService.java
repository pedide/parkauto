package com.parkauto.parkauto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkauto.parkauto.entity.Vehicule;
import com.parkauto.parkauto.repository.IVehiculeRepository;

@Service
public class VehiculeService {
	@Autowired
	IVehiculeRepository vehiculeRepository;
	
	//Liste de véhicules
	public List<Vehicule> getVehicules(){
		return vehiculeRepository.findAll();
		
	}
	
	//Save
	public Vehicule saveVehicule(Vehicule vehicule) {
		return vehiculeRepository.save(vehicule);
	}

	//get a Véhicule
	public Vehicule getVehiculeByid(Long idvehi) {
		return vehiculeRepository.findById(idvehi).get();
	}
	
	//Delete a vehicule
	public void deleteVehicule(Vehicule vehicule) {
		vehiculeRepository.delete(vehicule);
	}
	
	
}
