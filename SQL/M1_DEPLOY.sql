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

CREATE TABLE QuestionType(
    id INT PRIMARY KEY AUTO_INCREMENT,
    questionTypeName VARCHAR
(45)
);

CREATE TABLE Question(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    instructorID BIGINT NOT NULL,
    type_id INT NOT NULL,
    title VARCHAR
(75),
    questionText VARCHAR
(100),
    createdOn DATE,
    FOREIGN KEY
(instructorID) REFERENCES User
(id),
    FOREIGN KEY
(type_id) REFERENCES QuestionType
(id)
);

CREATE TABLE QuestionOptions(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    questionID BIGINT,
    displayText VARCHAR (100),
    optionNumber INT,
        FOREIGN KEY
(questionID) REFERENCES Question
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

CREATE TABLE Algorithm(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  courseID BIGINT NOT NULL,
  createOn Date,
  updateOn Date
);

CREATE TABLE AlgorithmDefinition(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    algorithmID BIGINT NOT NULL,
    questionID BIGINT NOT NULL,
    weight int not null,
    isChoiceSimilarity TINYINT,
    FOREIGN KEY (algorithmID) REFERENCES Algorithm(id),
    FOREIGN KEY (questionID) REFERENCES Question(id)
);

CREATE TABLE FormedGroup(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    courseID BIGINT NOT NULL,
    createOn Date,
    updateOn Date,
    FOREIGN KEY (courseID) REFERENCES Course(id)
);

CREATE TABLE  GroupMember(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    groupID BIGINT NOT NULL,
    userID  BIGINT NOT NULL,
    FOREIGN KEY (groupID) REFERENCES FormedGroup(id),
    FOREIGN KEY (userID) REFERENCES User(id)
);

CREATE TABLE Log(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    class VARCHAR(125) NOT NULL,
    method VARCHAR(125) NOT NULL,
    createTime date,
    level VARCHAR(75) NOT NULL,
    message VARCHAR(200) NOT NULL,
    possibleSolution VARCHAR(200)
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
