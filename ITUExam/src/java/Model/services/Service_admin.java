package Model.services;

import Model.admin.Pourboire;
import Model.admin.Produit;
import Model.cnx.Connexion;
import Model.serveur.Plat_commander;
import java.sql.Connection;
import java.sql.ResultSet;
import Model.admin.Recette_detail;

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
    
    
    public Produit [] Get_all_produit() throws Exception
    {
        Produit [] tab = null;
        try (Connection con = new Connexion().getConnection()) 
        {
            try
            {
                java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet res = stmt.executeQuery("select * from produit");
                int i=0;
                res.last();
                tab = new Produit[res.getRow()];
                res.beforeFirst();
                while(res.next())
                {
                    tab[i] = new Produit(res.getInt("id"),res.getInt(" id_categorie"),res.getString("nom"),res.getString("image"));
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
    
    public Recette_detail [] get_recette_details(int idProduit) throws Exception
    {
        Recette_detail [] tab = null;
        try (Connection con = new Connexion().getConnection()) 
        {
            try
            {
                java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet res = stmt.executeQuery("select * from recette_detail where id_produit="+idProduit+"");
                int i=0;
                res.last();
                tab = new Recette_detail[res.getRow()];
                res.beforeFirst();
                while(res.next())
                {
                    tab[i] = new Recette_detail(res.getInt("id"),res.getInt("id_produit"),res.getString("nom_produit"),res.getString("nom_ingredient"),res.getInt("quantite"),res.getInt("montant"));
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
    
    public double reste_stock_produit(int id) throws Exception
    {
        double reste = 0;
        try (Connection con = new Connexion().getConnection()) 
        {
            java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet res = stmt.executeQuery("select * from reste_stock_produit where id="+id+"");
            while(res.next())
            {
                reste = res.getInt("reste");
            }
        }
        return reste;
    }
    
    public double reste_stock_ingredient(int id) throws Exception
    {
        double reste = 0;
        try (Connection con = new Connexion().getConnection()) 
        {
            java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet res = stmt.executeQuery("select * from reste_stock_ingredient where id="+id+"");
            while(res.next())
            {
                reste = res.getInt("reste");
            }
        }
        return reste;
    }
    
    
    
    public void inserer_inventaire_ingredient(int id_ingredient,int quantite,String date) throws Exception
    {
        try (Connection con = new Connexion().getConnection()) {
            java.sql.Statement stmt = con.createStatement();
            String req = String.format("insert into inventaire_ingredient values(default,"+id_ingredient+","+quantite+",'"+date+"')");
            stmt.executeUpdate(req);
            System.out.println(req);
            con.commit();
        }
    }
    
    public void inserer_inventaire_produit(int id_produit,int quantite,String date) throws Exception
    {
        try (Connection con = new Connexion().getConnection()) {
            java.sql.Statement stmt = con.createStatement();
            String req = String.format("insert into inventaire_produit values(default,"+id_produit+","+quantite+",'"+date+"')");
            stmt.executeUpdate(req);
            System.out.println(req);
            con.commit();
        }
    }
    
    public double get_qte_inventaire_produit(int id,int idproduit,String date) throws Exception
    {
        double qt = 0;
        try (Connection con = new Connexion().getConnection()) 
        {
            java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet res = stmt.executeQuery("select qte  from inventaire_produit where id="+id+" and id_produit="+idproduit+" and date='"+date+"'");
            while(res.next())
            {
                qt = res.getInt("qte");
            }
        }
        return qt;
    }
    
    public double get_qte_inventaire_ingredient(int id,int idingredient,String date) throws Exception
    {
        double qt = 0;
        try (Connection con = new Connexion().getConnection()) 
        {
            java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet res = stmt.executeQuery("select qte  from inventaire_ingredient where id="+id+" and id_ingredient="+idingredient+" and date='"+date+"'");
            while(res.next())
            {
                qt = res.getInt("qte");
            }
        }
        return qt;
    }
    
    public void inscrire (int id_profil,String username,String password) throws Exception
    {
        try (Connection con = new Connexion().getConnection()) {
            java.sql.Statement stmt = con.createStatement();
            String req = String.format("insert into utilisateur values(default,'"+username+"','"+password+"',"+id_profil+")");
            stmt.executeUpdate(req);
            System.out.println(req);
            con.commit();
        }
    }
    
    
    public int get_prix_revient(int idproduit) throws Exception
    {
        int sum = 0;
        try (Connection con = new Connexion().getConnection()) 
        {
            java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet res = stmt.executeQuery("select sum(quantite*montant) from recette_detail where id_produit="+idproduit+"");
            while(res.next())
            {
                sum = res.getInt("sum");
            }
        }
        return sum;
    }
    
    public int propose_prix_vente(int idproduit,int marge) throws Exception
    {
        int sum = 0;
        try (Connection con = new Connexion().getConnection()) 
        {
            java.sql.Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet res = stmt.executeQuery("select sum(quantite*montant)+(sum(quantite*montant)*"+marge+") as sum from recette_detail where id_produit="+idproduit+"");
            while(res.next())
            {
                sum = res.getInt("sum");
            }
        }
        return sum;
    }
    
}
