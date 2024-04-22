-- Creation of table
CREATE TABLE IF NOT EXISTS students (
  matrikl_nr INT NOT NULL,
  student_course_id INT NOT NULL,
  name varchar(250) NOT NULL,
  first_name varchar(250) NOT NULL,
  dob DATE,
  PRIMARY KEY (matrikl_nr),
  FOREIGN KEY (student_course_id) REFERENCES courses(course_id)
);

CREATE TABLE IF NOT EXISTS modules (
  module_id INT NOT NULL,
  description varchar(250) NOT NULL,
  c_p INT NOT NULL,
  PRIMARY KEY (module_id)
);

CREATE TABLE if NOT EXISTS signUp (
  student_matrikl_nr INT NOT NULL,
  module_id INT NOT NULL,
  grade DOUBLE PRECISION,
  passed BOOLEAN,
  attempt INT NOT NULL,
  PRIMARY KEY (student_matrikl_nr, module_id),
  FOREIGN KEY (student_matrikl_nr) REFERENCES students(matrikl_nr),
  FOREIGN KEY (module_id) REFERENCES modules(module_id)
);

CREATE TABLE if NOT EXISTS courses (
    course_id INT NOT NUll,
    description VARCHAR(250) NOT NULL,
    degree VARCHAR(250) NOT NULL,
    PRIMARY KEY (course_id)
);