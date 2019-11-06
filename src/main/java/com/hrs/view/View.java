package com.hrs.view;

import com.hrs.configs.Configuration;
import com.hrs.util.Utility;
import com.hrs.view.controller.Controller;
import com.hrs.view.util.FieldValue;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.stage.Screen;
import javafx.stage.Stage;

public class View extends Application
{
    private Controller controller;
    private Stage primaryStage;
    private Scene homeScene;
    private BorderPane homeSceneContainer;
    
    private MenuBar menuBarUI;
    private GridPane searchBarUI;
    
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
    
        Utility.setOnCenter(primaryStage);
        
        primaryStage.show();
    }
    
    private void initialize()
    {
        homeSceneContainer = new BorderPane();
        
        menuBarUI = ui_menuBar();
        homeSceneContainer.setTop(menuBarUI);
    
        searchBarUI = ui_searchBarContainer();
        homeSceneContainer.setCenter(searchBarUI);
        
        homeSceneContainer.setLeft(new VBox());
        homeSceneContainer.setRight(new VBox());
        homeSceneContainer.setBottom(new HBox());
        
        controller = Configuration.getController();
        controller.setView(this);
    }
    
    private MenuBar ui_menuBar()
    {
        MenuBar menuBar = new MenuBar();
    
        final Menu loginMenu = new Menu(FieldValue.LOGIN_LABEL);
        final Menu airportsMenu = new Menu(FieldValue.AIRPORT_LABEL);
        final Menu airlinesMenu = new Menu(FieldValue.AIRLINE_LABEL);
    
        final MenuItem customerLogin = new MenuItem(FieldValue.CUSTOMER_LOGIN_LABEL);
        customerLogin.setOnAction(e -> controller.eventLaunchLogin(ui_loginContainer()));
    
        final MenuItem newCustomer = new MenuItem(FieldValue.NEW_CUST_LABEL);
        newCustomer.setOnAction(e -> controller.eventLaunchNewCustomer(ui_newCustomerContainer()));
        
        final MenuItem airport1 = new MenuItem(FieldValue.AIRPORT1);
        airport1.setOnAction(e -> controller.eventLaunchAirport(airport1.getText().split(" ")[2]));
        
        final MenuItem airport2 = new MenuItem(FieldValue.AIRPORT2);
        airport2.setOnAction(e -> controller.eventLaunchAirport(airport2.getText().split(" ")[2]));
        
        final MenuItem airport3 = new MenuItem(FieldValue.AIRPORT3);
        airport3.setOnAction(e -> controller.eventLaunchAirport(airport3.getText().split(" ")[2]));
        
        final MenuItem airline1 = new MenuItem(FieldValue.AIRLINE1);
        airline1.setOnAction(e -> controller.eventHelp());
        final MenuItem airline2 = new MenuItem(FieldValue.AIRLINE2);
        airline2.setOnAction(e -> controller.eventHelp());
        final MenuItem airline3 = new MenuItem(FieldValue.AIRLINE3);
        airline3.setOnAction(e -> controller.eventHelp());
        
        airlinesMenu.getItems().addAll(airline1, airline2, airline3);
        airportsMenu.getItems().addAll(airport1, airport2, airport3);
        loginMenu.getItems().addAll(customerLogin, newCustomer);
        menuBar.getMenus().addAll(loginMenu, airportsMenu, airlinesMenu);
        
        return menuBar;
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
                    controller.eventSearchBar(searchBar.getText());
                    searchBar.clear();
                    gridPane.requestFocus();
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
    
    public BorderPane getHomeSceneContainer()
    {
        return homeSceneContainer;
    }
    
    public void setHomeSceneContainer(BorderPane homeSceneContainer)
    {
        this.homeSceneContainer = homeSceneContainer;
    }
    
    //    private VBox initialize_leftSide()
    //    {
    //        VBox leftSide = new VBox();
    //
    //        Button customerLogin = new Button(FieldValue.LOGIN_LABEL);
    //        customerLogin.setMaxSize(FieldValue.BUTTON_HEIGHT_D, FieldValue.BUTTON_WIDTH_D);
    //        customerLogin.setOnAction(e -> controller.eventLaunchCustomerLogin());
    //
    //        Button airlines = new Button(FieldValue.AIRPORT_LABEL+"s");
    //        airlines.setMaxSize(FieldValue.BUTTON_HEIGHT_D, FieldValue.BUTTON_WIDTH_D);
    //
    //        Button airports = new Button(FieldValue.AIRLINE_LABEL+"s");
    //        airports.setMaxSize(FieldValue.BUTTON_HEIGHT_D, FieldValue.BUTTON_WIDTH_D);
    //
    //        Button currentDate = new Button(FieldValue.CURRENT_DATE);
    //        currentDate.setMaxSize(FieldValue.BUTTON_HEIGHT_D, FieldValue.BUTTON_WIDTH_D);
    //        currentDate.setOnAction(e -> controller.eventGetDate());
    //
    //        leftSide.getChildren().addAll(new Label(), customerLogin, airlines, airports, currentDate);
    //
    //        leftSide.setSpacing(20.0);
    //        leftSide.setAlignment(Pos.TOP_CENTER);
    //
    //        return leftSide;
    //    }
    
}
