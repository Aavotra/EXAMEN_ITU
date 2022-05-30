package Model.serveur;

/**
 *
 * @author sony
 */
public class Last_detail_commande {
    private int id;
    private String nom ;
    private int count ; 

    public Last_detail_commande(int id, String nom, int count) {
        this.id = id;
        this.nom = nom;
        this.count = count;
    }

        
    public Last_detail_commande(String nom, int count) {
        this.nom = nom;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
