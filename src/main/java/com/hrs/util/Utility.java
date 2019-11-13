package com.hrs.util;

import com.hrs.configs.Configuration;
import com.hrs.view.models.Airplane;
import com.hrs.view.models.Airport;
import com.hrs.view.models.Flight;
import com.hrs.view.style.CSSStyle;
import com.hrs.view.util.FieldValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * A Utility/helper class for others usage. Provides support to other classes. Any method in here must be stand
 * alone method. All methods must be -- public static --. Other methods can exists but with must be private access.
 */
public class Utility
{
    public static final String HOME_PIC_PATH = "home.jpg";
    
    public static Node getNodeByRowColumnIndex (int row, int column, GridPane gridPane)
    {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();
        
        for (Node node : children)
        {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column)
            {
                result = node;
                break;
            }
        }
        return result;
    }
    
    public static Button button(String label)
    {
        Button button = new Button(label);
        button.setMinHeight(30);
        button.setMinWidth(130);
        button.setMaxHeight(70);
        button.setMaxWidth(500);
        return button;
    }
    
    public static Label label(String label)
    {
        return new Label(label);
    }
    
    public static HBox reservationHeaders()
    {
        HBox hBox = new HBox();
        
        Button b1 = button(FieldValue.FLIGHT_CODE); b1.setStyle(HEADERS_STYLE());
        Button b2 = button(FieldValue.SOURCE); b2.setStyle(HEADERS_STYLE());
        Button b3 = button(FieldValue.DESTINATION); b3.setStyle(HEADERS_STYLE());
        Button b4 = button(FieldValue.AIRLINE); b4.setStyle(HEADERS_STYLE());
        Button b5 = button(FieldValue.AIRPLANE); b5.setStyle(HEADERS_STYLE());
        Button b6 = button(FieldValue.FARE); b6.setStyle(HEADERS_STYLE());
        Button b7 = button(FieldValue.RV_DATE); b7.setStyle(HEADERS_STYLE());
        
        hBox.getChildren().addAll(b1, b2, b3, b4, b5, b6, b7);
        
        return hBox;
    }
    
    public static HBox ARRIVAL_HEADERS()
    {
        HBox hBox = new HBox();
    
        Button b1 = button(FieldValue.FLIGHT_CODE); b1.setStyle(HEADERS_STYLE());
        Button b2 = button(FieldValue.AIRLINE); b2.setStyle(HEADERS_STYLE());
        Button b3 = button(FieldValue.AIRPLANE); b3.setStyle(HEADERS_STYLE());
        Button b4 = button(FieldValue.SOURCE); b4.setStyle(HEADERS_STYLE());
        Button b5 = button(FieldValue.STATUS); b5.setStyle(HEADERS_STYLE());
        Button b6 = button(FieldValue.DATE); b6.setStyle(HEADERS_STYLE());
    
        hBox.getChildren().addAll(b1, b2, b3, b4, b5, b6);
    
        return hBox;
    }
    
    public static HBox DEPARTURE_HEADERS()
    {
        HBox hBox = new HBox();
    
        Button b1 = button(FieldValue.FLIGHT_CODE); b1.setStyle(HEADERS_STYLE());
        Button b2 = button(FieldValue.AIRLINE); b2.setStyle(HEADERS_STYLE());
        Button b3 = button(FieldValue.AIRPLANE); b3.setStyle(HEADERS_STYLE());
        Button b4 = button(FieldValue.DESTINATION); b4.setStyle(HEADERS_STYLE());
        Button b5 = button(FieldValue.STATUS); b5.setStyle(HEADERS_STYLE());
        Button b6 = button(Configuration.getCurrentDate().toString()); b6.setStyle(HEADERS_STYLE());
    
    
        hBox.getChildren().addAll(b1, b2, b3, b4, b5, b6);
        
        return hBox;
    }
    
    public static HBox CUSTOMER_RSVP_HEADERS()
    {
        HBox hBox = new HBox();
    
        Button b1 = button(FieldValue.AIRLINE); b1.setStyle(HEADERS_STYLE());
        Button b2 = button(FieldValue.AIRPLANE); b2.setStyle(HEADERS_STYLE());
        Button b3 = button(FieldValue.FLIGHT_CODE); b3.setStyle(HEADERS_STYLE());
        Button b4 = button(FieldValue.SOURCE); b4.setStyle(HEADERS_STYLE());
        Button b5 = button(FieldValue.DESTINATION); b5.setStyle(HEADERS_STYLE());
        Button b6 = button(FieldValue.FARE); b6.setStyle(HEADERS_STYLE());
        Button b7 = button(FieldValue.RV_DATE); b7.setStyle(HEADERS_STYLE());
        Button b8 = button(FieldValue.STATUS); b8.setStyle(HEADERS_STYLE());
        Button b9 = button(FieldValue.TO_CANCEL); b9.setStyle(HEADERS_STYLE());
    
    
        hBox.getChildren().addAll(b1, b2, b3, b4, b5, b6, b7, b8, b9);
        
        return hBox;
    }
    
    public static HBox CUSTOMER_PAST_FLIGHTS_HEADERS()
    {
        HBox hBox = new HBox();
    
        Button b1 = button(FieldValue.AIRLINE); b1.setStyle(HEADERS_STYLE());
        Button b2 = button(FieldValue.AIRPLANE); b2.setStyle(HEADERS_STYLE());
        Button b3 = button(FieldValue.FLIGHT_CODE); b3.setStyle(HEADERS_STYLE());
        Button b4 = button(FieldValue.SOURCE); b4.setStyle(HEADERS_STYLE());
        Button b5 = button(FieldValue.DESTINATION); b5.setStyle(HEADERS_STYLE());
        Button b6 = button(FieldValue.FARE); b6.setStyle(HEADERS_STYLE());
    
    
        hBox.getChildren().addAll(b1, b2, b3, b4, b5, b6);
        
        return hBox;
    }
    
    public static HBox GLOBAL_SEARCH_FLIGHT_HEADERS()
    {
        HBox hBox = new HBox();
        
        Button b1 = button(FieldValue.FLIGHT_CODE); b1.setStyle(HEADERS_STYLE());
        Button b2 = button(FieldValue.SOURCE); b2.setStyle(HEADERS_STYLE());
        Button b3 = button(FieldValue.DEP_DATE_TIME); b3.setStyle(HEADERS_STYLE());
        Button b4 = button(FieldValue.DESTINATION); b4.setStyle(HEADERS_STYLE());
        Button b5 = button(FieldValue.ARR_DATE_TIME); b5.setStyle(HEADERS_STYLE());
        Button b6 = button(FieldValue.AIRLINE); b6.setStyle(HEADERS_STYLE());
        Button b7 = button(FieldValue.AIRPLANE); b7.setStyle(HEADERS_STYLE());
        Button b8 = button(FieldValue.FARE); b8.setStyle(HEADERS_STYLE());
        Button b9 = button(FieldValue.AVAILABLE_SEAT); b9.setStyle(HEADERS_STYLE());
        Button b10 = button(FieldValue.STATUS); b10.setStyle(HEADERS_STYLE());
        Button b11 = button(FieldValue.TO_RSVP); b11.setStyle(HEADERS_STYLE());
        
        hBox.getChildren().addAll(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11);
        
        return hBox;
    }
    
    public static String HEADERS_STYLE()
    {
        return "-fx-padding: 8 15 15 15;" +
            "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;" +
            "-fx-background-radius: 8;" +
            "-fx-background-color:" +
            "linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),"+
            "#9d4024, #d86e3a,"+
            "radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);"+
            "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );"+
            "-fx-font-weight: bold;"+
            "-fx-font-size: 1.1em;" + "-fx-text-fill: white";
    }
    
    public static HBox AIRLINE_SEARCH_FLIGHT_HEADERS()
    {
        HBox hBox = new HBox();
    
        Button b1 = button(FieldValue.FLIGHT_CODE); b1.setStyle(HEADERS_STYLE());
        Button b2 = button(FieldValue.SOURCE); b2.setStyle(HEADERS_STYLE());
        Button b3 = button(FieldValue.DEP_DATE_TIME); b3.setStyle(HEADERS_STYLE());
        Button b4 = button(FieldValue.DESTINATION); b4.setStyle(HEADERS_STYLE());
        Button b5 = button(FieldValue.ARR_DATE_TIME); b5.setStyle(HEADERS_STYLE());
        Button b6 = button(FieldValue.AIRLINE); b6.setStyle(HEADERS_STYLE());
        Button b7 = button(FieldValue.AIRPLANE); b7.setStyle(HEADERS_STYLE());
        Button b8 = button(FieldValue.FARE); b8.setStyle(HEADERS_STYLE());
        Button b9 = button(FieldValue.AVAILABLE_SEAT); b9.setStyle(HEADERS_STYLE());
        Button b10 = button(FieldValue.STATUS); b10.setStyle(HEADERS_STYLE());
        Button b11 = button(FieldValue.TO_RSVP); b11.setStyle(HEADERS_STYLE());
    
    
        hBox.getChildren().addAll(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11);
        
        return hBox;
    }
    
    public static String STYLE()
    {
        return "-fx-background-color:"+
        "linear-gradient(#f2f2f2, #d6d6d6),"+
                "linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),"+
                "linear-gradient(#dddddd 0%, #f6f6f6 50%);"+
        "-fx-background-radius: 8,7,6;"+
        "-fx-background-insets: 0,1,2;"+
        "-fx-text-fill: black;"+
        "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );";
    }
    
    public static Label SE_HEADER()
    {
        Label l = new Label(FieldValue.SEARCH_ENGINE_RESULTS);
        l.setAlignment(Pos.CENTER);
        l.setStyle(STYLE()
                .concat(CSSStyle.fontFamily(FieldValue.FONT_MONACO)
                                .concat(CSSStyle.fontSize(18))).concat("-fx-padding: 8; -fx-border-padding: 10"));
        return l;
    }
    
    public static Label SORT_LABEL()
    {
        Label l = new Label(FieldValue.SORT_BY);
        l.setAlignment(Pos.CENTER);
        l.setStyle(CSSStyle.fontFamily(FieldValue.FONT_MONACO).concat(CSSStyle.fontSize(18)).concat(STYLE()));
        return l;
    }
    
    public static Label SEARCH_HEADER_LABEL(String label)
    {
        Label l = new Label(label);
        l.setAlignment(Pos.CENTER);
        l.setStyle(CSSStyle.fontFamily(FieldValue.FONT_MONACO)
                           .concat(CSSStyle.fontSize(18))
                           .concat(CSSStyle.effect(FieldValue.EFFECT_LB)));
        return l;
    }
    
    public static Label AIRLINE_RESULTS_HEADER_LABEL(String airline)
    {
        Label l = new Label(FieldValue.RESULTS_LABEL.concat(" ").concat(airline));
        l.setAlignment(Pos.CENTER);
        l.setStyle(CSSStyle.fontFamily(FieldValue.FONT_MONACO)
                           .concat(CSSStyle.fontSize(15)));
        return l;
    }
    
    public static Insets FLIGHT_LABEL()
    {
        return new Insets(8, 8, 8, 8);
    }
    
    public static Set<String> TIMES()
    {
        Set<String> times = new LinkedHashSet <>();
        
        times.add("12:00 am");
        times.add("3:00 am");
        times.add("6:00 am");
        times.add("9:00 am");
        times.add("12:00 pm");
        times.add("3:00 pm");
        times.add("6:00 pm");
        times.add("9:00 pm");
        
        return times;
    }
    
    public static HBox ADMIN_CANCEL_FLIGHT_HEADERS()
    {
        HBox hBox = new HBox();
    
        Button b1 = button(FieldValue.FLIGHT_CODE); b1.setStyle(HEADERS_STYLE());
        Button b2 = button(FieldValue.AIRPLANE); b2.setStyle(HEADERS_STYLE());
        Button b3 = button(FieldValue.SOURCE); b3.setStyle(HEADERS_STYLE());
        Button b4 = button(FieldValue.DEP_DATE_TIME); b4.setStyle(HEADERS_STYLE());
        Button b5 = button(FieldValue.DESTINATION); b5.setStyle(HEADERS_STYLE());
        Button b6 = button(FieldValue.ARR_DATE_TIME); b6.setStyle(HEADERS_STYLE());
        Button b7 = button(FieldValue.TOTAL_RSVP); b7.setStyle(HEADERS_STYLE());
        Button b8 = button(FieldValue.STATUS); b8.setStyle(HEADERS_STYLE());
        Button b9 = button(FieldValue.TO_CANCEL); b9.setStyle(HEADERS_STYLE());
        
        hBox.getChildren().addAll(b1, b2, b3, b4, b5, b6, b7, b8, b9);
        
        return hBox;
    }
    
    public static HBox ADMIN_VIEW_RESERVATION_HEADERS()
    {
        HBox hBox = new HBox();
    
        Button b1 = button(FieldValue.FLIGHT_CODE); b1.setStyle(HEADERS_STYLE());
        Button b2 = button(FieldValue.AIRPLANE); b2.setStyle(HEADERS_STYLE());
        Button b3 = button(FieldValue.SOURCE); b3.setStyle(HEADERS_STYLE());
        Button b4 = button(FieldValue.DESTINATION); b4.setStyle(HEADERS_STYLE());
        Button b5 = button(FieldValue.LAST_NAME); b5.setStyle(HEADERS_STYLE());
        Button b6 = button(FieldValue.STATUS); b6.setStyle(HEADERS_STYLE());
        Button b7 = button(FieldValue.RSVP_BY); b7.setStyle(HEADERS_STYLE());
        Button b8 = button(FieldValue.RSVP_DATE); b8.setStyle(HEADERS_STYLE());
        
        hBox.getChildren().addAll(b1, b2, b3, b4, b5, b6, b7, b8);
        
        return hBox;
    }
    
    public static Set<Flight> SORT_BY_AIRLINE(Set<Flight> flights)
    {
        List<Flight> list = new LinkedList <>(flights);
    
        Collections.sort(list, new Comparator <Flight>()
        {
            @Override
            public int compare(Flight o1, Flight o2)
            {
                return o1.getAirLine().getAirlineName().compareTo(o2.getAirLine().getAirlineName());
            }
        });
        
        return new LinkedHashSet <>(list);
    }
    
    public static Set<Flight> SORT_BY_FARE(Set<Flight> flights)
    {
        List<Flight> list = new LinkedList <>(flights);
        
        Collections.sort(list, new Comparator <Flight>()
        {
            @Override
            public int compare(Flight o1, Flight o2)
            {
                return o1.getFare().compareTo(o2.getFare());
            }
        });
        
        return new LinkedHashSet <>(list);
    }
    
    public static Background BACKGROUND_IMAGE_BY_AIRLINE(String path)
    {
        BackgroundImage backgroundImage = new BackgroundImage(new Image(new File(new File("")
                        .getAbsolutePath()+"/src/main/java/com/hrs/resources/"+path).toURI().toString(),true),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
        return new Background(backgroundImage);
    }
    
    public static ObservableList<Airplane> AIRPLANES_LIST(Set<Airplane> airplanes)
    {
        List<Airplane> airplanes1 = new LinkedList <>(airplanes);
        return FXCollections.observableArrayList(airplanes1);
    }
    
    public static ObservableList<Airport> AIRPORTS_LIST(Set<Airport> airports)
    {
        List<Airport> airports1 = new LinkedList <>(airports);
        return FXCollections.observableArrayList(airports1);
    }
    
    public static ObservableList<String> TIMES_LIST()
    {
        List<String> airports1 = new LinkedList <>(Utility.TIMES());
        return FXCollections.observableArrayList(airports1);
    }
    
    public static String RSVP_CUSTOMER_MESSAGE(String name)
    {
        return "Successfully reserved a seat for user="+name+".\n"
                + "Please check your account to verify.\n\n";
    }
    
    public static String FIND_FLIGHTS_BY_LABEL(String airlineName)
    {
        return "Find Flights for ".concat(airlineName);
    }
    
    public static String TITTLE_BY(String label, String airportName)
    {
        return "Displaying all " + label + " flights for " + airportName;
    }
    
    public static String NEW_CUSTOMER_ADDED_MSG(String first, String last, String email)
    {
        return "A customer has successfully been added.\n"
                .concat("Name: ").concat(first).concat(" ").concat(last).concat(".\n")
                .concat("Email: ").concat(email).concat("\n\n");
    }
    
    public static String ALL_ACTIVE_FLIGHT(String loginViewKey)
    {
        return "All active flights for ".concat(loginViewKey.toUpperCase());
    }
    
    public static String ADD_FLIGHT_FOR(String airline)
    {
        return "Adding a flight for ".concat(airline.toUpperCase());
    }
    
    public static String FLIGHT_BY_ADMIN(String firstName, String airline)
    {
        return "A flight has successfully been added by "
                .concat(firstName).concat(" ").concat( "for Airline ".concat(airline.toUpperCase()).concat("\n\n"));
    }
    
    public static String ACCESS(String airline)
    {
        return "Admin Access Enabled for ".concat(airline.toUpperCase());
    }
    
    public static SeparatorMenuItem SEPARATOR()
    {
        return new SeparatorMenuItem();
    }
    
    public static String SORT_FARE()
    {
        return STYLE() + "-fx-background-color: lightgreen;";
    }
    
    public static String SORT_AIRLINE()
    {
        return STYLE() + "-fx-background-color: lightblue; ";
    }
    
    public static String FULL_BTN_STYLE()
    {
        return "";
    }
    
    public static String OPEN_BTN_STYLE()
    {
        return "";
    }
    
    public static String CLICK_ME()
    {
        return "-fx-background-color:"+
        "linear-gradient(#ffd65b, #e68400),"+
                "linear-gradient(#ffef84, #f2ba44),"+
        " linear-gradient(#ffea6a, #efaa22),"+
        "linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),"+
        " linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));"+
        " -fx-background-radius: 30;"+
        "-fx-background-insets: 0,1,2,3,0;"+
        " -fx-text-fill: #654b00;"+
        " -fx-font-weight: bold;"+
        " -fx-font-size: 14px;"+
        " -fx-padding: 10 20 10 20;";
    }
    
    public static String TO_CANCEL_STYLE()
    {
        return "-fx-background-color: linear-gradient(#ff5400, #be1d00);"+
        "-fx-background-radius: 30;"+
        "-fx-background-insets: 0;"+
        "-fx-text-fill: white;";
    }
    
    public static String TO_RSVP_STYLE()
    {
        return  "-fx-background-color:"+
                "linear-gradient(#f0ff35, #a9ff00),"+
                "radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);"+
                "-fx-background-radius: 6, 5;"+
                "-fx-background-insets: 0, 1;"+
                "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"+
                "-fx-text-fill: #395306;";
    }
    
    public static String HOME_STYLE()
    {
        return Utility.STYLE().concat("-fx-background-color: black; -fx-text-fill: white; -fx-font-wight: bold;");
    }
}
