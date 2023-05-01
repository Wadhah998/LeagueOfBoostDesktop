/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.services;

import LeagueOfBoost.entities.ReservationC;
import LeagueOfBoost.entities.SessionC;
import LeagueOfBoost.utils.MyDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
/**
 *
 * @author Sami
 */
public class ServiceSessionC implements IService {
    Connection con = MyDB.createorgetInstance().getCon();
 
   
   public void ajouterSessionCoaching(SessionC s) {
    try {
        String sql = "INSERT INTO session_coaching(titre, description, prix, date, user_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, s.getTitre());
        stmt.setString(2, s.getDescription());
        stmt.setInt(3, s.getPrix());
        stmt.setDate(4, new java.sql.Date(s.getDate().getTime()));
        stmt.setInt(5,4);
        int rowsInserted = stmt.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("La session a été ajoutée avec succès !");
        } else {
            System.out.println("La session n'a pas pu être ajoutée.");
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de l'ajout de la session : " + ex.getMessage());
    }
}
   public void modifierSessionCoaching(int id, SessionC sc) {
    try {
        String sql = "update session_coaching set titre=?, description=?, prix=?, date=?, user_id=? where id=?";
        PreparedStatement ste = con.prepareStatement(sql);
        ste.setString(1, sc.getTitre());
        ste.setString(2, sc.getDescription());
        ste.setInt(3, sc.getPrix());
        ste.setDate(4, new java.sql.Date(sc.getDate().getTime()));
        ste.setInt(5,4);
        ste.setInt(6,id);
        
        ste.executeUpdate();
        System.out.println("Session de boosting modifiée !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
   public void supprimerSessionCoaching(int id) {
    try {
        String sql = "delete from session_coaching where id=?";
        PreparedStatement ste = con.prepareStatement(sql);
        ste.setInt(1, id);
        
        ste.executeUpdate();
        System.out.println("Session de boosting supprimée !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
   public void afficherSessionCoach() {
        try {
            String sql = "select * from session_coaching";
            PreparedStatement ste = con.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("titre") + " | " + rs.getString("description") + " | " + 
                        rs.getInt("prix") + " | " + rs.getDate("date") + " | " + rs.getInt("user_id"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   public List<SessionC> afficherSessions() {
    List<SessionC> scs = new ArrayList<>();
    try {
        String sql = "SELECT * FROM `LOB`.`session_coaching`";
        PreparedStatement ste = con.prepareStatement(sql);

        ResultSet result = ste.executeQuery();

        while (result.next()) {
            int id = result.getInt("id");
            String titre = result.getString("titre");
            
            String description = result.getString("description");
            int prix = result.getInt("prix");
            Date date = result.getDate("date");
            int user_id = result.getInt("user_id");
            

            SessionC sc = new SessionC(id, titre, description, prix, date, user_id);
            scs.add(sc);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return scs;
}
   @Override
    public ObservableList<ReservationC> afficherReservation() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void ajouterReservation(ReservationC r) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modifierReservation(ReservationC r) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void supprimerReservation(ReservationC r) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
