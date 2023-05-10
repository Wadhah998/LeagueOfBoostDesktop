package LeagueOfBoost.gui.ReservationB;

import LeagueOfBoost.entities.News;
import LeagueOfBoost.entities.Reservationb;
import LeagueOfBoost.gui.ReservationC.ReservationCController;
import LeagueOfBoost.services.ServiceReservationB;
import LeagueOfBoost.utils.MyDB;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import static sun.font.FontManagerNativeLibrary.load;

/**
 * FXML Controller class
 *
 * @author Mega-PC
 */
public class ReservationBController implements Initializable {
    Stage Stage1_1;
public static Reservationb rb;
 
     Connection con = MyDB.createorgetInstance().getCon();
    Button[] supprimerb = new Button[100];
    ServiceReservationB sg = new ServiceReservationB();

    List<Reservationb> lt = sg.afficherR();
    /**
     * Initializes the controller class.
     */
    int index=101;
    
    @FXML
    private TableView<Reservationb> RBList;

    @FXML
    private TableColumn<?, ?> Supprimer;

    @FXML
    private Button btnmod;


    @FXML
    private AnchorPane main;

    @FXML
    private TableColumn<?, ?> newrank;

    @FXML
    private TableColumn<?, ?> oldrank;

    @FXML
    private TableColumn<?, ?> prix;

    @FXML
    private TableColumn<?, ?> user_id;
    @FXML
    private Button btn_qrcode;
    private TextField tfrech;
    @FXML
    private TextField txtrech;
private void handleButtonAction (ActionEvent event)
    {
       
         for (int i = 0; i < lt.size(); i++) {
            // Button a = supprimerb[i];
            
             if (event.getSource() == supprimerb[i])
             {
                 index=i;
             }
            
                 
         }
          System.out.println(index);
          int index1 = lt.get(index).getId();
            ServiceReservationB sg = new ServiceReservationB();
            
             sg.supprimerReservation(index1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("suppression affecté!");
            alert.show();
             lt = sg.afficherR();
             RBList.getItems().clear();
             afficher_A(lt);
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

      for (int i = 0; i < lt.size(); i++) {
         
    ImageView supprimer = new ImageView(new Image(getClass().getResourceAsStream("/images/not_sending_video_frames_16px.png")));
         supprimerb[i] = new Button("", supprimer);
             lt.get(i).setSupprimer(supprimerb[i]);
           supprimerb[i].setOnAction(this::handleButtonAction);
           txtrech.textProperty().addListener((observable, oldValue, newValue) -> {
    recherche();});
        }

       ObservableList<Reservationb> datalist = FXCollections.observableArrayList(lt);
       // System.out.println("hiiiii");
        user_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        oldrank.setCellValueFactory(new PropertyValueFactory<>("oldrank"));
        newrank.setCellValueFactory(new PropertyValueFactory<>("newrank"));
        Supprimer.setCellValueFactory(new PropertyValueFactory<>("supprimer"));

        
        RBList.setItems(datalist);
    }    

    @FXML
    private void AjouterButton(MouseEvent event) {
    }
    
    
    public void afficher_A(List<Reservationb> ltt)
    {
      
      for (int i = 0; i < lt.size(); i++) {
         
        ImageView supprimer = new ImageView(new Image(getClass().getResourceAsStream("/images/not_sending_video_frames_16px.png")));
         supprimerb[i] = new Button("", supprimer);
             lt.get(i).setSupprimer(supprimerb[i]);
           supprimerb[i].setOnAction(this::handleButtonAction);
        }

        
       ObservableList<Reservationb> datalist = FXCollections.observableArrayList(lt);
        

        user_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        oldrank.setCellValueFactory(new PropertyValueFactory<>("oldrank"));
        newrank.setCellValueFactory(new PropertyValueFactory<>("newrank"));

        Supprimer.setCellValueFactory(new PropertyValueFactory<>("supprimer"));

        RBList.setItems(datalist);
    }

    @FXML
    private void quit(MouseEvent event) {
        
        Stage1_1 = (Stage)main.getScene().getWindow();
        System.out.println("you succesfully close the application");
        Stage1_1.close();
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        Reservationb b = RBList.getSelectionModel().getSelectedItem();
        rb=b;
        Parent fxml = FXMLLoader.load(getClass().getResource("modifierB.fxml"));
        main.getChildren().removeAll();
        main.getChildren().setAll(fxml);
        System.out.println("test");
    }

    @FXML
    private void generateQRCode(ActionEvent event) {
        // Récupérer l'utilisateur sélectionné dans la tv_user
        Reservationb selectedReservationb= RBList.getSelectionModel().getSelectedItem();

        // Vérifier que l'utilisateur est sélectionné
        if (selectedReservationb == null) {
            // Afficher un message d'erreur si aucun utilisateur n'est sélectionné
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun utilisateur sélectionné");
            alert.setContentText("Sélectionnez un utilisateur dans la liste pour générer le code QR.");
            alert.showAndWait();
            return;
        }

        // Créer une chaîne de texte formatée avec les informations de l'utilisateur
        String text = String.format("Oldrank: %s\nNewRank: %s\nPrix: %s", selectedReservationb.getOldrank(), selectedReservationb.getNewrank() ,
                selectedReservationb.getPrix());
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
    
    
private void recherche() {
    String keyword = txtrech.getText();
    ObservableList<Reservationb> filteredList = FXCollections.observableArrayList();
    ObservableList<TableColumn<Reservationb, ?>> columns = RBList.getColumns();
    for (Reservationb reservation : sg.afficherReservation(1)) {
        for (TableColumn<Reservationb, ?> column : columns) {
            try {
                String cellValue = column.getCellData(reservation).toString();
                if (cellValue.contains(keyword)) {
                    filteredList.add(reservation);
                    break;
                }
            } catch (NullPointerException ex) {
                // Ignore null cell values
            }
        }
    }
    RBList.setItems(filteredList);
}


    @FXML
    private void ajouterR(ActionEvent event) throws IOException {
        try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("rb.fxml"));
            main.getChildren().removeAll() ;
            main.getChildren().setAll(sv) ;
        } catch (IOException ex) {
            Logger.getLogger(ReservationCController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tosesion(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/LeagueOfBoost/gui/sessionb/as.fxml"));
        main.getChildren().removeAll();
        main.getChildren().setAll(fxml);
    }
}


    
    
