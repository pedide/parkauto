package com.parkauto.parkauto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="LIGNECMD")
public class LigneCmd {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="IDLIGNECMD")
	private Long id;
	
	@Column(name="QTE")
	private int qte;
	
	@Column(name="LIBELLE")
	private String libelle;
	
	@Column(name="PRIX")
	private double prix;
	
	@ManyToOne
	@JoinColumn(name="commandes_id")
	private Commande commandes;
	
	

	public LigneCmd() {
		super();
	}

	public LigneCmd(int qte, String libelle, double prix, Commande commandes) {
		super();
		this.qte = qte;
		this.libelle = libelle;
		this.prix = prix;
		this.commandes = commandes;
	}

	public LigneCmd(Long id, int qte, String libelle, double prix, Commande commandes) {
		super();
		this.id = id;
		this.qte = qte;
		this.libelle = libelle;
		this.prix = prix;
		this.commandes = commandes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Commande getCommandes() {
		return commandes;
	}

	public void setCommandes(Commande commandes) {
		this.commandes = commandes;
	}
	
	

}
