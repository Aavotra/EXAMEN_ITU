package Model.admin;

public class Produit {
    private int id ; 
    private int id_categorie ;
    private String nom ;
    private String image ;

    public Produit(int id, int id_categorie, String nom, String image) {
        this.id = id;
        this.id_categorie = id_categorie;
        this.nom = nom;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
