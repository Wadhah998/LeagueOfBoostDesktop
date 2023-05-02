/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.gui.SessionC;

import LeagueOfBoost.entities.ReservationC;
import LeagueOfBoost.entities.SessionC;
import LeagueOfBoost.gui.ReservationC.ReservationCController;
import LeagueOfBoost.gui.SessionC.SessionCController;
import LeagueOfBoost.services.ServiceReservationC;
import LeagueOfBoost.services.ServiceSessionC;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class ModifiersessionController implements Initializable {

    @FXML
    private AnchorPane main;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnretour;
    @FXML
    private TextField txttitre;
    @FXML
    private TextField txtdesc;
    @FXML
    private TextField txtprix;

    /**
     * Initializes the controller class.
     */
    ServiceSessionC sb = new ServiceSessionC();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Modifier(ActionEvent event) {
        SessionC r = SessionCController.sb;
        r.setTitre((txttitre.getText()));
        r.setDescription((txtdesc.getText()));
        r.setPrix(Integer.parseInt(txtprix.getText()));
        sb.modifierSessionCoaching(r.getId(), r);
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SessionC.fxml"));
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
    }
    
}
