CREATE TABLE voting(
id serial primary key,
age smallint,
vote varchar(10)
);
CREATE EXTENSION IF NOT EXISTS tablefunc;
INSERT INTO voting (age, vote)
SELECT 
    FLOOR(RANDOM() * (80 - 18 + 1)) + 18 AS age,
    CASE FLOOR(RANDOM() * 3)
        WHEN 0 THEN 'Congress'
        WHEN 1 THEN 'BJP'
        ELSE 'NOTA'
    END AS vote
FROM generate_series(1, 50);

SELECT * FROM voting;

SELECT age, vote, count(age) FROM voting 
GROUP BY age, vote
ORDER BY age;

SELECT * FROM crosstab(
'SELECT age, vote, count(age) FROM voting GROUP BY age, vote ORDER BY age',
'VALUES (''Congress''), (''BJP''), (''NOTA'')'
) AS ct(age int, Congress int, BJP int, NOTA int);



SELECT CONCAT((age / 10) * 10, '-', ((age / 10) * 10) + 9) AS age_group, vote, COUNT(*) 
FROM voting 
GROUP BY age_group, vote 
ORDER BY age_group;

SELECT * FROM crosstab(
'SELECT CONCAT((age / 10) * 10, ''-'', ((age / 10) * 10) + 9) AS age_group, vote, COUNT(*) 
FROM voting 
GROUP BY age_group, vote 
ORDER BY age_group',
'VALUES (''Congress''), (''BJP''), (''NOTA'')'
) AS ct(age_group varchar(10), Congress int, BJP int, NOTA int);

