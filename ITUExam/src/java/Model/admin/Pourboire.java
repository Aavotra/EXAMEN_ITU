package Model.admin;

public class Pourboire {
    private String date  ;
    private int id_serveur ;
    private int montant ;
    private String  username ;

    public Pourboire(String date, int id_serveur, int montant, String username) {
        this.date = date;
        this.id_serveur = id_serveur;
        this.montant = montant;
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId_serveur() {
        return id_serveur;
    }

    public void setId_serveur(int id_serveur) {
        this.id_serveur = id_serveur;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
