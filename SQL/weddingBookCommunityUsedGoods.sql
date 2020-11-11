create table Community_usedGoods
(
	no int auto_increment primary key,
	id varchar(20),
	nickName varchar(20) not null,
	title varchar(100) not null,
	contents text not null,
	hit int not null default 0,
	wTime timestamp not null default current_timestamp,
	groupNum int not null default 0,
	stepNum int not null default 0,
	indentNum int not null default 0,
	foreign key (id) references membership (id) on delete cascade
);