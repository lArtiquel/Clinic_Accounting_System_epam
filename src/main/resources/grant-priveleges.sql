-- after db creation go to mysql cmd and type folowing command
-- to create db user with all priveleges
CREATE USER 'ClinicAdmin'@'localhost' IDENTIFIED BY 'pass';
GRANT ALL PRIVILEGES ON `clinic`.* TO 'ClinicAdmin'@'localhost';
FLUSH PRIVILEGES;