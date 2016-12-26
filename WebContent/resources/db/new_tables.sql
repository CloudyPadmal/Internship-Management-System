CREATE TABLE IF NOT EXISTS password_table (
	id int(5) NOT NULL AUTO_INCREMENT,
	username varchar(50) NOT NULL,
	password varchar(300) NOT NULL,
	user_type BOOL NOT NULL,
	PRIMARY KEY(id)
);
	
CREATE TABLE IF NOT EXISTS admin_table (
	id int(5) NOT NULL AUTO_INCREMENT,
	username varchar(50) NOT NULL,
	password varchar(300) NOT NULL,
	user_type BOOL NOT NULL,
	PRIMARY KEY(id)
);
	
CREATE TABLE user_table (
	id int(5) AUTO_INCREMENT,
	name varchar(50) NOT NULL,
	surname varchar(50) NOT NULL,
	gender varchar(1),
	indexNumber varchar(10) NOT NULL,
	emailAddress varchar(100) NOT NULL,
	telephone varchar(20) NOT NULL,
	gradedPoint DECIMAL NOT NULL,
	aboutMe varchar(500) NOT NULL,
	ARDUINO BOOL,
	FPGA BOOL,
	ROBOTICS BOOL,
	WIFI BOOL,
	ANTENNAS BOOL,
	NETWORKING BOOL,
	PROCESSORDESIGN BOOL,
	IMAGEPROCESSING BOOL,
	PROGRAMMING BOOL,
	AUTOMATION BOOL,
	BIOMEDICAL BOOL,
	BIOMECHANICS BOOL,
	TELECOM BOOL,
	SEMICONDUCTORS BOOL,
	CIRCUITS BOOL,
	IOT BOOL,
	AI BOOL,
	SIGNALPROCESSING BOOL,
	PRIMARY KEY(id)
);

CREATE TABLE company_table (
	id int(5) AUTO_INCREMENT,
	loginID varchar(100) NOT NULL,
	company varchar(50) NOT NULL,
	address varchar(150) NOT NULL,
	emailAddress varchar(100) NOT NULL,
	telephone varchar(20) NOT NULL,
	aboutUs varchar(500) NOT NULL,
	positions INT(5),
	PRIMARY KEY(id)
);

CREATE TABLE positions (
	id int(5) AUTO_INCREMENT,
	company varchar(50) NOT NULL,
	salary DECIMAL,
	title varchar(100) NOT NULL,
	subTitle varchar(100) NOT NULL,
	PRIMARY KEY(id)
);