package com.hrs.resources;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class FieldValue
{
    public static final String APP_TITLE = "Airline Reservation System";
    
    public static final String CUSTOMER = "Customer";
    public static final String CUSTOMER_LOGIN_LABEL = "Customer Login";
    
    public static final String AIRPORT_LABEL = "Airports";
    public static final String AIRLINE_LABEL = "Airlines";
    
    public static final String AR_AMERICAN = "American";
    public static final String AR_JET_BLUE = "JetBlue";
    public static final String AR_DELTA = "Delta";
    
    public static final String AP_LA = "LAX";
    public static final String AP_LAX_NAME = "Los Angeles I.A. - CA";
    public static final String AP_JFK = "JFK";
    public static final String AP_JFK_NAME = "John F. Kennedy I.A. - NY";
    public static final String AP_MI = "MI";
    public static final String AP_MI_NAME = "Michigan I.A. - MI";
    public static final String AP_BOSTON = "Boston";
    public static final String AP_BOSTON_NAME = "Boston I.A. - MA";
    public static final String AP_NEWARK = "Newark";
    public static final String AP_NEWARK_NAME = "Newark I.A. - NJ";
    public static final String AP_GEORGIA = "Georgia";
    public static final String AP_GEORGIA_NAME = "Georgia I.A. - GA";
    
    public static final String CUST_REG_FORM = "New Customer Registration Form";
    
    public static final String SELECT_DATE = "Please select a date: ";
    public static final String DATE_TITLE = "A future date for reservations";
    
    public static final String NEW_CUST_LABEL = "New Customer";
    
    public static final Integer CUST_FIRST_ROW = 1;
    public static final Integer CUST_FIRST_COL = 1;
    
    public static final Integer CUST_LAST_ROW = 2;
    public static final Integer CUST_LAST_COL = 1;
    
    public static final Integer CUST_EMAIL_ROW = 3;
    public static final Integer CUST_EMAIL_COL = 1;
    
    public static final Integer CUST_PASS_ROW = 4;
    public static final Integer CUST_PASS_COL = 1;
    
    public static final Integer NEW_CUST_SUB_ROW = 5;
    public static final Integer NEW_CUST_SUB_COL = 0;
    
    public static final String FIRSTNAME = "First Name : ";
    public static final String LASTNAME = "Last Name : ";
    
    public static final String USERNAME = "User Name : ";
    public static final String ADMIN_NAME = "Admin Name : ";
    
    public static final String ADMINS = "Admins";
    
    public static final String GLOBAL_SEARCH_ENGINE_LABEL = "Search Engine for Flights Reservation (SEFR)";
    
    public static final Double SEARCH_BAR_HEIGHT = 40.0;
    public static final Double SEARCH_BAR_WIDTH = 600.0;
    
    public static final Integer SEARCH_BAR_RAW = 2;
    public static final Integer SEARCH_BAR_COL = 0;
    
    public static final Integer LOGIN_SUBMIT_RAW = 4;
    public static final Integer LOGIN_SUBMIT_COL = 1;
    
    public static final Integer USERNAME_RAW = 1;
    public static final Integer USERNAME_COL = 1;
    
    public static final Integer PASSWORD_RAW = 2;
    public static final Integer PASSWORD_COL = 1;
    
    public static final Double LOGIN_WINDOW_WIDTH = 500.0;
    public static final Double LOGIN_WINDOW_HEIGHT = 300.0;
    
    public static final String EMAIL = "Email : ";
    public static final String PASSWORD = "Password : ";
    public static final String SUBMIT = "Submit";
    
    public static final String FONT_MONACO = "monaco";
    
    public static final Integer FONT_SIZE_17 = 17;
    
    public static final Double HOME_WINDOW_WIDTH = 1650.0;
    public static final Double HOME_WINDOW_HEIGHT = 1000.0;
    
    public static final Double HOME_SCENE_WIDTH = 1650.0;
    public static final Double HOME_SCENE_HEIGHT = 1000.0;
    
    public static final String ARRIVAL_LABEL = "arrival";
    
    public static final String DEPARTURE_LABEL = "departure";
    
    public static final String FLIGHT_CODE = "FLIGHT CODE";
    public static final String AIRLINE = "AIRLINE";
    public static final String AIRPLANE = "AIRPLANE";
    
    public static final String DEP_DATE_TIME = "DEP. DATE-TIME";
    public static final String ARR_DATE_TIME = "ARR. DATE-TIME";
    
    public static final String STATUS = "STATUS";
    public static final String TOTAL_RSVP = "TOTAL RSVP";
    public static final String DATE = "DATE";
    public static final String FARE = "FARE";
    public static final String SOURCE = "FROM";
    public static final String DESTINATION = "TO";
    
    public static final String CANCELABLE = "Cancelable flights for ";
    
    public static final String NO_ARRIVAL_FLIGHTS = "NO ARRIVAL FLIGHTS TO DISPLAY";
    public static final String NO_DEPARTURE_FLIGHTS = "NO DEPARTURE FLIGHTS TO DISPLAY";
    
    public static final String SE_ADMIN_LABEL = "Global Admin";
    public static final String SE_ADMIN_LOGIN_LABEL = "Global Admin Login";
    
    public static final String LOGIN_VIEW_KEY_GLOBAL = "global";
    public static final String LOGIN_VIEW_KEY_CUSTOMER = "customer";
    
    public static final String RV_DATE = "RSVP DATE";
    
    public static final String RSVP_BY_SE = "Displaying all reservations made using Search Engine";
    public static final String SEARCH_ENGINE_RESULTS = "Displaying all search results from Search Engine";
    public static final String RESULTS_LABEL = "Displaying all search results for ";
    
    public static final String RSVP_TITLE = "Displaying all RSVPs for ";
    public static final String ALL_FLIGHTS = "Displaying all flights for ";
    
    public static final String ADD_FLIGHT = "ADD FLIGHT";
    public static final String CANCEL_FLIGHT = "CANCEL FLIGHT";
    
    public static final String LOGOUT_LABEL = "Logout";
    
    public static final Double HOME_BTN_WIDTH = 150.0;
    
    public static final String TO_CANCEL = "TO CANCEL";
    public static final String LAST_NAME = "LAST NAME";
    
    public static final String RSVP_BY = "RSVP BY";
    public static final String RSVP_DATE = "RSVP DATE";
    
    public static final String SE = "SE";
    public static final String WP = "WP";
    public static final String AVAILABLE_SEAT = "AVL. SEAT";
    
    public static final String FULL = "FULL";
    public static final String OPEN = "OPEN";
    
    public static final String FLIGHT_ADDITION_SUCCESS_HEADER = "Flight has successfully been added";
    
    public static final String $ = "$";
    
    public static final String ON_TIME = "ON TIME";
    public static final String ACTIVE = "ACTIVE";
    public static final String CANCELED = "CANCELLED";
    public static final String CLICK = "CLICK ME";
    public static final String SEARCH_ENGINE = "Search Engine";
    
    public static final String LOGGED_USER = "User";
    public static final String ACCOUNT = "Account Info";
    
    public static final String TO_RSVP = "TO RSVP";
    
    public static final String REFRESH = "REFRESH";
    public static final String ARRIVAL_HEADER = "Arrival Flights for ";
    public static final String DEPARTURE_HEADER = "Departure Flights for ";
    
    public static final String RSVP_SUCCESS = "Reservation Has Been Successful";
    public static final String EXP_RSVP = "Express Reservation";
    public static final String HOME = "Home";
    
    public static final String NEW_CUSTOMER_ADDED = "A New Customer Has Successfully Been Added";
    
    public static final String RSVP_FLIGHT = "Reservation and Flight Histories";
    
    public static final String ALL_UPCOMING_RSVP = "All Future Reservations";
    
    public static final String CANCEL_HEADER = "Canceling this flight?";
    public static final String CANCEL_MSG = "Do you really want to cancel this flight?";
    
    public static final String PAST_FLIGHTS = "All Past Flights";
    
    public static final String NO_USER = "User Not In Session";
    
    public static final String NO_VALID_USER = "No valid user found in session to continue.";
    public static final String ENTER_CODE = "Enter a flight code/name: ";
    public static final String SELECT_AP = "Select an airplane: ";
    public static final String SELECT_SRC = "Select departure airport: ";
    public static final String SELECT_DPT_DATE = "Select departure date: ";
    public static final String SELECT_DPT_TIME = "Select departure time: ";
    public static final String SELECT_ARR = "Select arrival airport: ";
    public static final String SELECT_ARR_DATE = "Select arrival date: ";
    public static final String SELECT_ARR_TIME = "Select arrival time: ";
    public static final String ENTER_FARE = "Enter fare $: ";
    public static final String ENTER_CAP = "Enter max capacity: ";
    
    public static final String ALL_RSVP = "All RSVPs";
    
    public static final String EFFECT_LB = "lightblue";
    public static final String SORT_BY = "Sort Results By: ";
    
    public static final String KEY_WORDS = "Ex. find flights, get flights, all flights, flights etc.";
    
    public static final String INVALID_QUERY = "Invalid query!";
    public static final String INVALID_LOGIN = "Invalid login!";
    public static final String INVALID_INFO = "Provided information not suitable!";
    public static final String NO_USER_FOUND = "No user found with given username=";
    
    public static final String ADMIN_LOGIN_FOR = "Admin Login for ";
    public static final String LOGIN = "Login Page";
    
    public static final String INVALID_INSERT = "Invalid Info";
    
    public static final String UNK = "UNKNOWN";
    
    public static final Set<String> QUERIES =
            new LinkedHashSet <>(Arrays.asList("find flights", "get flights", "all flights", "flights"));
}
