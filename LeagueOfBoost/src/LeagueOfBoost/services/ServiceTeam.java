
package LeagueOfBoost.services;


import LeagueOfBoost.entities.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import LeagueOfBoost.utils.MyDB;
import java.util.List;
import java.util.ArrayList;


public class ServiceTeam implements IService<Team>{
    
    Connection con ; 

    public ServiceTeam() {
        
    con = MyDB.createorgetInstance().getCon();

    }
    
  //Les fonctions d'affichage
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

    public List<Team> afficherTeams() {
    List<Team> teams = new ArrayList<>();
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

            Team team = new Team(id, game_id, name, description, player1, player2, player3, player4, player5);
            teams.add(team);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return teams;
}

    //Les fonctions CRUD

    @Override
    public void Ajouter(Team t) {
        try {
            String sql = "INSERT INTO `LOB`.`Team` (game_id, name, description, player1, player2, player3, player4, player5) VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1, t.getGame_id());
            ste.setString(2, t.getName());
            ste.setString(3, t.getDescription());
            ste.setString(4, t.getPlayer1());
            ste.setString(5, t.getPlayer2());
            ste.setString(6, t.getPlayer3());
            ste.setString(7, t.getPlayer4());
            ste.setString(8, t.getPlayer5());
            
        
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
    
     public void SupprimerById(int t) {
    try {
        String sql = "DELETE FROM `LOB`.`Team` WHERE id=?";
        PreparedStatement ste = con.prepareStatement(sql);
        ste.setInt(1, t);
        
        ste.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

      @Override
    public void Supprimer(Team t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}



    
     
