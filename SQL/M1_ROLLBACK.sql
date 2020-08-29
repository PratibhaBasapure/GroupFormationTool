DROP PROCEDURE IF EXISTS spAddInstructorToCourse;
DROP PROCEDURE IF EXISTS spCreateCourse;
DROP PROCEDURE IF EXISTS spCreateUser;
DROP PROCEDURE IF EXISTS spDeleteCourse;
DROP PROCEDURE IF EXISTS spFindUserByBannerID;
DROP PROCEDURE IF EXISTS spFindUserByEmail;
DROP PROCEDURE IF EXISTS spFindUsersByName;
DROP PROCEDURE IF EXISTS spFindUsersWithCourseRole;
DROP PROCEDURE IF EXISTS spFindUsersWithSystemRole;
DROP PROCEDURE IF EXISTS spLoadUser;
DROP PROCEDURE IF EXISTS spUpdateUser;
DROP PROCEDURE IF EXISTS spLoadAllCourses;
DROP PROCEDURE IF EXISTS spEnrollUser;
DROP PROCEDURE IF EXISTS spFindCourseByID;
DROP PROCEDURE IF EXISTS spFindUsersWithoutCourseRole;
DROP PROCEDURE IF EXISTS spLoadUserRolesForCourse;

DROP TABLE SystemRole;

DROP TABLE CourseRole;

DROP TABLE Course;

DROP TABLE Role;

DROP TABLE UserContactInfo;

DROP TABLE History_password;

DROP TABLE Question_in_set;

DROP TABLE Question_set;

DROP TABLE Numeric_question;

DROP TABLE Free_text_question;

DROP TABLE Multiple_question;

DROP TABLE Question;

DROP TABLE Question_type;

DROP TABLE User;

DROP TABLE Algorithm;

DROP TABLE AlgorithmDefinition;

DROP TABLE FormedGroup;

DROP TABLE GroupMember;

DROP TABLE Log;