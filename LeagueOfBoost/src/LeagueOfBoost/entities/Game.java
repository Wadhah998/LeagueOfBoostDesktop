/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.entities;

import java.time.LocalDate;
import java.util.Date;
import javafx.scene.control.Button;

/**
 *
 * @author Mega-PC
 */
public class Game {
    
    public int game_id;
    public String title;
    public String description;
    public int price;
    public Date date;
    public Button supprimer;
    
     public Game(){
        
    }


    public Game(String title, String description, int price, Date date) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.date = date;
    }
     public Game(int game_id, String title, String description, int price, Date date) {
        this.game_id = game_id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.date = date;
    }

    public Game(String title, String description, int price) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public Button getSupprimer() {
        return supprimer;
    }

    public void setSupprimer(Button supprimer) {
        this.supprimer = supprimer;
    }
    
   

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
    
    @Override
    public String toString() {
        return "Game{" + "id=" + game_id + ", title=" + title + ", description=" + description + ", price=" + price + ", date=" + date + '}';
    }
}