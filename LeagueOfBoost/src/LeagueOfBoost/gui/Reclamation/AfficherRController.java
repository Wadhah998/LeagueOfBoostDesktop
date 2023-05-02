package LeagueOfBoost.gui.Reclamation;

import LeagueOfBoost.gui.Reclamation.MyData;
import LeagueOfBoost.gui.Reclamation.ReclamationController;
import LeagueOfBoost.entities.Message;
import LeagueOfBoost.entities.Reclamation;
import LeagueOfBoost.services.ServiceMessage;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.util.Callback;

public class AfficherRController implements Initializable {

    @FXML
    private Label idLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label themeLabel;

    @FXML
    private Label objetLabel;

    @FXML
    private Label textLabel;
    
    
    @FXML
    private TextArea addmessageLabel;
    
    @FXML
    private Button ajouterm;
    

   
    private Reclamation selectedReclamation;
    
    ServiceMessage sm = new ServiceMessage();
    @FXML
    private ListView<MyData> messagelist;
    

    public void setSelectedReclamation(Reclamation reclamation) {
        selectedReclamation = reclamation;
        displayReclamation();
    }

    private void displayReclamation() {
        idLabel.setText(String.valueOf(selectedReclamation.getId()));
        dateLabel.setText(String.valueOf(selectedReclamation.getDate()));
        themeLabel.setText(selectedReclamation.getTheme());
        objetLabel.setText(selectedReclamation.getObject());
        textLabel.setText(selectedReclamation.getText());
    }

    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Message> lv2 = new ArrayList<>();
         ServiceMessage su = new ServiceMessage();
            lv2.addAll(su.AffichermessById2(ReclamationController.varstat));
        
        
                
        List<MyData> lmd = new ArrayList<>();
        for (int i = 0; i < lv2.size(); i++){  
            lmd.add(new MyData(lv2.get(i).getId(), lv2.get(i).getUser_id(),lv2.get(i).getMessage(), (int) lv2.get(i).getDate().getTime()));
        }

        ObservableList<MyData> data = FXCollections.observableArrayList(
                lmd);
        messagelist.setItems(data);
        messagelist.setCellFactory(new Callback<ListView<MyData>, ListCell<MyData>>() {
            @Override
            public ListCell<MyData> call(ListView<MyData> listView) {
                return new MyListCell();
            }
        });
        
        
    }
    
     @FXML
    private void ajouterMessage(ActionEvent event) {
        // Vérifie que tous les champs ont été remplis
        if (addmessageLabel.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs doient être remplis !");
            alert.showAndWait();
            return;
        }
        
        int reclamation_id = Integer.parseInt(String.valueOf(selectedReclamation.getId()));
        Date Datenow2 = new Date();
        String message = addmessageLabel.getText();
        Message r = new Message(reclamation_id,Datenow2, message);
        sm.Ajouter(r);
        
        // Affiche un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("message ajouté avec succès !");
        alert.showAndWait();

        // Réinitialise les champs de saisie
        
        addmessageLabel.setText("");
        
    }
    
    
   
    
    
    
    
    
    
    
    
    
    
}