package Model.services;

import Model.admin.Pourboire;
import Model.cnx.Connexion;
import Model.serveur.Addition;
import Model.serveur.Menu;
import Model.serveur.Plat_commander;
import Model.utilisateur.Utilisateur;
import java.sql.Connection;
import java.sql.ResultSet;

public class Service_serveur {

    public Service_serveur() { }
    
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
    
    int get_last_commande(Connexion c) throws Exception
    {
        int max = 0 ;
        try (Connection con = new Connexion().getConnection()) 
        {
            java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet res = stmt.executeQuery("select max(id) as max from  commande");
            while(res.next())
            {
                max = res.getInt("max");
            }
        }
        return max;
    }
    
    
    
    public Addition get_addition(String design) throws Exception
    {
        
        Addition a = null;
        try (Connection con = new Connexion().getConnection()) {
            System.out.println("Hello");
            java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet res = stmt.executeQuery("select * from addition_table where designation='"+design+"'");
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
                montant[i] = res.getInt("montant");
                i++;
            }
            a = new Addition(id_produit , id_commande , id_point_livraison , designation , nom_produit  , quantite ,date_commande  , prix_unitaire , montant);
        }
        catch(Exception e)
        {
           System.out.println(e);
        }
        return a; 
    }
    
    
    public Addition get_all_addition() throws Exception
    {
        
        Addition a = null;
        try (Connection con = new Connexion().getConnection()) {
            System.out.println("Hello");
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
                montant[i] = res.getInt("montant");
                i++;
            }
            a = new Addition(id_produit , id_commande , id_point_livraison , designation , nom_produit  , quantite ,date_commande  , prix_unitaire , montant);
        }
        catch(Exception e)
        {
           System.out.println(e);
        }
        return a; 
    }
    
    
    
    public Pourboire [] get_all_pourboire(String d1,String d2) throws Exception
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
                tab = new Pourboire [res.getRow()];
                res.beforeFirst();
                while(res.next())
                {
                    tab[i] = new Pourboire(res.getString("date"), res.getInt("id_serveur"), res.getInt("montant"),res.getString("username"));
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
    
    
    public Menu get_menu (String categ) throws Exception
    {
        Utilisateur[] tab = null;
        Menu m = null;
        try (Connection con = new Connexion().getConnection()) {
            java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet res = stmt.executeQuery("select * from menu where categorie = '"+categ+"'");
            int i=0;
            res.last();
            int [] id_produit = new int[res.getRow()];
            String [] nom_produit = new String [res.getRow()];
            double [] prix_produit = new double [res.getRow()];
            String [] categorie = new String[res.getRow()];
            String [] date = new String[res.getRow()];
            String [] image = new String[res.getRow()];
            res.beforeFirst();
            while(res.next())
            {
                id_produit [i]= res.getInt("id_produit");
                nom_produit [i]= res.getString("produit");
                prix_produit [i]= res.getDouble("prix");
                categorie [i]= res.getString("categorie");
                date[i] = res.getString("date");
                image[i] = res.getString("image");
                i++;
            }
            m = new Menu(id_produit,nom_produit,prix_produit, categorie,date);
        }
        return m;
        
    }
    
    public Menu get_all_menu () throws Exception
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
            String [] image = new String[res.getRow()];
            res.beforeFirst();
            while(res.next())
            {
                id_produit [i]= res.getInt("id_produit");
                nom_produit [i]= res.getString("produit");
                prix_produit [i]= res.getDouble("prix");
                categorie [i]= res.getString("categorie");
                date[i] = res.getString("date");
                image[i] = res.getString("image");
                i++;
            }
            m = new Menu(id_produit,nom_produit,prix_produit, categorie,date);
        }
        return m;
        
    }
    
    public Plat_commander [] Get_liste_plats_commander(String d1,String d2) throws Exception
    {
        Plat_commander[] tab = null;
        try (Connection con = new Connexion().getConnection()) 
        {
            try
            {
                java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet res = stmt.executeQuery("select * from plat_commande where etat > 0 and date between '"+d1+"' and '"+d2+"'");
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
    
    public Plat_commander[] Get_all_liste_plats_commander() throws Exception 
    {
        Plat_commander[] tab = null;
        try (Connection con = new Connexion().getConnection()) 
        {
            try
            {
                java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet res = stmt.executeQuery("select * from plat_commande where etat > 0 ");
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
    
    public Plat_commander [] Get_liste_plats_cuit(String d1,String d2) throws Exception
    {
        Plat_commander[] tab = null;
        try (Connection con = new Connexion().getConnection()) 
        {
            try
            {
                java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet res = stmt.executeQuery("select * from plat_commande where etat = 2 and date between '"+d1+"' and '"+d2+"'");
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
    
    public Plat_commander[] Get_all_liste_plats_cuit() throws Exception 
    {
        Plat_commander[] tab = null;
        try (Connection con = new Connexion().getConnection()) 
        {
            try
            {
                java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet res = stmt.executeQuery("select * from plat_commande where etat = 2 ");
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
