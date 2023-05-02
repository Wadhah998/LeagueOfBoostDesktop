package LeagueOfBoost.gui.Team;

import LeagueOfBoost.entities.Team;
import LeagueOfBoost.gui.Game.DesignController;
import LeagueOfBoost.services.ServiceTeam;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import java.awt.image.BufferedImage;
import nl.captcha.Captcha;


public class AjouterController{ 

    public static void setGame_id(int game_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private Button btnres;
    
    @FXML
    private TextField txtid;
    
    @FXML
    private TextField txtname;
    
    @FXML
    private TextField txtdes;

    @FXML
    private TextField txtp1;
    
    @FXML
    private TextField txtp2;
    
    @FXML
    private TextField txtp3;
    
    @FXML
    private TextField txtp4;
    
    @FXML
    private TextField txtp5;
    

    ServiceTeam sp = new ServiceTeam();
    
     
    @FXML
    private ImageView icaptcha;
    
    Captcha captcha = new Captcha.Builder(200, 50)
           .addText()
           .addBackground()
           .addNoise()
           .addBorder()
           .build();
    @FXML
    private TextField tcaptcha;
    
   
    
    public void initialize() {
        
        BufferedImage i = captcha.getImage();
        Image ii = SwingFXUtils.toFXImage(i, null);
        ImageView ll = new ImageView(ii);
        icaptcha.setImage(ii);
        
        
        txtid.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtid.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        txtname.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtname.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        txtdes.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtdes.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
          txtp1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtp1.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        txtp2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtp2.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
          txtp3.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtp3.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        txtp4.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtp4.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
          txtp5.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtp5.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
       
    }    

    @FXML
    void reserver(ActionEvent event) {
        // Vérifie que tous les champs ont été remplis
        if (txtid.getText().isEmpty() || txtname.getText().isEmpty() || txtdes.getText().isEmpty() || txtp1.getText().isEmpty() || txtp2.getText().isEmpty()|| txtp3.getText().isEmpty()|| txtp4.getText().isEmpty()|| txtp5.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Please insert text !");
            alert.showAndWait();
            return;
        }
        if (verif(txtname.getText()) == 1) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Bad Words are not allowed !!");
            a.setHeaderText(null);
            a.showAndWait();
        }
        
        else if(captcha.isCorrect(tcaptcha.getText())){
            
            
        int game_id = Integer.parseInt(txtid.getText());
        String name = txtname.getText();
        String description = txtdes.getText();
        String player1 = txtp1.getText();
        String player2 = txtp2.getText();
        String player3 = txtp3.getText();
        String player4 = txtp4.getText();
        String player5 = txtp5.getText();
        
        Team r = new Team(game_id, name, description, player1, player2, player3, player4, player5);
        sp.Ajouter(r);
        // Affiche un message de confirmation
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Team Registered Successfully !");
        alert.showAndWait();

        // Réinitialise les champs de saisie
        txtid.setText("");
        txtname.setText("");
        txtdes.setText("");
        txtp1.setText("");
        txtp2.setText("");
        txtp3.setText("");
        txtp4.setText("");
        txtp5.setText("");
              
        }
        else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Recaptcha is not done yet !");
        alert.showAndWait();
        }
       

    }
    private void refCaptcha(MouseEvent event) {
        try{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Team/Ajout.fxml"));
                 Parent root = loader.load();
                 AjouterController mdc = loader.getController();
                 
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 Scene scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
        }catch(IOException ex){
            System.out.println(ex);
        }
    }
    
    public void handlePreviousClick(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LeagueOfBoost/gui/Game/design.fxml"));
        Parent root = loader.load();
        DesignController designController = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
}
        
    public int verif(String mot){
        String[] badwords ={"shit","fuck","wtf"};      
        for(String dt : badwords)  {
       
           if (mot.toLowerCase().contains(dt.toLowerCase()) ) {
              return 1;
               
              } }

             return 0;  
    }


    }