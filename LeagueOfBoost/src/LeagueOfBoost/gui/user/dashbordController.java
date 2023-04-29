/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package LeagueOfBoost.gui.user;

import java.net.URL;
import java.util.ResourceBundle;

import LeagueOfBoost.services.ServicePersonne;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author daoid
 */
public class dashbordController implements Initializable {

    @FXML
    private PieChart pieChart;





    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServicePersonne sp = new ServicePersonne();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Boosters: "+sp.countBoosters(), sp.countBoosters()),
                new PieChart.Data("Coaches: "+sp.countCoaches(), sp.countCoaches()),
                new PieChart.Data("Users: "+ sp.countUsers(), sp.countUsers())



        );
        pieChart.setData(pieChartData);










    // TODO
    }

}
