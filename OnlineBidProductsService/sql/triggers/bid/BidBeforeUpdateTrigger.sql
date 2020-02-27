-- check if there already exists a bid of status of '1' of that product
DELIMITER $$
CREATE TRIGGER BID_BEFORE_UPDATE_TRIG
BEFORE UPDATE ON `BID` FOR EACH ROW
begin
    IF NEW.STATUS = '1' THEN
        SET @OLDSTS = (SELECT STATUS FROM BID WHERE STATUS = '1' AND PRODUCT_ID = NEW.PRODUCT_ID);
        IF @OLDSTS = '1' THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'There can only be one accepted bid for one product!';
        END IF;
    END IF;
END;
$$
DELIMITER ;