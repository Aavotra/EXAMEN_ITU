package Model.services;

import Model.cnx.Connexion;
import Model.serveur.Addition;
import Model.serveur.Menu;
import java.sql.Connection;
import java.sql.ResultSet;

public class Service {

    public Service() {
    }
    
    void do_commande(int idtable) throws Exception
    {
        try (Connection con = new Connexion().getConnection()) {
            java.sql.Statement stmt = con.createStatement();
            String req = String.format("insert into commande values(default,now(),"+idtable+")");
            stmt.executeUpdate(req);
            System.out.println(req);
            con.commit();
        }
    }
    
    void commander_produit(int id_serveur , int id_commande , int id_produit) throws Exception
    {
        try (Connection con = new Connexion().getConnection()) {
            java.sql.Statement stmt = con.createStatement();
            String req = String.format("insert into details_commande values(default,"+id_commande+","+id_produit+","+id_serveur+",1)");
            stmt.executeUpdate(req);
            System.out.println(req);
            con.commit();
        }
    }
    
    int get_last_commande(Connexion c)
    {
        return 0;
    }
    
    public Addition get_addition() throws Exception
    {
        Addition a = null;
        try (Connection con = new Connexion().getConnection()) {
            java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet res = stmt.executeQuery("select * from addition_table");
            int i=0;
            res.last();
            int [] id_produit = new int[res.getRow()];
            int [] id_commande = new int[res.getRow()];
            int [] id_point_livraison = new int[res.getRow()]; 
            String []  designation = new String [res.getRow()];
            String []  nom_produit = new String [res.getRow()];
            int [] quantite = new int[res.getRow()];
            String []  date_commande = new String [res.getRow()];
            int [] prix_unitaire = new int[res.getRow()];
            int [] montant = new int[res.getRow()];
            res.beforeFirst();
            while(res.next())
            {
                id_produit[i] = res.getInt("id_produit");
                id_commande[i] = res.getInt("id_commande");
                id_point_livraison[i] = res.getInt("id_point_livraison");
                designation[i] = res.getString("designation");
                nom_produit[i] = res.getString("nom_produit");
                quantite[i] = res.getInt("quantite");
                date_commande[i] = res.getString("date_commande");
                prix_unitaire[i] = res.getInt("prix_unitaire");
                montant[i] = res.getInt("montant ");
                i++;
            }
            a = new Addition(id_produit , id_commande , id_point_livraison , designation , nom_produit  , quantite ,   date_commande    , prix_unitaire , montant);
        }
        return a; 
    }
    
}
