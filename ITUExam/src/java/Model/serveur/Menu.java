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
public class Menu {
    private int [] id_produit;
    private String [] nom_produit;
    private double [] prix_produit;
    private String [] categorie;

    public Menu(int[] id_produit, String[] nom_produit, double[] prix_produit, String[] categorie, String[] date) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.prix_produit = prix_produit;
        this.categorie = categorie;
        this.date = date;
    }
    private String [] date;

    public Menu(int[] id_produit, String[] nom_produit, double[] prix_produit, String[] categorie) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.prix_produit = prix_produit;
        this.categorie = categorie;
    }

    public String[] getCategorie() {
        return categorie;
    }

    public void setCategorie(String[] categorie) {
        this.categorie = categorie;
    }

    public int[] getId_produit() {
        return id_produit;
    }

    public void setId_produit(int[] id_produit) {
        this.id_produit = id_produit;
    }

    public String[] getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String[] nom_produit) {
        this.nom_produit = nom_produit;
    }

    public double[] getPrix_produit() {
        return prix_produit;
    }

    public void setPrix_produit(double[] prix_produit) {
        this.prix_produit = prix_produit;
    }

    public String[] getDate() {
        return date;
    }

    public void setDate(String[] date) {
        this.date = date;
    }
    
}
