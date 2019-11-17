package com.hrs.view;

import com.hrs.configs.Configuration;
import com.hrs.util.Utility;
import com.hrs.view.alerts.AlertBox;
import com.hrs.view.controller.Controller;
import com.hrs.view.models.Admin;
import com.hrs.view.models.Airplane;
import com.hrs.view.models.Airport;
import com.hrs.view.models.Flight;
import com.hrs.view.models.Reservation;
import com.hrs.view.util.FieldValue;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.hrs.util.Utility.LOGOUT_STYLE;
import static com.hrs.util.Utility.NAME_HEADER_STYLE;
import static com.hrs.util.Utility.STYLE;
import static com.hrs.util.Utility.button;
import static com.hrs.util.Utility.FONT_SIZE;
import static com.hrs.util.Utility.label;

public class View extends Application
{
    private Controller controller = Configuration.getController();
    
    private Stage primaryStage;
    private Stage arrivalStage;
    private Stage departureStage;
    private Stage cancelFlightStage;
    
    private Scene homeScene;
    private BorderPane homeSceneContainer;
    
    public void start(Stage primaryStage) throws Exception
    {
        this.primaryStage = primaryStage;
        initialize();
        controller.eventLaunchDatePicker();
    }
    
    public void start2()
    {
        this.homeScene = new Scene(this.homeSceneContainer, FieldValue.HOME_SCENE_WIDTH, FieldValue.HOME_SCENE_HEIGHT);
        
        primaryStage.setTitle(FieldValue.APP_TITLE);
        primaryStage.setWidth(FieldValue.HOME_WINDOW_WIDTH);
        primaryStage.setHeight(FieldValue.HOME_WINDOW_HEIGHT);
        primaryStage.setScene(this.homeScene);
        primaryStage.show();
    }
    
    private void initialize()
    {
        homeSceneContainer = new BorderPane();
        
        homeSceneContainer.setTop(ui_homeMenuBar());
        
        homeSceneContainer.setCenter(ui_searchBarContainer(FieldValue.GLOBAL_SEARCH_ENGINE_LABEL));
        
        homeSceneContainer.setLeft(new VBox());
        homeSceneContainer.setRight(new VBox());
        homeSceneContainer.setBottom(new HBox());
        
        controller = Configuration.getController();
        controller.setView(this);
    }
    
    public void setTop(MenuBar menuBar)
    {
        this.homeSceneContainer.setTop(menuBar);
    }
    
    public void setCenter(Node node)
    {
        this.homeSceneContainer.setCenter(node);
    }
    
    public void setHome()
    {
        this.homeSceneContainer.setTop(ui_homeMenuBar());
        this.homeSceneContainer.setCenter(ui_searchBarContainer(FieldValue.GLOBAL_SEARCH_ENGINE_LABEL));
    }
    
    public void ui_addFlightForAirline(Admin admin, String airline, Set<Airport> airports, Set<Airplane> airplanes)
    {
        Stage stage = new Stage();
        stage.setWidth(900);
        stage.setHeight(700);
        stage.setTitle(Utility.ADD_FLIGHT_FOR(airline));
    
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.BASELINE_CENTER);
        gridPane.setHgap(12);
        gridPane.setVgap(8);
    
        gridPane.add(new Label(), 0, 0);
        gridPane.add(new Label(), 0, 1);
    
        Label codeLabel = label(FieldValue.ENTER_CODE);
        codeLabel.setPadding(Utility.FLIGHT_LABEL());
        codeLabel.setStyle(STYLE().concat(FONT_SIZE(18)).concat("-fx-padding: 8; -fx-border-padding: 10"));
    
        Label airplaneLabel = label(FieldValue.SELECT_AP);
        airplaneLabel.setPadding(Utility.FLIGHT_LABEL());
        airplaneLabel.setStyle(STYLE().concat(FONT_SIZE(18)).concat("-fx-padding: 8; -fx-border-padding: 10"));
    
        Label sourceLabel = label(FieldValue.SELECT_SRC);
        sourceLabel.setPadding(Utility.FLIGHT_LABEL());
        sourceLabel.setStyle(STYLE().concat(FONT_SIZE(18)).concat("-fx-padding: 8; -fx-border-padding: 10"));
        
    
        Label sourceDateLabel = label(FieldValue.SELECT_DPT_DATE);
        sourceDateLabel.setPadding(Utility.FLIGHT_LABEL());
        sourceDateLabel.setStyle(STYLE().concat(FONT_SIZE(18)).concat("-fx-padding: 8; -fx-border-padding: 10"));
    
        Label sourceTimeLabel = label(FieldValue.SELECT_DPT_TIME);
        sourceTimeLabel.setPadding(Utility.FLIGHT_LABEL());
        sourceTimeLabel.setStyle(STYLE().concat(FONT_SIZE(18)).concat("-fx-padding: 8; -fx-border-padding: 10"));
    
