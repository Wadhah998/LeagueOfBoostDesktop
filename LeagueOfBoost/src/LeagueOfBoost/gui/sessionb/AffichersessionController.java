package LeagueOfBoost.gui.sessionb;

import LeagueOfBoost.entities.Reservationb;
import LeagueOfBoost.entities.SessionBoosting;
import LeagueOfBoost.services.ServiceSessionBoosting;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AffichersessionController implements Initializable {
    Stage Stage1_1;

    @FXML
    private TableView<SessionBoosting> SBList;
    @FXML
    private TableColumn<SessionBoosting, String> description;
    @FXML
    private TableColumn<SessionBoosting, String> titre;
    @FXML
    private TableColumn<SessionBoosting, Double> prix;
    @FXML
    private TableColumn<SessionBoosting, Integer> nbr_heure;
    @FXML
    private TableColumn<SessionBoosting, Button> Supprimer;
    
    Button[] supprimers = new Button[100];
    ServiceSessionBoosting sb = new ServiceSessionBoosting();

    List<SessionBoosting> lt = sb.afficherS();
    int index = 101;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        for (int i = 0; i < lt.size(); i++) {
            if (event.getSource() == supprimers[i]) {
                index = i;
            }
        }
        System.out.println(index);
        int index1 = lt.get(index).getId();
        ServiceSessionBoosting sb = new ServiceSessionBoosting();
        sb.supprimerSessionBoosting(index1);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("suppression effectuée!");
        alert.show();
        lt = sb.afficherS();
        SBList.getItems().clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < lt.size(); i++) {
    ImageView supprimer = new ImageView(new Image(getClass().getResourceAsStream("/images/not_sending_video_frames_16px.png")));
            supprimers[i] = new Button("", supprimer);
            lt.get(i).setSupprimerS(supprimers[i]);
            supprimers[i].setOnAction(this::handleButtonAction);
        }

        ObservableList<SessionBoosting> datalist = FXCollections.observableArrayList(lt);

        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        nbr_heure.setCellValueFactory(new PropertyValueFactory<>("nbr_heure"));
    }
}