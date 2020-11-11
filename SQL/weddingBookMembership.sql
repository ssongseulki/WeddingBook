create table membership
(
	name varchar(50) not null,
	id varchar(50) primary key,
	nickName varchar(50) not null,
	password varchar(50) not null,
	phone1 varchar(5) not null,
	phone2 varchar(5) not null,
	phone3 varchar(5) not null,
	email varchar(50) not null,
	gender varchar(20) not null,
	weddingDate varchar(20) not null,
	local varchar(20) not null,
	wTime Timestamp default current_timestamp
);