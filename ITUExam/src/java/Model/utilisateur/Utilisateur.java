/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.utilisateur;

/**
 *
 * @author sony
 */
public class Utilisateur {
    
    private int id;
    private String username;
    private String password;
    private int id_profil;

    public Utilisateur(int id, String username, String password, int id_profil) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.id_profil = id_profil;
    }

    public int getId_profil() {
        return id_profil;
    }

    public void setId_profil(int id_profil) {
        this.id_profil = id_profil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
