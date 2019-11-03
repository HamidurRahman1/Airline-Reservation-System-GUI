package com.hrs.view;

import com.hrs.controller.Controller;
import com.hrs.util.Utility;
import javafx.application.Application;
import javafx.stage.Stage;

public class View extends Application {
    private Controller controller;

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(Utility.APP_TITLE);
        primaryStage.show();
    }
}
