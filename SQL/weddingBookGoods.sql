create table Goods
(
	no int auto_increment primary key,
	goodsSDM varchar(20) not null,
	goodsName varchar(30) not null,
	goodsPosition varchar(100) not null,
	goodsFile1 varchar(100),
	goodsFile2 varchar(100),
	goodsFile3 varchar(100),
	goodsFile4 varchar(100),
	goodsFile5 varchar(100),
	goodsPromotion text,
	goodsInfo text,
	goodsBusinessHours varchar(40),
	goodsBusinessTel varchar(15),
	goodsBusinessAdd varchar(100),
	goodsHomepageAdd varchar(100)
);