package com.parkauto.parkauto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkauto.parkauto.entity.Camion;
import com.parkauto.parkauto.entity.Camion;
import com.parkauto.parkauto.repository.ICamionRepository;

@Service
public class CamionService {
	@Autowired
	ICamionRepository camionRepository;
	
	//Liste des camions
	public List<Camion> getCamions(){
		return camionRepository.findAll();
	}
	
	//Save
		public Camion saveCamion(Camion camion) {
			return camionRepository.save(camion);
		}

		//get a Camion
		public Camion getCamionByid(Long idCami) {
			return camionRepository.findById(idCami).get();
		}
		
		//Delete a camion
		public void deleteCamion(Camion camion) {
			camionRepository.delete(camion);
		}

}
