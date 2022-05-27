/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.serveur;

/**
 *
 * @author sony
 */
public class Addition 
{
    private int [] id_produit ;
    private int [] id_commande ;
    private int [] id_point_livraison; 
    private String []  designation ;
    private String []  nom_produit  ;
    private int [] quantite ;
    private String []  date_commande ;  
    private int [] prix_unitaire ;
    private int [] montant;

    public Addition(int [] id_produit, int [] id_commande, int [] id_point_livraison, String []  designation, String []  nom_produit, int [] quantite, String []  date_commande, int [] prix_unitaire, int [] montant) {
        this.id_produit = id_produit;
        this.id_commande = id_commande;
        this.id_point_livraison = id_point_livraison;
        this.designation = designation;
        this.nom_produit = nom_produit;
        this.quantite = quantite;
        this.date_commande = date_commande;
        this.prix_unitaire = prix_unitaire;
        this.montant = montant;
    }

    public int [] getMontant() {
        return montant;
    }

    public void setMontant(int [] montant) {
        this.montant = montant;
    }

    public int [] getId_produit() {
        return id_produit;
    }

    public void setId_produit(int [] id_produit) {
        this.id_produit = id_produit;
    }

    public int [] getId_commande() {
        return id_commande;
    }

    public void setId_commande(int [] id_commande) {
        this.id_commande = id_commande;
    }

    public int [] getId_point_livraison() {
        return id_point_livraison;
    }

    public void setId_point_livraison(int [] id_point_livraison) {
        this.id_point_livraison = id_point_livraison;
    }

    public String []  getDesignation() {
        return designation;
    }

    public void setDesignation(String []  designation) {
        this.designation = designation;
    }

    public String []  getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String []  nom_produit) {
        this.nom_produit = nom_produit;
    }

    public int [] getQuantite() {
        return quantite;
    }

    public void setQuantite(int [] quantite) {
        this.quantite = quantite;
    }

    public String []  getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(String []  date_commande) {
        this.date_commande = date_commande;
    }

    public int [] getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(int [] prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }
}
