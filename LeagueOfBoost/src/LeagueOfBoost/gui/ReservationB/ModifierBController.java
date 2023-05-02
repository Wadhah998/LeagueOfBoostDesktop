/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package LeagueOfBoost.gui.ReservationB;

import LeagueOfBoost.entities.Reservationb;
import LeagueOfBoost.entities.SessionBoosting;
import LeagueOfBoost.gui.sessionb.ModifiersController;
import LeagueOfBoost.gui.ReservationB.ReservationBController;
import LeagueOfBoost.services.ServiceReservationB;
import LeagueOfBoost.services.ServiceSessionBoosting;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ModifierBController implements Initializable {

   @FXML
    private Button btnconf;

    @FXML
    private TextField id;

    @FXML
    private TextField txtnew;

    @FXML
    private TextField txtold;

    @FXML
    private TextField txtprix;

    /**
     * Initializes the controller class.
     */
    ServiceReservationB rb = new ServiceReservationB();
    @FXML
    private Button btnretour;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Reservationb r = ReservationBController.rb;
        txtold.setText(r.getOldrank());
        txtnew.setText(r.getNewrank());
        txtprix.setText(Integer.toString(r.getPrix()));


       
    }    

    @FXML
    private void Modifier(ActionEvent event) {
        Reservationb r = ReservationBController.rb;
        r.setOldrank(txtold.getText());
        r.setNewrank(txtnew.getText());
        r.setPrix(Integer.parseInt(txtprix.getText()));
        rb.modifierReservation(r.getId(), r);
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservationB.fxml"));
            Parent root = loader.load();
            
           // ModifiersController controleur = loader.getController();
            
           // controleur.setTextFields(s);
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnconf.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getCause().getMessage());
        }
        
        

        
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservationB.fxml"));
            Parent root = loader.load();
            
          //  ModifierBController controleur = loader.getController();
            
           // controleur.setTextFields(s);
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnretour.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }
    
}
