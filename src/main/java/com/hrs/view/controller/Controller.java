package com.hrs.view.controller;

import com.hrs.configs.Configuration;
import com.hrs.exceptions.InvalidUserNameException;
import com.hrs.service.ApiService;
import com.hrs.view.alerts.AlertBox;
import com.hrs.view.models.Admin;
import com.hrs.view.models.AirLine;
import com.hrs.view.models.AirPlane;
import com.hrs.view.models.Airport;
import com.hrs.view.models.Arrival;
import com.hrs.test.Tester;
import com.hrs.util.Utility;
import com.hrs.view.View;
import com.hrs.view.models.Customer;
import com.hrs.view.models.Flight;
import com.hrs.view.models.Reservation;
import com.hrs.view.style.CSSStyle;
import com.hrs.view.util.FieldValue;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.time.LocalDate;

import java.util.LinkedList;
import java.util.List;

import static com.hrs.util.Utility.button;
import static com.hrs.util.Utility.label;

/**
 * A class that navigates views and talk to database
 */
public class Controller
{
    private View view;
    private ApiService apiService;
    
    public Controller()
    {
        apiService = Configuration.getApiService();
    }
    
    public View getView()
    {
        return view;
    }
    
    public void setView(View view)
    {
        this.view = view;
    }
    
    public void makeReservationFromSE(Integer flightId)
    {
        if(!Configuration.getSession().isCustomerInSession())
        {
            if(apiService.makeReservation(flightId, 101))
            {
                apiService.insertGlobalReservation(flightId);
                AlertBox.DisplayConfirmation("Reservation Successful",
                        "successfully reserved a seat for user="+"customer.getUsername()"+"." + " Please check your " +
                                "account to verify.");
                eventGlobalSearchBar();
            }
        }
        else
        {
            reservationWithUsernameAndPass(flightId);
        }
    }
    
    public void makeReservationByAirline(Integer flightId)
    {
        if(Configuration.getSession().isCustomerInSession())
        {
            if(apiService.makeReservation(flightId, 101))
            {
                System.out.println("in cust session");
                AlertBox.DisplayConfirmation("Reservation Successful",
                        "successfully reserved a seat for user="+"customer.getUsername()"+"."
                                + " Please check your account to verify.");
            }
        }
        else
        {
            reservationWithUsernameAndPass2(flightId, 1);
        }
    }
    
    public void reservationWithUsernameAndPass(Integer flightIdPk)
    {
        Stage stage = new Stage();
        VBox vBox = new VBox();
        
        Label l = new Label("Please enter your username to make reservation");
        TextField textField = new TextField();
        Button submit = button("Make Reservation");
        
        vBox.getChildren().addAll(l, textField, submit);
        
        Scene scene = new Scene(vBox, FieldValue.LOGIN_WINDOW_WIDTH, FieldValue.LOGIN_WINDOW_HEIGHT);
        
        submit.setOnAction(e ->
        {
            String username = textField.getText();
            try
            {
                if(apiService.makeReservation(flightIdPk, username, ""))
                {
                    apiService.insertGlobalReservation(flightIdPk);
                    stage.close();
                    AlertBox.DisplayConfirmation("Reservation Successful",
                            "successfully reserved a seat for user="+username+"." + " Please check your " +
                                    "account to verify.");
                    eventGlobalSearchBar();
                }
            }
            catch(InvalidUserNameException ex)
            {
                stage.close();
                AlertBox.DisplayError("Incorrect username", "No user found with username="+username);
            }
        });
        stage.setScene(scene);
        stage.setTitle(FieldValue.CUSTOMER);
        stage.setAlwaysOnTop(true);
        stage.showAndWait();
    }
    
