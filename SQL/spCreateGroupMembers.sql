DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateGroupMembers $$

CREATE PROCEDURE spCreateGroupMembers (
	IN groupID BIGINT,
	IN userID BIGINT,
    OUT id BIGINT
)
BEGIN
    INSERT INTO GroupMember(groupID,userID)
    VALUES (groupID,userID);
    SET id = LAST_INSERT_ID();
END $$

DELIMITER ;