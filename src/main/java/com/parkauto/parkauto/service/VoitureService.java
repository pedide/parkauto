package com.parkauto.parkauto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkauto.parkauto.entity.Voiture;
import com.parkauto.parkauto.repository.IVoitureRepository;

@Service
public class VoitureService {
	@Autowired
	IVoitureRepository voitureRepository;
	
	//Liste des voitures
	public List<Voiture> getVoitures(){
		return voitureRepository.findAll();
	}
	
	//Save
		public Voiture saveVoiture(Voiture voiture) {
			return voitureRepository.save(voiture);
		}

		//get a Voiture
		public Voiture getVoitureByid(Long idVoit) {
			return voitureRepository.findById(idVoit).get();
		}
		
		//Delete a voiture
		public void deleteVoiture(Voiture voiture) {
			voitureRepository.delete(voiture);
		}

}
