package com.parkauto.parkauto.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="vehicule")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Vehicule {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name="IDVEHICULE")
private Long id;

@Column(name="MODELVEHICULE")
private String modelVehicule;

@Column(name="ANNEEMODEL")
private int anneeModel;

@Column(name="PRIX")
private double prix;

@Column(name="IMAGEVEHICULE")
private String imageVehicule;

@JsonIgnore
@ManyToMany
@JoinTable(name="Location_Vehicule")
private List<Location> locationList;


public Vehicule() {
	super();
}

public Vehicule(Long id, int anneeModel, double prix) {
	super();
	this.id = id;
	this.anneeModel = anneeModel;
	this.prix = prix;
}

public Vehicule(int anneeModel, double prix, List<Location> locationList) {
	super();
	this.anneeModel = anneeModel;
	this.prix = prix;
	this.locationList = locationList;
}

public Vehicule(String modelVehicule, int anneeModel, double prix, String imageVehicule, List<Location> locationList) {
	super();
	this.modelVehicule = modelVehicule;
	this.anneeModel = anneeModel;
	this.prix = prix;
	this.imageVehicule = imageVehicule;
	this.locationList = locationList;
}

public Vehicule(Long id, String modelVehicule, int anneeModel, double prix, String imageVehicule,
		List<Location> locationList) {
	super();
	this.id = id;
	this.modelVehicule = modelVehicule;
	this.anneeModel = anneeModel;
	this.prix = prix;
	this.imageVehicule = imageVehicule;
	this.locationList = locationList;
}

public Vehicule(Long id, int anneeModel, double prix, List<Location> locationList) {
	super();
	this.id = id;
	this.anneeModel = anneeModel;
	this.prix = prix;
	this.locationList = locationList;
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


public String getModelVehicule() {
	return modelVehicule;
}

public void setModelVehicule(String modelVehicule) {
	this.modelVehicule = modelVehicule;
}

public String getImageVehicule() {
	return imageVehicule;
}

public void setImageVehicule(String imageVehicule) {
	this.imageVehicule = imageVehicule;
}

public List<Location> getLocationList() {
	return locationList;
}

public void setLocationList(List<Location> locationList) {
	this.locationList = locationList;
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
