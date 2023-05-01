/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.entities;

import javafx.scene.control.Button;

/**
 *
 * @author Sami
 */
public class ReservationC {
    private int id,coach_id,user_id,nbr_heures,prix;
    private String langue;
    public Button supprimer;

    public Button getSupprimer() {
        return supprimer;
    }

    public void setSupprimer(Button supprimer) {
        this.supprimer = supprimer;
    }

    public ReservationC(int nbr_heures, int prix, String langue) {
        this.nbr_heures = nbr_heures;
        this.prix = prix;
        this.langue = langue;
    }

    public ReservationC(int id, int coach_id, int user_id, int nbr_heures, int prix, String langue) {
        this.id = id;
        this.coach_id = coach_id;
        this.user_id = user_id;
        this.nbr_heures = nbr_heures;
        this.prix = prix;
        this.langue = langue;
    }
    public ReservationC(int id, int nbr_heures, int prix, String langue) {
        this.id = id;
        this.nbr_heures = nbr_heures;
        this.prix = prix;
        this.langue = langue;
    }

    public int getId() {
        return id;
    }

    public int getCoach_id() {
        return coach_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getNbr_heures() {
        return nbr_heures;
    }

    public int getPrix() {
        return prix;
    }

    public String getLangue() {
        return langue;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCoach_id(int coach_id) {
        this.coach_id = coach_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setNbr_heures(int nbr_heures) {
        this.nbr_heures = nbr_heures;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    @Override
    public String toString() {
        return "ReservationC{" + "id=" + id + ", coach_id=" + coach_id + ", user_id=" + user_id + ", nbr_heures=" + nbr_heures + ", prix=" + prix + ", langue=" + langue + '}';
    }
    
}
