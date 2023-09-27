use enjoytrip;

drop table if exists members;

CREATE TABLE `members` (
  `user_id` varchar(40) NOT NULL,
  `user_pass` varchar(40) NOT NULL,
  `user_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(40) NOT NULL,
  `age` int NOT NULL,
  `joinDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`)
);

insert into members (user_id, user_pass, user_name, email, age) 
values ('admin', '1234', '관리자', 'admin@ssafy.com', 30);

select * from members;