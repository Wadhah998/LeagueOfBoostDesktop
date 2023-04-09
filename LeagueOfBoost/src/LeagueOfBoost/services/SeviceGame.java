/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.services;
import LeagueOfBoost.entities.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import LeagueOfBoost.utils.MyDB;

/**
 *
 * @author Mega-PC
 */
public class SeviceGame{
    
    Connection con ; 

    public SeviceGame() {
        
    con = MyDB.createorgetInstance().getCon();

    }
    public void Afficher() {
    try {
        String sql = "SELECT * FROM `LOB`.`Game`";
        PreparedStatement ste = con.prepareStatement(sql);
        
        ResultSet result = ste.executeQuery();
        
        while (result.next()) {
            int game_id = result.getInt("game_id");
            String title = result.getString("title");
            String description = result.getString("description");
            int price = result.getInt("price");
            int date = result.getInt("date");
            
            System.out.println("Game ID: " + game_id);
            System.out.println("Title: " + title);
            System.out.println("Description: " + description);
            System.out.println("Price: " + price);
            System.out.println("Release Date: " + date);
            System.out.println("---------------");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    
    public void AfficherById(Game t) {
    try {
        String sql = "SELECT * FROM `LOB`.`Game` WHERE game_id=" + t.getGame_id();
        PreparedStatement ste = con.prepareStatement(sql);
        
        ResultSet result = ste.executeQuery();
        
        while (result.next()) {
            int game_id = result.getInt("game_id");
            String title = result.getString("title");
            String description = result.getString("description");
            int price = result.getInt("price");
            int date = result.getInt("date");
            
            System.out.println("Game ID: " + game_id);
            System.out.println("Title: " + title);
            System.out.println("Description: " + description);
            System.out.println("Price: " + price);
            System.out.println("Release Date: " + date);
            System.out.println("---------------");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


    
    
    public void Ajouter(Game t) {
        try {
            String sql = "INSERT INTO `LOB`.`Game` (game_id, title, description, price, date) VALUES (?,?,?,?,?);";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1, 5);
            ste.setString(2, t.getTitle());
            ste.setString(3, t.getDescription());
            ste.setInt(4, t.getPrice());
            ste.setInt(5, t.getDate());
        
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Modifier(Game t) {
    try {
        String sql = "UPDATE `LOB`.`Game` SET title=?, description=?, price=?, date=? WHERE game_id=?";
        PreparedStatement ste = con.prepareStatement(sql);
        ste.setString(1, t.getTitle());
        ste.setString(2, t.getDescription());
        ste.setInt(3, t.getPrice());
        ste.setInt(4, t.getDate());
        ste.setInt(5, t.getGame_id());
        
        ste.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}



    public void Supprimer(Game t) {
    try {
        String sql = "DELETE FROM `LOB`.`Game` WHERE game_id=?";
        PreparedStatement ste = con.prepareStatement(sql);
        ste.setInt(1, t.getGame_id());
        
        ste.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}



    
     
    
}
