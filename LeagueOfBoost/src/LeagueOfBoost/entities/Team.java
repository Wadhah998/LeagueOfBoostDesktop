
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.entities;

import javafx.scene.control.Button;

/**
 *
 * @author Mega-PC
 */
public class Team {
    int id, game_id, wins, losses;
    String name, description, player1,player2,player3,player4,player5;
     public Button supprimer;

    public Button getSupprimer() {
        return supprimer;
    }

    public void setSupprimer(Button supprimer) {
        this.supprimer = supprimer;
    }
    
    
    public Team(){
        
    }

    public Team(String name, int wins, int losses) {
       
        this.name = name;
        this.wins = wins;
        this.losses = losses;
    }

    public Team(int game_id, String name, String description, String player1, String player2, String player3, String player4, String player5) {
        this.game_id = game_id;
        this.name = name;
        this.description = description;
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        this.player5 = player5;
    }
    

    public Team(int id, int game_id, String name, String description, String player1, String player2, String player3, String player4, String player5) {
        this.id = id;
        this.game_id = game_id;
        this.name = name;
        this.description = description;
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        this.player5 = player5;
    }

    @Override
    public String toString() {
        return "Team{" + "id=" + id + ", game_id=" + game_id + ", name=" + name + ", description=" + description + ", player1=" + player1 + ", player2=" + player2 + ", player3=" + player3 + ", player4=" + player4 + ", player5=" + player5 + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getPlayer3() {
        return player3;
    }

    public void setPlayer3(String player3) {
        this.player3 = player3;
    }

    public String getPlayer4() {
        return player4;
    }

    public void setPlayer4(String player4) {
        this.player4 = player4;
    }

    public String getPlayer5() {
        return player5;
    }

    public void setPlayer5(String player5) {
        this.player5 = player5;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }
    
    
    
    
}