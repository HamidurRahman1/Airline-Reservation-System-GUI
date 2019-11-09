package com.hrs.view;

import com.hrs.configs.Configuration;
import com.hrs.util.Utility;
import com.hrs.view.controller.Controller;
import com.hrs.view.models.Flight;
import com.hrs.view.models.Reservation;
import com.hrs.view.util.FieldValue;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
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

import java.util.Comparator;
import java.util.List;

import static com.hrs.util.Utility.button;

public class View extends Application
{
    private Controller controller;
    
    private Stage primaryStage;
    private Stage arrivalStage;
    private Stage departureStage;
    
    private Scene homeScene;
    private BorderPane homeSceneContainer;
    
    private MenuBar menuBar;
    private GridPane searchBar;
    
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
        
        homeSceneContainer.setTop(menuBar(logins(customerLoginItem(), newCustomerItem(), globalAdminLoginItem()),
                airports(), airlines()));
        
        homeSceneContainer.setCenter(ui_searchBarContainer());
        
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
    
    public VBox Ui_searchBarContainer(String airlineName, String adminLabel)
    {
        VBox superV = new VBox();
        
        VBox admin = getAdminVBox(airlineName, adminLabel);
        
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.BASELINE_CENTER);
    
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        
        Label searchLabel = new Label("Find Flights for ".concat(airlineName.toUpperCase()));
        searchLabel.setFont(Font.font(FieldValue.FONT_MONACO, FontWeight.BOLD, FieldValue.FONT_SIZE_17));
        gridPane.add(searchLabel, 0,0,2,1);
        GridPane.setHalignment(searchLabel, HPos.CENTER);
        GridPane.setMargin(searchLabel, new Insets(20, 0,20,0));
    
        TextField searchBar = new TextField();
        gridPane.add(searchBar, 1,1);
        GridPane.setHalignment(searchBar, HPos.CENTER);
        searchBar.setMinHeight(FieldValue.SEARCH_BAR_HEIGHT);
        searchBar.setMinWidth(FieldValue.SEARCH_BAR_WIDTH);
        
        superV.getChildren().addAll(admin, gridPane, new GridPane());
        superV.setAlignment(Pos.TOP_CENTER);
        
