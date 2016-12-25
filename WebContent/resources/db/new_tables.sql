CREATE TABLE IF NOT EXISTS password_table (
	id int(5) NOT NULL AUTO_INCREMENT,
	username varchar(50) NOT NULL,
	password varchar(300) NOT NULL,
	user_type BOOL NOT NULL,
	PRIMARY KEY(id));
	
CREATE TABLE IF NOT EXISTS admin_table (
	id int(5) NOT NULL AUTO_INCREMENT,
	username varchar(50) NOT NULL,
	password varchar(300) NOT NULL,
	user_type BOOL NOT NULL,
	PRIMARY KEY(id));