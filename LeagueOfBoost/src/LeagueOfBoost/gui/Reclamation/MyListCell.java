/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.gui.Reclamation;

import LeagueOfBoost.gui.Reclamation.MyData;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

class MyListCell extends ListCell<MyData> {

    private AnchorPane myAnchorPane;
    private Label iduLabel;
    private Label dateLabel;
    private Label messgaeLabel;

    public MyListCell() {
        super();
        myAnchorPane = new AnchorPane();
        iduLabel = new Label();
        dateLabel = new Label();
        messgaeLabel = new Label();

        myAnchorPane.getChildren().addAll(iduLabel,dateLabel,messgaeLabel);

      AnchorPane.setLeftAnchor(iduLabel, 10.0);
        AnchorPane.setTopAnchor(iduLabel, 20.0);

        AnchorPane.setLeftAnchor(dateLabel,110.0);
        AnchorPane.setTopAnchor(dateLabel, 20.0);
        
        AnchorPane.setLeftAnchor(messgaeLabel, 210.0);
        AnchorPane.setTopAnchor(messgaeLabel, 20.0);
    }

    @Override
    protected void updateItem(MyData item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
           iduLabel.setText(Integer.toString(item.getIdu()));
           dateLabel.setText(Integer.toString(item.getDate()));
           messgaeLabel.setText(item.getMessage());

            setGraphic(myAnchorPane);
        }
    }
}


