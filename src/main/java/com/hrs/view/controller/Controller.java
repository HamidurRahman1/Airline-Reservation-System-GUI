package com.hrs.view.controller;

import com.hrs.configs.Configuration;
import com.hrs.view.models.Arrival;
import com.hrs.test.Tester;
import com.hrs.util.Utility;
import com.hrs.view.View;
import com.hrs.view.style.CSSStyle;
import com.hrs.view.util.FieldValue;

import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

/**
 * A class that navigates views and talk to database
 */
public class Controller
{
    private View view;
    
    public Controller() {}

    public Controller(View view)
    {
        this.view = view;
    }
    
    public View getView()
    {
        return view;
    }
    
    public void setView(View view)
    {
        this.view = view;
    }
    
    public void eventLaunchAirport(String airportName)
    {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Double x1 = primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 840;
        Double y1 = primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - 770;
    
        Double x2 = primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 1200;
        
        arrivalScreen(airportName, x1, y1);
        departureScreen(airportName, x2, y1);
    }
    
    private void departureScreen(String airportName, Double x1, Double y1)
    {
        VBox departure = genericAirport(airportName, "departure", x1, y1, FieldValue.DEPARTURE_CONTAINER_ID);
    
        GridPane gridPane = populateGrid(FieldValue.TABLE_DEPARTURE_DESTINATION, Tester.arrivals());
    
        Label header = new Label("Departure Flights for ".concat(airportName.toUpperCase()));
        header.setStyle(CSSStyle.fontFamily(FieldValue.FONT_MONACO).concat(CSSStyle.fontSize(20)));
    
        Button submit = new Button("Refresh");
    
        submit.setOnAction(e ->
        {
            departure.getChildren().set(2, populateGrid(FieldValue.TABLE_DEPARTURE_DESTINATION, Tester.arrivals2()));
        });
    
        departure.getChildren().add(header);
        departure.getChildren().add(gridPane);
        departure.getChildren().add(submit);
    }
    
    private void arrivalScreen(String airportName, Double x1, Double y1)
    {
        VBox arrival = genericAirport(airportName, "arrival", x1, y1, FieldValue.ARRIVAL_CONTAINER_ID);
        
        GridPane gridPane = populateGrid(FieldValue.TABLE_ARRIVAL_SOURCE, Tester.arrivals());
    
        Label header = new Label("Arrival Flights for ".concat(airportName.toUpperCase()));
        header.setStyle(CSSStyle.fontFamily(FieldValue.FONT_MONACO).concat(CSSStyle.fontSize(20)));
    
        Button submit = new Button("Refresh");
        
        submit.setOnAction(e ->
        {
            arrival.getChildren().set(2, populateGrid(FieldValue.TABLE_ARRIVAL_SOURCE, Tester.arrivals2()));
        });
    
        arrival.getChildren().add(header);
        arrival.getChildren().add(gridPane);
        arrival.getChildren().add(submit);
    }
    
    public VBox genericAirport(String airportName, String label, Double x, Double y, String id)
    {
        Stage stage = new Stage();
    
        VBox container = new VBox(new Label());
        container.setId(id);
        container.setAlignment(Pos.TOP_CENTER);
        container.setPadding(new Insets(5, 5, 5, 5));
        
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.setTitle("Displaying all " + label + " flights for " + airportName);
    
        stage.setWidth(580);
        stage.setHeight(480);
        
        stage.setX(x);
        stage.setY(y);
        stage.show();
        
        return container;
    }
    
    public GridPane populateGrid(String airportHeader, List<Arrival> arrivals)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
    
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
    
        Label flight = new Label(FieldValue.TABLE_FLIGHT);
        gridPane.add(flight, 0,0);
        Label airline = new Label(FieldValue.TABLE_AIRLINE);
        gridPane.add(airline, 1,0);
        Label airport = new Label(airportHeader);
        gridPane.add(airport, 2,0);
        Label time = new Label(FieldValue.TABLE_TIME);
        gridPane.add(time, 3,0);
        Label status = new Label(FieldValue.TABLE_STATUS);
        gridPane.add(status, 4,0);
        
        int row = 1;
        
        for(int i = 0; i < arrivals.size(); i++)
        {
            gridPane.add(new Label(arrivals.get(i).flightName), 0, row);
            gridPane.add(new Label(arrivals.get(i).airlineName), 1, row);
            gridPane.add(new Label(arrivals.get(i).sourceName), 2, row);
            gridPane.add(new Label(arrivals.get(i).time), 3, row);
            gridPane.add(new Label(arrivals.get(i).status), 4, row);
            row++;
        }
        
