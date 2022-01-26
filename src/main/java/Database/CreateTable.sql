create table if not exists admin(
                id serial primary key,
                firstname varchar(30),
                lastname varchar (50) ,
                username varchar(15) not null,
                password varchar(15) not null);

create table if not exists cinema(
                id serial primary key,
                name varchar(50),
                address varchar(100),
                username varchar(15) not null,
                password varchar(15) not null,
                authentication boolean);

create table if not exists customer(
                national_code char(10) primary key,
                firstname varchar(30),
                lastname varchar(50),
                phone_number char(11),
                username varchar(15) not null ,
                password varchar(15) not null);

create table if not exists ticket(
                id serial primary key,
                cinema_id integer ,
                film_name varchar(30) not null,
                date_set varchar(12) not null,
                start_at varchar(8) not null,
                end_at varchar(8) not null,
                capacity integer null,
                price float ,
                constraint fk_cinema foreign key (cinema_id) references cinema(id));

create table if not exists reserve_ticket(
                id serial primary key,
                ticket_id integer ,
                customer_id char(10) ,
                count integer ,
                total_price float ,
                constraint fk_ticket foreign key (ticket_id) references ticket(id) ,
                constraint fk_customer foreign key (customer_id) references customer(national_code));