
package Model.services;

import Model.cnx.Connexion;
import Model.serveur.Plat_commander;
import java.sql.Connection;
import java.sql.ResultSet;


public class Service_cuisine 
{
    
    void cuire(int id_serveur , int id_commande , int id_produit) throws Exception
    {
        try (Connection con = new Connexion().getConnection()) {
            java.sql.Statement stmt = con.createStatement();
            String req = String.format("insert into details_commande values(default,"+id_commande+","+id_produit+","+id_serveur+",1)");
            stmt.executeUpdate(req);
            System.out.println(req);
            con.commit();
        }
    }
    
    
    public Plat_commander [] Get_liste_plats_preparer(String d1,String d2) throws Exception
    {
        Plat_commander[] tab = null;
        try (Connection con = new Connexion().getConnection()) 
        {
            try
            {
                java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet res = stmt.executeQuery("select * from plat_commande where etat = 1 and date between '"+d1+"' and '"+d2+"'");
                int i=0;
                res.last();
                tab = new Plat_commander[res.getRow()];
                res.beforeFirst();
                while(res.next())
                {
                    tab[i] = new Plat_commander(res.getInt("id_produit"), res.getString("nom_produit"), res.getString("lieu"), res.getInt("etat"),res.getInt("id_serveur"));
                    i++;
                }
            }
             catch(Exception e)
            {
                throw e;
            }
        }
        return tab;
    }
    
    public Plat_commander[] Get_all_liste_plats_preparer() throws Exception 
    {
        Plat_commander[] tab = null;
        try (Connection con = new Connexion().getConnection()) 
        {
            try
            {
                java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet res = stmt.executeQuery("select * from plat_commande where etat = 1 ");
                int i=0;
                res.last();
                tab = new Plat_commander[res.getRow()];
                res.beforeFirst();
                while(res.next())
                {
                    tab[i] = new Plat_commander(res.getInt("id_produit"), res.getString("nom_produit"), res.getString("lieu"), res.getInt("etat"),res.getInt("id_serveur"));
                    i++;
                }
            }
             catch(Exception e)
            {
                throw e;
            }
        }
        return tab;
    }
    
}
