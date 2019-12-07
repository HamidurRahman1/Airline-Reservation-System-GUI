package com.hrs.view.controller;

import com.hrs.configs.Configuration;
import com.hrs.exceptions.IllegalArgumentException;
import com.hrs.exceptions.InvalidLoginException;
import com.hrs.service.ApiService;
import com.hrs.view.alerts.AlertBox;
import com.hrs.view.models.Admin;
import com.hrs.view.models.Airline;
import com.hrs.view.models.Airplane;
import com.hrs.view.models.Airport;
import com.hrs.util.Utility;
import com.hrs.view.View;
import com.hrs.view.models.Customer;
import com.hrs.view.models.Destination;
import com.hrs.view.models.Flight;
import com.hrs.view.models.Reservation;
import com.hrs.view.models.Source;
import com.hrs.resources.FieldValue;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Set;

/**
 * A class that navigates views and talk to database
 */
public class Controller
{
    private View view = Configuration.GET_VIEW();
    private ApiService apiService = Configuration.GET_API_SERVICE();
    
    public Controller() {}
    
    public View getView()
    {
        return view;
    }
    
    public void setView(View view)
    {
        this.view = view;
    }
    
    public void handleInitialDate(DatePicker datePicker)
    {
        Configuration.SET_CURRENT_DATE(datePicker.getValue());
        view.start2();
    }
    
