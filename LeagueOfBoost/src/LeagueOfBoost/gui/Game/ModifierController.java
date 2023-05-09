/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.gui.Game;


import LeagueOfBoost.entities.Game;
import LeagueOfBoost.services.SeviceGame;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class ModifierController implements Initializable {

    @FXML
    private TextField txttitle;
    @FXML
    private TextField txtdes;
    @FXML
    private TextField txtprice;
    @FXML
    private TextField txtdate;
    @FXML
    private TextField id;
   
    @FXML
    private Button btnConfirmer;

    SeviceGame sr = new SeviceGame();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmer(ActionEvent event) {
        SeviceGame sr = new SeviceGame();
         
         Integer id2=Integer.valueOf(id.getText());
         System.out.println("idd :::: "+id);
         String title2=txttitle.getText();

         String description2=txtdes.getText();
         Integer price2=Integer.valueOf(txtprice.getText());
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Create a date format object
        Date date2 = null;
        try {
            date2 = dateFormat.parse(txtdate.getText()); // Parse the date string to a Date object
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
         
         Game r =new Game(id2, title2, description2, price2, date2);
           sr.Modifier(r);
           System.out.println("r :: "+r);
          
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("LOB :: Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Game Modifié avec succés !");
                alert.showAndWait();  
        
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnConfirmer.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
        }    
    }
    
    public void setTextFields(Game r){
        id.setText(String.valueOf(r.getId()));
        txttitle.setText(r.getTitle());
        txtdes.setText(r.getDescription());
        txtprice.setText(String.valueOf(r.getPrice())); 
        txtdate.setText(String.valueOf(r.getDate()));   
    }
    }
