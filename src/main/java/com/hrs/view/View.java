package com.hrs.view;

import com.hrs.configs.Configuration;
import com.hrs.view.alerts.AlertBox;
import com.hrs.view.controller.Controller;
import com.hrs.view.models.Admin;
import com.hrs.view.models.Airplane;
import com.hrs.view.models.Airport;
import com.hrs.view.models.Customer;
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

import static com.hrs.util.Utility.GENERAL_BTN_STYLE;
import static com.hrs.util.Utility.BUTTON;
import static com.hrs.util.Utility.LABEL;
import static com.hrs.util.Utility.GREEN;
import static com.hrs.util.Utility.RED;
import static com.hrs.util.Utility.SEPARATOR;
import static com.hrs.util.Utility.GET_NODE;
import static com.hrs.util.Utility.FLIGHT_LABEL;
import static com.hrs.util.Utility.CLICK_ME;
import static com.hrs.util.Utility.SORT_LABEL;
import static com.hrs.util.Utility.SORT_FARE;
import static com.hrs.util.Utility.SEARCH_HEADER_LABEL;
import static com.hrs.util.Utility.FLIGHT_BY_ADMIN;
import static com.hrs.util.Utility.AIRPLANES_LIST;
import static com.hrs.util.Utility.NAME_HEADER_STYLE;
import static com.hrs.util.Utility.FONT_SIZE;
import static com.hrs.util.Utility.FONT_FAMILY;
import static com.hrs.util.Utility.ADD_FLIGHT_FOR;
import static com.hrs.util.Utility.AIRPORTS_LIST;
import static com.hrs.util.Utility.TIMES_LIST;
import static com.hrs.util.Utility.ACCESS;
import static com.hrs.util.Utility.ACCESS_STYLE;
import static com.hrs.util.Utility.LOGOUT_STYLE;
import static com.hrs.util.Utility.ADMIN_CANCEL_FLIGHT_HEADERS;
import static com.hrs.util.Utility.ADMIN_VIEW_RESERVATION_HEADERS;
import static com.hrs.util.Utility.GLOBAL_SEARCH_FLIGHT_HEADERS;
import static com.hrs.util.Utility.SE_HEADER;
import static com.hrs.util.Utility.HOME_STYLE;
import static com.hrs.util.Utility.AIRLINE_SEARCH_FLIGHT_HEADERS;
import static com.hrs.util.Utility.AIRLINE_RESULTS_HEADER_LABEL;
import static com.hrs.util.Utility.SORT_AIRLINE;
import static com.hrs.util.Utility.SORT_BY_AIRLINE;
import static com.hrs.util.Utility.SORT_BY_FARE;
import static com.hrs.util.Utility.RESERVATION_HEADERS;
import static com.hrs.util.Utility.CUSTOMER_RSVP_HEADERS;
import static com.hrs.util.Utility.CUSTOMER_PAST_FLIGHTS_HEADERS;
import static com.hrs.util.Utility.TITTLE_BY;
import static com.hrs.util.Utility.ARRIVAL_HEADERS;
import static com.hrs.util.Utility.DEPARTURE_HEADERS;

public class View extends Application
{
    private Controller controller = Configuration.GET_CONTROLLER();
    private Stage primaryStage;
    private BorderPane homeSceneContainer;
    
    public View() {}
    
    public void start(Stage primaryStage) throws Exception
    {
        this.primaryStage = primaryStage;
        
        initialize();
        
        ui_datePicker();
    }
    
