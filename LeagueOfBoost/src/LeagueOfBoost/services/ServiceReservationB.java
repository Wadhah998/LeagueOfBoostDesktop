/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LeagueOfBoost.services;

import LeagueOfBoost.entities.Reservationb;
import LeagueOfBoost.gui.user.InscriptionController;
import LeagueOfBoost.utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class ServiceReservationB{
    
    Connection con = MyDB.createorgetInstance().getCon();


    public void ajouterReservation(Reservationb r) {
try {
            String sql = "insert into reservation_booster(user_id,oldrank,newrank,prix)"
                    + "values (?,?,?,?)";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1, InscriptionController.userc.getId());
            ste.setString(2, r.getOldrank());
            ste.setString(3,r.getNewrank());
            ste.setInt(4,r.getPrix());
            
            ste.executeUpdate();
            System.out.println("Reservation Ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       }

    public void modifierReservation(int id ,Reservationb r) {
    try {
        String sql = "update reservation_booster set oldrank=?, newrank=?, prix=? where id=?";
        PreparedStatement ste = con.prepareStatement(sql);
        ste.setString(1, r.getOldrank());
        ste.setString(2, r.getNewrank());
        ste.setInt(3, r.getPrix());
        ste.setInt(4, r.getId());
        
        ste.executeUpdate();
        System.out.println("Reservation modifiée !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

// Fonction pour supprimer une réservation existante
public void supprimerReservation(int id) {
    try {
        String sql = "delete from reservation_booster where id=?";
        PreparedStatement ste = con.prepareStatement(sql);
        ste.setInt(1, id);
        
        ste.executeUpdate();
        System.out.println("Reservation supprimée !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
// Fonction pour afficher toutes les réservations
public void afficherReservations() {
    try {
        String sql = "select * from reservation_booster";
        PreparedStatement ste = con.prepareStatement(sql);
        
        ResultSet result = ste.executeQuery();
        
        while (result.next()) {
            int id = result.getInt("id");
            int userId = result.getInt("user_id");
            String oldRank = result.getString("oldrank");
            String newRank = result.getString("newrank");
            int prix = result.getInt("prix");
            
            System.out.println("ID: " + id + ", User ID: " + userId + ", Ancien Rank: " + oldRank + ", Nouveau Rank: " + newRank + ", Prix: " + prix);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
 public List<Reservationb> afficherR() {
    List<Reservationb> boosters = new ArrayList<>();
    try {
        String sql = "SELECT * FROM `LOB`.`reservation_booster`";
        Statement st = con.createStatement();

        ResultSet result = st.executeQuery(sql);

        while (result.next()) {
            int id = result.getInt("id");
            int user_id = result.getInt("user_id");
            int prix = result.getInt("prix");
            String oldrank = result.getString("oldrank");
            String newrank = result.getString("newrank");
            

            Reservationb r = new Reservationb(id, user_id, prix, oldrank, newrank);
            boosters.add(r);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return boosters;
}


    public ObservableList<Reservationb> afficherReservation(int user_id) {
        ObservableList<Reservationb> Reservationb = FXCollections.observableArrayList();
        
        try {
            String sql ="select * from reservation_booster where user_id=?";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1,user_id);
            ResultSet s = ste.executeQuery();
            
            while (s.next()) {
                
                Reservationb rb = new Reservationb(s.getInt(1), s.getInt("prix"), s.getString("oldrank"), s.getString("newrank"));
                Reservationb.add(rb);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());    
        }
                        System.out.println("listeee::"+Reservationb);

        return Reservationb;
    

    }





}
