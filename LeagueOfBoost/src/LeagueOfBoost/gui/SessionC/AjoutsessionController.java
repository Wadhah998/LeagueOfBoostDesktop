/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.gui.SessionC;

import LeagueOfBoost.entities.SessionC;
import LeagueOfBoost.gui.ReservationC.AjoutController;
import LeagueOfBoost.services.ServiceSessionC;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class AjoutsessionController implements Initializable {

    @FXML
    private Button btnajout;
    @FXML
    private Button btnretour;
    @FXML
    private TextField txttitre;
    @FXML
    private TextField txtdesc;
    @FXML
    private AnchorPane main;
    

      ServiceSessionC sc = new ServiceSessionC();
    @FXML
    private TextField txtprix;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Définit les contraintes de validation pour chaque champ de saisie
        txttitre.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txttitre.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        txtdesc.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtdesc.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        txtprix.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtprix.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    
    }    
    

    @FXML
    private void Ajouter(ActionEvent event) {
        // Vérifie que tous les champs ont été remplis
        if (txttitre.getText().isEmpty() || txtdesc.getText().isEmpty() || txtprix.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs doivent être remplis !");
            alert.showAndWait();
            return;
        }

        String Titre = txttitre.getText();
        String Description = txtdesc.getText();
        int Prix = Integer.parseInt(txtprix.getText());
        Date date1 = new Date();
        SessionC r = new SessionC(Titre,Description,Prix,date1);
        sc.ajouterSessionCoaching(r);
        
        // Affiche un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Réservation ajoutée avec succès !");
        alert.showAndWait();

        // Réinitialise les champs de saisie
        txttitre.setText("");
        txtdesc.setText("");
        txtprix.setText("");
        
        
    }   
    @FXML
    private void Retour(ActionEvent event) {
        try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("SessionC.fxml"));
          main.getChildren().removeAll() ; 
          main.getChildren().setAll(sv) ;                      
        } catch (IOException ex) {
             Logger.getLogger(AjoutsessionController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    }