/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package LeagueOfBoost.gui;

import LeagueOfBoost.entities.Reservationb;
import LeagueOfBoost.entities.SessionBoosting;
import LeagueOfBoost.services.ServiceSessionBoosting;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import static javax.management.Query.lt;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AsController implements Initializable {
    public static SessionBoosting sn;

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
    private void modifier(ActionEvent event) {
        SessionBoosting s = table.getSelectionModel().getSelectedItem();
        sn =s;
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifiers.fxml"));
            Parent root = loader.load();
            
            ModifiersController controleur = loader.getController();
            
           // controleur.setTextFields(s);
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnmod.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            System.out.println(s);
        } catch (IOException e) {
            System.out.println(e.getCause().getMessage());
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        SessionBoosting s = table.getSelectionModel().getSelectedItem();
        System.out.println(s.getId());
        sb.supprimerSessionBoosting(s.getId());
        loadAS();
    }

    @FXML
    private void generateQRCode(ActionEvent event) {
          // Récupérer l'utilisateur sélectionné dans la tv_user
        SessionBoosting selectedSessionBoosting = table.getSelectionModel().getSelectedItem();

        // Vérifier que l'utilisateur est sélectionné
        if (selectedSessionBoosting == null) {
            // Afficher un message d'erreur si aucun utilisateur n'est sélectionné
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun utilisateur sélectionné");
            alert.setContentText("Sélectionnez un utilisateur dans la liste pour générer le code QR.");
            alert.showAndWait();
            return;
        }

        // Créer une chaîne de texte formatée avec les informations de l'utilisateur
        String text = String.format("Description: %s\nTitre: %s\nNbr_heure: %s %s\nPrix:", selectedSessionBoosting.getDescription(),selectedSessionBoosting.getTitre(),selectedSessionBoosting.getNbr_heure(),
                selectedSessionBoosting.getPrix());

        // Générer le code QR à partir de la chaîne de texte
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix;
        try {
            bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
        } catch (WriterException e) {
            // Afficher un message d'erreur si la génération du code QR a échoué
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la génération du code QR");
            alert.setContentText("Une erreur s'est produite lors de la génération du code QR. Veuillez réessayer.");
            alert.showAndWait();
            return;
        }

        // Convertir le code QR en image
        BufferedImage qrImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        qrImage.createGraphics();
        Graphics2D graphics = (Graphics2D) qrImage.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 200, 200);
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 200; j++) {
                if (bitMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }

        // Afficher le code QR dans une nouvelle fenêtre
        Stage qrStage = new Stage();
        qrStage.setTitle("Code QR");
        ImageView imageView = new ImageView(SwingFXUtils.toFXImage(qrImage, null));
        StackPane pane = new StackPane();
        pane.getChildren().add(imageView);
        qrStage.setScene(new Scene(pane, 220, 220));
        qrStage.show();
    }
    
}
