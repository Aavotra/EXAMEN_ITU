/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.cnx;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author sony
 */
public class Connexion 
{
    public Connection getConnection() throws Exception
    {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/resto?user=postgres&password=ETU001146";
        Connection con = DriverManager.getConnection(url);
        con.setAutoCommit(false);
        return con;
    }
}
