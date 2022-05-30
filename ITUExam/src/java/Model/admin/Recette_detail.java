
package Model.admin;

public class Recette_detail {
    private int id ;
    private int id_produit ;
    private String nom_produit  ;
    private String nom_ingredient ;
    private int quantite ;
    private int montant ;

    public Recette_detail(int id, int id_produit, String nom_produit, String nom_ingredient, int quantite, int montant) {
        this.id = id;
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.nom_ingredient = nom_ingredient;
        this.quantite = quantite;
        this.montant = montant;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNom_ingredient() {
        return nom_ingredient;
    }

    public void setNom_ingredient(String nom_ingredient) {
        this.nom_ingredient = nom_ingredient;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
}
