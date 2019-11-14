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
import com.hrs.test.Tester;
import com.hrs.util.Utility;
import com.hrs.view.View;
import com.hrs.view.models.Customer;
import com.hrs.view.models.Destination;
import com.hrs.view.models.Flight;
import com.hrs.view.models.Reservation;
import com.hrs.view.models.Source;
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

import java.util.Set;

import static com.hrs.util.Utility.FONT_SIZE;
import static com.hrs.util.Utility.GREEN;
import static com.hrs.util.Utility.STYLE;
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
    
    public void makeReservationFromSE(Flight flight)
    {
        if(Configuration.getSession().isCustomerInSession())
        {
            apiServiceImpl.makeReservationBySE(flight.getFlightId(), Configuration.getSession().getCustomer().getCustomerId());
            AlertBox.DisplayConfirmation(FieldValue.RSVP_SUCCESS, Utility.RSVP_CUSTOMER_MESSAGE
                    (Configuration.getSession().getCustomer().getFirstName().concat(" ")
                                  .concat(Configuration.getSession().getCustomer().getLastName())));
            view.setCenter(view.ui_globalSearchResults(Tester.testFlights2()));
        }
        else
        {
            reservationWithUsernameAndPass(flight, 0);
        }
    }
    
    public void makeReservationByAirline(Flight flight)
    {
        if(Configuration.getSession().isCustomerInSession())
        {
            apiServiceImpl.makeReservation(flight.getFlightId(), Configuration.getSession().getCustomer().getCustomerId());
            AlertBox.DisplayConfirmation(FieldValue.RSVP_SUCCESS, Utility.RSVP_CUSTOMER_MESSAGE
                    (Configuration.getSession().getCustomer().getFirstName().concat(" ")
                                  .concat(Configuration.getSession().getCustomer().getLastName())));
            view.setCenter(view.ui_searchResultsByAirline(flight.getAirLine().getAirlineName(), Tester.testFlights2()));
        }
        else
        {
            reservationWithUsernameAndPass(flight, 1);
        }
    }
    
    public void reservationWithUsernameAndPass(Flight flight, Integer key)
    {
        Stage stage = new Stage();
        stage.setTitle(FieldValue.EXP_RSVP);
    
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
                    if(apiServiceImpl.makeReservationBySE(flight.getFlightId(), "", ""))
                    {
                        AlertBox.DisplayConfirmation(FieldValue.RSVP_SUCCESS,
                                Utility.RSVP_CUSTOMER_MESSAGE(username.getText()));
                    }
                    view.setCenter(view.ui_globalSearchResults(Tester.testFlights2()));
                }
                else
                {
                    if(apiServiceImpl.makeReservation(flight.getFlightId(), username.getText(), pass.getText()))
                    {
                        AlertBox.DisplayConfirmation(FieldValue.RSVP_SUCCESS,
                                Utility.RSVP_CUSTOMER_MESSAGE(username.getText()));
                    }
                    view.setCenter(view.ui_searchResultsByAirline(flight.getAirLine().getAirlineName(), Tester.testFlights()));
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
        if(Configuration.getSession().isCustomerInSession())
            view.setTop(view.menuBar(view.ui_loggedUser(), view.airlines(), view.airports()));
        else view.setTop(view.ui_homeMenuBar());
        
        GridPane gridPane = view.ui_searchBarContainer(Utility.FIND_FLIGHTS_BY_LABEL(airlineName));
        
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
                    view.setCenter(view.ui_searchResultsByAirline(airlineName, Tester.testFlights()));
                }
            }
        });
        
        HBox hBox = new HBox();
        Button button = button(FieldValue.HOME);
        button.setStyle(Utility.HOME_STYLE());
        button.setAlignment(Pos.CENTER);
        button.setOnAction(e ->
        {
            if(Configuration.getSession().isCustomerInSession())
                view.setTop(view.menuBar(view.ui_loggedUser(), view.airlines(), view.airports()));
            else view.setTop(view.ui_homeMenuBar());
            view.setCenter(view.ui_searchBarContainer(FieldValue.GLOBAL_SEARCH_ENGINE_LABEL));
        });
        
        hBox.getChildren().add(button);
        gridPane.add(hBox, 1, 8);
        
        view.setCenter(gridPane);
    }
    
    public void eventLaunchAirport(String airportName)
    {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Double x1 = primaryScreenBounds.getWidth()/2;
        Double y1 = primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - 770;
    
        Double x2 = primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 2000;
        
        arrivalScreen(airportName, x1, y1);
        departureScreen(airportName, x2, y1);
    }
    
    private void departureScreen(String airportName, Double x1, Double y1)
    {
        VBox departure = genericAirport(airportName, FieldValue.DEPARTURE_LABEL, x1, y1);
    
        GridPane gridPane = populateGridForDeparture(apiServiceImpl.getAllFlightsByAirport(airportName));
    
        Label header = new Label(FieldValue.DEPARTURE_HEADER.concat(airportName.toUpperCase()));
        header.setStyle(STYLE().concat(FONT_SIZE(20)).concat("-fx-padding: 8; -fx-border-padding: 10"));
    
        Button submit = new Button(FieldValue.REFRESH);
        submit.setStyle(GREEN());
    
        submit.setOnAction(e -> departure.getChildren().set(2, populateGridForDeparture
                (apiServiceImpl.getAllFlightsByAirport(airportName))));
    
        departure.getChildren().add(header);
        departure.getChildren().add(gridPane);
        departure.getChildren().add(submit);
    }
    
    private void arrivalScreen(String airportName, Double x1, Double y1)
    {
        VBox arrival = genericAirport(airportName, FieldValue.ARRIVAL_LABEL, x1, y1);
        
        GridPane gridPane = populateGridForArrival(apiServiceImpl.getAllFlightsByAirport(airportName));
    
        Label header = new Label(FieldValue.ARRIVAL_HEADER.concat(airportName.toUpperCase()));
    
        header.setStyle(STYLE().concat(FONT_SIZE(20)).concat("-fx-padding: 8; -fx-border-padding: 10"));
    
        Button submit = new Button(FieldValue.REFRESH);
        submit.setStyle(GREEN());
        
        submit.setOnAction(e -> arrival.getChildren().set(2,
                populateGridForArrival(apiServiceImpl.getAllFlightsByAirport(airportName))));
    
        arrival.getChildren().add(header);
        arrival.getChildren().add(gridPane);
        arrival.getChildren().add(submit);
    }
    
    public VBox genericAirport(String airportName, String label, Double x, Double y)
    {
        Stage stage = new Stage();
        stage.setMinWidth(900);
        stage.setMinHeight(600);
    
        VBox container = new VBox(new Label());
        container.setAlignment(Pos.TOP_CENTER);
        container.setPadding(new Insets(5, 5, 5, 5));
        
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.setTitle(Utility.TITTLE_BY(label, airportName));
        
        stage.setX(x);
        stage.setY(y);
        if(label.equalsIgnoreCase(FieldValue.ARRIVAL_LABEL)) view.ui_arrivalWindow(stage);
        else view.ui_departureWindow(stage);
        
        return container;
    }
    
    public GridPane populateGridForArrival(Set<Flight> flights)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(8);
        gridPane.setVgap(13);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        int row = 0;
        
        for(int i = 0; i < Utility.ARRIVAL_HEADERS().getChildren().size(); i++)
            gridPane.add(Utility.ARRIVAL_HEADERS().getChildren().get(i), i, row);
        row++;
    
        for(int i = 0; i < Utility.ARRIVAL_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(Flight flight : flights)
        {
            gridPane.add(button(flight.getFlightCode()), 0, row);
            gridPane.add(button(flight.getAirLine().getAirlineName()), 1, row);
            gridPane.add(button(flight.getAirplane().getAirPlaneName()), 2, row);
            gridPane.add(button(flight.getSource().getAirportName()), 3, row);
            gridPane.add(button(flight.getStatus()), 4, row);
            gridPane.add(button(Configuration.getCurrentDate().toString()), 5, row);
            row++;
        }
        
        return gridPane;
    }
    
    public GridPane populateGridForDeparture(Set<Flight> flights)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(8);
        gridPane.setVgap(13);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        int row = 0;
        
        for(int i = 0; i < Utility.DEPARTURE_HEADERS().getChildren().size(); i++)
            gridPane.add(Utility.DEPARTURE_HEADERS().getChildren().get(i), i, row);
        row++;
        
        for(int i = 0; i < Utility.DEPARTURE_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(Flight flight : flights)
        {
            gridPane.add(button(flight.getFlightCode()), 0, row);
            gridPane.add(button(flight.getAirLine().getAirlineName()), 1, row);
            gridPane.add(button(flight.getAirplane().getAirPlaneName()), 2, row);
            gridPane.add(button(flight.getDestination().getAirportName()), 3, row);
            gridPane.add(button(flight.getStatus()), 4, row);
            gridPane.add(button(Configuration.getCurrentDate().toString()), 5, row);
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
        label.setStyle(Utility.FONT_FAMILY(FieldValue.FONT_MONACO)+Utility.FONT_SIZE(15));
        
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
        Scene scene = new Scene(gridPane, 600, 450);
        
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
            try
            {
                if(apiServiceImpl.insertNewCustomer(firstName.getText(), lastName.getText(), email.getText(), password.getText()))
                {
                    stage.close();
                    AlertBox.DisplayInformation(FieldValue.NEW_CUSTOMER_ADDED,
                            Utility.NEW_CUSTOMER_ADDED_MSG(firstName.getText(), lastName.getText(), email.getText()));
                }
            }
            catch(Exception ex)
            {
                stage.close();
                if(AlertBox.DisplayError("Error Occurred Inserting New Customer", ex.getMessage()))
                {
                    firstName.setText(firstName.getText());
                    lastName.setText(lastName.getText());
                    email.setText(email.getText());
                    password.setText(password.getText());
                    stage.showAndWait();
                }
            }
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
    
    public void takeLoggedUserToSearchEngine()
    {
        view.setTop(view.menuBar(view.ui_loggedUser(), view.airlines(), view.airports()));
        view.setCenter(view.ui_searchBarContainer(FieldValue.GLOBAL_SEARCH_ENGINE_LABEL));
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
                    Set<Reservation> reservations = apiServiceImpl.getGlobalReservationsMadeUsingSearchEngine();
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
                    Configuration.getSession().addCustomerToSession(customer);
                    view.setTop(view.menuBar(view.searchEngine(), view.airlines(), view.airports()));
                    VBox center = customerCenterContainer(customer);
                    view.setCenter(center);
                }
                catch(InvalidUserNameException ex){}
                catch(InvalidPasswordException ex){}
            }
            else if(loginViewKey.equalsIgnoreCase(FieldValue.AR_AMERICAN)
                            || loginViewKey.equalsIgnoreCase(FieldValue.AR_JET_BLUE)
                            || loginViewKey.equalsIgnoreCase(FieldValue.AR_UNITED)
                            || loginViewKey.equalsIgnoreCase(FieldValue.AR_SPIRIT))
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
                    add.setStyle(STYLE().concat(FONT_SIZE(18)).concat("-fx-padding: 8; -fx-border-padding: 10"));
                    
                    cancel.setOnAction(event ->
                    {
                        view.ui_cancelFlightsByAirlineAdmin
                                (loginViewKey, apiServiceImpl.getAllFlightsByAirline(loginViewKey, Configuration.getCurrentDate()));
                    });
                    cancel.setStyle(STYLE().concat(FONT_SIZE(18)).concat("-fx-padding: 8; -fx-border-padding: 10"));
                    
                    rsvp.setOnAction(event -> view.RSVPsByAirline(loginViewKey,
                            view.ui_displayAllRSVPsByAirline(loginViewKey, Tester.testReservation())));
                    rsvp.setStyle(STYLE().concat(FONT_SIZE(18)).concat("-fx-padding: 8; -fx-border-padding: 10"));
                    
                    logout.setOnAction(event ->
                    {
                        Configuration.getSession().deleteAdminFromSession();
                        view.setHome();
                    });
                    
                    view.setTop(view.menuBar(view.airports()));
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
    
    public void cancelFlight(Integer flight, String airlineName)
    {
        if(AlertBox.DisplayConfirmation("?", "?"))
        {
            apiServiceImpl.cancelFlight(flight);
            view.ui_cancelFlightsByAirlineAdmin(airlineName, Tester.testFlights2());
        }
    }
    
    private VBox customerCenterContainer(Customer customer)
    {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);

        vBox.getChildren().add(new Label());
        vBox.getChildren().add(new Label());
        vBox.getChildren().add(customerNameHBox(customer));
        vBox.getChildren().add(new Label());
        Label label = new Label(FieldValue.RSVP_FLIGHT);
        label.setStyle(STYLE().concat(FONT_SIZE(18)).concat("-fx-padding: 8; -fx-border-padding: 10"));
        vBox.getChildren().add(label);
        vBox.getChildren().add(new Label());
        vBox.getChildren().add(populateRSVPsFlightsForCustomer(customer));
        vBox.getChildren().add(new Label());
        vBox.getChildren().add(new Label());
        
        HBox hBox = logoutHBox();
        Button logout = (Button)hBox.getChildren().get(0);
        logout.setStyle(Utility.LOGOUT_STYLE());
        logout.setOnAction(e ->
        {
            Configuration.getSession().deleteCustomerFromSession();
            view.setTop(view.ui_homeMenuBar());
            view.setCenter(view.ui_searchBarContainer(FieldValue.GLOBAL_SEARCH_ENGINE_LABEL));
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
        Label rsvpLabel = new Label(FieldValue.ALL_UPCOMING_RSVP);
        rsvpLabel.setStyle(STYLE().concat(FONT_SIZE(18)).concat("-fx-padding: 8; -fx-border-padding: 10"));
        
        gridPane.add(rsvpLabel, 0, row++, 4, 1);
    
        for(int i = 0; i < Utility.CUSTOMER_RSVP_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(int i = 0; i < Utility.CUSTOMER_RSVP_HEADERS().getChildren().size(); i++)
            gridPane.add(Utility.CUSTOMER_RSVP_HEADERS().getChildren().get(i), i, row);
        row++;
        
        for(int i = 0; i < Utility.CUSTOMER_RSVP_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(Reservation reservation : reservations)
        {
            gridPane.add(button(reservation.getFlight().getAirLine().getAirlineName()), 0, row);
            gridPane.add(button(reservation.getFlight().getAirplane().getAirPlaneName()), 1, row);
            gridPane.add(button(reservation.getFlight().getFlightCode()), 2, row);
            gridPane.add(button(reservation.getFlight().getSource().getAirportName()), 3, row);
            gridPane.add(button(reservation.getFlight().getDestination().getAirportName()), 4, row);
            gridPane.add(button(FieldValue.$.concat(reservation.getFlight().getFare().toString())), 5, row);
            gridPane.add(button(reservation.getRsvpDate().toString()), 6, row);
            gridPane.add(button(reservation.getStatus()), 7, row);
            
            if(FieldValue.ACTIVE.equalsIgnoreCase(reservation.getStatus()))
            {
                Button cancel = button(FieldValue.CLICK);
                gridPane.add(cancel, 8, row);
                cancel.setOnAction(e ->
                {
                    if(AlertBox.DisplayConfirmation(FieldValue.CANCEL_HEADER, FieldValue.CANCEL_MSG))
                    {
                        apiServiceImpl.cancelReservation(customer.getCustomerId(), reservation.getReservationId());
                        customer.setReservations(apiServiceImpl.getAllReservationsByCustomerId(customer.getCustomerId()));
                        VBox center = customerCenterContainer(customer);
                        view.setCenter(center);
                    }
                });
            }
            row++;
        }
    
        for(int i = 0; i < Utility.CUSTOMER_RSVP_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
    
        for(int i = 0; i < Utility.CUSTOMER_PAST_FLIGHTS_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
    
        Label flightsLabel = new Label(FieldValue.PAST_FLIGHTS);
    
        flightsLabel.setStyle(STYLE().concat(FONT_SIZE(18)).concat("-fx-padding: 8; -fx-border-padding: 10"));
        
        gridPane.add(flightsLabel, 1, row, 4, 1);
        row++;
    
        for(int i = 0; i < Utility.CUSTOMER_PAST_FLIGHTS_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
    
        int j = 1;
        for(int i = 0; i < Utility.CUSTOMER_PAST_FLIGHTS_HEADERS().getChildren().size(); i++)
            gridPane.add(Utility.CUSTOMER_PAST_FLIGHTS_HEADERS().getChildren().get(i), j++, row);
        row++;
        
        for(int i = 0; i < Utility.CUSTOMER_PAST_FLIGHTS_HEADERS().getChildren().size(); i++)
            gridPane.add(new Label(), i, row);
        row++;
        
        for(Flight flight : flights)
        {
            gridPane.add(button(flight.getAirLine().getAirlineName()), 1, row);
            gridPane.add(button(flight.getAirplane().getAirPlaneName()), 2, row);
            gridPane.add(button(flight.getFlightCode()), 3, row);
            gridPane.add(button(flight.getSource().getAirportName()), 4, row);
            gridPane.add(button(flight.getDestination().getAirportName()), 5, row);
            gridPane.add(button(FieldValue.$.concat(flight.getFare().toString())), 6, row);
            row++;
        }
        
        return gridPane;
    }
    
    private HBox customerNameHBox(Customer customer)
    {
        HBox name = new HBox();
        name.setAlignment(Pos.TOP_CENTER);
        Label label = new Label(FieldValue.USERNAME + customer.getFirstName() + " " + customer.getLastName());
        label.setStyle(Utility.NAME_HEADER_STYLE());
        name.getChildren().add(label);
        return name;
    }
    
    public HBox logoutHBox()
    {
        HBox outContainer = new HBox();
        outContainer.setAlignment(Pos.BOTTOM_CENTER);
        Button logout = new Button(FieldValue.LOGOUT_LABEL);
        outContainer.getChildren().add(logout);
        return outContainer;
    }
    
    public void eventGlobalSearchBar()
    {
        Set<Flight> flights = apiServiceImpl.getAllFlightsForReservation();
    
        GridPane center = view.ui_globalSearchResults(flights);
        
        view.setSearchResultsInCenter(center);
    }
    
    public void adminLogout()
    {
        Configuration.getSession().deleteAdminFromSession();
        view.setTop(view.ui_homeMenuBar());
        view.setCenter(view.ui_searchBarContainer(FieldValue.GLOBAL_SEARCH_ENGINE_LABEL));
    }
    
    public void handleLoggedUser()
    {
        if(Configuration.getSession().isCustomerInSession())
        {
            Customer customer = Configuration.getSession().getCustomer();
            view.setTop(view.menuBar(view.searchEngine(), view.airlines(), view.airports()));
            VBox center = customerCenterContainer(customer);
            view.setCenter(center);
        }
        else
        {
            AlertBox.DisplayInformation(FieldValue.NO_USER, FieldValue.NO_VALID_USER);
            view.setTop(view.ui_homeMenuBar());
            view.setCenter(view.ui_searchBarContainer(FieldValue.GLOBAL_SEARCH_ENGINE_LABEL));
        }
    }
    
    public boolean addFlightForAirline(String airline, TextField codeField, ChoiceBox<Airplane> airPlaneChoiceBox,
                                    ChoiceBox<Airport> sourceChoices, DatePicker sourceDate,
                                    ChoiceBox<String> sourceTimes, ChoiceBox<Airport> destinationChoices,
                                    DatePicker destinationDate, ChoiceBox<String> destinationTimes, TextField capacity1)
    {
        Flight flight = new Flight();
        flight.setAirLine(new Airline(airline));
        flight.setFlightCode(codeField.getText());
        flight.setAirplane(airPlaneChoiceBox.getValue());
        flight.setSource(new Source(sourceChoices.getValue().getAirportName(), sourceDate.getValue(), sourceTimes.getValue()));
        flight.setDestination(new Destination(destinationChoices.getValue().getAirportName(), destinationDate.getValue(), destinationTimes.getValue()));
        flight.setCapacity(Integer.parseInt(capacity1.getText()));
        
        return apiServiceImpl.insertFlightByAirline(flight);
    }
}
