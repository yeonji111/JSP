CREATE TABLE MEMBER (
id varchar(30) primary key not null,
name varchar(30) NOT NULL,
password varchar(30) not null,
email varchar(50) not null,
create_date DATE default sysdate,
modify_date Date
);

--alter table member add constraint pk_member primary key(id);



create table board (
	no number GENERATED ALWAYS AS IDENTITY primary key,
	writer varchar2(30) not null,
	title varchar2(1000),
	content varchar2(4000),
	create_date date default sysdate,
	modify_date date,
	hits number default 0
);

