insert into customer_info(customer_first_name, customer_last_name, customer_email) values ('Megatron', 'live', 'megatron@email.com');
insert into customer_info(customer_first_name, customer_last_name, customer_email) values ('Optimus', 'Prime', 'optimus@email.com');
insert into customer_info(customer_first_name, customer_last_name, customer_email) values ('Iron', 'Hide', 'iron@email.com');

insert into airline_info(airline_name) values ('Delta');
insert into airline_info(airline_name) values ('American Airlines');
insert into airline_info(airline_name) values ('Jet Blue');

insert into airport_info(airport_name) values ('JFK');
insert into airport_info(airport_name) values ('LA');
insert into airport_info(airport_name) values ('MI');

insert into source_info(airport_id) values (1);
insert into source_info(airport_id) values (2);
insert into source_info(airport_id) values (1);

insert into destination_info(airport_id) values (2);
insert into destination_info(airport_id) values (3);
insert into destination_info(airport_id) values (3);

insert into customer_login(cust_username, cust_password, customer_id) values('megatron@email.com', '12345', 1);
insert into customer_login(cust_username, cust_password, customer_id) values('optimus@email.com', '12345', 2);
insert into customer_login(cust_username, cust_password, customer_id) values('iron@email.com', '12345', 3);

insert into airline_flight_info(airline_flight_name, airline_id, fare, flight_max_capacity, flight_current_capacity)
	value ('Boeing 707', 1,	35.0,	60, 0);
insert into airline_flight_info(airline_flight_name, airline_id, fare, flight_max_capacity, flight_current_capacity)
	value ('Boeing 77X', 2,	50.0,	40, 0);
insert into airline_flight_info(airline_flight_name, airline_id, fare, flight_max_capacity, flight_current_capacity)
	value ('Boeing 37E', 2,	65.0,	50, 0);
insert into airline_flight_info(airline_flight_name, airline_id, fare, flight_max_capacity, flight_current_capacity)
	value ('Boeing 505', 2,	75.0,	70, 0);
insert into airline_flight_info(airline_flight_name, airline_id, fare, flight_max_capacity, flight_current_capacity)
	value ('Boeing 77X', 3,	35.0,	30, 0);
insert into airline_flight_info(airline_flight_name, airline_id, fare, flight_max_capacity, flight_current_capacity)
	value ('Boeing 707', 1,	55.0,	25, 0);

insert into airline_admin(airline_id) values (2);
insert into airline_admin(airline_id) values (3);
insert into airline_admin(airline_id) values (1);

insert into airline_admin_login(airline_admin_id, admin_username, admin_password) values (2,'america1234', 12345);
insert into airline_admin_login(airline_admin_id, admin_username, admin_password) values (3,'jetblue1234', 12345);
insert into airline_admin_login(airline_admin_id, admin_username, admin_password) values (1,'delta1234', 12345);

insert into available_flight(airline_flight_id, available_date) values(4, '2019-12-10');
insert into available_flight(airline_flight_id, available_date) values(6, '2019-12-16');
insert into available_flight(airline_flight_id, available_date) values(3, '2019-12-14');
insert into available_flight(airline_flight_id, available_date) values(1, '2019-12-10');
insert into available_flight(airline_flight_id, available_date) values(2, '2019-12-16');
insert into available_flight(airline_flight_id, available_date) values(5, '2019-12-14');

insert into flight_status(airline_flight_id,flight_status_info) values (5, 'On Time');
insert into flight_status(airline_flight_id,flight_status_info) values (4, 'Cancelled');
insert into flight_status(airline_flight_id,flight_status_info) values (3, 'On Time');
insert into flight_status(airline_flight_id,flight_status_info) values (6, 'On Time');
insert into flight_status(airline_flight_id,flight_status_info) values (2, 'Cancelled');
insert into flight_status(airline_flight_id,flight_status_info) values (1, 'On Time');

insert into reservation_info(customer_id,reservation_date) values(1, '2018-12-4');
insert into reservation_info(customer_id,reservation_date) values(2, '2019-12-9');
insert into reservation_info(customer_id,reservation_date) values(3, '2019-12-26');
insert into reservation_info(customer_id,reservation_date) values(2, '2019-11-10');
insert into reservation_info(customer_id,reservation_date) values(3, '2015-01-04');
insert into reservation_info(customer_id,reservation_date) values(1, '2014-02-04');
insert into reservation_info(customer_id,reservation_date) values(2, '2019-12-08');
insert into reservation_info(customer_id,reservation_date) values(3, '2013-02-04');
insert into reservation_info(customer_id,reservation_date) values(1, '2012-02-04');
insert into reservation_info(customer_id,reservation_date) values(3, '2011-09-04');

insert into arrival_info(airport_id, airline_flight_id, flight_status_id) values(3,5,1);
insert into arrival_info(airport_id, airline_flight_id, flight_status_id) values(2,3,3);
insert into arrival_info(airport_id, airline_flight_id, flight_status_id) values(1,2,5);

insert into departures_info(airport_id, airline_flight_id, flight_status_id) values(2,4,2);
insert into departures_info(airport_id, airline_flight_id, flight_status_id) values(3,6,4);
insert into departures_info(airport_id, airline_flight_id, flight_status_id) values(1,1,6);

insert into flight_info(reservation_id,airline_flight_id,flight_date,source_id,destination_id)
	values(5,3,'2017-01-06', 3 , 2);
insert into flight_info(reservation_id,airline_flight_id,flight_date,source_id,destination_id)
	values(4,6,'2012-01-03', 1 , 3);
insert into flight_info(reservation_id,airline_flight_id,flight_date,source_id,destination_id)
	values(3,1,'2018-12-04', 2 , 3);
insert into flight_info(reservation_id,airline_flight_id,flight_date,source_id,destination_id)
	values(1,2,'2019-09-12', 3 , 1);
insert into flight_info(reservation_id,airline_flight_id,flight_date,source_id,destination_id)
	values(8,3,'2017-12-16', 2 , 1);
insert into flight_info(reservation_id,airline_flight_id,flight_date,source_id,destination_id)
	values(9,5,'2019-11-10', 1 , 2);
insert into flight_info(reservation_id,airline_flight_id,flight_date,source_id,destination_id)
	values(4,6,'2015-01-03', 3 , 2);
insert into flight_info(reservation_id,airline_flight_id,flight_date,source_id,destination_id)
	values(2,5,'2014-02-01', 2 , 3);
insert into flight_info(reservation_id,airline_flight_id,flight_date,source_id,destination_id)
	values(9,3,'2019-12-04', 1 , 2);
insert into flight_info(reservation_id,airline_flight_id,flight_date,source_id,destination_id)
	values(1,4,'2018-01-06', 1 , 2);
