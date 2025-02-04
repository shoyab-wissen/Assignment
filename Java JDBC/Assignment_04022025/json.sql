create table emp_json(
id serial primary key,
data JSON
);

select data->'name' as name from emp_json where data ->> 'name' = 'Shoyab Siddique';

select * from emp_json;

alter table emp_json alter column data type JSONB; 

update emp_json
set data = JSONB_SET(data, '{name}', '"Shoyab"', false)
where data ->> 'name' = 'Shoyab Siddique';

