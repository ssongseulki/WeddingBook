create table Goodsreservation
(
	no int auto_increment primary key,
	memberId varchar(20),
	goodsName varchar(30),
	goodsBusinessTel varchar(15),
	reservationName varchar(20),
	reservationDate varchar(20),
	reservationHours varchar(20),
	wTime timestamp not null default current_timestamp
);