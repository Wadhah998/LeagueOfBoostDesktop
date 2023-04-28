/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.services;

import LeagueOfBoost.utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import LeagueOfBoost.entities.Reclamation;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author wassim
 */
public class ServiceReclamation implements IService<Reclamation> {
    Connection con;

    public ServiceReclamation() {
        con = MyDB.createorgetInstance().getCon();

    }

    public void Affichertout() {
        try {
            String sql = "SELECT * FROM `LOB`.`reclamation`";
            PreparedStatement ste = con.prepareStatement(sql);

            ResultSet result = ste.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                int user_id = result.getInt("user_id");
                boolean etat = result.getBoolean("etat");
                Date date = result.getDate("date");
                String theme = result.getString("theme");
                String object = result.getString("object");
                String text = result.getString("text");


                System.out.println("reclamation ID: " + id);
                System.out.println("user's id: " + user_id);
                System.out.println("etat: " + etat);
                System.out.println("date: " + date);
                System.out.println("theme: " + theme);
                System.out.println("object: " + object);
                System.out.println("text: " + text);
                System.out.println("---------------");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void AfficherByUser_Id(int t) {
        try {
            String sql = "SELECT * FROM `LOB`.`reclamation` WHERE user_id=" + t;
            PreparedStatement ste = con.prepareStatement(sql);

            ResultSet result = ste.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                int user_id = result.getInt("user_id");
                boolean etat = result.getBoolean("etat");
                Date date = result.getDate("date");
                String theme = result.getString("theme");
                String object = result.getString("object");
                String text = result.getString("text");

                System.out.println("reclamation ID: " + id);
                System.out.println("user's id: " + user_id);
                System.out.println("etat: " + etat);
                System.out.println("date: " + date);
                System.out.println("theme: " + theme);
                System.out.println("object: " + object);
                System.out.println("text: " + text);
                System.out.println("---------------");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void AfficherById(Reclamation t) {
        try {
            String sql = "SELECT * FROM `LOB`.`reclamation` WHERE id=" + t.getId();
            PreparedStatement ste = con.prepareStatement(sql);

            ResultSet result = ste.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                int user_id = result.getInt("user_id");
                boolean etat = result.getBoolean("etat");
                Date date = result.getDate("date");
                String theme = result.getString("theme");
                String object = result.getString("object");
                String text = result.getString("text");

                System.out.println("reclamation ID: " + id);
                System.out.println("user's id: " + user_id);
                System.out.println("etat: " + etat);
                System.out.println("date: " + date);
                System.out.println("theme: " + theme);
                System.out.println("object: " + object);
                System.out.println("text: " + text);
                System.out.println("---------------");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Reclamation> afficherReclamation() {
        ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();;
        try {
            String sql = "SELECT * FROM `LOB`.`reclamation`";
            Statement ste = con.createStatement();

            ResultSet result = ste.executeQuery(sql);

            while (result.next()) {
                int id = result.getInt("id");
                int user_id = result.getInt("user_id");
                boolean etat = result.getBoolean("etat");
                Date date = result.getDate("date");
                String theme = result.getString("theme");
                String object = result.getString("object");
                String text = result.getString("text");

                Reclamation reclamation = new Reclamation(id,user_id,etat, date, theme, object, text);
                reclamations.add(reclamation);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamations;
    }

    @Override
    public void Ajouter(Reclamation t) {
        try {
            String sql = "insert into reclamation (user_id, etat, date, theme , object, text)"
                    + "values (?,?,?,?,?,?)";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1, 3);
            ste.setBoolean(2, false);
            ste.setDate(3, new java.sql.Date(t.getDate().getTime()));
            ste.setString(4, t.getTheme());
            ste.setString(5, t.getObject());
            ste.setString(6, t.getText());
            ste.executeUpdate(); 
           System.out.println("Reclamation ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Modifier(Reclamation t) {
        try {
            
            String sql = "UPDATE `LOB`.`reclamation` SET date=?, theme=?, object=?, text=?, etat=?  WHERE id=?";
            PreparedStatement ste = con.prepareStatement(sql); 
            ste.setDate(1, new java.sql.Date(t.getDate().getTime()));
            ste.setString(2, t.getTheme());
            ste.setString(3, t.getObject());
            ste.setString(4, t.getText());
            ste.setBoolean(5, t.isEtat());
            ste.setInt(6, t.getId());

            
            ste.executeUpdate();
            
            //System.out.print("utilisateur modifier");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Supprimerrec(int a) {
        try {
            String sql = "DELETE FROM `LOB`.`reclamation` WHERE id=?";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1, a);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public void Supprimer(Reclamation t) {
        try {
            String sql = "DELETE FROM `LOB`.`reclamation` WHERE id=?";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1, t.getId());

            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }
    
    public void AfficherMessages(Reclamation r) {
    try {
        String sql = "SELECT * FROM `LOB`.`message` WHERE reclamation_id=" + r.getId();
        PreparedStatement ste = con.prepareStatement(sql);

        ResultSet result = ste.executeQuery();

        while (result.next()) {
            int id = result.getInt("id");
            int user_id = result.getInt("user_id");
            int reclamation_id = result.getInt("reclamation_id");
            String text = result.getString("text");
            Date date = result.getDate("date");

            System.out.println("message ID: " + id);
            System.out.println("user's id: " + user_id);
            System.out.println("reclamation id: " + reclamation_id);
            System.out.println("text: " + text);
            System.out.println("date: " + date);
            System.out.println("---------------");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    
    public void rateService(int id, int rating) {
    try {
        String sql = "UPDATE `LOB`.`reclamation` SET `rating` = ? WHERE `id` = ?";
        PreparedStatement ste = con.prepareStatement(sql);
        ste.setInt(1, rating);
        ste.setInt(2, id);
        ste.executeUpdate();
        System.out.println("Rating updated successfully");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

        public ObservableList<Reclamation> afficherReclamationbyuser() {
        ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();;
        try {
            String sql = "SELECT * FROM `LOB`.`reclamation` WHERE `user_id` =3";
            Statement ste = con.createStatement();

            ResultSet result = ste.executeQuery(sql);

            while (result.next()) {
                int id = result.getInt("id");
                int user_id = result.getInt("user_id");
                boolean etat = result.getBoolean("etat");
                Date date = result.getDate("date");
                String theme = result.getString("theme");
                String object = result.getString("object");
                String text = result.getString("text");

                Reclamation reclamation = new Reclamation(id,user_id,etat, date, theme, object, text);
                reclamations.add(reclamation);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamations;
    }
    
    

    
}
