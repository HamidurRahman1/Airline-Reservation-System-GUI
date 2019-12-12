
use cs370;

insert into Status (status)  values ('ON TIME'), ('CANCELLED'), ('ACTIVE');

insert into Airports (airportName) values ('JFK'), ('LAX'), ('MI'), ('Boston'), ('Newark'), ('Georgia');

insert into Airlines (airlineName) values ('American'), ('Delta'), ('JetBlue');

insert into Airplanes(airplaneName, airlineId) values
('A330', 1), ('B777', 1), 
('L980', 1), ('E670', 1), 
('A291', 2), ('B707', 2), 
('L925', 2), ('E655', 2), 
('A211', 3), ('B701', 3),
('L910', 3), ('E645', 3);

insert into Logins (email, password) values 
('megatron@gmail.com', 'megatron123'),
('adnan@gmail.com', 'adnan123'),
('syed@gmail.com', 'syed123'),
('hamidur@gmail.com', 'hamidur123'),
('random@gmail.com', 'random123');

insert into Admins(firstName, lastName, airlineId, loginId) values
('Megatron', 'Live', null, 1),
('Adnan', 'Rahin', 1, 2),
('Syed', 'Hussain', 2, 3),
('Hamidur', 'Rahman', 3, 4);

insert into Customers(firstName, lastName, loginId) values
('Random', 'User', 5);


insert into Flights(flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('V1X9', 1, 1, 1, '90', 25, 5, 1, 'JFK',  '2019-12-05', '1:00 pm', 2, 'LAX', '2019-12-05', '10:00 pm');

insert into Flights_Customers (flightId, customerId) values (1,1);




insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('T101', 1, 2, 1, 87, 25, 19, 1, 'JFK','2019-12-12', '12:00 pm', 6, 'Georgia', '2019-12-12', '6:00 pm');

insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('T102', 1, 4, 1, 78, 25, 15, 1, 'JFK','2019-12-12', '12:00 am', 3, 'MI', '2019-12-12', '6:00 am');


insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('T103', 2, 5, 1, 65, 25, 20, 3, 'MI', '2019-12-12', '3:00 pm', 4, 'Boston', '2019-12-12', '9:00 pm');

insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('T104', 2, 7, 1, 65, 25, 20, 4, 'Boston', '2019-12-12', '3:00 pm', 3, 'MI', '2019-12-12', '9:00 pm');

insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('T106', 3, 10, 1, 87, 25, 19, 6, 'Georgia','2019-12-12', '12:00 pm', 5, 'Newark', '2019-12-12', '6:00 pm');

insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('T107', 1, 4, 1, 145, 25, 17, 2, 'LAX', '2019-12-12', '3:00 pm', 1, 'JFK' , '2019-12-12', '9:00 pm');

insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('T105', 3, 11, 1, 145, 25, 17, 5, 'Newark', '2019-12-12', '3:00 pm', 2, 'LAX' , '2019-12-12', '9:00 pm');

insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('T108', 3, 12, 1, 145, 25, 17, 4, 'Boston', '2019-12-12', '3:00 pm', 2, 'LAX' , '2019-12-12', '9:00 pm');






insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat, 
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('P5Q', 1, 1, 1, 94, 25, 0, 1, 'JFK', '2019-12-13', '3:00 am', 4, 'Boston', '2019-12-13', '9:00 am');

insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('E41X', 1, 3, 1, 133, 25, 3, 3, 'MI', '2019-12-13', '12:00 pm', 6, 'Georgia', '2019-12-13', '6:00 pm');


insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('UYT6', 2, 6, 1, 85, 25, 16, 6, 'Georgia', '2019-12-13', '6:00 pm', 3, 'MI', '2019-12-13', '9:00 pm');

insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('109F', 2, 8, 1, 125, 25, 20, 4, 'Boston', '2019-12-14', '6:00 pm', 1, 'JFK', '2019-12-14', '9:00 pm');


insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('D5F3', 3, 9, 1, 87, 25, 19, 1, 'JFK','2019-12-14', '12:00 pm', 6, 'Georgia', '2019-12-14', '6:00 pm');

insert into Flights (flightCode, airlineId, airplaneId, statusId, fare, capacity, avlSeat,
dept_airportId, dept_airportName, dept_date, dept_time, arri_airportId, arri_airportName, arri_date, arri_time)
values ('X34A', 3, 10, 1, 65, 25, 20, 3, 'MI', '2019-12-15', '3:00 am', 4, 'Boston', '2019-12-15', '9:00 am');


insert into Reservations (customerId, flightId, rsvpDate, rsvpStatus, rsvpBy) values
(1, 11, '2019-12-12', 'ACTIVE', 1);

insert into Reservations (customerId, flightId, rsvpDate, rsvpStatus, rsvpBy) values
(1, 13, '2019-12-12', 'CANCELLED', 0);