        return superV;
    }
    
    private VBox getAdminVBox(String airline, String adminLabel)
    {
        VBox admin = new VBox();
        admin.setAlignment(Pos.TOP_CENTER);
        Button button = new Button(adminLabel);
        admin.getChildren().addAll(new Label(), new Label(), button);
        button.setOnAction(e ->
        {
            controller.adminLogin(airline);
        });
        return admin;
    }
    
    public GridPane ui_searchBarContainer()
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.BASELINE_CENTER);
    
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
    
        Label searchLabel = new Label(FieldValue.SEARCH);
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
    
    public GridPane ui_loginContainer()
    {
        GridPane grid = new GridPane();
        
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
    
        Label custLabel = new Label(FieldValue.LOGIN_LABEL);
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
    
    public void ui_customerHome(MenuBar menuBar, VBox center)
    {
        Scene scene = null;
        BorderPane borderPane = new BorderPane();
        
        borderPane.setTop(menuBar);
        borderPane.setCenter(center);
        
        scene = new Scene(borderPane);
        primaryStage.setScene(scene);
    }
    
    public MenuBar getMenuBar()
    {
        return menuBar;
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
    
    public void switchScene(Scene scene)
    {
        this.primaryStage.setScene(scene);
    }
    
    public GridPane ui_globalSearchResults(List<Flight> flights)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.BASELINE_CENTER);
        gridPane.setHgap(8);
        gridPane.setVgap(5);
    
        gridPane.add(new Label(), 0, 0);
        gridPane.add(new Label(), 0, 1);
        
        gridPane.add(sortSection(flights), 0, 2);
        
        gridPane.add(new Label(), 0, 3);
        gridPane.add(new Label(), 0, 4);
        
        for(int i = 0; i < Utility.flightHeaders().getChildren().size(); i++)
        {
            gridPane.add(Utility.flightHeaders().getChildren().get(i), i, 5);
        }
    
        for(int i = 0; i < Utility.flightHeaders().getChildren().size(); i++)
        {
            gridPane.add(new Label(), i, 6);
        }
        
        int j = 7;
    
        for(int i = 0; i < flights.size(); i++)
        {
            gridPane.add(button(flights.get(i).flightName), 0, j);
            gridPane.add(button(flights.get(i).source), 1, j);
            gridPane.add(button(flights.get(i).destination), 2, j);
            gridPane.add(button(flights.get(i).airline), 3, j);
            gridPane.add(button(flights.get(i).date), 4, j);
            gridPane.add(button(flights.get(i).fare), 5, j);
            Button status = button(flights.get(i).status);
            gridPane.add(status, 6, j);
            if("open".equalsIgnoreCase(flights.get(i).status))
            {
                final Integer id = flights.get(i).flightId;
                status.setOnAction(e ->
                {
                    controller.eventMakeReservation(id);
                });
            }
            j++;
        }
        
        gridPane.add(new Label(), 0, ++j);
        
        Button button = new Button("Back/Home");
        button.setOnAction(e -> this.setCenter(ui_searchBarContainer()));
        
        gridPane.add(button, 0, ++j);
        
        return gridPane;
    }
    
    public HBox sortSection(List<Flight> flights)
    {
        HBox hBox = new HBox();
        
        RadioButton fare = new RadioButton("Fare");
        RadioButton airline = new RadioButton("Airline");
        
        ToggleGroup toggleGroup = new ToggleGroup();
        fare.setToggleGroup(toggleGroup);
        airline.setToggleGroup(toggleGroup);
        
        hBox.getChildren().addAll(new Label("Sort results by: "), fare, new Label(), airline);
    
        toggleGroup.selectedToggleProperty().addListener(new ChangeListener <Toggle>()
        {
            public void changed(ObservableValue <? extends Toggle> ob, Toggle o, Toggle n)
            {
                RadioButton rb = (RadioButton)toggleGroup.getSelectedToggle();
                if (rb != null)
                {
                    if(rb.getText().equalsIgnoreCase("Fare"))
                    {
                        flights.sort(Comparator.comparing(Flight::getFare));
                        setSearchResultsInCenter(ui_globalSearchResults(flights));
                    }
                    else
                    {
                        flights.sort(Comparator.comparing(Flight::getAirline));
                        setSearchResultsInCenter(ui_globalSearchResults(flights));
                    }
                }
            }
        });
    
        return hBox;
    }
    
    public GridPane ui_reservationResults(List<Reservation> reservations)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(8);
        gridPane.setVgap(5);
        
        for(int i = 0; i < Utility.reservationHeaders().getChildren().size(); i++)
        {
            gridPane.add(Utility.reservationHeaders().getChildren().get(i), i, 0);
        }
    
        for(int i = 0; i < Utility.reservationHeaders().getChildren().size(); i++)
        {
            gridPane.add(new Label(), i, 1);
        }
        
        int j = 2;
        
        for(int i = 0; i < reservations.size(); i++)
        {
            gridPane.add(button(reservations.get(i).getFlight().getFlightName()), 0, j);
            gridPane.add(button(reservations.get(i).getFlight().getSource()), 1, j);
            gridPane.add(button(reservations.get(i).getFlight().getDestination()), 2, j);
            gridPane.add(button(reservations.get(i).getFlight().getAirline()), 3, j);
            gridPane.add(button(reservations.get(i).getFlight().getDate()), 4, j);
            gridPane.add(button(reservations.get(i).getFlight().getFare()), 5, j);
            gridPane.add(button(reservations.get(i).getLocalDate()), 6, j);
            j++;
        }
        
        return gridPane;
    }
    
    public void setSearchResultsInCenter(GridPane gridPane)
    {
        this.homeSceneContainer.setCenter(gridPane);
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
        Menu loginMenu = new Menu(FieldValue.LOGIN_LABEL);
        loginMenu.getItems().addAll(menuItems);
        return loginMenu;
    }
    
    public MenuItem globalAdminLoginItem()
    {
        MenuItem global = new MenuItem(FieldValue.SE_ADMIN_LABEL);
        global.setOnAction(e -> controller.launchLoginForGlobalAdmin(ui_loginContainer()));
        return global;
    }
    
    public MenuItem customerLoginItem()
    {
        MenuItem customerLogin = new MenuItem(FieldValue.CUSTOMER_LOGIN_LABEL);
        customerLogin.setOnAction(e -> controller.launchLoginForCustomer(ui_loginContainer()));
        return customerLogin;
    }
    
    public MenuItem airlineAdminLoginItem()
    {
        MenuItem customerLogin = new MenuItem(FieldValue.AIR_ADMIN_LABEL);
        customerLogin.setOnAction(e -> controller.launchLoginForAirlineAdmin(ui_loginContainer()));
        return customerLogin;
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
        return menuBar(logins(customerLoginItem(), newCustomerItem(), globalAdminLoginItem()), airports(), airlines());
    }
}
