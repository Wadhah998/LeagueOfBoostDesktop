/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package LeagueOfBoost.gui;

import LeagueOfBoost.entities.SessionBoosting;
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
public class ModifiersController implements Initializable {

    @FXML
    private TextField des;
    @FXML
    private TextField tit;
    @FXML
    private TextField nbr;
    @FXML
    private TextField pr;
    @FXML
    private TextField id;
    @FXML
    private Button btncom;

    /**
     * Initializes the controller class.
     */
    ServiceSessionBoosting sb = new ServiceSessionBoosting();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SessionBoosting sn = AsController.sn;
        des.setText(sn.getDescription());
        tit.setText(sn.getTitre());
        nbr.setText(Integer.toString(sn.getNbr_heure()));
        pr.setText(Integer.toString(sn.getPrix()));


       
    }    

    @FXML
    private void Modifier(ActionEvent event) {
        SessionBoosting sn = AsController.sn;
        sn.setDescription(des.getText());
        sn.setTitre(tit.getText());
        sn.setNbr_heure(Integer.parseInt(nbr.getText()));
        sn.setPrix(Integer.parseInt(pr.getText()));
        sb.modifierSessionBoosting(sn.getId(), sn);
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("as.fxml"));
            Parent root = loader.load();
            
            ModifiersController controleur = loader.getController();
            
           // controleur.setTextFields(s);
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) btncom.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getCause().getMessage());
        }
        
        

        
    }
    
}
