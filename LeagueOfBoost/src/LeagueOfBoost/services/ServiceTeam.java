
package LeagueOfBoost.services;


import LeagueOfBoost.entities.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import LeagueOfBoost.utils.MyDB;


public class ServiceTeam implements IService<Team>{
    
    Connection con ; 

    public ServiceTeam() {
        
    con = MyDB.createorgetInstance().getCon();

    }
    
    @Override
    public void Afficher() {
    try {
        String sql = "SELECT * FROM `LOB`.`Team`";
        PreparedStatement ste = con.prepareStatement(sql);
        
        ResultSet result = ste.executeQuery();
        
        while (result.next()) {
            int id = result.getInt("id");
            int game_id = result.getInt("game_id");
            String name = result.getString("name");
            String description = result.getString("description");
            String player1 = result.getString("player1");
            String player2 = result.getString("player2");
            String player3 = result.getString("player3");
            String player4 = result.getString("player4");
            String player5 = result.getString("player5");
            
            System.out.println("Team ID: " + id);
            System.out.println("Game ID: " + game_id);
            System.out.println("Name: " + name);
            System.out.println("Description: " + description);
            System.out.println("Players: " + player1 + ", " + player2 + ", " + player3 + ", " + player4 + ", " + player5);
            System.out.println("---------------");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    @Override
    public void AfficherById(Team t) {
    try {
        String sql = "SELECT * FROM `LOB`.`Team` WHERE id="+ t.getId();
        PreparedStatement ste = con.prepareStatement(sql);
        
        ResultSet result = ste.executeQuery();
        
        while (result.next()) {
            int id = result.getInt("id");
            int game_id = result.getInt("game_id");
            String name = result.getString("name");
            String description = result.getString("description");
            String player1 = result.getString("player1");
            String player2 = result.getString("player2");
            String player3 = result.getString("player3");
            String player4 = result.getString("player4");
            String player5 = result.getString("player5");
            
            System.out.println("Team ID: " + id);
            System.out.println("Game ID: " + game_id);
            System.out.println("Name: " + name);
            System.out.println("Description: " + description);
            System.out.println("Players: " + player1 + ", " + player2 + ", " + player3 + ", " + player4 + ", " + player5);
            System.out.println("---------------");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    @Override
    public void Ajouter(Team t) {
        try {
            String sql = "INSERT INTO `LOB`.`Team` (id, game_id, name, description, player1, player2, player3, player4, player5) VALUES (?,?,?,?,?,?,?,?,?);";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1, 67);
            ste.setInt(2, t.getGame_id());
            ste.setString(3, t.getName());
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

    @Override
    public void Modifier(Team t) {
     try {
         String sql = "UPDATE `LOB`.`Team` SET name=?, description=?, player1=?, player2=?, player3=?, player4=?, player5=? WHERE id=? AND game_id=?";
         PreparedStatement ste = con.prepareStatement(sql);
         ste.setString(1, t.getName());
         ste.setString(2, t.getDescription());
         ste.setString(3, t.getPlayer1());
         ste.setString(4, t.getPlayer2());
         ste.setString(5, t.getPlayer3());
         ste.setString(6, t.getPlayer4());
         ste.setString(7, t.getPlayer5());
         ste.setInt(8, t.getId());
         ste.setInt(9, t.getGame_id());

         ste.executeUpdate();
     } catch (SQLException ex) {
         System.out.println(ex.getMessage());
        }  
    }

    @Override
    public void Supprimer(Team t) {
    try {
        String sql = "DELETE FROM `LOB`.`Team` WHERE id=? AND game_id=?";
        PreparedStatement ste = con.prepareStatement(sql);
        ste.setInt(1, t.getId());
        ste.setInt(2, t.getGame_id());
        
        ste.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}



    
     
    
}
