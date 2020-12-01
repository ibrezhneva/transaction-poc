drop table if exists room;

create table room
(
  id          integer not null constraint room_pk primary key,
  name        varchar(200)
);
