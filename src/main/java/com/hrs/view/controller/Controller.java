package com.hrs.view.controller;

import com.hrs.configs.Configuration;
import com.hrs.exceptions.InvalidPasswordException;
import com.hrs.exceptions.InvalidUserNameException;
import com.hrs.service.ApiApiServiceImpl;
import com.hrs.view.alerts.AlertBox;
import com.hrs.view.models.Admin;
import com.hrs.view.models.Airline;
import com.hrs.view.models.Airplane;
import com.hrs.view.models.Airport;
import com.hrs.view.models.Arrival;
import com.hrs.test.Tester;
import com.hrs.util.Utility;
import com.hrs.view.View;
import com.hrs.view.models.Customer;
import com.hrs.view.models.Destination;
import com.hrs.view.models.Flight;
import com.hrs.view.models.Reservation;
import com.hrs.view.models.Source;
import com.hrs.view.style.CSSStyle;
import com.hrs.view.util.FieldValue;

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

import java.util.List;
import java.util.Set;

import static com.hrs.util.Utility.button;

/**
 * A class that navigates views and talk to database
 */
public class Controller
{
    private View view;
    private ApiApiServiceImpl apiServiceImpl;
    
    public Controller()
    {
        apiServiceImpl = Configuration.getApiServiceImpl();
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
        if(Configuration.getSession().isCustomerInSession())
        {
            apiServiceImpl.makeReservation(flightId, Configuration.getSession().getCustomer().getCustomerId());
            apiServiceImpl.makeReservationBySE(flightId);
            AlertBox.DisplayConfirmation("Reservation Successful",
                    "successfully reserved a seat for user="+"customer.getUsername()"+"." + " Please check your " +
                            "account to verify.");
            eventGlobalSearchBar();
        }
        else
        {
            reservationWithUsernameAndPass(flightId, 0);
        }
    }
    
    public void makeReservationByAirline(Integer flightId)
    {
        if(Configuration.getSession().isCustomerInSession())
        {
            apiServiceImpl.makeReservation(flightId, Configuration.getSession().getCustomer().getCustomerId());
            AlertBox.DisplayConfirmation("Reservation Successful",
                    "successfully reserved a seat for user="+"customer.getUsername()"+"."
                            + " Please check your account to verify.");
        }
        else
        {
            reservationWithUsernameAndPass(flightId, 1);
        }
    }
    
