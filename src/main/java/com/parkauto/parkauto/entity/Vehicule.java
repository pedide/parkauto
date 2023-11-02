package com.parkauto.parkauto.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name="vehicule")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Vehicule {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name="IDVEHICULE")
private Long id;

@Column(name="ANNEEMODEL")
private int anneeModel;

@Column(name="PRIX")
private double prix;


public Vehicule() {
	super();
}

public Vehicule(Long id, int anneeModel, double prix) {
	super();
	this.id = id;
	this.anneeModel = anneeModel;
	this.prix = prix;
}

public Vehicule(int anneeModel, double prix) {
	super();
	this.anneeModel = anneeModel;
	this.prix = prix;
}

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public int getAnneeModel() {
	return anneeModel;
}
public void setAnneeModel(int anneeModel) {
	this.anneeModel = anneeModel;
}
public double getPrix() {
	return prix;
}
public void setPrix(double prix) {
	this.prix = prix;
}

@Override
public String toString() {
	return "Vehicule [id=" + id + ", anneeModel=" + anneeModel + ", prix=" + prix + "]";
}

public String demarrer() {
	return "Véhicule démarré !!";
}

public String accelerer() {
	return "Véhicule accelère !!";
}
}
