

create table person(
	num int Primary key auto_increment,
    name varchar(10),
    jumin varchar(14),
    birth date);
    
	insert into person values(null,'곽희원','911114-1095619','1991-11-14');
	insert into person values(null,'홍길동','911115-1095419','1991-11-15');
    
    select * from person;
    
    use kwak;
