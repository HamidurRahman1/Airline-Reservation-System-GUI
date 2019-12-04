
use cs370;

insert into Status (status)  values ('ON TIME'), ('CANCELLED'), ('ACTIVE');

insert into Airports (airportName) values ('JFK'), ('LAX'), ('MI'), ('Boston'), ('Newark'), ('Georgia');

insert into Airlines (airlineName) values ('American'), ('JetBlue'), ('Delta');

insert into Airplanes(airplaneName, airlineId) values
('A330', 1), ('B777', 1), 
('L980', 1), ('E670', 1), 
('A291', 2), ('B707', 2), 
('L925', 2), ('E655', 2), 
('A211', 3), ('B701', 3),
('L910', 3), ('E645', 3);

insert into Logins (email, password) values 
('m', 'm'),
('aa', 'aa'),
('jb', 'jb'),
('da', 'da'),
('hamidur@gmail.com', 'pass');

insert into Admins(firstName, lastName, airlineId, loginId) values
('Megatron', 'Live', null, 1),
('Hamidur', 'Rahman', 1, 2),
('Adnan', 'Rahin', 2, 3),
('Syed', 'Hussain', 3, 4);

insert into Customers(firstName, lastName, loginId) values
('Hamidur', 'Rahman', 5);


insert into Flights(flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('V1X9', 1, 1, 1, '90', 25, 5, 1, 'JFK',  '2019-12-05', '1:00 pm', 2, 'LAX', '2019-12-05', '10:00 pm');

insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat, 
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('P5Q', 1, 1, 1, '94', 25, 23, 1, 'JFK', '2019-12-13', '3:00 am', 4, 'Boston', '2019-12-13', '9:00 am');

insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('Y82Z', 2, 5, 1, '75', 25, 21, 2, 'LAX', '2019-12-13', '6:00 pm', 5, 'Boston', '2019-12-13', '9:00 pm');

insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('D5F3', 3, 9, 1, '87', 25, 19, 3, 'MI','2019-12-13', '12:00 pm', 6, 'Georgia', '2019-12-13', '6:00 pm');

insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('X34A', 3, 10, 1, '65', 25, 20, 3, 'MI', '2019-12-13', '3:00 am', 4, 'Boston', '2019-12-13', '9:00 am');

insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('UYT6', 2, 6, 1, '85', 25, 18, 6, 'Georgia', '2019-12-13', '6:00 pm', 3, 'MI', '2019-12-13', '9:00 pm');

insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('FGT1', 1, 2, 1, '105', 25, 5, 5, 'Newark', '2019-12-13', '12:00 pm', 2, 'LAX', '2019-12-13', '6:00 pm');

insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('W67A', 1, 4, 1, '120', 25, 17, 2, 'LAX', '2019-12-13', '3:00 am', 6, 'Georgia', '2019-12-13', '9:00 am');

insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('109F', 2, 8, 1, '125', 25, 15, 5, 'Newark', '2019-12-13', '6:00 pm', 4, 'Boston', '2019-12-13', '9:00 pm');

insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('KL1R', 3, 12, 1, '118', 25, 3, 1, 'JFK', '2019-12-13', '12:00 pm', 3, 'MI', '2019-12-13', '6:00 pm');

insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('6T7A', 3, 11, 1, '145', 25, 17, 5, 'Newark', '2019-12-13', '3:00 am', 2, 'LAX' , '2019-12-13', '9:00 am');

insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('1K9F', 2, 7, 1, '138', 25, 15, 2, 'LAX', '2019-12-13', '6:00 pm', 3, 'MI' , '2019-12-13', '9:00 pm');

insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('E41X', 1, 3, 1, '133', 25, 3, 3, 'MI', '2019-12-13', '12:00 pm', 2, 'LAX', '2019-12-13', '6:00 pm');

insert into Flights_Customers (flightId, customerId) values (1,1);

insert into Reservations (customerId, flightId, rsvpDate, rsvpStatus, rsvpBy) values
(1, 2, '2019-12-11', 'ACTIVE', 1);

insert into Reservations (customerId, flightId, rsvpDate, rsvpStatus, rsvpBy) values
(1, 7, '2019-12-10', 'CANCELLED', 0);

