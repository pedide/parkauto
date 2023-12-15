package com.parkauto.parkauto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name="VOITURE")
public class Voiture extends Vehicule{
	
	@Column(name="MEDIA")
	private String media;
	@Column(name="IMMAT")
	private String immatriculation;
	@Column(name="PWFISCALE")
	private String puissanceFiscale;
	@Column(name="CATEGORIE")
	private String categorie;
	@Column(name="NBPORTE")
	private int nbPorte;
	@Column(name="POIDSTOTAL")
	private int poidsTotal;


	
	
	

}
