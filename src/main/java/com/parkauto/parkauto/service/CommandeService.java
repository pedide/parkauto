package com.parkauto.parkauto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkauto.parkauto.entity.Commande;
import com.parkauto.parkauto.repository.ICommandeRepository;

@Service
public class CommandeService {
	@Autowired
	ICommandeRepository commandeRepository;
	
	//Liste des commandes
	public List<Commande> getCommandes(){
		return commandeRepository.findAll();
	}
	
	//Save
		public Commande saveCommande(Commande commande) {
			return commandeRepository.save(commande);
		}

		//get a Commande
		public Commande getCommandeByid(Long idCami) {
			return commandeRepository.findById(idCami).get();
		}
		
		//Delete a commande
		public void deleteCommande(Commande commande) {
			commandeRepository.delete(commande);
		}

}
