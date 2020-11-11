create table community_Board
(
	no int auto_increment primary key,
	nickName varchar(20) not null,
	weddingDate varchar(20) not null,
	contents text not null,
	wTime timestamp not null default current_timestamp,
	groupNum int not null default 0,
	stepNum int not null default 0,
	indentNum int not null default 0
);