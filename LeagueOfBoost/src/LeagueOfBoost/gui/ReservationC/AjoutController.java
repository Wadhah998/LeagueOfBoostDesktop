/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.gui.ReservationC;

import LeagueOfBoost.entities.ReservationC;
import LeagueOfBoost.services.ServiceReservationC;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class AjoutController implements Initializable {

    @FXML
    private Button btnajout;
    @FXML
    private Button btnretour;
    @FXML
    private TextField txtnbr;
    @FXML
    private TextField txtprx;
    @FXML
    private TextField txtlng;
    @FXML
    private AnchorPane main;
    
    private int i ;
    

      ServiceReservationC sp = new ServiceReservationC();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Définit les contraintes de validation pour chaque champ de saisie
        txtnbr.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtnbr.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        txtprx.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtprx.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        txtlng.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtlng.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
    
    }    
    

    @FXML
    private void Ajouter(ActionEvent event) {
        // Vérifie que tous les champs ont été remplis
        if (txtnbr.getText().isEmpty() || txtprx.getText().isEmpty() || txtlng.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs doivent être remplis !");
            alert.showAndWait();
            return;
        }

        int Nbr_heures = Integer.parseInt(txtnbr.getText());
        int prix = Integer.parseInt(txtprx.getText());
        String Langue = txtlng.getText();
        ReservationC r = new ReservationC(Nbr_heures, prix, Langue);
        sp.ajouterReservation(r);
        try {
    // Create a new PDF document
    Document document = new Document();
    
    // Create a new file output stream and set the file name
    FileOutputStream fos = new FileOutputStream("reservation_details"+ i + ".pdf" );
    i++ ;
    // Set the PdfWriter to the file output stream
    PdfWriter.getInstance(document, fos);
    
    // Open the document
    document.open();
    
    // Add the reservation details to the PDF document
    document.add(new Paragraph("nbr_heures: " + r.getNbr_heures()));
    document.add(new Paragraph("Prix: " + r.getPrix()));
    document.add(new Paragraph("Langue: " + r.getLangue()));
    
    // Close the document
    document.close();
    
    // Close the file output stream
    fos.close();
    
    System.out.println("PDF file created successfully.");
} catch (Exception e) {
    e.printStackTrace();
}
        
        // Affiche un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Réservation ajoutée avec succès !");
        alert.showAndWait();

        // Réinitialise les champs de saisie
        txtnbr.setText("");
        txtprx.setText("");
        txtlng.setText("");
        
    }   
    @FXML
    private void Retour(ActionEvent event) {
        try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("ReservationC.fxml"));
          main.getChildren().removeAll() ; 
          main.getChildren().setAll(sv) ;                      
        } catch (IOException ex) {
             Logger.getLogger(AjoutController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    }