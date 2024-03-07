insert into user_details(id,birth_date,name)
values(10001,current_date(),'A');
insert into user_details(id,birth_date,name)
values(10002,current_date(),'B');
insert into user_details(id,birth_date,name)
values(10003,current_date(),'BC');
insert into post(id,description,user_id) values(20001,'Hi',10001);
insert into post(id,description,user_id) values(20002,'Hello',10001);