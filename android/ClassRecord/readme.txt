student_info 信息：
"create table if not exists student_info
(student_id integer primary key autoincrement," +
 "student_name varchar(20)," +
 "grade varchar(16)," +
 "subject varchar(32))";

 class_record 信息：
 CREATE TABLE
 IF
 	NOT EXISTS class_record (
 	record_id INTEGER PRIMARY KEY autoincrement,
 	student_id INTEGER,
 	_month INTEGER,
 	_day INTEGER,
 	_week INTEGER,
 	class_type INTEGER,
 	subject VARCHAR ( 8 ),
 	start_time VARCHAR ( 8 ),
 	end_time VARCHAR ( 8 ),
 	class_size DOUBLE,
 	detail VARCHAR ( 1024 )
 	);