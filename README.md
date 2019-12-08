<h4>Project 2: Airline-Reservation-System</h4>

<ul>
    <li>Students work in teams for this project.
        <ol>
            <li>A team consists of three students.</li>
            <li>There are totally 54 students registered for this course (in two sections), hence 18 teams.</li>
            <li>The members of a team need not all be in the same section.</li>
        </ol>
    </li>
</ul>

<ul>
    <li>Students form their own teams and inform me of the team membership (see below).
        <ol>
            <li>A student may not be in more than one team.</li>
            <li>A student found to be in multiple teams will fail automatically.</li>
            <li>Therefore do not allow your name to be used without your permission.</li>
        </ol>
    </li>
</ul>

<ul>
    <li>Please inform me of the names of your team members by Saturday 11/9/2019.
        <ol>
            <li>If you do not inform me of your membership in a team by Saturday 11/9/2019, I will
                place you at random with other students who have also not entered into teams.</li>
        </ol>
    </li>
</ul>

<ul>
    <li>Once a team is set, its membership may not be changed.
        <ol>
            <li>You may not switch teams (e.g. halfway through the project, etc.).</li>
            <li>If any students drop out of the course, I will deal with the situation on a case-by-case basis.</li>
        </ol>
    </li>
</ul>


<h4>2.1 GUI and data storage and coding</h4>

<ul>
    <li>This project requires a GUI (multiple screens).</li>
</ul>

<ul>
    <li>This project requires persistence across invocations.
        <ol>
            <li>“Persistence across invocations” means that all the data must be saved and must be available
             if your team’s program is switched off and restarted.</li>
            <li>This can be implemented by storing the data in a database.</li>
            <li>Alternatively, your team may store the data in hash tables (text files).</li>
        </ol>
    </li>
</ul>

<ul>
    <li>The details how “persistence across invocations” is implemented is up to your team.</li>
</ul>

<ul>
    <li>The program code (and database, if any) may be implemented using any language of your team’s choice.</li>
</ul>


<h4>2.2 Project description</h4>

<ul><li>This project is to create an online reservation system for airlines (flight bookings).</li></ul>
<ul><li>The project contains the following entities:
    <ol>
        <li>Customers</li>
        <li>Search engine</li>
        <li>Airlines</li>
        <li>Airports</li>
    </ol>
</li></ul>

<h4>Customer</h4>
<ul>
    <li>A customer can make reservations for flights.
        <ol>
            <li>A customer can go to a search engine (= GUI) to look up available airlines and flights and fares.</li>
            <li>Alternatively, a customer can go directly to the “web page” (= GUI) of an airline and make a
            reservation there.</li>
            <li>A customer can also cancel a reservation.</li>
        </ol>
    </li>
</ul>

<ul>
    <li>A customer must have a password protected account.
        <ol>
            <li>A customer can pull up a read-only GUI to display all his/her reservations.</li>
            <li>A customer is not allowed to see data for other customers.</li>
        </ol>
    </li>
</ul>

<h4>Airline</h4>
<ul>
    <li>
        An airline must have a “web page” (= GUI) which displays all its flights and fares.
        <ol>
            <li>An airline administrator can insert additional flights/fares and/or cancel flights.</li>
            <li>Customers can only read the flight/fares information but cannot edit it.</li>
        </ol>
    </li>
</ul>

<ul> <li>Customers can make reservations (and cancel) using an airline’s “web page” (= GUI). </li> </ul>

<ul>
    <li>Each flight has a max capacity (set by the airline administrator).
        <ol><li>If a flight is full, customers cannot make reservations for that flight. </li></ol>
    </li>
</ul>

<ul>
    <li>An airline must have a read-only GUI which displays all the reservations made by all the customers.
        <ol>
            <li>This GUI can be read only by an airline administrator, not by customers.</li>
        </ol>
    </li>
</ul>

<ul>
    <li>If a flight is cancelled, the accounts of all customers with bookings on that flight must be updated to indicate
their reservation was cancelled.</li>
</ul>


<h4>Airport</h4>

<ul>
    <li>An airport must have a “arrivals information screen” (= GUI) which displays all the arriving flights and times
    by all the airlines operating at that airport.
        <ol>
            <li>The GUI is public (no password required) and read-only.</li>
            <li>If an airline cancels a flight, a “cancelled” flight status must be displayed for that flight.</li>
            <li>Else display “on time” as the flight status.</li>
            <li>Therefore the GUI should have a timer to access information from the airline (database?) to check
             if the flight is cancelled, and the display should be updated to display changes.</li>
        </ol>
    </li>
    <li>An airport must have a “departures information screen” (= GUI) which displays all the departing flights and
    times by all the airlines operating at that airport.
        <ol>
            <li>The GUI is public (no password required) and read-only.</li>
            <li>If an airline cancels a flight, a “cancelled” flight status must be displayed for that flight.</li>
            <li>Else display “on time” as the flight status.</li>
            <li>Therefore the GUI should have a timer to access information from the airline (database?) to check if
            the flight is cancelled, and the display should be updated to display changes.</li>
        </ol>
    </li>
    <li>There is no “airport administrator” in the sense that all the GUIs for an airport are public
    (no password required) and read-only.</li>
</ul>

<h4>Search engine</h4>

<ul>
    <li>Customers can go to a “search engine” (= GUI) to look up information for flights and fares.</li>
    <li>Customers input selection criteria your team thinks are relevant, and the search engine displays
    the results of the query.</li>
    <li>Customers can sort the display by fares or airline names.</li>
    <li>If a flight is full the display must say so.</li>
    <li>Customers can make flight reservations using the search engine.</li>
    <li>Your team decides if customers can cancel a booking using the search engine or if they must go
    to the airline GUI to cancel a booking.</li>
    <li>
        The search engine administrator can view all the reservations made by all the customers using the search engine.
        <ol>
            <li>This GUI can be read only by the search engine administrator, not by customers and not by the airlines.</li>
            <li>The search engine administrator can only view reservations made using the search engine.</li>
            <li>The search engine administrator cannot view reservations that customers made using an airline’s GUI.</li>
        </ol>
    </li>
</ul>