        Label destinationLabel = label(FieldValue.SELECT_ARR);
        destinationLabel.setPadding(Utility.FLIGHT_LABEL());
        destinationLabel.setStyle(STYLE().concat(FONT_SIZE(18)).concat("-fx-padding: 8; -fx-border-padding: 10"));
    
        Label destinationDateLabel = label(FieldValue.SELECT_ARR_DATE);
        destinationDateLabel.setPadding(Utility.FLIGHT_LABEL());
        destinationDateLabel.setStyle(STYLE().concat(FONT_SIZE(18)).concat("-fx-padding: 8; -fx-border-padding: 10"));
    
        Label destinationTimeLabel = label(FieldValue.SELECT_ARR_TIME);
        destinationTimeLabel.setPadding(Utility.FLIGHT_LABEL());
        destinationTimeLabel.setStyle(STYLE().concat(FONT_SIZE(18)).concat("-fx-padding: 8; -fx-border-padding: 10"));
    
        Label capacityLabel = label(FieldValue.ENTER_CAP);
        capacityLabel.setPadding(Utility.FLIGHT_LABEL());
        capacityLabel.setStyle(STYLE().concat(FONT_SIZE(18)).concat("-fx-padding: 8; -fx-border-padding: 10"));
    
        TextField codeField = new TextField();
        codeField.setStyle(STYLE().concat(FONT_SIZE(18)).concat("-fx-padding: 8; -fx-border-padding: 10"));
        
        ChoiceBox<Airplane> airPlaneChoiceBox = new ChoiceBox<>(Utility.AIRPLANES_LIST(airplanes));
    
        ChoiceBox<Airport> sourceChoices = new ChoiceBox<>(Utility.AIRPORTS_LIST(airports));
        
