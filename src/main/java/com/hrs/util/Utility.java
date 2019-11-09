package com.hrs.util;

import com.hrs.view.models.Arrival;
import com.hrs.view.style.CSSStyle;
import com.hrs.view.util.FieldValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

/**
 * A Utility/helper class for others usage. Provides support to other classes. Any method in here must be stand
 * alone method. All methods must be -- public static --. Other methods can exists but with must be private access.
 */
public class Utility
{
    public static Node getNodeByRowColumnIndex (int row, int column, GridPane gridPane)
    {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();
        
        for (Node node : children)
        {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column)
            {
                result = node;
                break;
            }
        }
        return result;
    }
    
//    public static void setOnCenter(Stage stage)
//    {
//        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
//        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
//        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
//    }
    
    public static ObservableList<Arrival> toObservableList(List<Arrival> arrivals)
    {
        return FXCollections.observableArrayList(arrivals);
    }
    
    public static List<Label> arrivalHeaders()
    {
        return Arrays.asList(new Label(FieldValue.FLIGHT), new Label(FieldValue.AIRLINE),
                new Label(FieldValue.SOURCE),
                new Label(FieldValue.DATE_TIME), new Label(FieldValue.STATUS));
    }
    
    public static Button button(String label)
    {
        Button button = new Button(label);
        button.setMinHeight(30);
        button.setMinWidth(130);
        button.setMaxHeight(70);
        button.setMaxWidth(500);
        return button;
    }
    
    public static Label label(String label)
    {
        return new Label(label);
    }
    
    public static HBox reservationHeaders()
    {
        HBox hBox = new HBox();
        
        hBox.getChildren().addAll
                (button(FieldValue.FLIGHT), button(FieldValue.SOURCE), button(FieldValue.DESTINATION),
                button(FieldValue.AIRLINE), button(FieldValue.DATE_TIME), button(FieldValue.FARE),
                        button(FieldValue.RV_DATE));
        
        return hBox;
    }
    
    public static HBox customerHistoryHeaders()
    {
        HBox hBox = new HBox();
        
        hBox.getChildren().addAll
                (button(FieldValue.FLIGHT), button(FieldValue.SOURCE), button(FieldValue.DESTINATION),
                        button(FieldValue.AIRLINE), button(FieldValue.DATE_TIME), button(FieldValue.FARE),
                        button(FieldValue.RV_DATE));
        
        return hBox;
    }
    
    public static HBox airlineSpecificHeaders()
    {
        HBox hBox = new HBox();
    
        hBox.getChildren().addAll
                (button(FieldValue.FLIGHT), button(FieldValue.DESTINATION), button(FieldValue.AIRLINE),
                        button(FieldValue.DATE_TIME), button(FieldValue.FARE), button(FieldValue.RV_DATE));
    
        return hBox;
    }
    
    public static HBox flightHeaders()
    {
        HBox hBox = new HBox();
        
        hBox.getChildren().addAll
                (button(FieldValue.FLIGHT), button(FieldValue.SOURCE), button(FieldValue.DESTINATION),
                        button(FieldValue.AIRLINE), button(FieldValue.DATE_TIME), button(FieldValue.FARE),
                        button(FieldValue.STATUS));
        
        return hBox;
    }
    
    public static Label SE_HEADER()
    {
        Label l = new Label(FieldValue.RESULTS1);
        l.setAlignment(Pos.CENTER);
        l.setStyle(CSSStyle.fontFamily(FieldValue.FONT_MONACO)
                           .concat(CSSStyle.fontSize(15)));
        return l;
    }
    
    public static Label AIRLINE_HEADER(String airline)
    {
        Label l = new Label(FieldValue.RESULTS.concat(" ").concat(airline));
        l.setAlignment(Pos.CENTER);
        l.setStyle(CSSStyle.fontFamily(FieldValue.FONT_MONACO)
                           .concat(CSSStyle.fontSize(15)));
        return l;
    }
}