    public void reservationWithUsernameAndPass2(Integer flightIdPk, Integer key)
    {
        Stage stage = new Stage();
        stage.setTitle("Express Reservation");
    
        GridPane gridPane = view.ui_loginContainer(FieldValue.CUSTOMER_LOGIN_LABEL);
        
        HBox hBox = (HBox)Utility.getNodeByRowColumnIndex
                (FieldValue.LOGIN_SUBMIT_RAW, FieldValue.LOGIN_SUBMIT_COL, gridPane);
        Button submit = (Button) hBox.getChildren().get(0);
    
        TextField username = (TextField) Utility.getNodeByRowColumnIndex(FieldValue.USERNAME_RAW, FieldValue.USERNAME_COL, gridPane);
        TextField pass = (TextField) Utility.getNodeByRowColumnIndex(FieldValue.PASSWORD_RAW, FieldValue.PASSWORD_COL, gridPane);
        
        Scene scene = new Scene(gridPane, FieldValue.LOGIN_WINDOW_WIDTH, FieldValue.LOGIN_WINDOW_HEIGHT);
        
        submit.setOnAction(e ->
        {
            stage.close();
            try
            {
                if(key == 0)
                {
                    if(apiService.makeReservation(flightIdPk, username.getText(), pass.getText()))
                    {
                        apiService.insertGlobalReservation(flightIdPk);
                        AlertBox.DisplayConfirmation("Reservation Successful",
                                "successfully reserved a seat for user="+username.getText()
                                        +"." + " Please check your account to verify.");
                        eventGlobalSearchBar();
                    }
                }
                else
                {
                    if(apiService.makeReservation(flightIdPk, username.getText(), pass.getText()))
                    {
                        AlertBox.DisplayConfirmation("Reservation Successful",
                                "successfully reserved a seat for user="+username.getText()
                                        +"." + " Please check your account to verify.");
                    }
                }
            }
            catch(InvalidUserNameException ex)
            {
                AlertBox.DisplayError("Incorrect username", "No user found with username="+username);
            }
        });
        stage.setScene(scene);
        stage.setTitle(FieldValue.CUSTOMER);
        stage.setAlwaysOnTop(true);
        stage.showAndWait();
    }
    
