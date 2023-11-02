package com.parkauto.parkauto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkauto.parkauto.entity.Client;
import com.parkauto.parkauto.repository.IClientRepository;

@Service
public class ClientService {
	@Autowired
	IClientRepository clientRepository;
	
	//Liste des clients
	public List<Client> getClients(){
		return clientRepository.findAll();
	}
	
	//Save
		public Client saveClient(Client client) {
			return clientRepository.save(client);
		}

		//get a Client
		public Client getClientByid(Long idCami) {
			return clientRepository.findById(idCami).get();
		}
		
		//Delete a client
		public void deleteClient(Client client) {
			clientRepository.delete(client);
		}
}
