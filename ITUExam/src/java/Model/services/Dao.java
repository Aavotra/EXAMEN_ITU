package Model.services;

import Model.cnx.Connexion;
import Model.livreur.Details_livraison;
import Model.livreur.Livraison;
import Model.serveur.Plat_commander;
import Model.utilisateur.Utilisateur;
import Model.serveur.Menu;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;

public class Dao {

    public Dao(PrintWriter p) {
        p.println("Bien");
    }

    public Dao() {}
    
    public boolean test_login(String username,String password) throws Exception
    { 
        try
        {
            Utilisateur [] tab = find_all_user();
            System.out.println(tab.length);
            for(int i = 0 ; i < tab.length; i ++)
            {
                if(tab[i].getUsername().equals(username) && tab[i].getPassword().equals(password))
                {
                    return true;
                }
            }
            return false;
        }catch(Exception e)
        {
            throw e ;
        }
        
    }
    
    public Utilisateur [] find_all_user() throws Exception
    {
        Utilisateur[] tab = null;
        try (Connection con = new Connexion().getConnection()) {
            try
            {
                java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet res = stmt.executeQuery("select * from user_info");
                int i=0;
                res.last();
                tab = new Utilisateur[res.getRow()];
                res.beforeFirst();
                while(res.next())
                {
                    tab[i] = new Utilisateur(res.getInt("id"),res.getString("username"),res.getString("password"),res.getInt("id_profil"));
                    i++;
                }
            }catch(Exception e)
            {
                throw e;
            }
        }
        return tab;
    }
    
    
    
}