    public void reservationWithUsernameAndPass(Integer flightIdPk, Integer key)
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
                    if(apiServiceImpl.makeReservation(flightIdPk, username.getText(), pass.getText()))
                    {
                        apiServiceImpl.makeReservationBySE(flightIdPk, "", "");
                        AlertBox.DisplayConfirmation("Reservation Successful",
                                "successfully reserved a seat for user="+username.getText()
                                        +"." + " Please check your account to verify.");
                        eventGlobalSearchBar();
                    }
                }
                else
                {
                    if(apiServiceImpl.makeReservation(flightIdPk, username.getText(), pass.getText()))
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
            catch(InvalidPasswordException ex)
            {
            
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
                    // apiServiceImpl.getAllFlightsByAirline(airline);
                    
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
            Configuration.setCurrentDate(datePicker.getValue());
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
            if(apiServiceImpl.insertNewCustomer(firstName.getText(), lastName.getText(), email.getText(), password.getText()))
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
                try
                {
                    Admin admin = apiServiceImpl.getGlobalAdminByLogin(username.getText(), pass.getText());
                    Configuration.getSession().addAdminToSession(admin);
                    Set<Reservation> reservations = apiServiceImpl.getGlobalReservations();
                    view.ui_handleAfterGlobalAdminLogin(admin, reservations);
                }
                catch(InvalidUserNameException ex) {}
                catch(InvalidPasswordException ex) {}
            }
            else if(loginViewKey.equalsIgnoreCase(FieldValue.LOGIN_VIEW_KEY_CUSTOMER))
            {
                try
                {
                    Customer customer = apiServiceImpl.getCustomerByLogin(username.getText(), pass.getText());
    
                    view.setTop(view.menuBar(view.airports(), view.airlines()));
                    VBox center = customerCenterContainer(Tester.testCustomer());
                    view.setCenter(center);
                }
                catch(InvalidUserNameException ex){}
                catch(InvalidPasswordException ex){}
            }
            else if(loginViewKey.equalsIgnoreCase(FieldValue.A1)
                            || loginViewKey.equalsIgnoreCase(FieldValue.A2)
                            || loginViewKey.equalsIgnoreCase(FieldValue.A3))
            {
                try
                {
                    Admin admin = apiServiceImpl.getAirlineAdminByLogin("", "", "");
                    Configuration.getSession().addAdminToSession(admin);
                    
                    VBox adminAccessView = view.ui_adminAccessByAirline(Tester.admin(), loginViewKey);
                    
                    Button add = (Button) adminAccessView.getChildren().get(FieldValue.ADD_FLIGHT_INDEX);
                    Button cancel = (Button) adminAccessView.getChildren().get(FieldValue.CANCEL_FLIGHT_INDEX);
                    Button rsvp = (Button) adminAccessView.getChildren().get(FieldValue.RSVP_FLIGHT_INDEX);
                    Button logout = (Button) adminAccessView.getChildren().get(adminAccessView.getChildren().size()-1);
                    
                    add.setOnAction(event -> view.ui_addFlightForAirline(admin, loginViewKey, apiServiceImpl.getAllAirports(),
                                        apiServiceImpl.getAllAirPlaneByAirLine(loginViewKey)));
                    
                    cancel.setOnAction(event -> view.ui_cancelFlightsByAirlineAdmin(loginViewKey,
                            apiServiceImpl.getAllFlightsByAirline(loginViewKey)));
                    
//                    cancel.setOnAction(event -> cancelFlightsByAirline(loginViewKey,
//                            apiServiceImpl.getAllFlightsByAirline(loginViewKey)));
//
                    rsvp.setOnAction(event -> view.RSVPsByAirline(loginViewKey,
                            view.ui_displayAllRSVPsByAirline(loginViewKey, Tester.testReservation())));
                    
                    logout.setOnAction(event ->
                    {
                        Configuration.getSession().deleteAdminFromSession();
                        view.setHome();
                    });
                    
                    view.setCenter(adminAccessView);
                }
                catch(InvalidUserNameException ex) {}
                catch(InvalidPasswordException ex) {}
            
            }
        });
        stage.setScene(scene);
        stage.setTitle(FieldValue.CUSTOMER);
        stage.setAlwaysOnTop(true);
        stage.showAndWait();
    }
    
    private void cancelFlightsByAirline(String loginViewKey, Set<Flight> flights)
    {
        Stage stage = new Stage();
        stage.setWidth(FieldValue.HOME_SCENE_WIDTH);
        stage.setHeight(500);
        Scene scene = new Scene(view.ui_flightsToBeCanceledByAirline(loginViewKey, flights));
        stage.setScene(scene);
        stage.show();
    }
    
    public void cancelFlight(Integer flight, String airlineName)
    {
        if(AlertBox.DisplayConfirmation("?", "?"))
        {
            apiServiceImpl.cancelFlight(flight);
            cancelFlightsByAirline(airlineName, apiServiceImpl.getAllFlightsByAirline(airlineName));
        }
    }
    
    private VBox globalReservations(Admin admin, Set<Reservation> reservations)
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
        superV.getChildren().add(view.ui_globalReservationResultsForAdmin(reservations));
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
        vBox.getChildren().add(populateRSVPsFlightsForCustomer(customer));
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
    
    public GridPane populateRSVPsFlightsForCustomer(Customer customer)
    {
        Set<Flight> flights = customer.getFlights();
        Set<Reservation> reservations = customer.getReservations();
        
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(12);
        gridPane.setVgap(8);
        
        int row = 0;
        Label rsvpLabel = new Label("All future reservations");
        gridPane.add(rsvpLabel, 2, 0, 3, 1);
        row++;
    
        for(int i = 0; i < Utility.customerReservationHeaders().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(int i = 0; i < Utility.customerReservationHeaders().getChildren().size(); i++)
            gridPane.add(Utility.customerReservationHeaders().getChildren().get(i), i, row);
        row++;
        
        for(int i = 0; i < Utility.customerReservationHeaders().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(Reservation reservation : reservations)
        {
            gridPane.add(button(reservation.getFlight().getAirLine().getAirlineName()), 0, row);
            gridPane.add(button(reservation.getFlight().getAirplane().getAirPlaneName()), 1, row);
            gridPane.add(button(reservation.getFlight().getFlightCode()), 2, row);
            gridPane.add(button(reservation.getFlight().getSource().getAirportName()), 3, row);
            gridPane.add(button(reservation.getFlight().getDestination().getAirportName()), 4, row);
            gridPane.add(button(reservation.getFlight().getFare().toString()), 5, row);
            gridPane.add(button(reservation.getRsvpDate().toString()), 6, row);
            Button status = button(reservation.getStatus());
            gridPane.add(status, 7, row);
            row++;
            
            if("ACTIVE".equalsIgnoreCase(reservation.getStatus()))
            {
                status.setOnAction(e ->
                {
                    if(AlertBox.DisplayConfirmation("Canceling this flight?", "Do you really want " +
                            "to cancel this flight?")){
                        apiServiceImpl.cancelReservation(customer.getCustomerId(), reservation.getReservationId());
                        customer.setReservations(apiServiceImpl.getAllReservationsByCustomerId(customer.getCustomerId()));
                        VBox center = customerCenterContainer(customer);
                        view.setCenter(center);
                    }
                });
            }
        }
    
        for(int i = 0; i < Utility.customerReservationHeaders().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
    
        Label flightsLabel = new Label("All past flights");
        gridPane.add(flightsLabel, 3, row, 3, 1);
        row++;
    
        for(int i = 0; i < Utility.customerPastFlightHeaders().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(Flight flight : flights)
        {
            gridPane.add(button(flight.getAirLine().getAirlineName()), 0, row);
            gridPane.add(button(flight.getAirplane().getAirPlaneName()), 1, row);
            gridPane.add(button(flight.getFlightCode()), 2, row);
            gridPane.add(button(flight.getSource().getAirportName()), 3, row);
            gridPane.add(button(flight.getDestination().getAirportName()), 4, row);
            gridPane.add(button(flight.getFare().toString()), 5, row);
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
    
    public HBox logoutHBox()
    {
        HBox outContainer = new HBox();
        outContainer.setAlignment(Pos.BOTTOM_CENTER);
        Button logout = new Button("Logout");
        outContainer.getChildren().add(logout);
        return outContainer;
    }
    
    public void eventGlobalSearchBar()
    {
        Set<Flight> flights = Tester.testFlights();
    
        GridPane center = view.ui_globalSearchResults(flights);
        
        view.setSearchResultsInCenter(center);
    }
    
    public void customerLogout()
    {
        Configuration.getSession().deleteCustomerFromSession();
        view.setTop(view.ui_homeMenuBar());
        view.setCenter(view.ui_searchBarContainer(FieldValue.SEARCH));
    }
    
    public void adminLogout()
    {
        Configuration.getSession().deleteAdminFromSession();
        view.setTop(view.ui_homeMenuBar());
        view.setCenter(view.ui_searchBarContainer(FieldValue.SEARCH));
    }
    
    public boolean addFlightForAirline(String airline, TextField codeField, ChoiceBox<Airplane> airPlaneChoiceBox,
                                    ChoiceBox<Airport> sourceChoices, DatePicker sourceDate,
                                    ChoiceBox<String> sourceTimes, ChoiceBox<Airport> destinationChoices,
                                    DatePicker destinationDate, ChoiceBox<String> destinationTimes, TextField capacity1)
    {
        System.out.println(sourceChoices.getValue());
        Flight flight = new Flight();
        flight.setAirLine(new Airline(airline));
        flight.setFlightCode(codeField.getText());
        flight.setAirplane(airPlaneChoiceBox.getValue());
        flight.setSource(new Source(sourceChoices.getValue(), sourceDate.getValue(), sourceTimes.getValue()));
        flight.setDestination(new Destination(destinationChoices.getValue(), destinationDate.getValue(), destinationTimes.getValue()));
        flight.setCapacity(Integer.parseInt(capacity1.getText()));
        
        return apiServiceImpl.insertFlightByAirline(flight);
    }
}
