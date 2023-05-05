package LeagueOfBoost.gui.Game;
import LeagueOfBoost.gui.Team.AjouterController;

import LeagueOfBoost.entities.Game;
import LeagueOfBoost.entities.Team;
import LeagueOfBoost.services.ServiceTeam;
import LeagueOfBoost.services.SeviceGame;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class DesignController implements Initializable {

    @FXML
    private VBox gameList;
    
    @FXML
    private ImageView imageView;

    private SeviceGame sg = new SeviceGame();
    
    @FXML
    private Image gameImage;
    
    @FXML
    private AnchorPane main;
    
    @FXML
    private TableView<Team> TeamL;
    
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> wins;
    @FXML
    private TableColumn<?, ?> losses;

    @FXML
    private AnchorPane main1;
    @FXML
    private TableColumn<Team, Integer> wr;
    
    
    ServiceTeam st = new ServiceTeam();
    List<Team> ln = st.afficherTeams2();
    
    @FXML
    private TextField prixtotal;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        prixtotal.textProperty().addListener((observable, oldValue, newValue) -> {
            rechercherUtilisateurs();
        });
        
        List<Game> lt = sg.afficherGames();
        Image gameImage = imageView.getImage();
        gameList.getChildren().clear();


        lt.forEach((game) -> {
    // Create a new StackPane for the game
    StackPane gameStackPane = new StackPane();

    // Create labels for the game's title, description, and price
    Label title = new Label(game.getTitle());
    Label des = new Label(game.getDescription());
    Label price = new Label(String.valueOf(game.getPrice()) + " DT");
    title.setTextFill(Color.WHITE);
    des.setTextFill(Color.WHITE);
    price.setTextFill(Color.WHITE);

    // Create an ImageView for the game's image
    ImageView gameImageView = new ImageView(gameImage);
    gameImageView.setFitHeight(121);
    gameImageView.setFitWidth(318);
    gameImageView.setOpacity(0.55);
    gameImageView.setPickOnBounds(true);
    gameImageView.setPreserveRatio(true);
    VBox.setMargin(gameStackPane, new Insets(0, 0, 40, 0)); // 10 pixel margin at the bottom
  
    /*gameImageView.setEffect(new DropShadow(Color.web("#685858")));*/

    // Create a VBox for the labels
    VBox labelsBox = new VBox();
    labelsBox.getChildren().addAll(title, des, price);
    labelsBox.setAlignment(Pos.CENTER);
    labelsBox.setSpacing(10);


    // Add the ImageView and VBox to the StackPane
    gameStackPane.getChildren().addAll(gameImageView, labelsBox);

    // Add the gameStackPane to the main VBox
    gameList.getChildren().add(gameStackPane);
    
    ObservableList<Team> datalist = FXCollections.observableArrayList(ln);
        System.out.println("hiiiii");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        wins.setCellValueFactory(new PropertyValueFactory<>("wins"));
        losses.setCellValueFactory(new PropertyValueFactory<>("losses"));
        wr.setCellValueFactory(cellData -> new SimpleIntegerProperty(calculateWinRate(cellData.getValue().getWins(), cellData.getValue().getLosses())).asObject());

        TeamL.setItems(datalist);
        
        
     ObservableList<TableColumn<?, ?>> sortedColumns = FXCollections.observableArrayList();
        sortedColumns.add(wr);

        sortedColumns.sort((c1, c2) -> {
            if (c1 == wr) {
                return -1;
            } else if (c2 == wr) {
                return 1;
            } else {
                return c1.getText().compareToIgnoreCase(c2.getText());
            }
        });

        TeamL.getSortOrder().clear();
        TeamL.getSortOrder().addAll((TableColumn<Team, ?>[]) sortedColumns.toArray(new TableColumn<?, ?>[0]));
 
    
});
    }
   @FXML
public void handleGameClick(MouseEvent event) throws IOException {
       Parent fxml = FXMLLoader.load(getClass().getResource("/LeagueOfBoost/gui/Team/Ajouter.fxml"));
       main1.getChildren().removeAll();
       main1.getChildren().setAll(fxml);
}


  public void rechercherUtilisateurs() {
    String keyword = "";
    if (prixtotal != null) {
        keyword = prixtotal.getText();
    }
    ObservableList<Team> filteredList = FXCollections.observableArrayList();
    ObservableList<TableColumn<Team, ?>> columns = TeamL.getColumns();
    for (int i = 0; i < st.afficherTeams2().size(); i++) {
        Team team2 = st.afficherTeams2().get(i);
        System.out.println(team2);
        for (int j = 0; j < columns.size(); j++) {
            TableColumn<Team, ?> column = columns.get(j);
            // System.out.println(column);
            try {
                String cellValue = column.getCellData(team2).toString();
                System.out.println(cellValue);
                if (cellValue.contains(keyword)) {
                    filteredList.add(team2);
                    break;
                }
            } catch (NullPointerException ex) {
                // Ignore null cell values
            }
        }
    }
    // Update table view
    TeamL.setItems(filteredList);
}
 public int calculateWinRate(int wins, int losses) {
    int totalGames = wins + losses;
    int winRate = 0;
    
    if (totalGames > 0) {
        double wr = (double) wins / totalGames;
        winRate = (int) Math.round(wr * 100);
    }
    
    return winRate;
}



   
    

}