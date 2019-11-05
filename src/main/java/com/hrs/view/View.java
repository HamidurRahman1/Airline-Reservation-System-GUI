package com.hrs.view;

import com.hrs.configs.Configuration;
import com.hrs.view.controller.Controller;
import com.hrs.util.Utility;

import com.hrs.view.style.CSSStyle;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View extends Application
{
    private Controller controller;
    private Scene homeScene;
    private BorderPane homeSceneContainer;
    private Pane center;

    public void start(Stage primaryStage) throws Exception
    {
        initialize();
        
        this.homeScene = new Scene(this.homeSceneContainer, Utility.HOME_SCENE_WIDTH, Utility.HOME_SCENE_HEIGHT);
        
        primaryStage.setTitle(Utility.APP_TITLE);
        primaryStage.setMinWidth(Utility.HOME_WINDOW_WIDTH);
        primaryStage.setMinHeight(Utility.HOME_WINDOW_HEIGHT);
        primaryStage.setScene(this.homeScene);
        primaryStage.show();
    }
    
    private void initialize()
    {
        this.homeSceneContainer = new BorderPane();
        this.center = new Pane();
        this.homeSceneContainer.setCenter(center);
        this.homeSceneContainer.setLeft(initialize_leftSide());
        this.controller = Configuration.getController();
        this.controller.setView(this);
    }
    
    private MenuBar initialize_top()
    {
        MenuBar menuBar = new MenuBar();
    
        final Menu about = new Menu("About");
        about.setStyle("");
        final Menu help = new Menu("Help");
        help.setStyle("");
        final MenuItem aboutMI = new MenuItem("About");
        final MenuItem helpMI = new MenuItem("Help");
    
        about.getItems().add(aboutMI);
        help.getItems().add(helpMI);
        menuBar.getMenus().addAll(about, help);
    
        aboutMI.setOnAction(e -> controller.about());
        helpMI.setOnAction(e -> controller.help());
        
        return menuBar;
    }
    
    private VBox initialize_leftSide()
    {
        VBox leftSide = new VBox();
    
        Button customerLogin = new Button(Utility.CUSTOMER_LOGIN_LABEL);
        customerLogin.setStyle("");
        customerLogin.setOnAction(e -> this.controller.launchCustomerLogin());
    
        Button airlines = new Button(Utility.AIRPORT_LABEL+"s");
        airlines.setStyle("");
        airlines.setOnAction(e -> this.controller.launchCustomerLogin());
    
        Button airports = new Button(Utility.AIRLINE_LABEL+"s");
        airports.setStyle("");
        airports.setOnAction(e -> this.controller.launchCustomerLogin());
        
        leftSide.setAlignment(Pos.BASELINE_LEFT);
        leftSide.setStyle(CSSStyle.VBoxCSS());
        leftSide.getChildren().addAll(customerLogin, airlines, airports);
        
        return leftSide;
    }
}
