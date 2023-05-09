/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.gui.Reclamation;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import LeagueOfBoost.entities.Reclamation;
import LeagueOfBoost.services.ServiceReclamation;
import LeagueOfBoost.utils.MyDB;
import java.sql.SQLException;
import java.util.Date;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.DoubleStringConverter;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class AjouterRController {

    @FXML
    private TextField themeclm;
    @FXML
    private TextField objectclm;
    @FXML
    private TextField textclm;
    @FXML
    private Button ajouterclm;
    @FXML
    private AnchorPane main;
    
    
    
    ServiceReclamation sr = new ServiceReclamation();
    @FXML
    private Button Retour;
    

    /**
     * Initializes the controller class.
     */
    public void initialize() {

        
        themeclm.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                themeclm.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });

        objectclm.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                objectclm.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });        

        textclm.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                textclm.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });          
    }

    @FXML
    private void ajouter(ActionEvent event) {
        // Vérifie que tous les champs ont été remplis
        if (textclm.getText().isEmpty() || objectclm.getText().isEmpty() || themeclm.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs doivent être remplis !");
            alert.showAndWait();
            return;
        }

        Date Datenow = new Date();
        String Object = objectclm.getText();
        String Text = textclm.getText();
        String Theme = themeclm.getText();
        Reclamation r = new Reclamation(false, Datenow, Theme, Object, Text);
        sr.Ajouter(r);
        
        // Affiche un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Réclamation ajoutée avec succès !");
        alert.showAndWait();

        // Réinitialise les champs de saisie
        
        objectclm.setText("");
        textclm.setText("");
        themeclm.setText("");
    } 




    public void mesreclamations(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/LeagueOfBoost/gui/Reclamation/reclamationUser.fxml"));
        main.getChildren().removeAll();
        main.getChildren().setAll(fxml);


    }
}
