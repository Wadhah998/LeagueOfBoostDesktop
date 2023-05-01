/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.entities;
import java.util.Date;
import javafx.scene.control.Button;
/**
 *
 * @author Sami
 */
public class SessionC {
    private int id ;
    private int user_id ;
    private String titre ;
    private String description ;
    private int prix ;
    private Date date ;
    public Button supprimer;

    public Button getSupprimer() {
        return supprimer;
    }

    public void setSupprimer(Button supprimer) {
        this.supprimer = supprimer;
    }

    public SessionC() {
    }

    public SessionC(String titre, String description, int prix) {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
    }
    

    public SessionC(String titre, String description, int prix, Date date) {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.date = date;
    }

    public SessionC(int id,String titre, String description, int prix,Date date,int user_id) {
        this.id = id;
        
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.date = date;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public int getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SessionC{" + "id=" + id + ", user_id=" + user_id + ", titre=" + titre + ", description=" + description + ", prix=" + prix + ", date=" + date + '}';
    }

    
    
}
