/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package LeagueOfBoost.gui.sessionb;

import LeagueOfBoost.entities.SessionBoosting;
import LeagueOfBoost.services.ServiceSessionBoosting;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AsUserController implements Initializable {
    public static SessionBoosting sn;
    @FXML
    private Button btnres;

    @FXML
    private TableView<SessionBoosting> table;
    @FXML
    private TableColumn<SessionBoosting, String> des;
    @FXML
    private TableColumn<SessionBoosting, String> tit;
    @FXML
    private TableColumn<SessionBoosting, String> nbr;
    @FXML
    private TableColumn<SessionBoosting, Integer> pr;
    @FXML
    private Button btnmod;

    
    ServiceSessionBoosting sb = new ServiceSessionBoosting();
    @FXML
    private Button btnsupp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAS() ;
    }    
    
    private void loadAS() {

        ObservableList<SessionBoosting> listef = sb.afficherS();
        des.setCellValueFactory(new PropertyValueFactory<>("description"));
        tit.setCellValueFactory(new PropertyValueFactory<>("titre"));
        pr.setCellValueFactory(new PropertyValueFactory<>("prix"));
        nbr.setCellValueFactory(new PropertyValueFactory<>("nbr_heure"));

        table.setItems(listef);
    }

    @FXML
    void reservers(ActionEvent event) {
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
    }

    
}
