package com.parkauto.parkauto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="CAMION")
public class Camion extends Vehicule{

	@Override
	public String demarrer() {
		return "BRRRRRRRRRRR!";
	}
	
	@Override
	public String accelerer() {
		return "BROUMMMMMM!!";
	}

	public Camion() {
		super();
		
	}

	public Camion(int anneeModel, double prix) {
		super(anneeModel, prix);
		
	}

	public Camion(Long id, int anneeModel, double prix) {
		super(id, anneeModel, prix);
		
	}

	
}
