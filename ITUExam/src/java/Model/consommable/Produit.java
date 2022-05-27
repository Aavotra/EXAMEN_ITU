package Model.consommable;

/**
 *
 * @author sony
 */
public class Produit {
    
    private int id;
    private int id_categorie;
    private String nom;

    public Produit(int id, int id_categorie, String nom) 
    {
        this.id = id;
        this.id_categorie = id_categorie;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }
}
