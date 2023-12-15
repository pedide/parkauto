package com.parkauto.parkauto.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
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

@Column(name="DESCRIPTIF")
private String descriptif;

@Column(name="PRIX")
private double prix;

@Column(name="IMAGEVEHICULE")
private String imageVehicule;

@JsonIgnore
@ManyToMany
@JoinTable(name="Location_Vehicule")
private List<Location> locationList;

@OneToMany(mappedBy="vehicule")
private List<ParkautoImages> parkautoImagesList;


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
