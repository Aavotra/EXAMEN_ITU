package Model.services;

import Model.cnx.Connexion;
import Model.serveur.Addition;
import java.sql.Connection;

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
    
    Addition get_addition(Connexion c)
    {
        return null;
        
    }
    
}
