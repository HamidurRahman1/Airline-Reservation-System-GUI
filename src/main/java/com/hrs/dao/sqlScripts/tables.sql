
drop database if exists cs370;

create database cs370;

use cs370;

create table Logins
(loginId int not null auto_increment,
email varchar(35) not null unique,
password varchar(20) not null,
primary key (loginId)) 
auto_increment = 1;

create table Customers
(customerId int not null auto_increment,
firstName varchar(35) not null,
lastName varchar(35) null,
loginId int not null,
primary key(customerId),
foreign key(loginId) references Logins(loginId))
auto_increment = 1;

create table Status
(statusId int not null auto_increment,
status varchar(20) not null unique,
primary key(statusId))
auto_increment = 1;

create table Airports
(airportId int not null auto_increment,
airportName varchar(20) not null unique,
primary key(airportId)) 
auto_increment = 1;

create table Airlines
(airlineId int not null auto_increment,
airlineName varchar(20) not null unique,
primary key(airlineId))
auto_increment = 1;

create table Airplanes
(airplaneId int not null auto_increment,
airplaneName varchar(20) not null,
airlineId int not null,
primary key(airplaneId),
foreign key(airlineId) references Airlines(airlineId))
 auto_increment = 1;
 
 create table Admins
 (adminId int not null auto_increment,
 firstName varchar(35) not null,
 lastName varchar(35) null,
 airlineId int null,
 loginId int not null,
 primary key(adminId),
 foreign key(airlineId) references Airlines(airlineId),
 foreign key(loginId) references Logins(loginId))
 auto_increment = 1;
 
 create table Flights
 (flightId int not null auto_increment,
 flightCode varchar(20) not null,
 airlineId int not null,
 airplaneId int not null,
 statusId int not null,
 fare decimal(5, 2) not null,
 capacity int not null,
 avlSeat int not null,
 
 dept_airportId int not null,
 dept_airportName varchar(50) not null,
 dept_date date not null,
 dept_time varchar(10) not null,
 
 arri_airportId int not null,
 arri_airportName varchar(50) not null,
 arri_date date not null,
 arri_time varchar(10) not null,
 
 primary key(flightId),
 foreign key(airlineId) references Airlines(airlineId),
 foreign key(airplaneId) references Airplanes(airplaneId),
 foreign key(statusId) references Status(statusId))
 auto_increment = 1;

create table Reservations
(rsvpId int not null auto_increment,
customerId int not null,
flightId int not null,
rsvpDate date not null,
rsvpStatus varchar(10) not null,
rsvpBy int not null,
primary key(rsvpId),
foreign key(customerId) references Customers(customerId),
 foreign key(flightId) references Flights(flightId))
 auto_increment = 1;
 
 create table Flights_Customers
 (flightId int not null,
 customerId int not null,
 foreign key(flightId) references Flights(flightId),
 foreign key(customerId) references Customers(customerId))