        DatePicker sourceDate = new DatePicker();
        sourceDate.setDayCellFactory(picker -> new DateCell()
        {
            public void updateItem(LocalDate date, boolean empty)
            {
                super.updateItem(date, empty);
                LocalDate tomorrow = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),
                        LocalDate.now().getDayOfMonth()+1);
                setDisable(empty || date.compareTo(tomorrow) < 0 );
            }
        });
        
        ChoiceBox<String> sourceTimes = new ChoiceBox<>(Utility.TIMES_LIST());
    
        ChoiceBox<Airport> destinationChoices = new ChoiceBox<>(Utility.AIRPORTS_LIST(airports));
        
        DatePicker destinationDate = new DatePicker();
        destinationDate.setDayCellFactory(picker -> new DateCell()
        {
            public void updateItem(LocalDate date, boolean empty)
            {
                super.updateItem(date, empty);
                LocalDate tomorrow = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),
                        LocalDate.now().getDayOfMonth()+2);
                setDisable(empty || date.compareTo(tomorrow) < 0 );
            }
        });
        ChoiceBox<String> destinationTimes = new ChoiceBox<>(Utility.TIMES_LIST());
    
        TextField capacity1 = new TextField();
    
        EventHandler event = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Airport airport = sourceChoices.getValue();
                List<Airport> newAirports = new LinkedList <>();
            
                for(Airport airport1 : airports) if(!airport.equals(airport1)) newAirports.add(airport1);
                destinationChoices.setItems(FXCollections.observableList(newAirports));
            }
        };
    
        gridPane.add(codeLabel, 0, 2);
        gridPane.add(codeField, 1, 2);
    
        gridPane.add(airplaneLabel, 0, 3);
        gridPane.add(airPlaneChoiceBox, 1, 3);
    
        gridPane.add(sourceLabel, 0, 4);
        gridPane.add(sourceChoices, 1, 4);
        sourceChoices.setOnAction(event);
    
        gridPane.add(sourceDateLabel, 0, 5);
        gridPane.add(sourceDate, 1, 5);
    
        gridPane.add(sourceTimeLabel, 0, 6);
        gridPane.add(sourceTimes, 1, 6);
    
        gridPane.add(destinationLabel, 0, 7);
        gridPane.add(destinationChoices, 1, 7);
    
        gridPane.add(destinationDateLabel, 0, 8);
        gridPane.add(destinationDate, 1, 8);
    
        gridPane.add(destinationTimeLabel, 0, 9);
        gridPane.add(destinationTimes, 1, 9);
    
        gridPane.add(capacityLabel, 0, 10);
        gridPane.add(capacity1, 1, 10);
    
        gridPane.add(new Label(), 0, 11);
        gridPane.add(new Label(), 0, 12);
    
        Button submit = new Button(FieldValue.SUBMIT);
        submit.setStyle(Utility.CLICK_ME());
        submit.setOnAction(e ->
        {
            if(controller.addFlightForAirline(airline, codeField, airPlaneChoiceBox, sourceChoices,
                    sourceDate, sourceTimes, destinationChoices, destinationDate, destinationTimes, capacity1))
            {
                stage.close();
                AlertBox.DisplayInformation(FieldValue.FLIGHT_ADDITION_SUCCESS_HEADER,
                        Utility.FLIGHT_BY_ADMIN(admin.getFirstName(), airline));
            }
        });
    
        gridPane.add(submit, 1, 13, 1, 1);
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    public void ui_cancelFlightsByAirlineAdmin(String airline, Set<Flight> flights)
    {
        if(cancelFlightStage == null) cancelFlightStage = new Stage();
        cancelFlightStage.setTitle("Cancelable flights for ".concat(airline.toUpperCase()));
        cancelFlightStage.setWidth(FieldValue.HOME_SCENE_WIDTH-300);
        cancelFlightStage.setHeight(FieldValue.HOME_SCENE_HEIGHT-150);
        Scene scene = new Scene(ui_flightsToBeCanceledByAirline(airline, flights));
        cancelFlightStage.setScene(scene);
        cancelFlightStage.show();
    }
    
    public VBox ui_adminAccessByAirline(Admin admin, String airline)
    {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.BASELINE_CENTER);
        
        Label header = label(Utility.ACCESS(airline));
        header.setStyle(Utility.ACCESS_STYLE());
        
        Label name = label(FieldValue.ADMIN_NAME.concat(admin.getFirstName()).concat(" ").concat(admin.getLastName()));
        name.setStyle(NAME_HEADER_STYLE());
    
        Button addFlight = new Button(FieldValue.ADD_FLIGHT);
        addFlight.setPrefWidth(190);
        
        Button cancelFlight = new Button(FieldValue.CANCEL_FLIGHT);
        cancelFlight.setPrefWidth(190);
        
        Button allRSVP = new Button(FieldValue.ALL_RSVP);
        allRSVP.setPrefWidth(190);
        
        Button logout = new Button(FieldValue.LOGOUT_LABEL);
        logout.setPrefWidth(190);
        logout.setStyle(LOGOUT_STYLE());
        
        vBox.getChildren().addAll(new Label(), new Label(),
                header, new Label(), name, new Label(), new Label(),
                addFlight, new Label(),
                cancelFlight, new Label(),
                allRSVP, new Label(), new Label(),
                logout);
        
        return vBox;
    }
    
    public GridPane ui_displayAllRSVPsByAirline(String airline, Set<Reservation> reservations)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.BASELINE_CENTER);
        gridPane.setHgap(8);
        gridPane.setVgap(5);
        
        int row = 0;
    
        gridPane.add(new Label(), 0, row++);
        gridPane.add(new Label(), 0, row++);
    
        Label label = new Label(FieldValue.RSVP_BY_SE.concat(" and Airline GUI"));
        label.setStyle(STYLE().concat(FONT_SIZE(18)).concat("-fx-padding: 8; -fx-border-padding: 10"));
        gridPane.add(label, 1, row++, 6, 1);
        
        gridPane.add(new Label(), 0, row++);
        gridPane.add(new Label(), 0, row++);
        gridPane.add(new Label(), 0, row++);
    
        for(int i = 0; i < Utility.ADMIN_VIEW_RESERVATION_HEADERS().getChildren().size(); i++)
            gridPane.add(Utility.ADMIN_VIEW_RESERVATION_HEADERS().getChildren().get(i), i, row);
        row++;
    
        for(int i = 0; i < Utility.ADMIN_VIEW_RESERVATION_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(Reservation reservation : reservations)
        {
            Button b1 = button(reservation.getFlight().getFlightCode());
            b1.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b2 = button(reservation.getFlight().getAirplane().getAirPlaneName());
            b2.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b3 = button(reservation.getFlight().getSource().getAirportName());
            b3.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b4 = button(reservation.getFlight().getDestination().getAirportName());
            b4.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b5 = button(reservation.getCustomer().getLastName());
            b5.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b6 = button(reservation.getStatus());
            Button b7 = button(reservation.getRsvpBy() == 0 ? FieldValue.SE : FieldValue.WP);
            b7.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b8 = button(reservation.getRsvpDate().toString());
            b8.setStyle(Utility.GENERAL_BTN_STYLE());
            
            gridPane.add(b1, 0, row);
            gridPane.add(b2, 1, row);
            gridPane.add(b3, 2, row);
            gridPane.add(b4, 3, row);
            gridPane.add(b5, 4, row);
            gridPane.add(b6, 5, row);
            gridPane.add(b7, 6, row);
            gridPane.add(b8, 7, row);
            
            if(reservation.getStatus().equalsIgnoreCase(FieldValue.CANCELED)) b6.setStyle(Utility.RED());
            else b6.setStyle(Utility.GREEN());
            
            row++;
        }
    
        return gridPane;
    }
    
    public GridPane ui_newCustomerContainer()
    {
        GridPane gridPane = new GridPane();
        
        gridPane.setAlignment(Pos.CENTER);
        
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        
        gridPane.setHgap(10);
        
        gridPane.setVgap(10);
        
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);
        
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);
    
        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);
    
        Label headerLabel = new Label(FieldValue.CUST_REG_FORM);
        headerLabel.setFont(Font.font(FieldValue.FONT_MONACO, FontWeight.BOLD, FieldValue.FONT_SIZE_17));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));
        
        Label firstLabel = new Label(FieldValue.FIRSTNAME);
        gridPane.add(firstLabel, 0,1);
        GridPane.setHalignment(firstLabel, HPos.LEFT);
        
        TextField firstField = new TextField();
        firstField.setPrefHeight(40);
        gridPane.add(firstField, FieldValue.CUST_FIRST_COL,FieldValue.CUST_FIRST_ROW);
    
        Label lastLabel= new Label(FieldValue.LASTNAME);
        gridPane.add(lastLabel, 0,2);
        GridPane.setHalignment(lastLabel, HPos.LEFT);
    
        TextField lastField = new TextField();
        lastField.setPrefHeight(40);
        gridPane.add(lastField, FieldValue.CUST_LAST_COL, FieldValue.CUST_LAST_ROW);
        
        Label emailLabel = new Label(FieldValue.EMAIL);
        gridPane.add(emailLabel, 0, 3);
        GridPane.setHalignment(emailLabel, HPos.LEFT);
        
        TextField emailField = new TextField();
        emailField.setPrefHeight(40);
        gridPane.add(emailField, FieldValue.CUST_EMAIL_COL, FieldValue.CUST_EMAIL_ROW);
        
        Label passwordLabel = new Label(FieldValue.PASSWORD);
        gridPane.add(passwordLabel, 0, 4);
        GridPane.setHalignment(passwordLabel, HPos.LEFT);
        
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, FieldValue.CUST_PASS_COL, FieldValue.CUST_PASS_ROW);
        
        Button submitButton = new Button(FieldValue.SUBMIT);
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, FieldValue.NEW_CUST_SUB_COL, FieldValue.NEW_CUST_SUB_ROW, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));
        
        return gridPane;
    }
    
    public GridPane ui_searchBarContainer(String label)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.BASELINE_CENTER);
    
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        gridPane.add(new Label(), 2, 0, 1, 1);
        
        Label l = Utility.SEARCH_HEADER_LABEL(label);
        gridPane.add(l, 1,0,2,1);
        GridPane.setHalignment(l, HPos.CENTER);
        GridPane.setMargin(l, new Insets(20, 0, 20, 0));
        
        TextField searchBar = new TextField();
        gridPane.add(searchBar, 0,2, 3, 1);
        GridPane.setHalignment(searchBar, HPos.CENTER);
        searchBar.setMinHeight(FieldValue.SEARCH_BAR_HEIGHT);
        searchBar.setMinWidth(FieldValue.SEARCH_BAR_WIDTH);
        
        gridPane.add(new Label(), 0, 3, 3, 1);
    
        gridPane.add(new Label(FieldValue.KEY_WORDS), 1, 4, 3, 1);
        
        searchBar.setOnKeyPressed(new EventHandler <KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    controller.eventGlobalSearchBar();
                }
            }
        });
        
        return gridPane;
    }
    
    public GridPane ui_loginContainer(String label)
    {
        GridPane grid = new GridPane();
        
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
    
        Label custLabel = new Label(label);
        custLabel.setFont(Font.font(FieldValue.FONT_MONACO, FontWeight.NORMAL, 20));
        grid.add(custLabel, 0, 0, 2, 1);
    
        Label userName = new Label(FieldValue.USERNAME);
        grid.add(userName, 0, 1);
    
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);
    
        Label pw = new Label(FieldValue.PASSWORD);
        grid.add(pw, 0, 2);
    
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);
    
        Button btn = new Button(FieldValue.SUBMIT);
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);
        
        return grid;
    }
    
    public void ui_arrivalWindow(Stage stage)
    {
        if(this.arrivalStage != null) this.arrivalStage.close();
        this.arrivalStage = stage;
        this.arrivalStage.show();
    }
    
    public void ui_departureWindow(Stage stage)
    {
        if(this.departureStage != null) this.departureStage.close();
        this.departureStage = stage;
        this.departureStage.show();
    }
    
    public GridPane ui_globalSearchResults(Set<Flight> flights)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.BASELINE_CENTER);
        gridPane.setHgap(8);
        gridPane.setVgap(5);
        int row = 0;
    
        gridPane.add(new Label(), 0, row++);
        gridPane.add(new Label(), 0, row++);
        
        gridPane.add(Utility.SE_HEADER(), 4, row++, 5, 1);
    
        gridPane.add(new Label(), 0, row++);
        gridPane.add(new Label(), 0, row++);
        
        gridPane.add(sortSection(flights), 0, row++, 3, 1);
        
        gridPane.add(new Label(), 0, row++);
        gridPane.add(new Label(), 0, row++);
        
        for(int i = 0; i < Utility.GLOBAL_SEARCH_FLIGHT_HEADERS().getChildren().size(); i++)
            gridPane.add(Utility.GLOBAL_SEARCH_FLIGHT_HEADERS().getChildren().get(i), i, row);
        row++;
    
        for(int i = 0; i < Utility.GLOBAL_SEARCH_FLIGHT_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(Flight flight : flights)
        {
            Button b1 = button(flight.getFlightCode()); b1.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b2 = button(flight.getSource().getAirportName()); b2.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b3 = button(flight.getSource().getDate().toString()
                                     .concat(" ").concat(flight.getSource().getTime()));
                                    b3.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b4 = button(flight.getDestination().getAirportName()); b4.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b5 = button(flight.getDestination().getDate().toString()
                                     .concat(" ").concat(flight.getDestination().getTime()));
                                    b5.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b6 = button(flight.getAirLine().getAirlineName()); b6.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b7 = button(flight.getAirplane().getAirPlaneName()); b7.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b8 = button(FieldValue.$.concat(flight.getFare().toString())); b8.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b9 = button(flight.getAvailableSeat().toString()); b9.setStyle(Utility.GENERAL_BTN_STYLE());
            
            gridPane.add(b1, 0, row);
            gridPane.add(b2,1, row);
            gridPane.add(b3, 2, row);
            gridPane.add(b4, 3, row);
            gridPane.add(b5, 4, row);
            gridPane.add(b6, 5, row);
            gridPane.add(b7, 6, row);
            gridPane.add(b8, 7, row);
            gridPane.add(b9, 8, row);
            Button openClose = button(flight.getAvailableSeat() == 0 ? FieldValue.FULL : FieldValue.OPEN);
            gridPane.add(openClose, 9, row);
            
            if(flight.getAvailableSeat() != 0)
            {
                openClose.setStyle(Utility.GREEN());
                
                Button rsvp = button(FieldValue.CLICK);
                rsvp.setStyle(Utility.CLICK_ME());
                
                gridPane.add(rsvp, 10, row);
                rsvp.setOnAction(e -> controller.makeReservationFromSE(flight));
            }
            else openClose.setStyle(Utility.RED());
            
            
            row++;
        }
        
        gridPane.add(new Label(), 0, ++row);
        
        Button button = new Button(FieldValue.HOME); button.setMinWidth(FieldValue.HOME_BTN_WIDTH);
        button.setStyle(Utility.HOME_STYLE());
        button.setOnAction(e -> this.setCenter(ui_searchBarContainer(FieldValue.GLOBAL_SEARCH_ENGINE_LABEL)));
        
        gridPane.add(button, 4, ++row, 7, 1);
        
        return gridPane;
    }
    
    public GridPane ui_searchResultsByAirline(String airline, Set<Flight> flights)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.BASELINE_CENTER);
        gridPane.setHgap(8);
        gridPane.setVgap(5);
        
        int row = 0;
        
        gridPane.add(new Label(), 0, row++);
        gridPane.add(new Label(), 0, row++);
    
        gridPane.add(Utility.AIRLINE_RESULTS_HEADER_LABEL(airline.toUpperCase()), 3, row++, 7, 1);
        
        gridPane.add(new Label(), 0, row++);
        gridPane.add(new Label(), 0, row++);
        
        for(int i = 0; i < Utility.AIRLINE_SEARCH_FLIGHT_HEADERS().getChildren().size(); i++)
            gridPane.add(Utility.AIRLINE_SEARCH_FLIGHT_HEADERS().getChildren().get(i), i, row);
        row++;
        
        for(int i = 0; i < Utility.AIRLINE_SEARCH_FLIGHT_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(Flight flight : flights)
        {
            Button b1 = button(flight.getFlightCode()); b1.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b2 = button(flight.getSource().getAirportName()); b2.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b3 = button(flight.getSource().getDate().toString()
                                     .concat(" ").concat(flight.getSource().getTime()));
            b3.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b4 = button(flight.getDestination().getAirportName()); b4.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b5 = button(flight.getDestination().getDate().toString()
                                     .concat(" ").concat(flight.getDestination().getTime()));
            b5.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b6 = button(flight.getAirplane().getAirPlaneName()); b6.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b7 = button(FieldValue.$.concat(flight.getFare().toString())); b7.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b8 = button(flight.getAvailableSeat().toString()); b8.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b9 = button(flight.getAvailableSeat() == 0 ? FieldValue.FULL : FieldValue.OPEN);
            
            gridPane.add(b1, 0, row);
            gridPane.add(b2,1, row);
            gridPane.add(b3, 2, row);
            gridPane.add(b4, 3, row);
            gridPane.add(b5, 4, row);
            gridPane.add(b6, 5, row);
            gridPane.add(b7, 6, row);
            gridPane.add(b8, 7, row);
            gridPane.add(b9, 8, row);
            
            if(flight.getAvailableSeat() != 0)
            {
                b9.setStyle(Utility.GREEN());
                Button rsvp = button(FieldValue.CLICK);
                rsvp.setStyle(Utility.CLICK_ME());
                gridPane.add(rsvp, 9, row);
                rsvp.setOnAction(e -> controller.makeReservationByAirline(flight));
            }
            else b9.setStyle(Utility.RED());
            row++;
        }
    
        gridPane.add(new Label(), 0, ++row);
    
        Button button = new Button(FieldValue.HOME);
        button.setMinWidth(FieldValue.HOME_BTN_WIDTH);
        button.setStyle(Utility.HOME_STYLE());
        button.setOnAction(e -> controller.eventLaunchAirline(airline));
    
        gridPane.add(button, 4, ++row, 7, 1);
        
        return gridPane;
    }
    
    public GridPane ui_flightsToBeCanceledByAirline(String airline, Set<Flight> flights)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.BASELINE_CENTER);
        gridPane.setHgap(8);
        gridPane.setVgap(5);
        
        int row = 0;
        
        gridPane.add(new Label(), 0, row++);
        gridPane.add(new Label(), 0, row++);
    
        Label label1 = new Label(FieldValue.RESULTS_LABEL.concat(airline.toUpperCase()));
        label1.setStyle(STYLE().concat(FONT_SIZE(18)).concat("-fx-padding: 8; -fx-border-padding: 10"));
        gridPane.add(label1, 3, row++, 6, 1);
        
        
        gridPane.add(new Label(), 0, row++);
        gridPane.add(new Label(), 0, row++);
        
        for(int i = 0; i < Utility.ADMIN_CANCEL_FLIGHT_HEADERS().getChildren().size(); i++)
            gridPane.add(Utility.ADMIN_CANCEL_FLIGHT_HEADERS().getChildren().get(i), i, row);
        row++;
        
        for(int i = 0; i < Utility.ADMIN_CANCEL_FLIGHT_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(Flight flight : flights)
        {
            Button b1 = button(flight.getFlightCode());
            b1.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b2 = button(flight.getAirplane().getAirPlaneName());
            b2.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b3 = button(flight.getSource().getAirportName());
            b3.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b4 = button(flight.getSource().getDate().toString()
                                     .concat(" ").concat(flight.getSource().getTime()));
            b4.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b5 = button(flight.getDestination().getAirportName());
            b5.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b6 = button(flight.getDestination().getDate().toString()
                                     .concat(" ").concat(flight.getDestination().getTime()));
            b6.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b7 = button(flight.getCustomers().size()+"");
            b7.setStyle(Utility.GENERAL_BTN_STYLE());
            
            Button b8 = button(flight.getStatus());
            
            gridPane.add(b1, 0, row);
            gridPane.add(b2, 1, row);
            gridPane.add(b3, 2, row);
            gridPane.add(b4, 3, row);
            gridPane.add(b5, 4, row);
            gridPane.add(b6, 5, row);
            gridPane.add(b7, 6, row);
            gridPane.add(b8, 7, row);
            
            if(flight.getStatus().equalsIgnoreCase(FieldValue.ON_TIME))
            {
                b8.setStyle(Utility.GREEN());
                
                Button toCancel = button(FieldValue.CLICK);
                toCancel.setStyle(Utility.CLICK_ME());
                
                gridPane.add(toCancel, 8, row);
                toCancel.setOnAction(e ->
                {
                    if(flight.getStatus().equalsIgnoreCase(FieldValue.ON_TIME))
                    {
                        controller.cancelFlight(flight.getFlightId(), airline);
                    }
                });
            }
            else b8.setStyle(Utility.RED());
            row++;
        }
        
        return gridPane;
    }
    
    public HBox sortSection(Set<Flight> flights)
    {
        HBox hBox = new HBox();
        
        Button fare = button("Fare");
        fare.setStyle(Utility.SORT_FARE());
        Button airline = button("Airline");
        airline.setStyle(Utility.SORT_AIRLINE());
        
        Label label = Utility.SORT_LABEL();
        label.setPadding(new Insets(5, 5, 5, 5));
        
        hBox.getChildren().addAll(label, new Label(" "), fare, new Label(" "), airline);
        
        fare.setOnAction(e ->
        {
            VBox vBox = new VBox();
            vBox.getChildren().addAll(ui_globalSearchResults(Utility.SORT_BY_FARE(flights)));
            setSearchResultsInCenter(vBox);
        });
        
        airline.setOnAction(e ->
        {
            VBox vBox = new VBox();
            vBox.getChildren().addAll(ui_globalSearchResults(Utility.SORT_BY_AIRLINE(flights)));
            setSearchResultsInCenter(vBox);
        });
        
        return hBox;
    }
    
    public GridPane ui_globalReservationResultsForAdmin(Set<Reservation> reservations)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(8);
        gridPane.setVgap(5);
        
        int row = 0;
        
        for(int i = 0; i < Utility.reservationHeaders().getChildren().size(); i++)
            gridPane.add(Utility.reservationHeaders().getChildren().get(i), i, row);
        row++;
    
        for(int i = 0; i < Utility.reservationHeaders().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(Reservation reservation : reservations)
        {
            Button b1 = button(reservation.getFlight().getFlightCode());
            b1.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b2 = button(reservation.getFlight().getSource().getAirportName());
            b2.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b3 = button(reservation.getFlight().getDestination().getAirportName());
            b3.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b4 = button(reservation.getFlight().getAirLine().getAirlineName());
            b4.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b5 = button(reservation.getFlight().getAirplane().getAirPlaneName());
            b5.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b6 = button(FieldValue.$.concat(reservation.getFlight().getFare().toString()));
            b6.setStyle(Utility.GENERAL_BTN_STYLE());
            Button b7 = button(reservation.getRsvpDate().toString());
            b7.setStyle(Utility.GENERAL_BTN_STYLE());
            
            gridPane.add(b1, 0, row);
            gridPane.add(b2, 1, row);
            gridPane.add(b3, 2, row);
            gridPane.add(b4, 3, row);
            gridPane.add(b5, 4, row);
            gridPane.add(b6, 5, row);
            gridPane.add(b7, 6, row);
            row++;
        }
        
        return gridPane;
    }
    
    public void setSearchResultsInCenter(Node node)
    {
        this.primaryStage.setWidth(FieldValue.HOME_WINDOW_WIDTH);
        this.primaryStage.setHeight(FieldValue.HOME_WINDOW_HEIGHT);
        this.homeSceneContainer.setPrefHeight(FieldValue.HOME_SCENE_HEIGHT);
        this.homeSceneContainer.setPrefWidth(FieldValue.HOME_SCENE_WIDTH);
        this.homeSceneContainer.setCenter(node);
    }
    
    public Menu airports()
    {
        Menu airportsMenu = new Menu(FieldValue.AIRPORT_LABEL);
        
        MenuItem airport1 = new MenuItem(FieldValue.AP_JFK_NAME);
        airport1.setOnAction(e -> controller.eventLaunchAirport(FieldValue.AP_JFK));
        
        MenuItem airport2 = new MenuItem(FieldValue.AP_LAX_NAME);
        airport2.setOnAction(e -> controller.eventLaunchAirport(FieldValue.AP_LA));
        
        MenuItem airport3 = new MenuItem(FieldValue.AP_MI_NAME);
        airport3.setOnAction(e -> controller.eventLaunchAirport(FieldValue.AP_MI));
        
        MenuItem airport4 = new MenuItem(FieldValue.AP_BOSTON_NAME);
        airport4.setOnAction(e -> controller.eventLaunchAirport(FieldValue.AP_BOSTON));
        
        MenuItem airport5 = new MenuItem(FieldValue.AP_GEORGIA_NAME);
        airport5.setOnAction(e -> controller.eventLaunchAirport(FieldValue.AP_GEORGIA));
        
        MenuItem airport6 = new MenuItem(FieldValue.AP_NEWARK_NAME);
        airport6.setOnAction(e -> controller.eventLaunchAirport(FieldValue.AP_NEWARK));
        
        //        MenuItem airport7 = new MenuItem(FieldValue.AP_ATLANTA_NAME);
        //        airport7.setOnAction(e -> controller.eventLaunchAirport(FieldValue.AP_ATLANTA));
        
        airportsMenu.getItems().addAll(airport1, Utility.SEPARATOR(), airport2,
                Utility.SEPARATOR(), airport3, Utility.SEPARATOR(), airport4,
                Utility.SEPARATOR(), airport5, Utility.SEPARATOR(), airport6, Utility.SEPARATOR());
        
        return airportsMenu;
    }
    
    public Menu airlines()
    {
        Menu airlineMenu = new Menu(FieldValue.AIRLINE_LABEL);
        
        MenuItem airline1 = new MenuItem(FieldValue.AR_AMERICAN);
        airline1.setOnAction(e -> controller.eventLaunchAirline(FieldValue.AR_AMERICAN));
        
        MenuItem airline2 = new MenuItem(FieldValue.AR_JET_BLUE);
        airline2.setOnAction(e -> controller.eventLaunchAirline(FieldValue.AR_JET_BLUE));
        
        MenuItem airline3 = new MenuItem(FieldValue.AR_DELTA);
        airline3.setOnAction(e -> controller.eventLaunchAirline(FieldValue.AR_DELTA));
        
        airlineMenu.getItems().addAll(airline1, Utility.SEPARATOR(), airline2,
                Utility.SEPARATOR(), airline3, Utility.SEPARATOR());
        
        return airlineMenu;
    }
    
    public Menu login(MenuItem... menuItems)
    {
        Menu loginMenu = new Menu(FieldValue.CUSTOMER);
        for(MenuItem menuItem : menuItems)
        {
            loginMenu.getItems().add(menuItem);
            loginMenu.getItems().add(Utility.SEPARATOR());
        }
        return loginMenu;
    }
    
    public Menu admins(MenuItem... menuItems)
    {
        Menu menu = new Menu(FieldValue.ADMINS);
        for(MenuItem menuItem : menuItems)
        {
            menu.getItems().add(menuItem);
            menu.getItems().add(Utility.SEPARATOR());
        }
        return menu;
    }
    
    public MenuItem globalAdminLoginItem()
    {
        MenuItem global = new MenuItem(FieldValue.SE_ADMIN_LABEL);
        global.setOnAction(e -> controller.launchLoginForGlobalAdmin(ui_loginContainer(FieldValue.SE_ADMIN_LOGIN_LABEL)));
        return global;
    }
    
    public MenuItem customerLoginItem()
    {
        MenuItem customerLogin = new MenuItem(FieldValue.CUSTOMER_LOGIN_LABEL);
        customerLogin.setOnAction(e -> controller.launchLoginForCustomer(ui_loginContainer(FieldValue.CUSTOMER_LOGIN_LABEL)));
        return customerLogin;
    }
    
    public MenuItem airlineAdminLoginItem(String label)
    {
        MenuItem admin = new MenuItem(label);
        admin.setOnAction(e -> controller.launchLoginForAirlineAdmin(ui_loginContainer("Admin Login for "+label), label));
        return admin;
    }
    
    public MenuItem newCustomerItem()
    {
        final MenuItem newCustomerMenu = new MenuItem(FieldValue.NEW_CUST_LABEL);
        newCustomerMenu.setOnAction(e -> controller.eventLaunchNewCustomer(ui_newCustomerContainer()));
        return newCustomerMenu;
    }
    
    public MenuBar menuBar(Menu... menus)
    {
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menus);
        return menuBar;
    }
    
    public Menu ui_loggedUser()
    {
        Menu menu = new Menu(FieldValue.LOGGED_USER);
        
        MenuItem menuItem = new MenuItem(FieldValue.ACCOUNT);
        menu.setOnAction(e -> controller.handleLoggedUser());
        menu.getItems().addAll(menuItem);
        return menu;
    }
    
    public Menu searchEngine()
    {
        Menu menu = new Menu(FieldValue.SEARCH_ENGINE);
        MenuItem menuItem = new MenuItem("Search Engine");
        menu.setOnAction(e -> controller.takeLoggedUserToSearchEngine());
        menu.getItems().addAll(menuItem);
        return menu;
    }
    
    public MenuBar ui_homeMenuBar()
    {
        return menuBar
                (login(customerLoginItem(), newCustomerItem()),
                        airports(),
                        airlines(),
                        admins(globalAdminLoginItem(),
                                airlineAdminLoginItem(FieldValue.AR_AMERICAN),
                                airlineAdminLoginItem(FieldValue.AR_DELTA),
                                airlineAdminLoginItem(FieldValue.AR_JET_BLUE)));
    }
    
    public void RSVPsByAirline(String airlineName, GridPane ui_gridPane)
    {
        Stage stage = new Stage();
        stage.setTitle(FieldValue.RSVP_TITLE.concat(" ").concat(airlineName.toUpperCase()));
        stage.setHeight(600);
        stage.setWidth(1300);
        
        Scene scene = new Scene(ui_gridPane);
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    public HBox logoutHBox()
    {
        HBox outContainer = new HBox();
        outContainer.setAlignment(Pos.BOTTOM_CENTER);
        Button logout = new Button("Logout");
        outContainer.getChildren().add(logout);
        logout.setStyle(LOGOUT_STYLE());
        return outContainer;
    }
    
    public void ui_handleAfterGlobalAdminLogin(Admin admin, Set<Reservation> reservations)
    {
        VBox superV = new VBox();
        superV.setAlignment(Pos.TOP_CENTER);
    
        superV.getChildren().add(new Label());
        superV.getChildren().add(new Label());
        Label label = new Label(FieldValue.ADMIN_NAME.concat(admin.getFirstName() + " " + admin.getLastName()));
        label.setStyle(NAME_HEADER_STYLE());
        superV.getChildren().add(label);
        superV.getChildren().add(new Label());
        superV.getChildren().add(new Label());
        Label label1 = new Label(FieldValue.RSVP_BY_SE);
        label1.setStyle(STYLE().concat(FONT_SIZE(18)).concat("-fx-padding: 8; -fx-border-padding: 10"));
        superV.getChildren().add(label1);
        superV.getChildren().add(new Label());
        superV.getChildren().add(new Label());
        superV.getChildren().add(new Label());
        superV.getChildren().add(ui_globalReservationResultsForAdmin(reservations));
        superV.getChildren().add(new Label());
        superV.getChildren().add(new Label());
        HBox logout = logoutHBox();
        logout.setAlignment(Pos.BASELINE_CENTER);
        superV.getChildren().add(logout);
        Button out = (Button) logout.getChildren().get(0);
        out.setOnAction(e -> controller.adminLogout());
        
        setTop(menuBar(airports()));
        setCenter(superV);
    }
}
