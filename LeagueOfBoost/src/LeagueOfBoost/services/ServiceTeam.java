
package LeagueOfBoost.services;


import LeagueOfBoost.entities.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import LeagueOfBoost.utils.MyDB;


public class ServiceTeam {
    
    Connection con ; 

    public ServiceTeam() {
        
    con = MyDB.createorgetInstance().getCon();

    }
    
    
    public void Ajouter(Team t) {
        try {
            String sql = "INSERT INTO `LOB`.`Team` (id, game_id, name, description, player1, player2, player3, player4, player5) VALUES (?,?,?,?,?,?,?,?,?);";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1, 5);
            ste.setInt(2, t.getGame_id());
            ste.setString(2, t.getName());
            ste.setString(4, t.getDescription());
            ste.setString(5, t.getPlayer1());
            ste.setString(6, t.getPlayer2());
            ste.setString(7, t.getPlayer3());
            ste.setString(8, t.getPlayer4());
            ste.setString(9, t.getPlayer5());
            
        
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Modifier(Team t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void Supprimer(Team t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
     
    
}
