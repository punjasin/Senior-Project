
insert into User (email,password,firstName,lastName,student_id,major,faculty,token,role) values ('aaa@gmail.com','123456','111','222',542115001,'SE','CAMT',5000,'USER');
insert into User (email,password,firstName,lastName,student_id,major,faculty,token,role) values ('test@test.com','123456','ABC','DEF',542115002,'SE','CAMT',1000,'ADMIN');
insert into User (email,password,firstName,lastName,student_id,major,faculty,token,role) values ('aaa@aaa.com','123456','ABC','DEF',542115003,'SE','CAMT',1000,'ADMIN');

insert into Activity (activity_name,description,place,start_time,end_time,seat_quota,isSelected) values ('E3','Electronic Entertainment Expo','LA','2015-06-16 08:00:00','2015-06-18 20:00:00',100,TRUE);
insert into Activity (activity_name,description,place,start_time,end_time,seat_quota,isSelected) values ('Google I/O','Google Event','San Francisco','2015-05-15 08:00:00','2015-05-15 20:00:00',50,FALSE);
insert into Activity (activity_name,description,place,start_time,end_time,seat_quota,isSelected) values ('Microsoft Ignite','Microsoft Event','Chicago','2015-05-04 08:00:00','2015-05-04 20:00:00',2,TRUE);

insert into Bidding (activity_id,title,description,bStart_time,bEnd_time,seat_quota,status) values (3,'Bidding For Microsoft Ignite','Testttttttt','2015-10-20 05:12:00','2015-10-25 09:13:00',2,'Close');
insert into Bidding (activity_id,title,description,bStart_time,bEnd_time,seat_quota,status) values (1,'Bidding For E3','Testttttttt','2015-10-20 05:12:00','2015-10-22 03:00:00',2,'Close');

insert into BidData (bidding_id,student_id,firstName,lastName,token,placeBidTime,status) values (1,542115001,'Messi','Lionel',20,'2015-05-04 09:00:00','Bidding..');
insert into BidData (bidding_id,student_id,firstName,lastName,token,placeBidTime,status) values (1,542115002,'Ronaldo','Cristiano',500,'2015-05-04 09:00:00','Bidding..');
insert into BidData (bidding_id,student_id,firstName,lastName,token,placeBidTime,status) values (1,542115003,'Suarez','Suarez',200,'2015-05-04 09:00:00','Bidding..');

