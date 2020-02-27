-- update buyer_user_id of product table if the status of bid become '1'
DELIMITER $$
CREATE TRIGGER BID_AFTER_UPDATE_TRIG
AFTER UPDATE ON `BID` FOR EACH ROW
begin
    IF NEW.STATUS = '1' THEN
        UPDATE PRODUCT SET BUYER_USER_ID = OLD.FROM_USER_ID;
    END IF;
END;
$$
DELIMITER ;
