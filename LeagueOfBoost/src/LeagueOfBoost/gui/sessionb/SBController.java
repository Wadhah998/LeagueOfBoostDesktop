package LeagueOfBoost.gui.sessionb;

import LeagueOfBoost.entities.SessionBoosting;
import LeagueOfBoost.services.ServiceSessionBoosting;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SBController {

    @FXML
    private Button btnrr;

    @FXML
    private TextField txtdes;

    @FXML
    private TextField txtprix;

    @FXML
    private TextField txttit;

    @FXML
    private TextField txtnbr;

    ServiceSessionBoosting sb = new ServiceSessionBoosting();

    @FXML
    void ajouter(ActionEvent event) {
        // Vérifier que tous les champs ont été remplis
        if (txtdes.getText().isEmpty() || txtprix.getText().isEmpty() || txttit.getText().isEmpty() || txtnbr.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs doivent être remplis !");
            alert.showAndWait();
            return;
        }

        String description = txtdes.getText();
        int  prix = Integer.parseInt(txtprix.getText());
        String titre = txttit.getText();
        int nbrHeure = Integer.parseInt(txtnbr.getText());

        SessionBoosting s = new SessionBoosting(description, titre, nbrHeure ,prix);
        sb.ajouterSessionBoosting(s);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Confirmation");
           alert.setHeaderText(null);
           ImageView imageView = new ImageView(new Image("images/sb.jpg"));
           alert.setGraphic(imageView);
           alert.setContentText("Join US !!");

           // Set the dialog size
           alert.setResizable(false);
           alert.getDialogPane().setPrefSize(400, 200);

           alert.showAndWait();

                   // Réinitialise les champs de saisie
                   txtdes.setText("");
                   txtprix.setText("");
                   txttit.setText("");
                   txtnbr.setText("");
               }
}
