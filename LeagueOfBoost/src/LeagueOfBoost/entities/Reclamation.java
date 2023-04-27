/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wassim
 */
public class Reclamation {
    private int id;
    private int user_id;
    private boolean etat;
    private int date;
    private String theme ,object,text;

    

    public Reclamation(int id, int user_id, boolean etat, int date, String theme, String object, String text) {
        this.id = id;
        this.user_id = user_id;
        this.etat = etat;
        this.date = date;
        this.theme = theme;
        this.object = object;
        this.text = text;
    }

    public Reclamation( boolean etat,int date, String theme, String object, String text) {

        this.etat = etat;
        this.date = date;
        this.theme = theme;
        this.object = object;
        this.text = text;
    }
    public Reclamation(int id, int date, String theme, String object, String text) {
        this.id = id;
        this.date = date;
        this.theme = theme;
        this.object = object;
        this.text = text;
    }
    public Reclamation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", etat=" + etat + ", date=" + date + ", theme=" + theme + ", object=" + object + ", text=" + text + '}';
    }

    public String valueOf(int date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
 
    
}