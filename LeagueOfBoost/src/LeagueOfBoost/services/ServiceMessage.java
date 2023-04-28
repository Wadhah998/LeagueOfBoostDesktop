package LeagueOfBoost.services;

import LeagueOfBoost.entities.Reclamation;
import LeagueOfBoost.entities.Message;
import LeagueOfBoost.utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServiceMessage implements IService<Message> {

        Connection con;

        
         public ServiceMessage() {
        con = MyDB.createorgetInstance().getCon();

    }
  
        @Override
    public void Ajouter(Message t) {
        try {
            String sql = "insert into message (reclamation_id, user_id, date, message)"
                    + "values (?,?,?,?)";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1,t.getReclamation_id());
            ste.setInt(2,3);
             ste.setDate(3, new java.sql.Date(t.getDate().getTime()));
            ste.setString(4, t.getMessage());
            ste.executeUpdate();
            System.out.println("message ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Modifier(Message t) {
        try {
            String sql = "UPDATE `LOB`.`message` SET reclamation_id=?, user_id=?, date=?, message=? WHERE id=?";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1, t.getReclamation_id());
            ste.setInt(2, t.getUser_id());
            ste.setDate(3, new java.sql.Date(t.getDate().getTime()));
            ste.setString(4, t.getMessage());
            ste.setInt(5, t.getId());

            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    

    public void SupprimerById(Message t) {
        try {
            String sql = "DELETE FROM `LOB`.`message` WHERE id=?";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1, t.getId());

            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }
    
        
    public void Supprimermess(int t) {
        try {
            String sql = "DELETE FROM `LOB`.`message` WHERE id=?";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1, t);

            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }
    
    
    public void AfficherMessages(Reclamation reclamation) {
    try {
        String sql = "SELECT * FROM `LOB`.`message` WHERE reclamation_id=" + reclamation.getId();
        PreparedStatement ste = con.prepareStatement(sql);

        ResultSet result = ste.executeQuery();

        while (result.next()) {
            int id = result.getInt("id");
            int user_id = result.getInt("user_id");
            int reclamation_id = result.getInt("reclamation_id");
            String message = result.getString("message");
            Date date = result.getDate("date");

            System.out.println("message ID: " + id);
            System.out.println("user's id: " + user_id);
            System.out.println("reclamation id: " + reclamation_id);
            System.out.println("message: " + message);
            System.out.println("date: " + date);
            System.out.println("---------------");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    }
    
    public void AfficherByreclamationId(int t) {
        try {
            String sql = "SELECT * FROM `LOB`.`message` WHERE reclamation_id=" + t;
            PreparedStatement ste = con.prepareStatement(sql);

            ResultSet result = ste.executeQuery();

            while (result.next()) {
            int id = result.getInt("id");
            int user_id = result.getInt("user_id");
            int reclamation_id = result.getInt("reclamation_id");
            String message = result.getString("message");
            Date date = result.getDate("date");

            System.out.println("message ID: " + id);
            System.out.println("user's id: " + user_id);
            System.out.println("reclamation id: " + reclamation_id);
            System.out.println("message: " + message);
            System.out.println("date: " + date);
            System.out.println("---------------");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
        //////////////////////////////////////////////////////////////////////////////

    public void AffichermessById(Message t) {
        try {
            String sql = "SELECT * FROM `LOB`.`message` WHERE id=" + t.getId();
            PreparedStatement ste = con.prepareStatement(sql);

            ResultSet result = ste.executeQuery();

            while (result.next()) {
            int id = result.getInt("id");
            int user_id = result.getInt("user_id");
            int reclamation_id = result.getInt("reclamation_id");
            String message = result.getString("message");
            Date date = result.getDate("date");

            System.out.println("message ID: " + id);
            System.out.println("user's id: " + user_id);
            System.out.println("reclamation id: " + reclamation_id);
            System.out.println("message: " + message);
            System.out.println("date: " + date);
            System.out.println("---------------");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //////////////////////////////////////////////////////////////////////////////
        public List<Message> AffichermessById2(int t) {
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `LOB`.`message` WHERE reclamation_id=" + t;
            Statement ste = con.createStatement();

            ResultSet result = ste.executeQuery(sql);

            while (result.next()){
                int id = result.getInt("id");
                int user_id = result.getInt("user_id");
                int reclamation_id = result.getInt("reclamation_id");
                String message2 = result.getString("message");
                Date date = result.getDate("date");

                Message message = new Message(id, reclamation_id, user_id, date, message2);
                messages.add(message);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return messages;
    }
    
        //////////////////////////////////////////////////////////////////////////////

    
    
    
    
    
    
    
    
    
    
    
    
    
    
        @SuppressWarnings("empty-statement")
    public ObservableList<Message> afficherMessages() {
        ObservableList<Message> reclamations = FXCollections.observableArrayList();;
        try {
            String sql = "SELECT * FROM `LOB`.`message`";
            Statement ste = con.createStatement();

            ResultSet result = ste.executeQuery(sql);

            while (result.next()) {
                int id = result.getInt("id");
                int reclamation_id = result.getInt("reclamation_id");
                int user_id = result.getInt("user_id");

                Date date = result.getDate("date");
                String message = result.getString("message");
            
                Message reclamation = new Message(id,reclamation_id,user_id, date, message);
                reclamations.add(reclamation);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamations;
    }

    @Override
    public void Supprimer(Message t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
}

    
    

