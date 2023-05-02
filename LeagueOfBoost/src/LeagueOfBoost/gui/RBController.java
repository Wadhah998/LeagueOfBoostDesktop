package LeagueOfBoost.gui;

import LeagueOfBoost.entities.Reservationb;
import LeagueOfBoost.services.ServiceReservationB;
import java.io.IOException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class RBController {

    @FXML
    private Button btnres;

    @FXML
    private TextField txtnew;

    @FXML
    private TextField txtold;

    @FXML
    private TextField txtprix;

    ServiceReservationB sp = new ServiceReservationB();
    @FXML
    private Button btnretour;
    
    public void initialize() {
        txtold.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtold.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        txtnew.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtnew.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        txtprix.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtprix.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }    

    @FXML
    void reserver(ActionEvent event) {
        // Vérifie que tous les champs ont été remplis
        if (txtold.getText().isEmpty() || txtnew.getText().isEmpty() || txtprix.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs doivent être remplis !");
            alert.showAndWait();
            return;
        }

        String oldrank = txtold.getText();
        int prix = Integer.parseInt(txtprix.getText());
        String newrank = txtnew.getText();
        Reservationb r =new Reservationb(prix,oldrank, newrank);
        sp.ajouterReservation(r);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Confirmation");
alert.setHeaderText(null);
ImageView imageView = new ImageView(new Image("images/rb.jpg"));
alert.setGraphic(imageView);
alert.setContentText("Join US !!");

// Set the dialog size
alert.setResizable(false);
alert.getDialogPane().setPrefSize(400, 200);

alert.showAndWait();


        // Réinitialise les champs de saisie
        txtold.setText("");
        txtnew.setText("");
        txtprix.setText("");
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
