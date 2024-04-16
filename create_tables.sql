-- Creation of table
CREATE TABLE IF NOT EXISTS students (
  matrikl_nr INT NOT NULL,
  name varchar(250) NOT NULL,
  first_name varchar(250) NOT NULL,
  dob DATE,
  PRIMARY KEY (matrikl_nr)
);

CREATE TABLE IF NOT EXISTS modules (
  module_id INT NOT NULL,
  description varchar(250) NOT NULL,
  c_p INT NOT NULL,
  PRIMARY KEY (module_id)
);