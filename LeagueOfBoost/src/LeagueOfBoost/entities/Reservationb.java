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
public class Reservationb {
   private int id,user_id,prix;
   private String oldrank,newrank;
   public Button supprimer;

    public Button getSupprimer() {
        return supprimer;
    }

    public void setSupprimer(Button supprimer) {
        this.supprimer = supprimer;
    }

    public Reservationb(int id, int user_id, int prix, String oldrank, String newrank) {
        this.id = id;
        this.user_id = user_id;
        this.prix = prix;
        this.oldrank = oldrank;
        this.newrank = newrank;
    }

    public Reservationb(int id, int prix, String oldrank, String newrank) {
        this.id = id;
        this.prix = prix;
        this.oldrank = oldrank;
        this.newrank = newrank;
    }

    
    public Reservationb() {
    }

    public Reservationb(int prix, String oldrank, String newrank) {
        this.prix = prix;
        this.oldrank = oldrank;
        this.newrank = newrank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getOldrank() {
        return oldrank;
    }

    public void setOldrank(String oldrank) {
        this.oldrank = oldrank;
    }

    public String getNewrank() {
        return newrank;
    }

    public void setNewrank(String newrank) {
        this.newrank = newrank;
    }

    @Override
    public String toString() {
        return "Reservationb{" + "id=" + id + ", user_id=" + user_id + ", prix=" + prix + ", oldrank=" + oldrank + ", newrank=" + newrank + '}';
    }
  
}
    
