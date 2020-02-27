DELIMITER $$
CREATE TRIGGER COMMENT_AFTER_INSERT_TRIG
AFTER INSERT ON `COMMENT` FOR EACH ROW
begin
    UPDATE PRODUCT SET NUM_OF_COMMENT = NUM_OF_COMMENT + 1 WHERE ID = NEW.PRODUCT_ID;
END;
$$
DELIMITER ;
