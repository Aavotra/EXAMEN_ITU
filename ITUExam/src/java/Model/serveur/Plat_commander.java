package Model.serveur;

public class Plat_commander {
    private int id_produit;
    private String nom_produit;
    private String lieu;
    private int etat ;
    private int id_serveur;
    private String date;

    public Plat_commander(int id_produit, String nom_produit, String lieu, int etat, int id_serveur, String date) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.lieu = lieu;
        this.etat = etat;
        this.id_serveur = id_serveur;
        this.date = date;
    }

    public Plat_commander(int id_produit, String nom_produit, String lieu, int etat, int id_serveur) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.lieu = lieu;
        this.etat = etat;
        this.id_serveur = id_serveur;
    }

    public int getId_livreur() {
        return id_serveur;
    }

    public void setId_livreur(int id_serveur) {
        this.id_serveur = id_serveur;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
