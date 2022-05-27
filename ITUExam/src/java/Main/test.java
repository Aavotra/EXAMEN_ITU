/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Model.serveur.Menu;
import Model.services.Dao;
import Model.utilisateur.Utilisateur;
import java.sql.Connection;
import java.util.Calendar;

/**
 *
 * @author sony
 */
public class test {
    
    public static void main(String [] args) throws Exception
    {
            Dao d = new Dao();
            /*Utilisateur [] tab = d.find_all_user();
            for(int i = 0 ; i < tab.length; i ++)
            {
                System.out.println(tab[i].getUsername());
            }*/
            Menu m = d.get_menu();
            System.out.println(m.getDate()[0]);
    }
}
