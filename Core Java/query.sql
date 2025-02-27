create table company(company_name varchar(50) primary key);
create table model(model_name varchar(50) primary key);
create table fuel(fuel_name varchar(50) primary key);
create table type(type_name varchar(50) primary key);

drop table car;

create table car(
	carid serial primary key,
	company_name varchar(50) not null,
	model decimal(4, 0) not null,
	seater smallint not null,
	fuel_type varchar(50) not null,
	type varchar(50) not null,
	price decimal(12, 2) not null,
	sold boolean not null,
	foreign key(company_name) references company(company_name),
	foreign key(fuel_type) references fuel(fuel_name),
	foreign key(type) references type(type_name)
);

select * from car;

insert into company(company_name) values(
('Audi')
);

insert into fuel(fuel_name) values
('CNG'),
('Diesel'),
('Petrol')
;

insert into type(type_name) values
('Sedan'),
('SUV');