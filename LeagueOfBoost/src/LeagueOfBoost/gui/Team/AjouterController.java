package LeagueOfBoost.gui.Team;

import LeagueOfBoost.entities.Team;
import LeagueOfBoost.services.ServiceTeam;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AjouterController{ 

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
    
    public void initialize() {
        
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
            alert.setContentText("Tous les champs doivent être remplis !");
            alert.showAndWait();
            return;
        }

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
        alert.setContentText("Team ajoutée avec succès !");
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

    }
