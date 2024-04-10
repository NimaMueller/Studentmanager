-- Creation of table
CREATE TABLE IF NOT EXISTS students (
  matrklNr INT,
  name varchar(250) NOT NULL,
  firstName varchar(250) NOT NULL,
  PRIMARY KEY (matriklNr)
);