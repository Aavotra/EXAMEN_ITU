package Model.services;

import Model.cnx.Connexion;
import Model.livreur.Details_livraison;
import Model.livreur.Livraison;
import java.sql.Connection;
import java.sql.ResultSet;

public class Service_livreur 
{
    public Livraison get_livraison (int livreur) throws Exception
    {
        Livraison l = null;
        try (Connection con = new Connexion().getConnection()) {
            java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet res = stmt.executeQuery("select * from livraison where id_livreur="+livreur+"");
            int i=0;
            res.last();
            int [] id = new int[res.getRow()];
            String [] numero = new String [res.getRow()];
            int [] id_livreur = new int[res.getRow()];
            int [] id_commande = new int[res.getRow()];  
            String [] contact = new String [res.getRow()];
            String [] lieu = new String [res.getRow()];
            res.beforeFirst();
            while(res.next())
            {
                id [i] = res.getInt("id");
                numero [i] = res.getString("numero");
                id_livreur [i] = res.getInt("id_livreur");
                id_commande [i] = res.getInt("id_commande"); 
                contact [i] = res.getString("contact");
                lieu [i] = res.getString("lieu");
                i++;
            }
            l = new Livraison(id , numero ,id_livreur , id_commande ,contact,lieu);
        }
        return l;  
    }
    
    
    public Details_livraison get_details_livraison (int num_livraison) throws Exception
    {
        Details_livraison d = null;
        try (Connection con = new Connexion().getConnection()) {
            java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet res = stmt.executeQuery("select * from detail_livraison where n_livraison='"+num_livraison+"' ");
            int i=0;
            res.last();
            String [] n_livraison = new String [res.getRow()];    
            String [] nom_produit = new String [res.getRow()];   
            int [] id_produit = new int[res.getRow()];
            int [] id_detail_commande = new int[res.getRow()];
            int [] est_paye = new int[res.getRow()];
            res.beforeFirst();
            while(res.next())
            {
                n_livraison [i] = res.getString(" n_livraison");   
                nom_produit [i] = res.getString("nom_produit");   
                id_produit [i] = res.getInt("id_produit");
                id_detail_commande [i] = res.getInt("id_detail_commande");
                est_paye [i] = res.getInt("est_paye");
                i++;
            }
            d = new Details_livraison(n_livraison,nom_produit,id_produit,id_detail_commande,est_paye);
        }
        return d;  
    }

}
