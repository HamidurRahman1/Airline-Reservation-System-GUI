package com.hrs.view.controller;

import com.hrs.configs.Configuration;
import com.hrs.exceptions.InvalidUserName;
import com.hrs.service.ApiService;
import com.hrs.view.alerts.AlertBox;
import com.hrs.view.models.Admin;
import com.hrs.view.models.Arrival;
import com.hrs.test.Tester;
import com.hrs.util.Utility;
import com.hrs.view.View;
import com.hrs.view.models.Customer;
import com.hrs.view.models.Flight;
import com.hrs.view.models.Reservation;
import com.hrs.view.style.CSSStyle;
import com.hrs.view.util.FieldValue;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

import static com.hrs.util.Utility.button;

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
    
    public void adminLogin(String airline)
    {
        // apiService.getAdminByAirline(airline);
        
    }
    
    public void eventMakeReservation(Integer flightId)
    {
        if(Configuration.getSession().isInSession(new Customer()))
        {
            if(apiService.makeReservation(flightId, 101))
            {
                apiService.insertGlobalReservation(flightId);
                AlertBox.displayConfirmation("Reservation Successful",
                        "successfully reserved a seat for user="+"customer.getUsername()"+"." + " Please check your " +
                                "account to verify.");
                eventGlobalSearchBar();
            }
        }
        else
        {
            reservationWithUsername(flightId);
        }
    }
    
    public void reservationWithUsername(Integer flightIdPk)
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
                if(apiService.makeReservation(flightIdPk, username))
                {
                    apiService.insertGlobalReservation(flightIdPk);
                    stage.close();
                    AlertBox.displayConfirmation("Reservation Successful",
                            "successfully reserved a seat for user="+username+"." + " Please check your " +
                                    "account to verify.");
                    eventGlobalSearchBar();
                }
            }
            catch(InvalidUserName ex)
            {
                stage.close();
                AlertBox.displayError("Incorrect username", "No user found with username="+username);
            }
        });
        stage.setScene(scene);
        stage.setTitle(FieldValue.LOGIN_LABEL);
        stage.setAlwaysOnTop(true);
        stage.showAndWait();
    }
    
    public void eventLaunchAirline(String airlineName)
    {
        Scene scene = null;
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(view.ui_homeMenuBar());
        VBox gridPane = view.Ui_searchBarContainer(airlineName, "Admin");
        
        TextField searchBar = (TextField)Utility.getNodeByRowColumnIndex(FieldValue.SEARCH_BAR_RAW,
                FieldValue.SEARCH_BAR_COL, (GridPane) gridPane.getChildren().get(1));
        
        searchBar.setOnKeyPressed(new EventHandler <KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    apiService.getAllFlightsByAirline(searchBar.getText());
                    System.out.println(searchBar.getText());
                }
            }
        });
        
        borderPane.setCenter(gridPane);
        scene = new Scene(borderPane, FieldValue.HOME_SCENE_WIDTH, FieldValue.HOME_SCENE_HEIGHT);
        view.switchScene(scene);
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
    
        Label flight = new Label(FieldValue.FLIGHT);
        gridPane.add(flight, 0,0);
        Label airline = new Label(FieldValue.AIRLINE);
        gridPane.add(airline, 1,0);
        Label airport = new Label(airportHeader);
        gridPane.add(airport, 2,0);
        Label time = new Label(FieldValue.DATE_TIME);
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
    
    public void eventLaunchLogin(GridPane gridPane)
    {
        Stage stage = new Stage();
        Scene scene = new Scene(gridPane, FieldValue.LOGIN_WINDOW_WIDTH, FieldValue.LOGIN_WINDOW_HEIGHT);
        
        HBox hBox = (HBox)Utility.getNodeByRowColumnIndex
                (FieldValue.LOGIN_SUBMIT_RAW, FieldValue.LOGIN_SUBMIT_COL, gridPane);
        Button submit = (Button) hBox.getChildren().get(0);
        
        submit.setOnAction(e ->
        {
            TextField username = (TextField) Utility.getNodeByRowColumnIndex(FieldValue.USERNAME_RAW, FieldValue.USERNAME_COL, gridPane);
            TextField pass = (TextField) Utility.getNodeByRowColumnIndex(FieldValue.PASSWORD_RAW, FieldValue.PASSWORD_COL, gridPane);
    
            System.out.println(username.getText() + ' ' + pass.getText());
            Customer customer = apiService.getCustomerByLogin(username.getText(), pass.getText());
    
            if(customer != null)
            {
                stage.close();
                view.ui_customerHome(menuBar(), customerCenterContainer(customer));
            }
            else
            {
                System.out.println();
            }
        });
        stage.setScene(scene);
        stage.setTitle(FieldValue.LOGIN_LABEL);
        stage.setAlwaysOnTop(true);
        stage.showAndWait();
    }
    
    public void launchLoginForGlobalAdmin(GridPane gridPane)
    {
        launchLoginForAllByKey(gridPane, FieldValue.LOGIN_VIEW_KEY_GLOBAL);
    }
    
    public void launchLoginForAirlineAdmin(GridPane gridPane)
    {
        launchLoginForAllByKey(gridPane, FieldValue.LOGIN_VIEW_KEY_AIRLINE);
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
            TextField username = (TextField) Utility.getNodeByRowColumnIndex(FieldValue.USERNAME_RAW, FieldValue.USERNAME_COL, gridPane);
            TextField pass = (TextField) Utility.getNodeByRowColumnIndex(FieldValue.PASSWORD_RAW, FieldValue.PASSWORD_COL, gridPane);
            
            if(loginViewKey.equalsIgnoreCase(FieldValue.LOGIN_VIEW_KEY_GLOBAL))
            {
//                Admin admin = apiService.getGlobalAdminByLogin(username.getText(), pass.getText());
//                List<Reservation> reservations = apiService.getGlobalReservations();
            
                stage.close();
                view.setTop(view.menuBar(view.airports(), view.airlines()));
                VBox center = globalReservations(Tester.admin(), Tester.testReservation());
                view.setCenter(center);
            }
            else if(loginViewKey.equalsIgnoreCase(FieldValue.LOGIN_VIEW_KEY_CUSTOMER))
            {
                stage.close();
                
            }
            else
            {
                System.out.println("airline");
            }
        });
        stage.setScene(scene);
        stage.setTitle(FieldValue.LOGIN_LABEL);
        stage.setAlwaysOnTop(true);
        stage.showAndWait();
    }
    
    private VBox globalReservations(Admin admin, List<Reservation> reservations)
    {
        VBox superV = new VBox();
        superV.setAlignment(Pos.TOP_CENTER);
        
        superV.getChildren().add(new Label());
        superV.getChildren().add(new Label(admin.getFirstName() + " " + admin.getLastName()));
        superV.getChildren().add(new Label());
        superV.getChildren().add(new Label("Displaying all reservations made using SE"));
        superV.getChildren().add(new Label());
        superV.getChildren().add(view.ui_reservationResults(reservations));
        superV.getChildren().add(new Label());
        HBox logout = logout();
        logout.setAlignment(Pos.BASELINE_CENTER);
        superV.getChildren().add(logout);
        Button out = (Button) logout.getChildren().get(0);
        out.setOnAction(e ->
        {
            view.setTop(view.ui_homeMenuBar());
            view.setCenter(view.ui_searchBarContainer());
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
        vBox.getChildren().add(logoutHBox());
        vBox.getChildren().add(new Label());
        vBox.getChildren().add(new Label());
        
        return vBox;
    }
    
    private GridPane populateGridForCustomer(Customer customer)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        
        for(int i = 0; i < Utility.flightHeaders().getChildren().size(); i++)
            gridPane.add(Utility.flightHeaders().getChildren().get(i), i, 0);
        
        int j = 1;
        
        List<Flight> flights = customer.getFlights();
        
        for(int i = 0; i < flights.size(); i++)
        {
            gridPane.add(button(flights.get(i).flightName), 0, j);
            gridPane.add(button(flights.get(i).source), 1, j);
            gridPane.add(button(flights.get(i).destination), 2, j);
            gridPane.add(button(flights.get(i).airline), 3, j);
            gridPane.add(button(flights.get(i).date), 4, j);
            Button cancel = button(flights.get(i).status);
            gridPane.add(cancel, 5, j);
            if("a".equalsIgnoreCase(flights.get(i).status))
            {
                cancel.setOnAction(e ->
                {
                    if(AlertBox.displayConfirmation("Canceling this flight?", "Do you really want " +
                        "to cancel this flight?"))
                    {
                        apiService.cancelReservation2testFunc(customer.getCustomerId());
                        //List<Flight> flightList = apiService.getAllFlightsByCustomerId(customer.getCustomerId());
                        customer.setFlights(Tester.testFlights2());
                        view.ui_customerHome(menuBar(), customerCenterContainer(customer));
                    }
                });
            }
            j++;
        }
        
        return gridPane;
    }
    
    private HBox customerNameHBox(Customer customer)
    {
        HBox name = new HBox();
        name.setAlignment(Pos.TOP_CENTER);
        
        name.getChildren().add(new Label(customer.getFirstName() + " " + customer.getLastName()));
        
        return name;
    }
    
    private MenuBar menuBar()
    {
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(view.getMenuBar().getMenus().get(1));
        menuBar.getMenus().add(view.getMenuBar().getMenus().get(2));
        return menuBar;
    }
    
    private HBox logoutHBox()
    {
        HBox outContainer = new HBox();
        outContainer.setAlignment(Pos.BOTTOM_CENTER);
        Button logout = new Button("Logout");
        logout.setOnAction(e ->
        {
            if(Configuration.getSession().deleteFromSession(new Customer()))
            {
                view.switchToMainScreen();
            }
        });
        outContainer.getChildren().add(logout);
        return outContainer;
    }
    
    public HBox logout()
    {
        return new HBox(new Button("Logout"));
    }
    
    public void eventGlobalSearchBar()
    {
//        List<Flight> flights = apiService.getAllFlights();
        
        List<Flight> flights = Tester.testFlights();
        GridPane center = view.ui_globalSearchResults(flights);
        view.setSearchResultsInCenter(center);
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
