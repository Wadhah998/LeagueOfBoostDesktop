/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.services;

import LeagueOfBoost.entities.ReservationC;
import LeagueOfBoost.gui.user.InscriptionController;
import LeagueOfBoost.utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author Sami
 */
public class ServiceReservationC  {
    Connection con = MyDB.createorgetInstance().getCon();


    public void ajouterReservation(ReservationC r) {
try {
            String sql = "insert into reservation_c(coach_id,user_id,nbr_heures,prix,langue)"
                    + "values (?,?,?,?,?)";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1,4);
            ste.setInt(2, InscriptionController.userc.getId());
            ste.setInt(3, r.getNbr_heures());
            ste.setInt(4,r.getPrix());
            ste.setString(5,r.getLangue());
            
            ste.executeUpdate();
            System.out.println("Reservation Ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       }
    public void modifierReservation(int id,ReservationC r) {
        try {
            String sql = "update reservation_c set coach_id=?, user_id=?, nbr_heures=?, prix=?, langue=? where id=?";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1,4);
            ste.setInt(2,3);
            ste.setInt(3, r.getNbr_heures());
            ste.setInt(4, r.getPrix());
            ste.setString(5, r.getLangue());
            ste.setInt(6,id);
            ste.executeUpdate();
            System.out.println("Reservation modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerReservation(int id) {
        try {
            String sql = "delete from reservation_c where id=?";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1,id);
            
            ste.executeUpdate();
            System.out.println("Reservation supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     *
     */
    public void afficherReservations() {
        try {
            String sql = "select * from reservation_c";
            PreparedStatement ste = con.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getInt("coach_id") + " | " + rs.getInt("user_id") + " | " + 
                        rs.getInt("nbr_heures") + " | " + rs.getInt("prix") + " | " + rs.getString("langue"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public ObservableList<ReservationC> afficherReservation(int user_id) {
        ObservableList<ReservationC> ReservationC = FXCollections.observableArrayList();
        
        try {
            String sql ="select * from reservation_c where user_id=?";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1, user_id);
            ResultSet s = ste.executeQuery();
            
            while (s.next()) {
                
                ReservationC rc = new ReservationC(1,s.getInt("nbr_heures"), s.getInt("prix"), s.getString("langue"));
                ReservationC.add(rc);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());    
        }
        return ReservationC;
    }
    
    public List<ReservationC> afficherCoaches() {
    List<ReservationC> rcs = new ArrayList<>();
    try {
        String sql = "SELECT * FROM `LOB`.`reservation_c`";
        PreparedStatement ste = con.prepareStatement(sql);

        ResultSet result = ste.executeQuery();

        while (result.next()) {
            int id = result.getInt("id");
            int coach_id = result.getInt("coach_id");
            int user_id = result.getInt("user_id");
            int nbr_heures = result.getInt("nbr_heures");
            int prix = result.getInt("prix");
            String langue = result.getString("langue");
            

            ReservationC rc = new ReservationC(id, coach_id, user_id, nbr_heures, prix, langue);
            rcs.add(rc);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return rcs;
}


}