    public void eventLaunchAirline(String airlineName)
    {
        view.setTop(view.ui_homeMenuBar());
        
        GridPane gridPane = view.ui_searchBarContainer("Find Flights for "+airlineName);
        
        TextField searchBar = (TextField)Utility.getNodeByRowColumnIndex(FieldValue.SEARCH_BAR_RAW,
                FieldValue.SEARCH_BAR_COL, gridPane);
        
        searchBar.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                final String query = searchBar.getText();
                
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    // apiService.getAllFlightsByAirline(airline);
                    
                    view.setCenter(view.ui_searchResultsByAirline(airlineName, Tester.testFlights()));
                }
            }
        });
        
        HBox hBox = new HBox();
        Button button = button("Home");
        button.setAlignment(Pos.CENTER);
        button.setOnAction(e ->
        {
            view.setHome();
        });
        
        hBox.getChildren().add(button);
        gridPane.add(hBox, 1, 8);
        
        view.setCenter(gridPane);
    }
    
    public void eventLaunchAirport(String airportName)
    {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Double x1 = primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 840;
        Double y1 = primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - 770;
    
        Double x2 = primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 2000;
        
        arrivalScreen(airportName, x1, y1);
        departureScreen(airportName, x2, y1);
    }
    
    private void departureScreen(String airportName, Double x1, Double y1)
    {
        VBox departure = genericAirport(airportName, "departure", x1, y1, FieldValue.DEPARTURE_CONTAINER_ID);
    
        GridPane gridPane = populateGrid(FieldValue.DESTINATION, Tester.arrivals());
    
        Label header = new Label("Departure Flights for ".concat(airportName.toUpperCase()));
        header.setStyle(CSSStyle.fontFamily(FieldValue.FONT_MONACO).concat(CSSStyle.fontSize(20)));
    
        Button submit = new Button("Refresh");
    
        submit.setOnAction(e ->
        {
            departure.getChildren().set(2, populateGrid(FieldValue.DESTINATION, Tester.arrivals2()));
        });
    
        departure.getChildren().add(header);
        departure.getChildren().add(gridPane);
        departure.getChildren().add(submit);
    }
    
    private void arrivalScreen(String airportName, Double x1, Double y1)
    {
        VBox arrival = genericAirport(airportName, "arrival", x1, y1, FieldValue.ARRIVAL_CONTAINER_ID);
        
        GridPane gridPane = populateGrid(FieldValue.SOURCE, Tester.arrivals());
    
        Label header = new Label("Arrival Flights for ".concat(airportName.toUpperCase()));
        header.setStyle(CSSStyle.fontFamily(FieldValue.FONT_MONACO).concat(CSSStyle.fontSize(20)));
    
        Button submit = new Button("Refresh");
        
        submit.setOnAction(e ->
        {
            arrival.getChildren().set(2, populateGrid(FieldValue.SOURCE, Tester.arrivals2()));
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
        if(label.equalsIgnoreCase("arrival")) view.ui_arrivalWindow(stage);
        else view.ui_departureWindow(stage);
        
        return container;
    }
    
    public GridPane populateGrid(String airportHeader, List<Arrival> arrivals)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
    
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
    
        Label flight = new Label(FieldValue.FLIGHT_CODE);
        gridPane.add(flight, 0,0);
        Label airline = new Label(FieldValue.AIRLINE);
        gridPane.add(airline, 1,0);
        Label airport = new Label(airportHeader);
        gridPane.add(airport, 2,0);
        Label time = new Label(FieldValue.DATE);
        gridPane.add(time, 3,0);
        Label status = new Label(FieldValue.STATUS);
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
            if(apiService.insertNewCustomer(firstName.getText(), lastName.getText(), email.getText(), password.getText()))
            {
                stage.close();
                AlertBox.DisplayInformation("New Customer Successfully added",
                                "A customer has successfully been added.\nName: "
                                        + firstName.getText() + " " + lastName.getText());
            }
            System.out.println(firstName.getText() + ' ' + lastName.getText() + ' ' + email.getText() + " " +
                    password.getText());
        });
    
        stage.setScene(scene);
        stage.setTitle(FieldValue.NEW_CUST_LABEL);
        stage.setAlwaysOnTop(true);
        stage.showAndWait();
    }
    
    public void launchLoginForGlobalAdmin(GridPane gridPane)
    {
        launchLoginForAllByKey(gridPane, FieldValue.LOGIN_VIEW_KEY_GLOBAL);
    }
    
    public void launchLoginForAirlineAdmin(GridPane gridPane, String airlineAdmin)
    {
        launchLoginForAllByKey(gridPane, airlineAdmin);
    }
    
    public void launchLoginForCustomer(GridPane gridPane)
    {
        launchLoginForAllByKey(gridPane, FieldValue.LOGIN_VIEW_KEY_CUSTOMER);
    }
    
    private void launchLoginForAllByKey(GridPane gridPane, String loginViewKey)
    {
        Stage stage = new Stage();
        Scene scene = new Scene(gridPane, FieldValue.LOGIN_WINDOW_WIDTH, FieldValue.LOGIN_WINDOW_HEIGHT);
    
        HBox hBox = (HBox)Utility.getNodeByRowColumnIndex
                (FieldValue.LOGIN_SUBMIT_RAW, FieldValue.LOGIN_SUBMIT_COL, gridPane);
        Button submit = (Button) hBox.getChildren().get(0);
    
        submit.setOnAction(e ->
        {
            stage.close();
            
            TextField username = (TextField) Utility.getNodeByRowColumnIndex(FieldValue.USERNAME_RAW, FieldValue.USERNAME_COL, gridPane);
            TextField pass = (TextField) Utility.getNodeByRowColumnIndex(FieldValue.PASSWORD_RAW, FieldValue.PASSWORD_COL, gridPane);
            
            if(loginViewKey.equalsIgnoreCase(FieldValue.LOGIN_VIEW_KEY_GLOBAL))
            {
//                Admin admin = apiService.getGlobalAdminByLogin(username.getText(), pass.getText());
//                List<Reservation> reservations = apiService.getGlobalReservations();
//                Configuration.getSession().addGlobalAdmin(Tester.admin());
                
                stage.close();
                view.setTop(view.menuBar(view.airports(), view.airlines()));
                VBox center = globalReservations(Tester.admin(), Tester.testReservation());
                view.setCenter(center);
            }
            else if(loginViewKey.equalsIgnoreCase(FieldValue.LOGIN_VIEW_KEY_CUSTOMER))
            {
                stage.close();
                
//                Customer customer = apiService.getCustomerByLogin(username.getText(), pass.getText());
                
                view.setTop(view.menuBar(view.airports(), view.airlines()));
                VBox center = customerCenterContainer(Tester.testCustomer());
                view.setCenter(center);
            }
            else if(loginViewKey.equalsIgnoreCase(FieldValue.A1)
                            || loginViewKey.equalsIgnoreCase(FieldValue.A2)
                            || loginViewKey.equalsIgnoreCase(FieldValue.A3))
            {
//                Admin admin = apiService.getAdminByLogin("", "", "");
//                Configuration.getSession().addAdminToSession(admin);
                
                
                VBox adminAccessView = view.ui_adminAccessByAirline(Tester.admin(), loginViewKey);
                
                Button add = (Button) adminAccessView.getChildren().get(FieldValue.ADD_FLIGHT_INDEX);
                Button cancel = (Button) adminAccessView.getChildren().get(FieldValue.CANCEL_FLIGHT_INDEX);
                Button logout = (Button) adminAccessView.getChildren().get(adminAccessView.getChildren().size()-1);
                
                add.setOnAction(event ->
                {
                    addFlights(loginViewKey);
                });
                
                cancel.setOnAction(event ->
                {
                    cancelFlightsByAirline(loginViewKey, Tester.testFlights());
                });
                
                logout.setOnAction(event ->
                {
                    Configuration.getSession().deleteAdminFromSession();
                    view.setHome();
                });
                
                view.setCenter(adminAccessView);
            
            }
        });
        stage.setScene(scene);
        stage.setTitle(FieldValue.CUSTOMER);
        stage.setAlwaysOnTop(true);
        stage.showAndWait();
    }
    
    private void cancelFlightsByAirline(String loginViewKey, List<Flight> flights)
    {
        Stage stage = new Stage();
        stage.setWidth(FieldValue.HOME_SCENE_WIDTH);
        stage.setHeight(500);
        Scene scene = new Scene(view.ui_alightsToBeCanceledByAirline(loginViewKey, flights));
        stage.setScene(scene);
        stage.show();
    }
    
    public void cancelFlight(Integer flight, String airlineName)
    {
        AlertBox.DisplayConfirmation("?", "?");
        cancelFlightsByAirline(airlineName, Tester.testFlights2());
    }
    
    private void addFlights(String airline)
    {
        Flight flight = new Flight();
        List<Airport> airports = Tester.airports();
        List<AirPlane> airPlanes = Tester.airPlanes();
        List<String> times = Utility.timeList();
        
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
        
        ChoiceBox<AirPlane> airPlaneChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(airPlanes));
        airPlaneChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener <Number>()
        {
            @Override
            public void changed(ObservableValue <? extends Number> observableValue, Number number, Number t1)
            {
                System.out.println(airPlanes.get(t1.intValue()));
            }
        });
    
        ChoiceBox<Airport> sourceChoices = new ChoiceBox<>(FXCollections.observableArrayList(airports));
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
        sourceDate.setOnAction(e -> System.out.println(sourceDate.getValue().toString()));
        ChoiceBox<String> sourceTimes = new ChoiceBox<>(FXCollections.observableArrayList(times));
        
        ChoiceBox<Airport> destinationChoices = new ChoiceBox<>(FXCollections.observableArrayList(airports));
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
        destinationDate.setOnAction(e -> System.out.println(destinationDate.getValue().toString()));
        ChoiceBox<String> destinationTimes = new ChoiceBox<>(FXCollections.observableArrayList(times));
        
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
        destinationChoices.setOnAction(e -> System.out.println(destinationChoices.getValue()));
        
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
            try
            {
                if(apiService.addFlightByAirline(new AirLine(), new Flight()))
                {
                    stage.close();
                    AlertBox.DisplayConfirmation("Flight has successfully added",
                            "A flight has successfully been added by - for Airline - ");
                }
            }
            catch(Exception ex)
            {
            
            }
        });
        
        gridPane.add(submit, 1, 13, 1, 1);
        
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    private VBox globalReservations(Admin admin, List<Reservation> reservations)
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
        superV.getChildren().add(view.ui_reservationResults(reservations));
        superV.getChildren().add(new Label());
        superV.getChildren().add(new Label());
        HBox logout = logoutHBox();
        logout.setAlignment(Pos.BASELINE_CENTER);
        superV.getChildren().add(logout);
        Button out = (Button) logout.getChildren().get(0);
        out.setOnAction(e ->
        {
            Configuration.getSession().deleteAdminFromSession();
            view.setTop(view.ui_homeMenuBar());
            view.setCenter(view.ui_searchBarContainer(FieldValue.SEARCH));
        });
        return superV;
    }
    
    private VBox customerCenterContainer(Customer customer)
    {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);

        vBox.getChildren().add(new Label());
        vBox.getChildren().add(new Label());
        vBox.getChildren().add(customerNameHBox(customer));
        vBox.getChildren().add(new Label());
        vBox.getChildren().add(new Label("Flight Histories"));
        vBox.getChildren().add(new Label());
        vBox.getChildren().add(populateGridForCustomer(customer));
        vBox.getChildren().add(new Label());
        vBox.getChildren().add(new Label());
        
        HBox hBox = logoutHBox();
        Button logout = (Button)hBox.getChildren().get(0);
        logout.setOnAction(e ->
        {
            Configuration.getSession().deleteCustomerFromSession();
            view.setTop(view.ui_homeMenuBar());
            view.setCenter(view.ui_searchBarContainer(FieldValue.SEARCH));
        });
        
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(new Label());
        vBox.getChildren().add(new Label());

        return vBox;
    }
    
    private GridPane populateGridForCustomer(Customer customer)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(12);
        gridPane.setVgap(8);

        for(int i = 0; i < Utility.customerHistoryHeaders().getChildren().size(); i++)
            gridPane.add(Utility.customerHistoryHeaders().getChildren().get(i), i, 0);
    
        for(int i = 0; i < Utility.customerHistoryHeaders().getChildren().size(); i++)
            gridPane.add(new Label(), i, 1);
    
        int k = 2;

        List<Flight> flights = customer.getFlights();
        List<Reservation> reservations = customer.getReservations();
        
        for(int i = 0; i < reservations.size(); i++)
        {
            Reservation reservation = reservations.get(i);
            gridPane.add(button(reservation.getFlight().getFlightName()), 0, k);
            gridPane.add(button(reservation.getFlight().getSource()), 1, k);
            gridPane.add(button(reservation.getFlight().getDestination()), 2, k);
            gridPane.add(button(reservation.getFlight().getAirline()), 3, k);
            gridPane.add(button(reservation.getLocalDate()), 4, k);
            gridPane.add(button(reservation.getFlight().getFare()), 5, k);
            Button cancel = button(reservation.getStatus());
            gridPane.add(cancel, 6, k);
            k++;
            
            if("a".equalsIgnoreCase(reservation.getStatus()))
            {
                cancel.setOnAction(e ->
                {
                    if(AlertBox.DisplayConfirmation("Canceling this flight?", "Do you really want " +
                            "to cancel this flight?"))
                    {
                        apiService.cancelReservation2testFunc(customer.getCustomerId());
                        
                        //List<Flight> flightList = apiService.getAllFlightsByCustomerId(customer.getCustomerId());
                        
                        customer.setReservations(Tester.testReservation2());
                        VBox center = customerCenterContainer(customer);
                        view.setCenter(center);
                    }
                });
            }
        }

        int j = k+reservations.size();
        for(int i = 0; i < flights.size(); i++)
        {
            gridPane.add(button(flights.get(i).getFlightName()), 0, j);
            gridPane.add(button(flights.get(i).getSource()), 1, j);
            gridPane.add(button(flights.get(i).getDestination()), 2, j);
            gridPane.add(button(flights.get(i).getAirline()), 3, j);
            gridPane.add(button(flights.get(i).getDate()), 4, j);
            gridPane.add(button(flights.get(i).getFare()), 5, j);
            j++;
        }

        return gridPane;
    }
    
    private HBox customerNameHBox(Customer customer)
    {
        HBox name = new HBox();
        name.setAlignment(Pos.TOP_CENTER);
        name.getChildren().add(new Label("Customer Name: " + customer.getFirstName() + " " + customer.getLastName()));
        return name;
    }
    
    private HBox logoutHBox()
    {
        HBox outContainer = new HBox();
        outContainer.setAlignment(Pos.BOTTOM_CENTER);
        Button logout = new Button("Logout");
        outContainer.getChildren().add(logout);
        return outContainer;
    }
    
    public void eventGlobalSearchBar()
    {
//        List<Flight> flights = apiService.getAllFlights();
        
        List<Flight> flights = Tester.testFlights();
    
        GridPane center = view.ui_globalSearchResults(flights);
        
        view.setSearchResultsInCenter(center);
    }
}
