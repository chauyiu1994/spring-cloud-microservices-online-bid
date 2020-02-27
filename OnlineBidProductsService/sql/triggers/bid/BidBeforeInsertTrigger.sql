-- check if the from_user_id equal to the seller_user_id in product, if yes, reject the bid insert
-- only allow a status of '0' when insert
DELIMITER $$
CREATE TRIGGER BID_BEFORE_INSERT_TRIG
BEFORE INSERT ON `BID` FOR EACH ROW
begin
    SET @SELLER_USER_ID = (SELECT SELLER_USER_ID FROM PRODUCT WHERE ID = NEW.PRODUCT_ID);
    IF @SELLER_USER_ID = NEW.FROM_USER_ID THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Seller cannot bid on his/her own product!';
    END IF;
    SET NEW.STATUS = '0';
END;
$$
DELIMITER ;
