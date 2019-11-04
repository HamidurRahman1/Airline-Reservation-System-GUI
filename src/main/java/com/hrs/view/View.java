package com.hrs.view;

import com.hrs.controller.Controller;
import com.hrs.util.Utility;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class View extends Application
{
    private Controller controller;

    public void start(Stage primaryStage) throws Exception
    {
        Stage stage = new Stage();
    
        HBox buttonContainer = new HBox();
        Button button = new Button("Click");
        button.setOnAction(e ->
        {
            stage.show();
        });
        buttonContainer.setAlignment(Pos.BASELINE_CENTER);
        Scene scene = new Scene(buttonContainer);
        primaryStage.setScene(scene);
        buttonContainer.getChildren().add(button);
        primaryStage.setTitle(Utility.APP_TITLE);
        primaryStage.show();
    }
}
