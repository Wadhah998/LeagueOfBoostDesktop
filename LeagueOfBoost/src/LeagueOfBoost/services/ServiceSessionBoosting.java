/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LeagueOfBoost.services;

import LeagueOfBoost.entities.Reservationb;
import LeagueOfBoost.entities.SessionBoosting;
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
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javax.management.Query.lt;

/**
 *
 * @author ASUS
 */
public class ServiceSessionBoosting  {
 Connection con = MyDB.createorgetInstance().getCon();
 
   
   public void ajouterSessionBoosting(SessionBoosting s) {
    try {
        String sql = "INSERT INTO session_boosting(user_id, description, nbr_heure, titre, prix) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, InscriptionController.userc.getId());
        stmt.setString(2, s.getDescription());
        stmt.setInt(3, s.getNbr_heure());
        stmt.setString(4, s.getTitre());
        stmt.setInt(5, s.getPrix());
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
// Fonction pour modifier une session de boosting existante
public void modifierSessionBoosting(int id, SessionBoosting sb) {
    try {
        String sql = "update session_boosting set description=?, titre=?, nbr_heure=?, prix=? where id=?";
        PreparedStatement ste = con.prepareStatement(sql);
        ste.setString(1, sb.getDescription());
        ste.setString(2, sb.getTitre());
        ste.setInt(3, sb.getNbr_heure());
        ste.setInt(4, sb.getPrix());
        ste.setInt(5, sb.getId());
        
        ste.executeUpdate();
        System.out.println("Session de boosting modifiée !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

// Fonction pour supprimer une session de boosting existante
public void supprimerSessionBoosting(int id) {
    try {
        String sql = "delete from session_boosting where id=?";
        PreparedStatement ste = con.prepareStatement(sql);
        ste.setInt(1, id);
        
        ste.executeUpdate();
        System.out.println("Session de boosting supprimée !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

 public ObservableList<SessionBoosting> afficherS() {
    ObservableList<SessionBoosting> boosters = FXCollections.observableArrayList();
    try {
        String sql = "SELECT * FROM `LOB`.`session_boosting`";
        Statement st = con.createStatement();

        ResultSet result = st.executeQuery(sql);

        while (result.next()) {
     /*
            String titre = result.getString("titre");
            String description = result.getString("description");
            int nbr_heure = result.getInt("nbr_heure");
            int prix = result.getInt("prix");
       */     

            SessionBoosting r = new SessionBoosting( result.getInt("id"),result.getString("titre"), result.getString("description"), result.getInt("nbr_heure"),result.getInt("prix"));
            boosters.add(r);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return boosters;
}




}
