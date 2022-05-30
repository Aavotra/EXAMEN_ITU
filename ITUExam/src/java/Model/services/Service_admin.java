package Model.services;

import Model.admin.Pourboire;
import Model.cnx.Connexion;
import Model.serveur.Plat_commander;
import java.sql.Connection;
import java.sql.ResultSet;

public class Service_admin 
{
    
    public Pourboire [] Get_liste_pourboire(String d1,String d2) throws Exception
    {
        Pourboire [] tab = null;
        try (Connection con = new Connexion().getConnection()) 
        {
            try
            {
                java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet res = stmt.executeQuery("select * from pourboire join utilisateur on utilisateur.id = id_serveur where date between '"+d1+"' and '"+d2+"'");
                int i=0;
                res.last();
                tab = new Pourboire[res.getRow()];
                res.beforeFirst();
                while(res.next())
                {
                    tab[i] = new Pourboire(res.getString("date"),res.getInt("id_serveur"),res.getInt("montant"),res.getString("username"));
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
    
    public Pourboire [] Get_all_liste_pourboire() throws Exception
    {
        Pourboire [] tab = null;
        try (Connection con = new Connexion().getConnection()) 
        {
            try
            {
                java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet res = stmt.executeQuery("select * from pourboire join utilisateur on utilisateur.id = id_serveur ");
                int i=0;
                res.last();
                tab = new Pourboire[res.getRow()];
                res.beforeFirst();
                while(res.next())
                {
                    tab[i] = new Pourboire(res.getString("date"),res.getInt("id_serveur"),res.getInt("montant"),res.getString("username"));
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
