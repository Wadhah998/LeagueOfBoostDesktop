package LeagueOfBoost.gui.Game;

import LeagueOfBoost.entities.Game;
import LeagueOfBoost.services.SeviceGame;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AjouterController { 

    @FXML
    private Button btnres;
    
    @FXML
    private TextField txttitle;
    
    @FXML
    private TextField txtdes;

    @FXML
    private TextField txtprice;
    
    @FXML
    private DatePicker txtdate;
  
    SeviceGame sp = new SeviceGame();
    
    public void initialize() {
        txttitle.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txttitle.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        txtdes.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtdes.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        txtprice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtprice.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }    

        @FXML
        void reserver(ActionEvent event) {
            // Vérifie que tous les champs ont été remplis
            if (txttitle.getText().isEmpty() || txtdes.getText().isEmpty() || txtprice.getText().isEmpty() || txtdate.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Tous les champs doivent être remplis !");
                alert.showAndWait();
                return;
            }

            String title = txttitle.getText();
            String description = txtdes.getText();
            int price = Integer.parseInt(txtprice.getText());
            LocalDate localDate = txtdate.getValue();
            Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
            Date date = Date.from(instant);
            Game r = new Game(title, description, price, date);
            sp.Ajouter(r);


            // Affiche un message de confirmation
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Game added successfully !");
            alert.showAndWait();

            // Réinitialise les champs de saisie
            txttitle.setText("");
            txtdes.setText("");
            txtprice.setText("");
            txtdate.setValue(null); // Reset the DatePicker
        }

}
