package com.parkauto.parkauto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkauto.parkauto.entity.Vehicule;
import com.parkauto.parkauto.service.VehiculeService;

@RestController
@RequestMapping
@CrossOrigin("*")
public class VehiculeController {
	@Autowired
	VehiculeService vehiculeService;
	
	@PostMapping("/vehicule")
	public Vehicule createVehicule(@Validated @RequestBody(required = false) Vehicule vehicule) {
		return vehiculeService.saveVehicule(vehicule);
	}
	
	@GetMapping("/vehicules")
	public List<Vehicule> getAllVehicules(){
		return vehiculeService.getVehicules();
		
	}
	
	@GetMapping("/vehicules/{idVehicule}")
	public ResponseEntity findVehiculeByid(@PathVariable(name="idVehicule") Long idVehicule) {
	
		if(idVehicule == null) {
			return ResponseEntity.badRequest().body("Cannot retreive véhicule with null ID");
		}
		Vehicule vehicule = vehiculeService.getVehiculeByid(idVehicule);
		if(vehicule==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(vehicule);
	}
	
	
	@DeleteMapping("/vehicules/{idVehicule}")
	public ResponseEntity<Vehicule> deleteVehicule(@Validated @PathVariable(name="idVehicule") Long idVehicule) {
	
		
		Vehicule vehicule = vehiculeService.getVehiculeByid(idVehicule);
		if(vehicule==null) {
			return ResponseEntity.notFound().build();
		}
		vehiculeService.deleteVehicule(vehicule);
		return ResponseEntity.ok().body(vehicule);
	}
	
	
	

}