        return gridPane;
        
    }
    
    public void eventLaunchDatePicker()
    {
        Stage stage = new Stage();
        HBox hBox = new HBox();
        
        DatePicker datePicker = new DatePicker();
        
        datePicker.setDayCellFactory(picker -> new DateCell()
        {
            public void updateItem(LocalDate date, boolean empty)
            {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0 );
            }
        });
        
        datePicker.setOnAction(e ->
        {
            Configuration.setStartingDate(datePicker.getValue());
            System.out.println(Configuration.getStartingDate());
            stage.close();
            view.start2();
        });
    
        Label label = new Label(FieldValue.SELECT_DATE);
        label.setStyle(CSSStyle.fontFamily(FieldValue.FONT_MONACO)+CSSStyle.fontSize(15));
        
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(label, datePicker);
        Scene scene = new Scene(hBox);
        stage.setTitle(FieldValue.DATE_TITLE);
        stage.setHeight(350);
        stage.setWidth(450);
        stage.setScene(scene);
        stage.show();
    }
    
    public void eventLaunchNewCustomer(GridPane gridPane)
    {
        Stage stage = new Stage();
        Scene scene = new Scene(gridPane, 500, 400);
        
        TextField firstName = (TextField) Utility.getNodeByRowColumnIndex
                (FieldValue.CUST_FIRST_ROW, FieldValue.CUST_FIRST_COL, gridPane);
        TextField lastName = (TextField) Utility.getNodeByRowColumnIndex
                (FieldValue.CUST_LAST_ROW, FieldValue.CUST_LAST_COL, gridPane);
        TextField email = (TextField) Utility.getNodeByRowColumnIndex
                (FieldValue.CUST_EMAIL_ROW, FieldValue.CUST_EMAIL_COL, gridPane);
        TextField password = (TextField) Utility.getNodeByRowColumnIndex
                (FieldValue.CUST_PASS_ROW, FieldValue.CUST_PASS_COL, gridPane);
        Button submit = (Button) Utility.getNodeByRowColumnIndex
                (FieldValue.NEW_CUST_SUB_ROW, FieldValue.NEW_CUST_SUB_COL, gridPane);
        
        submit.setOnAction(e ->
        {
            System.out.println(firstName.getText() + ' ' + lastName.getText() + ' ' + email.getText() + " " +
                    password.getText());
        });
        stage.setScene(scene);
        stage.setTitle(FieldValue.NEW_CUST_LABEL);
        stage.setAlwaysOnTop(true);
        stage.showAndWait();
    }
    
    public void eventLaunchLogin(GridPane gridPane)
    {
        Stage stage = new Stage();
        Scene scene = new Scene(gridPane, FieldValue.LOGIN_WINDOW_WIDTH, FieldValue.LOGIN_WINDOW_HEIGHT);
        
        HBox hBox = (HBox)Utility.getNodeByRowColumnIndex
                (FieldValue.LOGIN_SUBMIT_RAW, FieldValue.LOGIN_SUBMIT_COL, gridPane);
        Button submit = (Button) hBox.getChildren().get(0);
        
        submit.setOnAction(e ->
        {
            TextField usernname = (TextField) Utility.getNodeByRowColumnIndex(FieldValue.USERNAME_RAW, FieldValue.USERNAME_COL, gridPane);
            TextField pass = (TextField) Utility.getNodeByRowColumnIndex(FieldValue.PASSWORD_RAW, FieldValue.PASSWORD_COL, gridPane);
    
            System.out.println(usernname.getText() + ' ' + pass.getText());
            
            if(true)
            {
                // set customer scene
            }
            else
            {
                // catch exceptions based on the
            }
        });
        stage.setScene(scene);
        stage.setTitle(FieldValue.LOGIN_LABEL);
        stage.setAlwaysOnTop(true);
        stage.showAndWait();
    }
    
    public void eventSearchBar(String query)
    {
        System.out.println(query);
    }
    
    public void eventAbout() {}
    
    public void eventHelp() {}
    
    public TableView populateTable(String flightHeader, String airlineHeader, String airportHeader,
                                   String timeHeader, String statusHeader, String message, List<Arrival> arrivals)
    {
        TableView<Arrival> tableView = new TableView<>();
        
        TableColumn flight = new TableColumn(flightHeader);
        TableColumn airline = new TableColumn(airlineHeader);
        TableColumn airport = new TableColumn(airportHeader);
        TableColumn time = new TableColumn(timeHeader);
        TableColumn status = new TableColumn(statusHeader);
        
        flight.setCellValueFactory(new PropertyValueFactory <>("flightName"));
        airline.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
        airport.setCellValueFactory(new PropertyValueFactory<>("sourceName"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        tableView.getColumns().add(flight);
        tableView.getColumns().add(airline);
        tableView.getColumns().add(airport);
        tableView.getColumns().add(time);
        tableView.getColumns().add(status);
        
        
        tableView.getItems().add(new Arrival("a", "b", "c", "d", "e"));
        
        //        arrivals.forEach(e ->
        //        {
        //            tableView.getItems().add(new Arrival("a", "b", "c", "d", "e"));
        //        });
        
        return tableView;
    }
}