    public void makeReservationFromSE(Flight flight)
    {
        if(Configuration.GET_SESSION().isCustomerInSession())
        {
            try
            {
                apiService.makeReservationBySearchEngine(flight.getFlightId(), Configuration.GET_SESSION().getCustomer().getCustomerId());
                
                AlertBox.DisplayConfirmation(FieldValue.RSVP_SUCCESS, Utility.RSVP_CUSTOMER_MESSAGE
                        (Configuration.GET_SESSION().getCustomer().getFirstName().concat(" ")
                                      .concat(Configuration.GET_SESSION().getCustomer().getLastName())));
                
                view.setCenter(view.ui_globalSearchResults(apiService.getAllFlightsForReservation(Configuration.GET_QUERY())));
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        else
        {
            reservationWithUsernameAndPass(flight, 0);
        }
    }
    
    public void makeReservationByAirline(Flight flight)
    {
        if(Configuration.GET_SESSION().isCustomerInSession())
        {
            try
            {
                apiService.makeReservation(flight.getFlightId(), Configuration.GET_SESSION().getCustomer().getCustomerId());
                
                AlertBox.DisplayConfirmation(FieldValue.RSVP_SUCCESS, Utility.RSVP_CUSTOMER_MESSAGE
                        (Configuration.GET_SESSION().getCustomer().getFirstName().concat(" ")
                                      .concat(Configuration.GET_SESSION().getCustomer().getLastName())));
                
                view.setCenter(view.ui_searchResultsByAirline(flight.getAirLine().getAirlineName(),
                        apiService.getAllFlightsByAirlineForReservation(flight.getAirLine().getAirlineName())));
            }
            catch(Exception ex)
            {
                AlertBox.DisplayError(FieldValue.UNK, ex.getMessage());
            }
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
        
        HBox hBox = (HBox)Utility.GET_NODE
                (FieldValue.LOGIN_SUBMIT_RAW, FieldValue.LOGIN_SUBMIT_COL, gridPane);
        Button submit = (Button) hBox.getChildren().get(0);
        
        TextField username = (TextField) Utility.GET_NODE(FieldValue.USERNAME_RAW, FieldValue.USERNAME_COL, gridPane);
        TextField pass = (TextField) Utility.GET_NODE(FieldValue.PASSWORD_RAW, FieldValue.PASSWORD_COL, gridPane);
        
        Scene scene = new Scene(gridPane, FieldValue.LOGIN_WINDOW_WIDTH, FieldValue.LOGIN_WINDOW_HEIGHT);
        
        submit.setOnAction(e ->
        {
            stage.close();
            try
            {
                if(key == 0)
                {
                    if(apiService.makeReservationBySearchEngine(flight.getFlightId(), username.getText(), pass.getText()))
                    {
                        AlertBox.DisplayConfirmation(FieldValue.RSVP_SUCCESS,
                                Utility.RSVP_CUSTOMER_MESSAGE(username.getText()));
                    }
                    try
                    {
                        view.setCenter(view.ui_globalSearchResults(apiService.getAllFlightsForReservation(Configuration.GET_QUERY())));
                    }
                    catch(Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                }
                else
                {
                    if(apiService.makeReservation(flight.getFlightId(), username.getText(), pass.getText()))
                    {
                        AlertBox.DisplayConfirmation(FieldValue.RSVP_SUCCESS, Utility.RSVP_CUSTOMER_MESSAGE(username.getText()));
                    }
                    try
                    {
                        view.setCenter(view.ui_searchResultsByAirline(flight.getAirLine().getAirlineName(),
                                apiService.getAllFlightsByAirlineForReservation(flight.getAirLine().getAirlineName())));
                    }
                    catch(Exception ex1)
                    {
                        System.out.println(ex1.getMessage());
                    }
                }
            }
            catch(Exception ex)
            {
                AlertBox.DisplayError(FieldValue.INVALID_LOGIN, FieldValue.NO_USER_FOUND+username);
            }
        });
        stage.setScene(scene);
        stage.setTitle(FieldValue.CUSTOMER);
        stage.setAlwaysOnTop(true);
        stage.showAndWait();
    }
    
    public void handleAirlineGui(String airlineName)
    {
        if(Configuration.GET_SESSION().isCustomerInSession())
            view.setTop(view.ui_menuBar(view.ui_loggedUser(), view.ui_airlines(), view.ui_airports()));
        else view.setTop(view.ui_homeMenuBar());
        
        GridPane gridPane = view.ui_searchBarContainer(Utility.FIND_FLIGHTS_BY_LABEL(airlineName));
        
        TextField searchBar = (TextField)Utility.GET_NODE(FieldValue.SEARCH_BAR_RAW, FieldValue.SEARCH_BAR_COL, gridPane);
        
        searchBar.setOnAction(e ->
        {
            try
            {
                if(FieldValue.QUERIES.contains(searchBar.getText().toLowerCase()))
                {
                    Configuration.SET_QUERY(searchBar.getText().toLowerCase());
                    view.setCenter(view.ui_searchResultsByAirline
                            (airlineName, apiService.getAllFlightsByAirlineForReservation(airlineName)));
                }
                else AlertBox.DisplayError(FieldValue.INVALID_QUERY, "Invalid query. query="
                        .concat(searchBar.getText().concat("\nPlease use one of the valid query."
                                .concat(FieldValue.QUERIES.toString()))));
            }
            catch(Exception ex)
            {
                AlertBox.DisplayError(FieldValue.INVALID_QUERY, ex.getMessage());
            }
        });
        
        HBox hBox = new HBox();
        Button button = Utility.BUTTON(FieldValue.HOME); button.setMinWidth(FieldValue.HOME_BTN_WIDTH);
        button.setStyle(Utility.HOME_STYLE());
        button.setAlignment(Pos.CENTER);
        button.setOnAction(e ->
        {
            if(Configuration.GET_SESSION().isCustomerInSession())
                view.setTop(view.ui_menuBar(view.ui_loggedUser(), view.ui_airlines(), view.ui_airports()));
            else view.setTop(view.ui_homeMenuBar());
            view.setCenter(view.ui_searchBarContainer(FieldValue.GLOBAL_SEARCH_ENGINE_LABEL));
        });
        
        hBox.getChildren().add(button);
        gridPane.add(hBox, 1, 8);
        
        view.setCenter(gridPane);
    }
    
    public void handleAirportScreen(String airportName)
    {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Double x1 = primaryScreenBounds.getWidth()/2;
        Double y1 = primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - 770;
        
        Double x2 = primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 2000;
        
        Set<Flight> flights = apiService.getAllFlightsByAirport(airportName);
        
        view.ui_arrivalWindow(airportName, x1, y1, flights);
        view.ui_departureWindow(airportName, x2, y1, flights);
    }
    
    public Set<Flight> refreshAirportScreen(String airportName)
    {
        return apiService.getAllFlightsByAirport(airportName);
    }
    
    public void handleNewCustomer(String firstName, String lastName, String email, String password)
    {
        try
        {
            apiService.insertNewCustomer(firstName, lastName, email, password);
            
            AlertBox.DisplayInformation(FieldValue.NEW_CUST_LABEL,
                    FieldValue.NEW_CUSTOMER_ADDED.concat("\n")
                                                 .concat(FieldValue.USERNAME
                                                         .concat(firstName).concat(" ").concat(lastName)));
        }
        catch(IllegalArgumentException ex)
        {
            AlertBox.DisplayError(FieldValue.INVALID_INSERT, ex.getMessage());
            view.ui_newCustomerRegistration();
        }
        catch(Exception ex)
        {
            AlertBox.DisplayError(FieldValue.INVALID_INSERT, ex.getMessage());
            view.ui_newCustomerRegistration();
        }
    }
    
    public void handleCustomerLogin(String username, String password)
    {
        try
        {
            Customer customer = apiService.getCustomerByLogin(username, password);
            
            Configuration.GET_SESSION().addCustomerToSession(customer);
            
            view.setTop(view.ui_menuBar(view.ui_searchEngine(), view.ui_airlines(), view.ui_airports()));
            view.setCenter(view.ui_customerInfo(customer));
        }
        catch(InvalidLoginException ex)
        {
            AlertBox.DisplayError(FieldValue.INVALID_INSERT, ex.getMessage());
            view.ui_customerLogin();
        }
        catch(Exception ex)
        {
            AlertBox.DisplayError(FieldValue.INVALID_QUERY, ex.getMessage().concat("\n\n"));
            view.ui_customerLogin();
        }
    }
    
    public void handleGlobalAdminLogin(String username, String password)
    {
        try
        {
            Admin admin = apiService.getGlobalAdminByLogin(username, password);
            
            Configuration.GET_SESSION().addAdminToSession(admin);
            
            view.ui_handleAfterGlobalAdminLogin(admin, apiService.getGlobalReservationsMadeUsingSearchEngine());
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            AlertBox.DisplayError(FieldValue.INVALID_QUERY, ex.getMessage().concat("\n\n"));
            view.ui_globalAdminLogin();
        }
    }
    
    public void handleAirlineAdminLogin(String airlineName, String username, String password)
    {
        try
        {
            Admin admin = apiService.getAirlineAdminByLogin(airlineName, username, password);
            
            Configuration.GET_SESSION().addAdminToSession(admin);
            
            VBox adminAccessView = view.ui_adminAccessByAirline(admin, airlineName);
            
            view.setTop(view.ui_menuBar(view.ui_airports()));
            view.setCenter(adminAccessView);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            AlertBox.DisplayError(FieldValue.INVALID_LOGIN, ex.getMessage().concat("\n\n"));
        }
    }
    
    public void handleAddFlightForAirline(Admin admin, String airlineName)
    {
        try
        {
            Airline airline = apiService.getAirlineByName(airlineName);
            Set<Airport> airports = apiService.getAllAirports();
            Set<Airplane> airplanes = apiService.getAllAirPlaneByAirLine(airlineName);
            
            view.ui_addFlightForAirline(admin, airline, airports, airplanes);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    public void handleCancelFlightForAirline(Admin admin, String airlineName)
    {
        try
        {
            view.ui_cancelFlightsByAirlineAdmin
                    (airlineName, apiService.getAllFlightsByAirline(airlineName, Configuration.GET_CURRENT_DATE()));
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    public void handleAllRSVPsForAirline(String airlineName)
    {
        view.ui_displayRSVPsByAirline(airlineName,
                apiService.getAllReservationsMadeUsingSearchEngineAndAirlineGui(airlineName));
    }
    
    public void logoutCustomer()
    {
        Configuration.GET_SESSION().deleteCustomerFromSession();
        view.setHome();
    }
    
    public void logoutAdmin()
    {
        Configuration.GET_SESSION().deleteAdminFromSession();
        view.setHome();
    }
    
    public void takeLoggedUserToSearchEngine()
    {
        view.setTop(view.ui_menuBar(view.ui_loggedUser(), view.ui_airlines(), view.ui_airports()));
        view.setCenter(view.ui_searchBarContainer(FieldValue.GLOBAL_SEARCH_ENGINE_LABEL));
    }
    
    public void cancelReservationByCustomer(Customer customer, Reservation reservation)
    {
        if(AlertBox.DisplayConfirmation(FieldValue.CANCEL_HEADER, FieldValue.CANCEL_MSG))
        {
            apiService.cancelReservation(customer.getCustomerId(), reservation.getReservationId());
            
            customer.setReservations(apiService.getAllReservationsByCustomerId(customer.getCustomerId()));
            
            view.setCenter(view.ui_customerInfo(customer));
        }
    }
    
    public void cancelFlight(Integer flight, String airlineName, Stage stage)
    {
        if(AlertBox.DisplayConfirmation(FieldValue.CANCEL_HEADER, FieldValue.CANCEL_MSG))
        {
            try
            {
                stage.close();
                apiService.cancelFlight(flight);
                view.ui_cancelFlightsByAirlineAdmin(airlineName,
                        apiService.getAllFlightsByAirline(airlineName, Configuration.GET_CURRENT_DATE()));
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public void handleGlobalSearchBar(String query)
    {
        try
        {
            GridPane center = view.ui_globalSearchResults(apiService.getAllFlightsForReservation(query));
            view.setSearchResultsInCenter(center);
        }
        catch(Exception ex)
        {
            AlertBox.DisplayError(FieldValue.INVALID_QUERY, ex.getMessage().concat("\n\n"));
            view.setHome();
        }
    }
    
    public void handleLoggedUser()
    {
        if(Configuration.GET_SESSION().isCustomerInSession())
        {
            Customer customer = Configuration.GET_SESSION().getCustomer();
            view.setTop(view.ui_menuBar(view.ui_searchEngine(), view.ui_airlines(), view.ui_airports()));
            VBox center = view.ui_customerInfo(customer);
            view.setCenter(center);
        }
        else
        {
            AlertBox.DisplayInformation(FieldValue.NO_USER, FieldValue.NO_VALID_USER);
            view.setTop(view.ui_homeMenuBar());
            view.setCenter(view.ui_searchBarContainer(FieldValue.GLOBAL_SEARCH_ENGINE_LABEL));
        }
    }
    
    public void addFlightForAirline(Airline airline, TextField codeField, ChoiceBox<Airplane> airPlaneChoiceBox,
                                    ChoiceBox<Airport> sourceChoices, DatePicker sourceDate,
                                    ChoiceBox<String> sourceTimes, ChoiceBox<Airport> destinationChoices,
                                    DatePicker destinationDate, ChoiceBox<String> destinationTimes, TextField capacity1,
                                    TextField fare)
    {
        try
        {
            Flight flight = new Flight();
            flight.setAirLine(airline);
            flight.setFlightCode(codeField.getText());
            flight.setAirplane(airPlaneChoiceBox.getValue());
            
            flight.setSource(new Source(sourceChoices.getValue().getAirportId(), sourceChoices.getValue().getAirportName(), sourceDate.getValue(), sourceTimes.getValue()));
            flight.setDestination(new Destination(destinationChoices.getValue().getAirportId(), destinationChoices.getValue().getAirportName(), destinationDate.getValue(), destinationTimes.getValue()));
            
            flight.setCapacity(Integer.parseInt(capacity1.getText()));
            flight.setFare(Float.parseFloat(fare.getText()));
            
            apiService.insertFlightByAirline(flight);
        }
        catch(Exception ex)
        {
            AlertBox.DisplayError(FieldValue.INVALID_INSERT, ex.getMessage());
        }
    }
}
