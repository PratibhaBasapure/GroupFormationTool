CREATE TABLE User (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    bannerID VARCHAR
(20) NOT NULL,
    password VARCHAR
(76) NOT NULL
);

CREATE TABLE History_password(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    userID BIGINT NOT NULL,
    password VARCHAR
(76) NOT NULL,
    modify_date DATE NOT NULL,
    FOREIGN KEY
(userID) REFERENCES User
(id)

);

CREATE TABLE Question_type(
    id INT PRIMARY KEY AUTO_INCREMENT,
    type_name VARCHAR
(50)
);

CREATE TABLE Question(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    owner_id BIGINT NOT NULL,
    type_id INT NOT NULL,
    title VARCHAR
(75),
    context VARCHAR
(100),
    public TINYINT,
    FOREIGN KEY
(owner_id) REFERENCES User
(id),
    FOREIGN KEY
(type_id) REFERENCES Question_type
(id)
);

CREATE TABLE Numeric_question(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    question_id BIGINT NOT NULL,
    answer VARCHAR
(70),
    FOREIGN KEY
(question_id) REFERENCES Question
(id)
);
CREATE TABLE Free_text_question(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    question_id BIGINT NOT NULL,
    answer TEXT,
    FOREIGN KEY
(question_id) REFERENCES Question
(id)
);

CREATE TABLE Multiple_question(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    question_id BIGINT NOT NULL,
    answer VARCHAR
(70),
    correct TINYINT,
    FOREIGN KEY
(question_id) REFERENCES Question
(id)
);


CREATE TABLE Question_set(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    owner_id BIGINT NOT NULL,
    title VARCHAR
(75),
    FOREIGN KEY
(owner_id) REFERENCES User
(id)
);
CREATE TABLE Question_in_set
(
    question_id BIGINT,
    question_set_id BIGINT,
    inner_index BIGINT,
    FOREIGN KEY
(question_id) REFERENCES Question
(id),
    FOREIGN KEY
(question_set_id) REFERENCES Question_set
(id)
);

CREATE TABLE UserContactInfo (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    userID BIGINT NOT NULL,
    firstName VARCHAR
(100) NULL,
    lastName VARCHAR
(100) NULL,
    email VARCHAR
(320) NOT NULL,
    FOREIGN KEY
(userID) REFERENCES User
(id)
);

CREATE TABLE Role (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role VARCHAR
(10)
);

CREATE TABLE Course (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR
(200)
);

CREATE TABLE CourseRole
(
    courseID BIGINT NOT NULL,
    roleID BIGINT NOT NULL,
    userID BIGINT NOT NULL,
    FOREIGN KEY (courseID) REFERENCES Course(id),
    FOREIGN KEY (roleID) REFERENCES Role(id),
    FOREIGN KEY (userID) REFERENCES User(id)
);

CREATE TABLE SystemRole
(
    roleID BIGINT NOT NULL,
    userID BIGINT NOT NULL,
    FOREIGN KEY (roleID) REFERENCES Role(id),
    FOREIGN KEY (userID) REFERENCES User(id)
);

INSERT INTO Role
    (role)
VALUES
    ('Admin'),
    ('Guest'),
    ('Student'),
    ('Instructor'),
    ('TA');

/*
	This is not how you would do this in the real world, it would not be safe to have passwords
    or accounts stored in files in git.  This creates the admin user with an empty password.
*/
INSERT INTO User
    (bannerID, password)
VALUES
    ('B-000000', '1234');

SELECT LAST_INSERT_ID()
INTO @adminID;

INSERT INTO UserContactInfo
    (userID, firstName, lastName, email)
VALUES
    (@adminID, 'Rob', 'Hawkey', 'rhawkey@dal.ca');

SELECT id
INTO @adminRoleID
FROM Role
WHERE role = 'Admin';

INSERT INTO SystemRole
    (roleID, userID)
VALUES
    (@adminRoleID, @adminID);

SELECT *
FROM Role;
SELECT *
FROM User;


-- Question type table initial entries
select * from QuestionType;
insert into QuestionType(id,questionTypeName) values(1,'Numeric');
insert into QuestionType(id,questionTypeName) values(2,'Multiple choice – choose one');
insert into QuestionType(id,questionTypeName) values(3,'Multiple choice – choose multiple');
insert into QuestionType(id,questionTypeName) values(4,'Free text');