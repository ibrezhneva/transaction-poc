drop table if exists setting;

create table setting
(
  id          integer not null constraint setting_pk primary key,
  name        varchar(200),
  value       varchar(200)
);
