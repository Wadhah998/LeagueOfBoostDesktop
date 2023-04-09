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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void Supprimer(Game t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
     
    
}
