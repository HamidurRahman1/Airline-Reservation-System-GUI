package com.hrs.view;

import com.hrs.configs.Configuration;
import com.hrs.util.Utility;
import com.hrs.view.alerts.AlertBox;
import com.hrs.view.controller.Controller;
import com.hrs.view.models.Admin;
import com.hrs.view.models.Airplane;
import com.hrs.view.models.Airport;
import com.hrs.view.models.Customer;
import com.hrs.view.models.Flight;
import com.hrs.view.models.Reservation;
import com.hrs.view.style.CSSStyle;
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
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.hrs.util.Utility.button;
import static com.hrs.util.Utility.label;

public class View extends Application
{
    private Controller controller;
    
    private Stage primaryStage;
    private Stage arrivalStage;
    private Stage departureStage;
    
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
        
        BackgroundImage myBI= new BackgroundImage
                (new Image(new File(new File("")
                        .getAbsolutePath()+"/src/main/java/com/hrs/resources/home.jpg").toURI().toString(),true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        
        this.homeSceneContainer.setBackground(new Background(myBI));
        
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
        
        homeSceneContainer.setCenter(ui_searchBarContainer(FieldValue.SEARCH));
        
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
        this.homeSceneContainer.setCenter(ui_searchBarContainer(FieldValue.SEARCH));
    }
    
    public void ui_addFlightForAirline(Admin admin, String airline, Set<Airport> airports, Set<Airplane> airplanes)
    {
        System.out.println(airports);
        System.out.println(airplanes);
        
        Stage stage = new Stage();
        stage.setWidth(900);
        stage.setHeight(700);
        stage.setTitle("Adding a flight for ".concat(airline));
    
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.BASELINE_CENTER);
        gridPane.setHgap(12);
        gridPane.setVgap(8);
    
        gridPane.add(new Label(), 0, 0);
        gridPane.add(new Label(), 0, 1);
    
        Label codeLabel = label("Enter a flight code/name: ");
        codeLabel.setPadding(Utility.FLIGHT_LABEL());
    
        Label airplaneLabel = label("Select an airplane: ");
        airplaneLabel.setPadding(Utility.FLIGHT_LABEL());
    
        Label sourceLabel = label("Select departure airport: ");
        sourceLabel.setPadding(Utility.FLIGHT_LABEL());
    
        Label sourceDateLabel = label("Select departure date: ");
        sourceDateLabel.setPadding(Utility.FLIGHT_LABEL());
    
        Label sourceTimeLabel = label("Select departure time: ");
        sourceTimeLabel.setPadding(Utility.FLIGHT_LABEL());
    
        Label destinationLabel = label("Select arrival airport: ");
        destinationLabel.setPadding(Utility.FLIGHT_LABEL());
    
        Label destinationDateLabel = label("Select arrival date: ");
        destinationDateLabel.setPadding(Utility.FLIGHT_LABEL());
    
        Label destinationTimeLabel = label("Select arrival time: ");
        destinationTimeLabel.setPadding(Utility.FLIGHT_LABEL());
    
        Label capacityLabel = label("Enter max capacity: ");
        capacityLabel.setPadding(Utility.FLIGHT_LABEL());
    
        TextField codeField = new TextField();
        
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
    
        Button submit = new Button("Submit");
        submit.setOnAction(e ->
        {
            if(controller.addFlightForAirline(airline, codeField, airPlaneChoiceBox, sourceChoices,
                    sourceDate, sourceTimes, destinationChoices, destinationDate, destinationTimes, capacity1))
            {
                stage.close();
                AlertBox.DisplayInformation(FieldValue.FLIGHT_ADDITION_SUCCESS_HEADER,
                        "A flight has successfully been added by "
                                + admin.getFirstName() + " for Airline ".concat(airline.toUpperCase()));
            }
            else{
            
            }
        });
    
        gridPane.add(submit, 1, 13, 1, 1);
        gridPane.setBackground(Utility.BACKGROUND_IMAGE_BY_AIRLINE(Utility.HOME_PIC_PATH));
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    public void ui_cancelFlightsByAirlineAdmin(String airline, Set<Flight> flights)
    {
        Stage stage = new Stage();
        stage.setWidth(FieldValue.HOME_SCENE_WIDTH);
        stage.setHeight(500);
        Scene scene = new Scene(ui_flightsToBeCanceledByAirline(airline, flights));
        stage.setScene(scene);
        stage.show();
    }
    
    public VBox ui_adminAccessByAirline(Admin admin, String airline)
    {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.BASELINE_CENTER);
        
        Label header = label("Admin Access Enabled for ".concat(airline.toUpperCase()));
        
        Label name = label("Admin Name: ".concat(admin.getFirstName()).concat(" ").concat(admin.getLastName()));
    
        Button addFlight = new Button(FieldValue.ADD_FLIGHT);
        addFlight.setPrefWidth(130);
        
        Button cancelFlight = new Button(FieldValue.CANCEL_FLIGHT);
        cancelFlight.setPrefWidth(130);
        
        Button allRSVP = new Button("All RSVPs");
        allRSVP.setPrefWidth(130);
        
        Button logout = new Button(FieldValue.LOGOUT_LABEL);
        logout.setPrefWidth(130);
        
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
    
        gridPane.add(new Label("Displaying all reservations made for all flights for ".concat(airline)), 2, row++, 4, 1);
        
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
            gridPane.add(button(reservation.getFlight().getFlightCode()), 0, row);
            gridPane.add(button(reservation.getFlight().getAirplane().getAirPlaneName()), 1, row);
            gridPane.add(button(reservation.getFlight().getSource().getAirportName()), 2, row);
            gridPane.add(button(reservation.getFlight().getDestination().getAirportName()), 3, row);
            gridPane.add(button(reservation.getCustomer().getLastName()), 4, row);
            gridPane.add(button(reservation.getStatus()), 5, row);
            gridPane.add(button(reservation.getRsvpBy() == 0 ? FieldValue.SE : FieldValue.WP), 6, row);
            gridPane.add(button(reservation.getRsvpDate().toString()), 7, row);
            
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
    
        Label searchLabel = new Label(label);
        searchLabel.setFont(Font.font(FieldValue.FONT_MONACO, FontWeight.BOLD, FieldValue.FONT_SIZE_17));
        gridPane.add(searchLabel, 0,0,2,1);
        GridPane.setHalignment(searchLabel, HPos.CENTER);
        GridPane.setMargin(searchLabel, new Insets(20, 0,20,0));
        
        TextField searchBar = new TextField();
        gridPane.add(searchBar, 1,1);
        GridPane.setHalignment(searchBar, HPos.CENTER);
        searchBar.setMinHeight(FieldValue.SEARCH_BAR_HEIGHT);
        searchBar.setMinWidth(FieldValue.SEARCH_BAR_WIDTH);
        
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
        
        gridPane.add(Utility.SE_HEADER(), 2, row++, 7, 1);
    
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
            gridPane.add(button(flight.getFlightCode()), 0, row);
            gridPane.add(button(flight.getSource().getAirportName()),1, row);
            gridPane.add(button(flight.getSource().getDate().toString()
                                      .concat(" ").concat(flight.getSource().getTime())), 2, row);
            gridPane.add(button(flight.getDestination().getAirportName()), 3, row);
            gridPane.add(button(flight.getDestination().getDate().toString()
                                      .concat(" ").concat(flight.getDestination().getTime())), 4, row);
            gridPane.add(button(flight.getAirLine().getAirlineName()), 5, row);
            gridPane.add(button(flight.getAirplane().getAirPlaneName()), 6, row);
            gridPane.add(button(flight.getFare().toString()), 7, row);
            gridPane.add(button(flight.getAvailableSeat().toString()), 8, row);
            
            Button status = button(flight.getAvailableSeat() == 0 ? FieldValue.FULL : FieldValue.OPEN);
            gridPane.add(status, 9, row);
            status.setOnAction(e ->
            {
                if(status.getText().equalsIgnoreCase(FieldValue.OPEN))
                {
                    controller.makeReservationFromSE(flight.getFlightId());
                }
            });
            
            row++;
        }
        
