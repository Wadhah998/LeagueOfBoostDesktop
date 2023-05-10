/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.gui.ReservationC;

import LeagueOfBoost.entities.ReservationC;
import LeagueOfBoost.services.ServiceReservationC;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class ModifierController implements Initializable {

    @FXML
    private AnchorPane main;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnretour;
    private TextField txtnbr;
    private TextField txtprx;
    private TextField txtlng;

    /**
     * Initializes the controller class.
     */
    ServiceReservationC rb = new ServiceReservationC();
    @FXML
    private TextField txttitre;
    @FXML
    private TextField txtdesc;
    @FXML
    private TextField txtprix;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReservationC r = ReservationCController.rb;
//        txtnbr.setText(Integer.toString(r.getNbr_heures()));
  //      txtprx.setText(Integer.toString(r.getPrix()));
    //    txtlng.setText(r.getLangue());


       
    }    

    @FXML
    private void Modifier(ActionEvent event) {
        ReservationC r = ReservationCController.rb;
        r.setNbr_heures(Integer.parseInt(txtnbr.getText()));
        r.setPrix(Integer.parseInt(txtprx.getText()));
        r.setLangue((txtlng.getText()));
        rb.modifierReservation(r.getId(), r);
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservationC.fxml"));
            Parent root = loader.load();
            
           // ModifiersController controleur = loader.getController();
            
           // controleur.setTextFields(s);
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnmodif.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getCause().getMessage());
        }
        
        

        
    }
    

    @FXML
    private void Retour(ActionEvent event) {
        try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("ReservationC.fxml"));
          main.getChildren().removeAll() ; 
          main.getChildren().setAll(sv) ;                      
        } catch (IOException ex) {
             Logger.getLogger(ModifierController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
}
