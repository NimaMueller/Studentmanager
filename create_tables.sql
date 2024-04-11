-- Creation of table
CREATE TABLE IF NOT EXISTS students (
  matriklNr INT,
  name varchar(250) NOT NULL,
  firstName varchar(250) NOT NULL,
  dob DATE
  PRIMARY KEY (matriklNr)
);