CREATE TABLE customer_info(
	customer_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_first_name VARCHAR(30) NOT NULL,
    customer_last_name varchar(30),
    customer_email varchar(30) NOT NULL UNIQUE
);

CREATE TABLE airline_info(
	airline_id INT PRIMARY KEY AUTO_INCREMENT,
    airline_name VARCHAR(30) NOT NULL
);

CREATE TABLE airport_info(
	airport_id INT PRIMARY KEY AUTO_INCREMENT,
    airport_name VARCHAR(30) NOT NULL
);

CREATE TABLE source_info(
	source_id INT PRIMARY KEY AUTO_INCREMENT,
    airport_id INT NOT NULL,
    FOREIGN KEY (airport_id) REFERENCES airport_info(airport_id) ON DELETE CASCADE
);

CREATE TABLE destination_info(
	destination_id INT PRIMARY KEY AUTO_INCREMENT,
    airport_id INT NOT NULL,
    FOREIGN KEY (airport_id) REFERENCES airport_info(airport_id) ON DELETE CASCADE
);

CREATE TABLE customer_login(
	custlogin_id INT PRIMARY KEY AUTO_INCREMENT,
    cust_username VARCHAR(60) NOT NULL,
    cust_password VARCHAR(60) NOT NULL,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customer_info(customer_id) ON DELETE CASCADE
);

CREATE TABLE airline_flight_info(
	airline_flight_id INT PRIMARY KEY AUTO_INCREMENT,
    airline_flight_name VARCHAR(10),
    airline_id INT,
    fare DECIMAL(13, 4),
    flight_max_capacity INT NOT NULL,
    flight_current_capacity INT,
    FOREIGN KEY (airline_id) REFERENCES airline_info(airline_id) ON DELETE CASCADE
);

CREATE TABLE airline_admin(
	airline_admin_id INT PRIMARY KEY AUTO_INCREMENT,
    airline_id INT,
    FOREIGN KEY (airline_id) REFERENCES airline_info(airline_id) ON DELETE CASCADE
);

CREATE TABLE airline_admin_login(
	airline_admin_login_id INT PRIMARY KEY AUTO_INCREMENT,
    airline_admin_id INT,
    admin_username VARCHAR(60) NOT NULL UNIQUE,
    admin_password VARCHAR(60) NOT NULL,
    FOREIGN KEY(airline_admin_id) REFERENCES airline_admin(airline_admin_id) ON DELETE CASCADE
);

CREATE TABLE available_flight(
	available_flight_id INT PRIMARY KEY AUTO_INCREMENT,
    airline_flight_id INT,
    available_date DATE NOT NULL,
    FOREIGN KEY(airline_flight_id) REFERENCES airline_flight_info(airline_flight_id) ON DELETE CASCADE
);

CREATE TABLE flight_status(
	flight_status_id INT PRIMARY KEY AUTO_INCREMENT,
    airline_flight_id INT,
    flight_status_info VARCHAR(20) NOT NULL,
	FOREIGN KEY(airline_flight_id) REFERENCES airline_flight_info(airline_flight_id) ON DELETE CASCADE
);

CREATE TABLE reservation_info(
	reservation_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    reservation_date DATE,
    FOREIGN KEY (customer_id) REFERENCES customer_info(customer_id) ON DELETE CASCADE
);

CREATE TABLE arrival_info(
	arrival_id INT PRIMARY KEY AUTO_INCREMENT,
    airport_id INT NOT NULL,
    airline_flight_id INT NOT NULL,
    flight_status_id INT NOT NULL,
    FOREIGN KEY(airline_flight_id) REFERENCES airline_flight_info(airline_flight_id) ON DELETE CASCADE,
    FOREIGN KEY (airport_id) REFERENCES airport_info(airport_id) ON DELETE CASCADE,
    FOREIGN KEY (flight_status_id) REFERENCES flight_status(flight_status_id) ON DELETE CASCADE
);

CREATE TABLE departures_info(
	departures_id INT PRIMARY KEY AUTO_INCREMENT,
    airport_id INT NOT NULL,
    airline_flight_id INT NOT NULL,
    flight_status_id INT NOT NULL,
    FOREIGN KEY(airline_flight_id) REFERENCES airline_flight_info(airline_flight_id) ON DELETE CASCADE,
    FOREIGN KEY (airport_id) REFERENCES airport_info(airport_id) ON DELETE CASCADE,
    FOREIGN KEY (flight_status_id) REFERENCES flight_status(flight_status_id) ON DELETE CASCADE
);

CREATE TABLE flight_info(
	flight_info_id INT PRIMARY KEY AUTO_INCREMENT,
    reservation_id INT,
    airline_flight_id INT NOT NULL,
    flight_date DATE NOT NULL,
    source_id INT NOT NULL,
    destination_id INT NOT NULL,
    FOREIGN KEY (reservation_id) REFERENCES reservation_info(reservation_id) ON DELETE CASCADE,
    FOREIGN KEY (airline_flight_id) REFERENCES airline_flight_info(airline_flight_id) ON DELETE CASCADE,
    FOREIGN KEY (source_id) REFERENCES source_info(source_id) ON DELETE CASCADE,
    FOREIGN KEY (destination_id) REFERENCES destination_info(destination_id) ON DELETE CASCADE
);