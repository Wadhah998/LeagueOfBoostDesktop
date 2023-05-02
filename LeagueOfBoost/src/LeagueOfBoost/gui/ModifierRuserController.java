/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.gui;

import LeagueOfBoost.entities.Reclamation;
import LeagueOfBoost.services.ServiceReclamation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class ModifierRuserController implements Initializable {

    @FXML
    private AnchorPane txt;
    @FXML
    private TextField txttheme;
    @FXML
    private TextField txtobj;
    @FXML
    private TextField txttxt;
    @FXML
    private TextField id;
    @FXML
    private Button btnConfirmer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmeruser(ActionEvent event) {
         ServiceReclamation sr = new ServiceReclamation();
          
         Integer id2=Integer.valueOf(id.getText());
         System.out.println("idd :::: "+id);
         String Theme2=txttheme.getText();
         String Object2=txtobj.getText();
         String Text2=txttxt.getText();
         
         Reclamation r =new Reclamation( Theme2, Object2, Text2, id2);
           sr.ModifierRuser(r);
           System.out.println("r :: "+r);
          
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("LOB :: Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Réclamation Modifié avec succés !");
                alert.showAndWait();  
        
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("reclamationUser.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnConfirmer.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
        }
        
    }
    
       public void setTextFields(Reclamation r){
        id.setText(String.valueOf(r.getId()));
        txttheme.setText(r.getTheme());
        txtobj.setText(r.getObject()); 
        txttxt.setText(r.getText());   
        
    }
    
    
}