        gridPane.add(new Label(), 0, ++row);
        
        Button button = new Button("Back/Home");
        button.setOnAction(e -> this.setCenter(ui_searchBarContainer(FieldValue.SEARCH)));
        
        gridPane.add(button, 3, ++row, 7, 1);
        
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
    
        gridPane.add(Utility.AIRLINE_HEADER(airline), 2, row++, 7, 1);
        
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
            gridPane.add(button(flight.getFlightCode()), 0, row);
            gridPane.add(button(flight.getSource().getAirportName()),1, row);
            gridPane.add(button(flight.getSource().getDate().toString()
                                      .concat(" ").concat(flight.getSource().getTime())), 2, row);
            gridPane.add(button(flight.getDestination().getAirportName()), 3, row);
            gridPane.add(button(flight.getDestination().getDate().toString()
                                      .concat(" ").concat(flight.getDestination().getTime())), 4, row);
            gridPane.add(button(flight.getAirplane().getAirPlaneName()), 5, row);
            gridPane.add(button(flight.getFare().toString()), 6, row);
            gridPane.add(button(flight.getAvailableSeat().toString()), 7, row);
            Button status = button(flight.getAvailableSeat() == 0 ? FieldValue.FULL : FieldValue.OPEN);
            gridPane.add(status, 8, row);
            status.setOnAction(e ->
            {
                if(status.getText().equalsIgnoreCase(FieldValue.OPEN))
                {
                    controller.makeReservationByAirline(flight.getFlightId());
                }
            });
            row++;
        }
    
        gridPane.add(new Label(), 0, ++row);
    
        Button button = new Button("Back/Home");
        button.setOnAction(e -> controller.eventLaunchAirline(airline));
    
        gridPane.add(button, 3, ++row, 7, 1);
        
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
        
        gridPane.add(new Label("Displaying all reservations made for all flights for "), 3, row++, 6, 1);
        
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
            gridPane.add(button(flight.getFlightCode()), 0, row);
            gridPane.add(button(flight.getAirplane().getAirPlaneName()), 1, row);
            gridPane.add(button(flight.getSource().getAirportName()), 2, row);
            gridPane.add(button(flight.getSource().getDate().toString()
                                      .concat(" ").concat(flight.getSource().getTime())), 3, row);
            gridPane.add(button(flight.getDestination().getAirportName()), 4, row);
            gridPane.add(button(flight.getDestination().getDate().toString()
                                      .concat(" ").concat(flight.getDestination().getTime())), 5, row);
            gridPane.add(button(flight.getCustomers().size()+""), 6, row);
            gridPane.add(button(flight.getStatus()), 7, row);
            Button toCancel = button(FieldValue.TO_CANCEL);
            gridPane.add(toCancel, 8, row);
            
            toCancel.setOnAction(e ->
            {
                if(flight.getStatus().equalsIgnoreCase(FieldValue.ON_TIME))
                    controller.cancelFlight(flight.getFlightId(), airline);
            });
            
            row++;
        }
        
        return gridPane;
    }
    
    public HBox sortSection(Set<Flight> flights)
    {
        HBox hBox = new HBox();
        
        Button fare = button("Fare");
        Button airline = button("Airline");
        
        Label label = new Label("Sort results by: ");
        label.setPadding(new Insets(5, 5, 5, 5));
        label.setStyle(CSSStyle.fontSize(20));
        
        hBox.getChildren().addAll(label, fare, new Label(" "), airline);
        
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
            gridPane.add(button(reservation.getFlight().getFlightCode()), 0, row);
            gridPane.add(button(reservation.getFlight().getSource().getAirportName()), 1, row);
            gridPane.add(button(reservation.getFlight().getDestination().getAirportName()), 2, row);
            gridPane.add(button(reservation.getFlight().getAirLine().getAirlineName()), 3, row);
            gridPane.add(button(reservation.getFlight().getAirplane().getAirPlaneName()), 4, row);
            gridPane.add(button(FieldValue.$.concat(reservation.getFlight().getFare().toString())), 5, row);
            gridPane.add(button(reservation.getRsvpDate().toString()), 6, row);
            row++;
        }
        
        return gridPane;
    }
    
    public void setSearchResultsInCenter(Node node)
    {
        this.homeSceneContainer.setCenter(node);
    }
    
    public Menu airports()
    {
        Menu airportsMenu = new Menu(FieldValue.AIRPORT_LABEL);
        
        MenuItem airport1 = new MenuItem(FieldValue.AIRPORT1);
        airport1.setOnAction(e -> controller.eventLaunchAirport(airport1.getText().split(" ")[2]));
    
        MenuItem airport2 = new MenuItem(FieldValue.AIRPORT2);
        airport2.setOnAction(e -> controller.eventLaunchAirport(airport2.getText().split(" ")[2]));
    
        MenuItem airport3 = new MenuItem(FieldValue.AIRPORT3);
        airport3.setOnAction(e -> controller.eventLaunchAirport(airport3.getText().split(" ")[2]));
    
        airportsMenu.getItems().addAll(airport1, airport2, airport3);
        
        return airportsMenu;
    }
    
    public Menu airlines()
    {
        Menu airlineMenu = new Menu(FieldValue.AIRLINE_LABEL);
        
        MenuItem airline1 = new MenuItem(FieldValue.AIRLINE1);
        airline1.setOnAction(e -> controller.eventLaunchAirline(airline1.getText().split(" ")[2]));
        
        MenuItem airline2 = new MenuItem(FieldValue.AIRLINE2);
        airline2.setOnAction(e -> controller.eventLaunchAirline(airline2.getText().split(" ")[2]));
        
        MenuItem airline3 = new MenuItem(FieldValue.AIRLINE3);
        airline3.setOnAction(e -> controller.eventLaunchAirline(airline3.getText().split(" ")[2]));
    
        airlineMenu.getItems().addAll(airline1, airline2, airline3);
        
        return airlineMenu;
    }
    
    public Menu logins(MenuItem... menuItems)
    {
        Menu loginMenu = new Menu(FieldValue.CUSTOMER);
        loginMenu.getItems().addAll(menuItems);
        return loginMenu;
    }
    
    public Menu admins(MenuItem... menuItems)
    {
        Menu menu = new Menu(FieldValue.ADMINS);
        menu.getItems().addAll(menuItems);
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
        admin.setOnAction(e -> controller.launchLoginForAirlineAdmin
                (ui_loginContainer("Admin Login for ".concat(label.split(" ")[2])),
                label.split(" ")[2]));
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
    
    public MenuBar ui_homeMenuBar()
    {
        return menuBar(
                logins(customerLoginItem(), newCustomerItem()),
                airports(), airlines(),
                admins(globalAdminLoginItem(),
                        airlineAdminLoginItem(FieldValue.AIR_ADMIN_LABEL.concat(" : SH")),
                        airlineAdminLoginItem(FieldValue.AIR_ADMIN_LABEL.concat(" : AR")),
                        airlineAdminLoginItem(FieldValue.AIR_ADMIN_LABEL.concat(" : HR"))));
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
    
    public VBox ui_customerInfo(Customer customer)
    {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
    
        vBox.getChildren().add(new Label());
        vBox.getChildren().add(new Label());
        vBox.getChildren().add(ui_nameHBox("Customer Name : "
                .concat(customer.getFirstName().concat(" ").concat(customer.getLastName()))));
        vBox.getChildren().add(new Label());
        vBox.getChildren().add(controller.populateRSVPsFlightsForCustomer(customer));
        vBox.getChildren().add(new Label());
        vBox.getChildren().add(new Label());
    
        HBox hBox = logoutHBox();
        Button logout = (Button)hBox.getChildren().get(0);
        logout.setOnAction(e -> controller.customerLogout());
    
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(new Label());
        vBox.getChildren().add(new Label());
    
        return vBox;
    }
    
    public HBox ui_nameHBox(String nameHeader)
    {
        HBox name = new HBox();
        name.setAlignment(Pos.TOP_CENTER);
        name.getChildren().add(new Label(nameHeader));
        return name;
    }
    
    public HBox logoutHBox()
    {
        HBox outContainer = new HBox();
        outContainer.setAlignment(Pos.BOTTOM_CENTER);
        Button logout = new Button("Logout");
        outContainer.getChildren().add(logout);
        return outContainer;
    }
    
    public void ui_handleAfterGlobalAdminLogin(Admin admin, Set<Reservation> reservations)
    {
        VBox superV = new VBox();
        superV.setAlignment(Pos.TOP_CENTER);
    
        superV.getChildren().add(new Label());
        superV.getChildren().add(new Label());
        superV.getChildren().add(new Label(admin.getFirstName() + " " + admin.getLastName()));
        superV.getChildren().add(new Label());
        superV.getChildren().add(new Label());
        superV.getChildren().add(new Label());
        superV.getChildren().add(new Label("Displaying all reservations made using SE"));
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
        
        setCenter(superV);
    }
}
