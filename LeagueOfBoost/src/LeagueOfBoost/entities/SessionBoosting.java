/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LeagueOfBoost.entities;

import javafx.scene.control.Button;

/**
 *
 * @author ASUS
 */
public class SessionBoosting {
    private int id;
    private int user_id;
    private String description,titre;
    private int nbr_heure,prix;
    
    public SessionBoosting() {
    }

    public SessionBoosting(int id, String description, String titre, int nbr_heure, int prix) {
        this.id = id;
        this.description = description;
        this.nbr_heure = nbr_heure;
        this.titre = titre;
        this.prix = prix;
    }

    public SessionBoosting(int id, String description, String titre, int nbr_heure) {
        this.id = id;
        this.description = description;
        this.nbr_heure = nbr_heure;
        this.titre = titre;

    }

    public SessionBoosting(String titre,String description, int nbr_heure, int prix) {
        this.titre = titre;
        this.description = description;
        this.nbr_heure = nbr_heure;
        this.prix = prix;
    }
    
    public int getId() {
        return id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbr_heure() {
        return nbr_heure;
    }

    public void setNbr_heure(int nbr_heure) {
        this.nbr_heure = nbr_heure;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "SessionBoosting{" + "id=" + id + ", description=" + description + ", titre=" + titre + ", nbr_heure=" + nbr_heure + ", prix=" + prix + '}';
    }

    public void setSupprimerS(Button supprimer) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