    public void start2()
    {
        Scene homeScene = new Scene(this.homeSceneContainer, FieldValue.HOME_SCENE_WIDTH, FieldValue.HOME_SCENE_HEIGHT);
        
        primaryStage.setTitle(FieldValue.APP_TITLE);
        primaryStage.setWidth(FieldValue.HOME_WINDOW_WIDTH);
        primaryStage.setHeight(FieldValue.HOME_WINDOW_HEIGHT);
        primaryStage.setScene(homeScene);
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
        
        controller = Configuration.GET_CONTROLLER();
        controller.setView(this);
        Configuration.SET_VIEW(this);
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
    
    public void ui_datePicker()
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
            stage.close();
            controller.handleInitialDate(datePicker);
        });
        
        Label label = new Label(FieldValue.SELECT_DATE);
        label.setStyle(FONT_FAMILY(FieldValue.FONT_MONACO)+FONT_SIZE(15));
        
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(label, datePicker);
        Scene scene = new Scene(hBox);
        stage.setTitle(FieldValue.DATE_TITLE);
        stage.setHeight(350);
        stage.setWidth(450);
        stage.setScene(scene);
        stage.show();
    }
    
    public void ui_addFlightForAirline(Admin admin, String airline, Set<Airport> airports, Set<Airplane> airplanes)
    {
        Stage stage = new Stage();
        stage.setWidth(900);
        stage.setHeight(700);
        stage.setTitle(ADD_FLIGHT_FOR(airline));
        
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.BASELINE_CENTER);
        gridPane.setHgap(12);
        gridPane.setVgap(8);
        
        gridPane.add(new Label(), 0, 0);
        gridPane.add(new Label(), 0, 1);
        
        Label codeLabel = LABEL(FieldValue.ENTER_CODE);
        codeLabel.setPadding(FLIGHT_LABEL());
        codeLabel.setStyle(GENERAL_BTN_STYLE());
        
        Label airplaneLabel = LABEL(FieldValue.SELECT_AP);
        airplaneLabel.setPadding(FLIGHT_LABEL());
        airplaneLabel.setStyle(GENERAL_BTN_STYLE());
        
        Label sourceLabel = LABEL(FieldValue.SELECT_SRC);
        sourceLabel.setPadding(FLIGHT_LABEL());
        sourceLabel.setStyle(GENERAL_BTN_STYLE());
        
        
        Label sourceDateLabel = LABEL(FieldValue.SELECT_DPT_DATE);
        sourceDateLabel.setPadding(FLIGHT_LABEL());
        sourceDateLabel.setStyle(GENERAL_BTN_STYLE());
        
        Label sourceTimeLabel = LABEL(FieldValue.SELECT_DPT_TIME);
        sourceTimeLabel.setPadding(FLIGHT_LABEL());
        sourceTimeLabel.setStyle(GENERAL_BTN_STYLE());
        
        Label destinationLabel = LABEL(FieldValue.SELECT_ARR);
        destinationLabel.setPadding(FLIGHT_LABEL());
        destinationLabel.setStyle(GENERAL_BTN_STYLE());
        
        Label destinationDateLabel = LABEL(FieldValue.SELECT_ARR_DATE);
        destinationDateLabel.setPadding(FLIGHT_LABEL());
        destinationDateLabel.setStyle(GENERAL_BTN_STYLE());
        
        Label destinationTimeLabel = LABEL(FieldValue.SELECT_ARR_TIME);
        destinationTimeLabel.setPadding(FLIGHT_LABEL());
        destinationTimeLabel.setStyle(GENERAL_BTN_STYLE());
        
        Label capacityLabel = LABEL(FieldValue.ENTER_CAP);
        capacityLabel.setPadding(FLIGHT_LABEL());
        capacityLabel.setStyle(GENERAL_BTN_STYLE());
        
        TextField codeField = new TextField();
        codeField.setStyle(GENERAL_BTN_STYLE());
        
        ChoiceBox<Airplane> airPlaneChoiceBox = new ChoiceBox<>(AIRPLANES_LIST(airplanes));
        
        ChoiceBox<Airport> sourceChoices = new ChoiceBox<>(AIRPORTS_LIST(airports));
        
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
        
        ChoiceBox<String> sourceTimes = new ChoiceBox<>(TIMES_LIST());
        
        ChoiceBox<Airport> destinationChoices = new ChoiceBox<>(AIRPORTS_LIST(airports));
        
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
        ChoiceBox<String> destinationTimes = new ChoiceBox<>(TIMES_LIST());
        
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
        submit.setStyle(CLICK_ME());
        submit.setOnAction(e ->
        {
            if(controller.addFlightForAirline(airline, codeField, airPlaneChoiceBox, sourceChoices,
                    sourceDate, sourceTimes, destinationChoices, destinationDate, destinationTimes, capacity1))
            {
                stage.close();
                AlertBox.DisplayInformation(FieldValue.FLIGHT_ADDITION_SUCCESS_HEADER,
                        FLIGHT_BY_ADMIN(admin.getFirstName(), airline));
            }
        });
        
        gridPane.add(submit, 1, 13, 1, 1);
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    public void ui_cancelFlightsByAirlineAdmin(String airline, Set<Flight> flights)
    {
        Stage cancelFlightStage = new Stage();
        cancelFlightStage.setTitle(FieldValue.CANCELABLE.concat(airline.toUpperCase()));
        
        cancelFlightStage.setWidth(FieldValue.HOME_SCENE_WIDTH-300);
        cancelFlightStage.setHeight(FieldValue.HOME_SCENE_HEIGHT-150);
        
        Scene scene = new Scene(ui_flightsToBeCanceledByAirline(airline, flights, cancelFlightStage));
        
        cancelFlightStage.setScene(scene);
        cancelFlightStage.show();
    }
    
    public VBox ui_adminAccessByAirline(Admin admin, String airline)
    {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.BASELINE_CENTER);
        
        Label header = LABEL(ACCESS(airline));
        header.setStyle(ACCESS_STYLE());
        
        Label name = LABEL(FieldValue.ADMIN_NAME.concat(admin.getFirstName()).concat(" ").concat(admin.getLastName()));
        name.setStyle(NAME_HEADER_STYLE());
        
        Button addFlight = new Button(FieldValue.ADD_FLIGHT);
        addFlight.setPrefWidth(190);
        addFlight.setStyle(GENERAL_BTN_STYLE().concat(FONT_SIZE(18)));
        addFlight.setOnAction(e -> controller.handleAddFlightForAirline(admin, airline));
        
        Button cancelFlight = new Button(FieldValue.CANCEL_FLIGHT);
        cancelFlight.setPrefWidth(190);
        cancelFlight.setStyle(GENERAL_BTN_STYLE().concat(FONT_SIZE(18)));
        cancelFlight.setOnAction(e -> controller.handleCancelFlightForAirline(admin, airline));
        
        Button allRSVP = new Button(FieldValue.ALL_RSVP);
        allRSVP.setPrefWidth(190);
        allRSVP.setStyle(GENERAL_BTN_STYLE().concat(FONT_SIZE(18)));
        allRSVP.setOnAction(e -> controller.handleAllRSVPsForAirline(airline));
        
        Button logout = new Button(FieldValue.LOGOUT_LABEL);
        logout.setOnAction(e -> controller.logoutAdmin());
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
    
    public GridPane ui_displayAllRSVPsByAirline(Set<Reservation> reservations)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.BASELINE_CENTER);
        gridPane.setHgap(8);
        gridPane.setVgap(5);
        
        int row = 0;
        
        gridPane.add(new Label(), 0, row++);
        gridPane.add(new Label(), 0, row++);
        
        Label label = new Label(FieldValue.RSVP_BY_SE.concat(" and Airline GUI"));
        label.setStyle(GENERAL_BTN_STYLE());
        gridPane.add(label, 2, row++, 6, 1);
        
        gridPane.add(new Label(), 0, row++);
        gridPane.add(new Label(), 0, row++);
        gridPane.add(new Label(), 0, row++);
        
        for(int i = 0; i < ADMIN_VIEW_RESERVATION_HEADERS().getChildren().size(); i++)
            gridPane.add(ADMIN_VIEW_RESERVATION_HEADERS().getChildren().get(i), i, row);
        row++;
        
        for(int i = 0; i < ADMIN_VIEW_RESERVATION_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(Reservation reservation : reservations)
        {
            Button b1 = BUTTON(reservation.getFlight().getFlightCode());
            b1.setStyle(GENERAL_BTN_STYLE());
            Button b2 = BUTTON(reservation.getFlight().getAirplane().getAirPlaneName());
            b2.setStyle(GENERAL_BTN_STYLE());
            Button b3 = BUTTON(reservation.getFlight().getSource().getAirportName());
            b3.setStyle(GENERAL_BTN_STYLE());
            Button b4 = BUTTON(reservation.getFlight().getDestination().getAirportName());
            b4.setStyle(GENERAL_BTN_STYLE());
            Button b5 = BUTTON(reservation.getCustomer().getLastName());
            b5.setStyle(GENERAL_BTN_STYLE());
            Button b6 = BUTTON(reservation.getStatus());
            Button b7 = BUTTON(reservation.getRsvpBy() == 0 ? FieldValue.SE : FieldValue.WP);
            b7.setStyle(GENERAL_BTN_STYLE());
            Button b8 = BUTTON(reservation.getRsvpDate().toString());
            b8.setStyle(GENERAL_BTN_STYLE());
            
            gridPane.add(b1, 0, row);
            gridPane.add(b2, 1, row);
            gridPane.add(b3, 2, row);
            gridPane.add(b4, 3, row);
            gridPane.add(b5, 4, row);
            gridPane.add(b6, 5, row);
            gridPane.add(b7, 6, row);
            gridPane.add(b8, 7, row);
            
            if(reservation.getStatus().equalsIgnoreCase(FieldValue.CANCELED)) b6.setStyle(RED());
            else b6.setStyle(GREEN());
            
            row++;
        }
        
        return gridPane;
    }
    
    public void ui_newCustomerRegistration()
    {
        Stage stage = new Stage();
        
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
        
        submitButton.setOnAction(e ->
        {
            stage.close();
            controller.handleNewCustomer(firstField.getText(), lastField.getText(),
                    emailField.getText(), passwordField.getText());
        });
        
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, FieldValue.NEW_CUST_SUB_COL, FieldValue.NEW_CUST_SUB_ROW, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));
        
        Scene scene = new Scene(gridPane, 600, 450);
        
        stage.setScene(scene);
        stage.setTitle(FieldValue.NEW_CUST_LABEL);
        stage.setAlwaysOnTop(true);
        stage.show();
    }
    
    public GridPane ui_searchBarContainer(String title)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.BASELINE_CENTER);
        
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        gridPane.add(new Label(), 2, 0, 1, 1);
        
        Label header = SEARCH_HEADER_LABEL(title);
        gridPane.add(header, 1,0,2,1);
        GridPane.setHalignment(header, HPos.CENTER);
        GridPane.setMargin(header, new Insets(20, 0, 20, 0));
        
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
    
    public GridPane ui_globalSearchResults(Set<Flight> flights)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.BASELINE_CENTER);
        gridPane.setHgap(8);
        gridPane.setVgap(5);
        int row = 0;
        
        gridPane.add(new Label(), 0, row++);
        gridPane.add(new Label(), 0, row++);
        
        gridPane.add(SE_HEADER(), 4, row++, 5, 1);
        
        gridPane.add(new Label(), 0, row++);
        gridPane.add(new Label(), 0, row++);
        
        gridPane.add(sortSection(flights), 0, row++, 3, 1);
        
        gridPane.add(new Label(), 0, row++);
        gridPane.add(new Label(), 0, row++);
        
        for(int i = 0; i < GLOBAL_SEARCH_FLIGHT_HEADERS().getChildren().size(); i++)
            gridPane.add(GLOBAL_SEARCH_FLIGHT_HEADERS().getChildren().get(i), i, row);
        row++;
        
        for(int i = 0; i < GLOBAL_SEARCH_FLIGHT_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(Flight flight : flights)
        {
            Button b1 = BUTTON(flight.getFlightCode()); b1.setStyle(GENERAL_BTN_STYLE());
            Button b2 = BUTTON(flight.getSource().getAirportName()); b2.setStyle(GENERAL_BTN_STYLE());
            Button b3 = BUTTON(flight.getSource().getDate().toString()
                                     .concat(" ").concat(flight.getSource().getTime()));
            b3.setStyle(GENERAL_BTN_STYLE());
            Button b4 = BUTTON(flight.getDestination().getAirportName()); b4.setStyle(GENERAL_BTN_STYLE());
            Button b5 = BUTTON(flight.getDestination().getDate().toString()
                                     .concat(" ").concat(flight.getDestination().getTime()));
            b5.setStyle(GENERAL_BTN_STYLE());
            Button b6 = BUTTON(flight.getAirLine().getAirlineName()); b6.setStyle(GENERAL_BTN_STYLE());
            Button b7 = BUTTON(flight.getAirplane().getAirPlaneName()); b7.setStyle(GENERAL_BTN_STYLE());
            Button b8 = BUTTON(FieldValue.$.concat(flight.getFare().toString())); b8.setStyle(GENERAL_BTN_STYLE());
            Button b9 = BUTTON(flight.getAvailableSeat().toString()); b9.setStyle(GENERAL_BTN_STYLE());
            
            gridPane.add(b1, 0, row);
            gridPane.add(b2,1, row);
            gridPane.add(b3, 2, row);
            gridPane.add(b4, 3, row);
            gridPane.add(b5, 4, row);
            gridPane.add(b6, 5, row);
            gridPane.add(b7, 6, row);
            gridPane.add(b8, 7, row);
            gridPane.add(b9, 8, row);
            Button openClose = BUTTON(flight.getAvailableSeat() == 0 ? FieldValue.FULL : FieldValue.OPEN);
            gridPane.add(openClose, 9, row);
            
            if(flight.getAvailableSeat() != 0)
            {
                openClose.setStyle(GREEN());
                
                Button rsvp = BUTTON(FieldValue.CLICK);
                rsvp.setStyle(CLICK_ME());
                
                gridPane.add(rsvp, 10, row);
                rsvp.setOnAction(e -> controller.makeReservationFromSE(flight));
            }
            else openClose.setStyle(RED());
            
            
            row++;
        }
        
        gridPane.add(new Label(), 0, ++row);
        
        Button button = new Button(FieldValue.HOME); button.setMinWidth(FieldValue.HOME_BTN_WIDTH);
        button.setStyle(HOME_STYLE());
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
        
        gridPane.add(AIRLINE_RESULTS_HEADER_LABEL(airline.toUpperCase()), 3, row++, 7, 1);
        
        gridPane.add(new Label(), 0, row++);
        gridPane.add(new Label(), 0, row++);
        
        for(int i = 0; i < AIRLINE_SEARCH_FLIGHT_HEADERS().getChildren().size(); i++)
            gridPane.add(AIRLINE_SEARCH_FLIGHT_HEADERS().getChildren().get(i), i, row);
        row++;
        
        for(int i = 0; i < AIRLINE_SEARCH_FLIGHT_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(Flight flight : flights)
        {
            Button b1 = BUTTON(flight.getFlightCode()); b1.setStyle(GENERAL_BTN_STYLE());
            Button b2 = BUTTON(flight.getSource().getAirportName()); b2.setStyle(GENERAL_BTN_STYLE());
            Button b3 = BUTTON(flight.getSource().getDate().toString()
                                     .concat(" ").concat(flight.getSource().getTime()));
            b3.setStyle(GENERAL_BTN_STYLE());
            Button b4 = BUTTON(flight.getDestination().getAirportName()); b4.setStyle(GENERAL_BTN_STYLE());
            Button b5 = BUTTON(flight.getDestination().getDate().toString()
                                     .concat(" ").concat(flight.getDestination().getTime()));
            b5.setStyle(GENERAL_BTN_STYLE());
            Button b6 = BUTTON(flight.getAirplane().getAirPlaneName()); b6.setStyle(GENERAL_BTN_STYLE());
            Button b7 = BUTTON(FieldValue.$.concat(flight.getFare().toString())); b7.setStyle(GENERAL_BTN_STYLE());
            Button b8 = BUTTON(flight.getAvailableSeat().toString()); b8.setStyle(GENERAL_BTN_STYLE());
            Button b9 = BUTTON(flight.getAvailableSeat() == 0 ? FieldValue.FULL : FieldValue.OPEN);
            
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
                b9.setStyle(GREEN());
                Button rsvp = BUTTON(FieldValue.CLICK);
                rsvp.setStyle(CLICK_ME());
                gridPane.add(rsvp, 9, row);
                rsvp.setOnAction(e -> controller.makeReservationByAirline(flight));
            }
            else b9.setStyle(RED());
            row++;
        }
        
        gridPane.add(new Label(), 0, ++row);
        
        Button button = new Button(FieldValue.HOME);
        button.setMinWidth(FieldValue.HOME_BTN_WIDTH);
        button.setStyle(HOME_STYLE());
        button.setOnAction(e -> controller.handleAirlineGui(airline));
        
        gridPane.add(button, 4, ++row, 7, 1);
        
        return gridPane;
    }
    
    public GridPane ui_flightsToBeCanceledByAirline(String airline, Set<Flight> flights, Stage stage)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.BASELINE_CENTER);
        gridPane.setHgap(8);
        gridPane.setVgap(5);
        
        int row = 0;
        
        gridPane.add(new Label(), 0, row++);
        gridPane.add(new Label(), 0, row++);
        
        Label label1 = new Label(FieldValue.ALL_FLIGHTS
                .concat(airline.toUpperCase()).concat(" of ").concat(Configuration.GET_CURRENT_DATE().toString()));
        label1.setStyle(GENERAL_BTN_STYLE());
        gridPane.add(label1, 3, row++, 6, 1);
        
        
        gridPane.add(new Label(), 0, row++);
        gridPane.add(new Label(), 0, row++);
        
        for(int i = 0; i < ADMIN_CANCEL_FLIGHT_HEADERS().getChildren().size(); i++)
            gridPane.add(ADMIN_CANCEL_FLIGHT_HEADERS().getChildren().get(i), i, row);
        row++;
        
        for(int i = 0; i < ADMIN_CANCEL_FLIGHT_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(Flight flight : flights)
        {
            Button b1 = BUTTON(flight.getFlightCode());
            b1.setStyle(GENERAL_BTN_STYLE());
            Button b2 = BUTTON(flight.getAirplane().getAirPlaneName());
            b2.setStyle(GENERAL_BTN_STYLE());
            Button b3 = BUTTON(flight.getSource().getAirportName());
            b3.setStyle(GENERAL_BTN_STYLE());
            Button b4 = BUTTON(flight.getSource().getDate().toString()
                                     .concat(" ").concat(flight.getSource().getTime()));
            b4.setStyle(GENERAL_BTN_STYLE());
            Button b5 = BUTTON(flight.getDestination().getAirportName());
            b5.setStyle(GENERAL_BTN_STYLE());
            Button b6 = BUTTON(flight.getDestination().getDate().toString()
                                     .concat(" ").concat(flight.getDestination().getTime()));
            b6.setStyle(GENERAL_BTN_STYLE());
            Button b7 = BUTTON(flight.getCustomers().size()+"");
            b7.setStyle(GENERAL_BTN_STYLE());
            
            Button b8 = BUTTON(flight.getStatus());
            
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
                b8.setStyle(GREEN());
                
                Button toCancel = BUTTON(FieldValue.CLICK);
                toCancel.setStyle(CLICK_ME());
                
                gridPane.add(toCancel, 8, row);
                toCancel.setOnAction(e ->
                {
                    if(flight.getStatus().equalsIgnoreCase(FieldValue.ON_TIME))
                    {
                        controller.cancelFlight(flight.getFlightId(), airline, stage);
                    }
                });
            }
            else b8.setStyle(RED());
            row++;
        }
        
        return gridPane;
    }
    
    public HBox sortSection(Set<Flight> flights)
    {
        HBox hBox = new HBox();
        
        Button fare = BUTTON(FieldValue.FARE);
        fare.setStyle(SORT_FARE());
        Button airline = BUTTON(FieldValue.AIRLINE);
        airline.setStyle(SORT_AIRLINE());
        
        Label label = SORT_LABEL();
        label.setPadding(new Insets(5, 5, 5, 5));
        
        hBox.getChildren().addAll(label, new Label(" "), fare, new Label(" "), airline);
        
        fare.setOnAction(e ->
        {
            VBox vBox = new VBox();
            vBox.getChildren().addAll(ui_globalSearchResults(SORT_BY_FARE(flights)));
            setSearchResultsInCenter(vBox);
        });
        
        airline.setOnAction(e ->
        {
            VBox vBox = new VBox();
            vBox.getChildren().addAll(ui_globalSearchResults(SORT_BY_AIRLINE(flights)));
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
        
        for(int i = 0; i < RESERVATION_HEADERS().getChildren().size(); i++)
            gridPane.add(RESERVATION_HEADERS().getChildren().get(i), i, row);
        row++;
        
        for(int i = 0; i < RESERVATION_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(Reservation reservation : reservations)
        {
            Button b1 = BUTTON(reservation.getFlight().getFlightCode());
            b1.setStyle(GENERAL_BTN_STYLE());
            Button b2 = BUTTON(reservation.getFlight().getSource().getAirportName());
            b2.setStyle(GENERAL_BTN_STYLE());
            Button b3 = BUTTON(reservation.getFlight().getDestination().getAirportName());
            b3.setStyle(GENERAL_BTN_STYLE());
            Button b4 = BUTTON(reservation.getFlight().getAirLine().getAirlineName());
            b4.setStyle(GENERAL_BTN_STYLE());
            Button b5 = BUTTON(reservation.getFlight().getAirplane().getAirPlaneName());
            b5.setStyle(GENERAL_BTN_STYLE());
            Button b6 = BUTTON(FieldValue.$.concat(reservation.getFlight().getFare().toString()));
            b6.setStyle(GENERAL_BTN_STYLE());
            Button b7 = BUTTON(reservation.getRsvpDate().toString());
            b7.setStyle(GENERAL_BTN_STYLE());
            
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
    
    public Menu ui_airports()
    {
        Menu airportsMenu = new Menu(FieldValue.AIRPORT_LABEL);
        
        MenuItem airport1 = new MenuItem(FieldValue.AP_JFK_NAME);
        airport1.setOnAction(e -> controller.handleAirportScreen(FieldValue.AP_JFK));
        
        MenuItem airport2 = new MenuItem(FieldValue.AP_LAX_NAME);
        airport2.setOnAction(e -> controller.handleAirportScreen(FieldValue.AP_LA));
        
        MenuItem airport3 = new MenuItem(FieldValue.AP_MI_NAME);
        airport3.setOnAction(e -> controller.handleAirportScreen(FieldValue.AP_MI));
        
        MenuItem airport4 = new MenuItem(FieldValue.AP_BOSTON_NAME);
        airport4.setOnAction(e -> controller.handleAirportScreen(FieldValue.AP_BOSTON));
        
        MenuItem airport5 = new MenuItem(FieldValue.AP_GEORGIA_NAME);
        airport5.setOnAction(e -> controller.handleAirportScreen(FieldValue.AP_GEORGIA));
        
        MenuItem airport6 = new MenuItem(FieldValue.AP_NEWARK_NAME);
        airport6.setOnAction(e -> controller.handleAirportScreen(FieldValue.AP_NEWARK));
        
        airportsMenu.getItems().addAll(airport1, SEPARATOR(), airport2,
                SEPARATOR(), airport3, SEPARATOR(), airport4,
                SEPARATOR(), airport5, SEPARATOR(), airport6, SEPARATOR());
        
        return airportsMenu;
    }
    
    public Menu ui_airlines()
    {
        Menu airlineMenu = new Menu(FieldValue.AIRLINE_LABEL);
        
        MenuItem airline1 = new MenuItem(FieldValue.AR_AMERICAN);
        airline1.setOnAction(e -> controller.handleAirlineGui(FieldValue.AR_AMERICAN));
        
        MenuItem airline2 = new MenuItem(FieldValue.AR_JET_BLUE);
        airline2.setOnAction(e -> controller.handleAirlineGui(FieldValue.AR_JET_BLUE));
        
        MenuItem airline3 = new MenuItem(FieldValue.AR_DELTA);
        airline3.setOnAction(e -> controller.handleAirlineGui(FieldValue.AR_DELTA));
        
        airlineMenu.getItems().addAll(airline1, SEPARATOR(), airline2,
                SEPARATOR(), airline3, SEPARATOR());
        
        return airlineMenu;
    }
    
    public Menu ui_login(MenuItem... menuItems)
    {
        Menu loginMenu = new Menu(FieldValue.CUSTOMER);
        for(MenuItem menuItem : menuItems)
        {
            loginMenu.getItems().add(menuItem);
            loginMenu.getItems().add(SEPARATOR());
        }
        return loginMenu;
    }
    
    public Menu ui_admins(MenuItem... menuItems)
    {
        Menu menu = new Menu(FieldValue.ADMINS);
        for(MenuItem menuItem : menuItems)
        {
            menu.getItems().add(menuItem);
            menu.getItems().add(SEPARATOR());
        }
        return menu;
    }
    
    public MenuItem ui_globalAdminLoginItem()
    {
        MenuItem global = new MenuItem(FieldValue.SE_ADMIN_LABEL);
        global.setOnAction(e -> ui_globalAdminLogin());
        return global;
    }
    
    public MenuItem ui_customerLoginItem()
    {
        MenuItem customerLogin = new MenuItem(FieldValue.CUSTOMER_LOGIN_LABEL);
        customerLogin.setOnAction(e -> ui_launchLoginForAllByKey(ui_loginContainer(FieldValue.CUSTOMER_LOGIN_LABEL),
                FieldValue.LOGIN_VIEW_KEY_CUSTOMER));
        return customerLogin;
    }
    
    public MenuItem ui_airlineAdminLoginItem(String label)
    {
        MenuItem admin = new MenuItem(label);
        admin.setOnAction(e -> ui_launchLoginForAllByKey(ui_loginContainer(FieldValue.ADMIN_LOGIN_FOR.concat(label)), label));
        return admin;
    }
    
    public MenuItem ui_newCustomerItem()
    {
        final MenuItem newCustomerMenu = new MenuItem(FieldValue.NEW_CUST_LABEL);
        newCustomerMenu.setOnAction(e -> ui_newCustomerRegistration());
        return newCustomerMenu;
    }
    
    public MenuBar ui_menuBar(Menu... menus)
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
    
    public Menu ui_searchEngine()
    {
        Menu menu = new Menu(FieldValue.SEARCH_ENGINE);
        MenuItem menuItem = new MenuItem(FieldValue.SEARCH_ENGINE);
        menu.setOnAction(e -> controller.takeLoggedUserToSearchEngine());
        menu.getItems().addAll(menuItem);
        return menu;
    }
    
    public MenuBar ui_homeMenuBar()
    {
        return ui_menuBar(ui_login(ui_customerLoginItem(), ui_newCustomerItem()),
                ui_airports(),
                ui_airlines(),
                ui_admins(ui_globalAdminLoginItem(),
                        ui_airlineAdminLoginItem(FieldValue.AR_AMERICAN),
                        ui_airlineAdminLoginItem(FieldValue.AR_DELTA),
                        ui_airlineAdminLoginItem(FieldValue.AR_JET_BLUE)));
    }
    
    public void ui_displayRSVPsByAirline(String airlineName, Set<Reservation> reservations)
    {
        Stage stage = new Stage();
        stage.setTitle(FieldValue.RSVP_TITLE.concat(airlineName.toUpperCase()));
        stage.setHeight(600);
        stage.setWidth(1300);
        
        Scene scene = new Scene(ui_displayAllRSVPsByAirline(reservations));
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    public HBox logoutHBox()
    {
        HBox outContainer = new HBox();
        outContainer.setAlignment(Pos.BOTTOM_CENTER);
        Button logout = new Button(FieldValue.LOGOUT_LABEL);
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
        label1.setStyle(GENERAL_BTN_STYLE());
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
        out.setOnAction(e -> controller.logoutAdmin());
        
        setTop(ui_menuBar(ui_airports()));
        setCenter(superV);
    }
    
    public VBox ui_customerInfo(Customer customer)
    {
        return ui_customerCenterContainer(customer);
    }
    
    public void ui_customerLogin()
    {
        ui_launchLoginForAllByKey(ui_loginContainer(FieldValue.CUSTOMER_LOGIN_LABEL), FieldValue.LOGIN_VIEW_KEY_CUSTOMER);
    }
    
    public void ui_globalAdminLogin()
    {
        ui_launchLoginForAllByKey(ui_loginContainer(FieldValue.SE_ADMIN_LOGIN_LABEL), FieldValue.LOGIN_VIEW_KEY_GLOBAL);
    }
    
    private void ui_launchLoginForAllByKey(GridPane gridPane, String key)
    {
        Stage stage = new Stage();
        Scene scene = new Scene(gridPane, FieldValue.LOGIN_WINDOW_WIDTH, FieldValue.LOGIN_WINDOW_HEIGHT);
        
        TextField username = (TextField) GET_NODE
                (FieldValue.USERNAME_RAW, FieldValue.USERNAME_COL, gridPane);
        TextField pass = (TextField) GET_NODE
                (FieldValue.PASSWORD_RAW, FieldValue.PASSWORD_COL, gridPane);
        
        HBox hBox = (HBox)GET_NODE
                (FieldValue.LOGIN_SUBMIT_RAW, FieldValue.LOGIN_SUBMIT_COL, gridPane);
        Button submit = (Button) hBox.getChildren().get(0);
        
        submit.setOnAction(e ->
        {
            stage.close();
            
            if(key.equalsIgnoreCase(FieldValue.LOGIN_VIEW_KEY_GLOBAL))
            {
                controller.handleGlobalAdminLogin(username.getText(), pass.getText());
            }
            else if(key.equalsIgnoreCase(FieldValue.LOGIN_VIEW_KEY_CUSTOMER))
            {
                controller.handleCustomerLogin(username.getText(), pass.getText());
            }
            else if(key.equalsIgnoreCase(FieldValue.AR_AMERICAN)
                    || key.equalsIgnoreCase(FieldValue.AR_JET_BLUE)
                    || key.equalsIgnoreCase(FieldValue.AR_DELTA))
            {
                controller.handleAirlineAdminLogin(key, username.getText(), pass.getText());
            }
        });
        stage.setTitle(FieldValue.LOGIN);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.showAndWait();
    }
    
    private HBox ui_customerNameHBox(Customer customer)
    {
        HBox name = new HBox();
        name.setAlignment(Pos.TOP_CENTER);
        Label label = new Label(FieldValue.USERNAME + customer.getFirstName() + " " + customer.getLastName());
        label.setStyle(NAME_HEADER_STYLE());
        name.getChildren().add(label);
        return name;
    }
    
    private VBox ui_customerCenterContainer(Customer customer)
    {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        
        vBox.getChildren().add(new Label());
        vBox.getChildren().add(new Label());
        vBox.getChildren().add(ui_customerNameHBox(customer));
        
        vBox.getChildren().add(new Label());
        Label label = new Label(FieldValue.RSVP_FLIGHT);
        label.setStyle(GENERAL_BTN_STYLE());
        vBox.getChildren().add(label);
        vBox.getChildren().add(new Label());
        vBox.getChildren().add(ui_populateRSVPsFlightsForCustomer(customer));
        vBox.getChildren().add(new Label());
        vBox.getChildren().add(new Label());
        
        HBox hBox = logoutHBox();
        Button logout = (Button)hBox.getChildren().get(0);
        logout.setStyle(LOGOUT_STYLE());
        logout.setOnAction(e -> controller.logoutCustomer());
        
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(new Label());
        vBox.getChildren().add(new Label());
        
        return vBox;
    }
    
    public GridPane ui_populateRSVPsFlightsForCustomer(Customer customer)
    {
        Set<Flight> flights = customer.getFlights();
        Set<Reservation> reservations = customer.getReservations();
        
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(12);
        gridPane.setVgap(8);
        
        int row = 0;
        Label rsvpLabel = new Label(FieldValue.ALL_UPCOMING_RSVP);
        rsvpLabel.setStyle(GENERAL_BTN_STYLE());
        
        gridPane.add(rsvpLabel, 0, row++, 4, 1);
        
        for(int i = 0; i < CUSTOMER_RSVP_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(int i = 0; i < CUSTOMER_RSVP_HEADERS().getChildren().size(); i++)
            gridPane.add(CUSTOMER_RSVP_HEADERS().getChildren().get(i), i, row);
        row++;
        
        for(int i = 0; i < CUSTOMER_RSVP_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(Reservation reservation : reservations)
        {
            Button b1 = BUTTON(reservation.getFlight().getAirLine().getAirlineName());
            b1.setStyle(GENERAL_BTN_STYLE());
            Button b2 = BUTTON(reservation.getFlight().getAirplane().getAirPlaneName());
            b2.setStyle(GENERAL_BTN_STYLE());
            Button b3 = BUTTON(reservation.getFlight().getFlightCode());
            b3.setStyle(GENERAL_BTN_STYLE());
            Button b4 = BUTTON(reservation.getFlight().getSource().getAirportName());
            b4.setStyle(GENERAL_BTN_STYLE());
            
            Button b5 = BUTTON(reservation.getFlight().getSource().getDate().toString()
                                          .concat(" ").concat(reservation.getFlight().getSource().getTime()));
            b5.setStyle(GENERAL_BTN_STYLE());
            
            Button b6 = BUTTON(reservation.getFlight().getDestination().getAirportName());
            b6.setStyle(GENERAL_BTN_STYLE());
            
            Button b7 = BUTTON(reservation.getFlight().getDestination().getDate().toString()
                                          .concat(" ").concat(reservation.getFlight().getDestination().getTime()));
            b7.setStyle(GENERAL_BTN_STYLE());
            
            
            Button b8 = BUTTON(FieldValue.$.concat(reservation.getFlight().getFare().toString()));
            b8.setStyle(GENERAL_BTN_STYLE());
            Button b9 = BUTTON(reservation.getRsvpDate().toString());
            b9.setStyle(GENERAL_BTN_STYLE());
            
            Button b10 = BUTTON(reservation.getStatus());
            if(reservation.getStatus().equalsIgnoreCase(FieldValue.CANCELED))
                b10.setStyle(RED());
            else b10.setStyle(GREEN());
            
            gridPane.add(b1, 0, row);
            gridPane.add(b2, 1, row);
            gridPane.add(b3, 2, row);
            gridPane.add(b4, 3, row);
            gridPane.add(b5, 4, row);
            gridPane.add(b6, 5, row);
            gridPane.add(b7, 6, row);
            gridPane.add(b8, 7, row);
            gridPane.add(b9, 8, row);
            gridPane.add(b10, 9, row);
            
            if(FieldValue.ACTIVE.equalsIgnoreCase(reservation.getStatus()))
            {
                Button cancel = BUTTON(FieldValue.CLICK);
                cancel.setStyle(CLICK_ME());
                gridPane.add(cancel, 10, row);
                cancel.setOnAction(e -> controller.cancelReservationByCustomer(customer, reservation));
            }
            row++;
        }
        
        for(int i = 0; i < CUSTOMER_RSVP_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(int i = 0; i < CUSTOMER_PAST_FLIGHTS_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        Label flightsLabel = new Label(FieldValue.PAST_FLIGHTS);
        
        flightsLabel.setStyle(GENERAL_BTN_STYLE());
        
        gridPane.add(flightsLabel, 2, row, 4, 1);
        row++;
        
        for(int i = 0; i < CUSTOMER_PAST_FLIGHTS_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        int j = 2;
        for(int i = 0; i < CUSTOMER_PAST_FLIGHTS_HEADERS().getChildren().size(); i++)
            gridPane.add(CUSTOMER_PAST_FLIGHTS_HEADERS().getChildren().get(i), j++, row);
        row++;
        
        for(int i = 0; i < CUSTOMER_PAST_FLIGHTS_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(Flight flight : flights)
        {
            Button b1 = BUTTON(flight.getAirLine().getAirlineName());
            b1.setStyle(GENERAL_BTN_STYLE());
            Button b2 = BUTTON(flight.getAirplane().getAirPlaneName());
            b2.setStyle(GENERAL_BTN_STYLE());
            Button b3 = BUTTON(flight.getFlightCode());
            b3.setStyle(GENERAL_BTN_STYLE());
            Button b4 = BUTTON(flight.getSource().getAirportName());
            b4.setStyle(GENERAL_BTN_STYLE());
            Button b5 = BUTTON(flight.getDestination().getAirportName());
            b5.setStyle(GENERAL_BTN_STYLE());
            Button b6 = BUTTON(FieldValue.$.concat(flight.getFare().toString()));
            b6.setStyle(GENERAL_BTN_STYLE());
            
            gridPane.add(b1, 2, row);
            gridPane.add(b2, 3, row);
            gridPane.add(b3, 4, row);
            gridPane.add(b4, 5, row);
            gridPane.add(b5, 6, row);
            gridPane.add(b6, 7, row);
            row++;
        }
        
        return gridPane;
    }
    
    public void ui_arrivalWindow(String airportName, Double x, Double y, Set<Flight> flights)
    {
        Stage stage = new Stage();
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        
        VBox container = new VBox(new Label());
        
        container.setAlignment(Pos.TOP_CENTER);
        container.setPadding(new Insets(5, 5, 5, 5));
        
        GridPane gridPane = ui_populateGridForArrival(flights);
        
        Label header = new Label(FieldValue.ARRIVAL_HEADER.concat(airportName.toUpperCase()));
        header.setStyle(GENERAL_BTN_STYLE());
        
        Button refresh = new Button(FieldValue.REFRESH);
        refresh.setStyle(GREEN());
        
        refresh.setOnAction(e -> container.getChildren().set(2,
                ui_populateGridForArrival(controller.refreshAirportScreen(airportName))));
        
        container.getChildren().add(header);
        container.getChildren().add(gridPane);
        container.getChildren().add(refresh);
        
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.setTitle(TITTLE_BY(FieldValue.ARRIVAL_LABEL, airportName));
        stage.setX(x);
        stage.setY(y);
        stage.show();
    }
    
    public GridPane ui_populateGridForArrival(Set<Flight> flights)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(8);
        gridPane.setVgap(13);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        int row = 0;
        
        for(int i = 0; i < ARRIVAL_HEADERS().getChildren().size(); i++)
            gridPane.add(ARRIVAL_HEADERS().getChildren().get(i), i, row);
        row++;
        
        for(int i = 0; i < ARRIVAL_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(Flight flight : flights)
        {
            Button b1 = BUTTON(flight.getFlightCode());  b1.setStyle(GENERAL_BTN_STYLE());
            Button b2 = BUTTON(flight.getAirLine().getAirlineName()); b2.setStyle(GENERAL_BTN_STYLE());
            Button b3 = BUTTON(flight.getAirplane().getAirPlaneName()); b3.setStyle(GENERAL_BTN_STYLE());
            Button b4 = BUTTON(flight.getSource().getAirportName()); b4.setStyle(GENERAL_BTN_STYLE());
            Button b5 = BUTTON(flight.getStatus());
            if(flight.getStatus().equalsIgnoreCase(FieldValue.CANCELED)) b5.setStyle(RED());
            else b5.setStyle(GREEN());
            Button b6 = BUTTON(Configuration.GET_CURRENT_DATE().toString()); b6.setStyle(GENERAL_BTN_STYLE());
            
            gridPane.add(b1, 0, row);
            gridPane.add(b2, 1, row);
            gridPane.add(b3, 2, row);
            gridPane.add(b4, 3, row);
            gridPane.add(b5, 4, row);
            gridPane.add(b6, 5, row);
            row++;
        }
        
        return gridPane;
    }
    
    public GridPane ui_populateGridForDeparture(Set<Flight> flights)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(8);
        gridPane.setVgap(13);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        int row = 0;
        
        for(int i = 0; i < DEPARTURE_HEADERS().getChildren().size(); i++)
            gridPane.add(DEPARTURE_HEADERS().getChildren().get(i), i, row);
        row++;
        
        for(int i = 0; i < DEPARTURE_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(Flight flight : flights)
        {
            Button b1 = BUTTON(flight.getFlightCode());  b1.setStyle(GENERAL_BTN_STYLE());
            Button b2 = BUTTON(flight.getAirLine().getAirlineName()); b2.setStyle(GENERAL_BTN_STYLE());
            Button b3 = BUTTON(flight.getAirplane().getAirPlaneName()); b3.setStyle(GENERAL_BTN_STYLE());
            Button b4 = BUTTON(flight.getDestination().getAirportName()); b4.setStyle(GENERAL_BTN_STYLE());
            Button b5 = BUTTON(flight.getStatus());
            if(flight.getStatus().equalsIgnoreCase(FieldValue.CANCELED)) b5.setStyle(RED());
            else b5.setStyle(GREEN());
            Button b6 = BUTTON(Configuration.GET_CURRENT_DATE().toString()); b6.setStyle(GENERAL_BTN_STYLE());
            
            gridPane.add(b1, 0, row);
            gridPane.add(b2, 1, row);
            gridPane.add(b3, 2, row);
            gridPane.add(b4, 3, row);
            gridPane.add(b5, 4, row);
            gridPane.add(b6, 5, row);
            row++;
        }
        
        return gridPane;
    }
    
    public void ui_departureWindow(String airportName, Double x, Double y, Set<Flight> flights)
    {
        Stage stage = new Stage();
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        
        VBox container = new VBox(new Label());
        
        container.setAlignment(Pos.TOP_CENTER);
        container.setPadding(new Insets(5, 5, 5, 5));
        
        GridPane gridPane = ui_populateGridForDeparture(flights);
        
        Label header = new Label(FieldValue.DEPARTURE_HEADER.concat(airportName.toUpperCase()));
        header.setStyle(GENERAL_BTN_STYLE());
        
        Button refresh = new Button(FieldValue.REFRESH);
        refresh.setStyle(GREEN());
        
        refresh.setOnAction(e -> container.getChildren().set(2,
                ui_populateGridForDeparture(controller.refreshAirportScreen(airportName))));
        
        container.getChildren().add(header);
        container.getChildren().add(gridPane);
        container.getChildren().add(refresh);
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.setTitle(TITTLE_BY(FieldValue.DEPARTURE_LABEL, airportName));
        stage.setX(x);
        stage.setY(y);
        stage.show();
    }
}
