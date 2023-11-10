package com.parkauto.parkauto.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="LOCATION")
public class Location {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name="IDLOCATION")
private Long id;

@Column(name="DEBUTLOCATION")
private String debutLocation;

@Column(name="DATERETOUR")
private String dateRetour;

@Column(name="DATEDEBUT")
private String dateDebut;

@ManyToMany
@JoinTable(name="LOCATION_CLIENT")
private List<Client> clientList;

@JsonIgnore
@ManyToMany(mappedBy="locationList")
private List<Vehicule> vehiculeList;

public Location() {
	super();
}

public Location(String debutLocation, String dateRetour, String dateDebut, List<Client> clientList) {
	super();
	this.debutLocation = debutLocation;
	this.dateRetour = dateRetour;
	this.dateDebut = dateDebut;
	this.clientList = clientList;
}

public Location(String debutLocation, String dateRetour, String dateDebut, List<Client> clientList,
		List<Vehicule> vehiculeList) {
	super();
	this.debutLocation = debutLocation;
	this.dateRetour = dateRetour;
	this.dateDebut = dateDebut;
	this.clientList = clientList;
	this.vehiculeList = vehiculeList;
}

public Location(Long id, String debutLocation, String dateRetour, String dateDebut, List<Client> clientList,
		List<Vehicule> vehiculeList) {
	super();
	this.id = id;
	this.debutLocation = debutLocation;
	this.dateRetour = dateRetour;
	this.dateDebut = dateDebut;
	this.clientList = clientList;
	this.vehiculeList = vehiculeList;
}

public Location(Long id, String debutLocation, String dateRetour, String dateDebut, List<Client> clientList) {
	super();
	this.id = id;
	this.debutLocation = debutLocation;
	this.dateRetour = dateRetour;
	this.dateDebut = dateDebut;
	this.clientList = clientList;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getDebutLocation() {
	return debutLocation;
}

public void setDebutLocation(String debutLocation) {
	this.debutLocation = debutLocation;
}

public List<Vehicule> getVehiculeList() {
	return vehiculeList;
}

public void setVehiculeList(List<Vehicule> vehiculeList) {
	this.vehiculeList = vehiculeList;
}

public String getDateRetour() {
	return dateRetour;
}

public void setDateRetour(String dateRetour) {
	this.dateRetour = dateRetour;
}

public String getDateDebut() {
	return dateDebut;
}

public void setDateDebut(String dateDebut) {
	this.dateDebut = dateDebut;
}

public List<Client> getClientList() {
	return clientList;
}

public void setClientList(List<Client> clientList) {
	this.clientList = clientList;
}


}
