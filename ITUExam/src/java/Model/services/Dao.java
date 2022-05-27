package Model.services;

import Model.cnx.Connexion;
import Model.consommable.Produit;
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

    public Dao() {
          }
    
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
                    System.out.println(tab[i].getUsername()+":"+username);
                    System.out.println(tab[i].getPassword()+":"+password);
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
    
    public Menu get_menu () throws Exception
    {
        Utilisateur[] tab = null;
        Menu m = null;
        try (Connection con = new Connexion().getConnection()) {
            java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet res = stmt.executeQuery("select * from menu");
            int i=0;
            res.last();
            int [] id_produit = new int[res.getRow()];
            String [] nom_produit = new String [res.getRow()];
            double [] prix_produit = new double [res.getRow()];
            String [] categorie = new String[res.getRow()];
            String [] date = new String[res.getRow()];
            res.beforeFirst();
            while(res.next())
            {
                id_produit [i]= res.getInt("id_produit");
                nom_produit [i]= res.getString("produit");
                prix_produit [i]= res.getDouble("prix");
                categorie [i]= res.getString("categorie");
                date[i] = res.getString("date");
                i++;
            }
            m = new Menu(id_produit,nom_produit,prix_produit, categorie,date);
        }
        return m;
        
    }
    
    Produit[] Get_liste_plats_commander(String date ,Connection c)
    {
        
        return null;
    }
    
}
