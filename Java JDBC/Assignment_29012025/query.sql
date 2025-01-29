create table emp(
name varchar(20),
age smallint
);

select * from emp;

create table designation(
designation_name varchar(20) primary key
);

create table department(
department_name varchar(20) primary key
);

select * from designation;

drop table employee;
delete from department;
create table employee(
eid serial primary key,
name varchar(30),
age smallint,
designation varchar(20) references designation(designation_name),
department varchar(20) references department(department_name),
salary decimal(12, 2)
);

insert into designation values('MANAGER')