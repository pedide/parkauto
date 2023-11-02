package com.parkauto.parkauto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkauto.parkauto.entity.LigneCmd;
import com.parkauto.parkauto.repository.ILigneCmdRepository;

@Service
public class LigneCmdService {
	@Autowired
	ILigneCmdRepository ligneCmdRepository;
	
	//Liste des ligneCmds
	public List<LigneCmd> getLigneCmds(){
		return ligneCmdRepository.findAll();
	}
	
	//Save
		public LigneCmd saveLigneCmd(LigneCmd ligneCmd) {
			return ligneCmdRepository.save(ligneCmd);
		}

		//get a LigneCmd
		public LigneCmd getLigneCmdByid(Long idCami) {
			return ligneCmdRepository.findById(idCami).get();
		}
		
		//Delete a ligneCmd
		public void deleteLigneCmd(LigneCmd ligneCmd) {
			ligneCmdRepository.delete(ligneCmd);
		}

}
