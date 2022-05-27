package Model.livreur;

public class Details_livraison 
{
    private String [] n_livraison ;    
    private String [] nom_produit  ;   
    private int [] id_produit ;
    private int [] id_detail_commande ;
    private int [] est_paye ;

    public Details_livraison(String[] n_livraison, String[] nom_produit, int[] id_produit, int[] id_detail_commande, int[] est_paye) {
        this.n_livraison = n_livraison;
        this.nom_produit = nom_produit;
        this.id_produit = id_produit;
        this.id_detail_commande = id_detail_commande;
        this.est_paye = est_paye;
    }

    public int[] getEst_paye() {
        return est_paye;
    }

    public void setEst_paye(int[] est_paye) {
        this.est_paye = est_paye;
    }

    public String[] getN_livraison() {
        return n_livraison;
    }

    public void setN_livraison(String[] n_livraison) {
        this.n_livraison = n_livraison;
    }

    public String[] getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String[] nom_produit) {
        this.nom_produit = nom_produit;
    }

    public int[] getId_produit() {
        return id_produit;
    }

    public void setId_produit(int[] id_produit) {
        this.id_produit = id_produit;
    }

    public int[] getId_detail_commande() {
        return id_detail_commande;
    }

    public void setId_detail_commande(int[] id_detail_commande) {
        this.id_detail_commande = id_detail_commande;
    }
}
