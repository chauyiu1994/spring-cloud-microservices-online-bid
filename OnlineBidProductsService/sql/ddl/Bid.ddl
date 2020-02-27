CREATE TABLE BID
(
    ID INT NOT NULL AUTO_INCREMENT UNIQUE,
    PRODUCT_ID INT NOT NULL,
    FROM_USER_ID VARCHAR(255) NOT NULL,
    OFFER DECIMAL(10, 2) NOT NULL,
    STATUS VARCHAR(1) NOT NULL DEFAULT '0',
    MESSAGE VARCHAR(255),
    CREATE_TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (ID),
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(ID) ON DELETE CASCADE,
    FOREIGN KEY (FROM_USER_ID) REFERENCES USER(USER_ID) ON DELETE CASCADE,
    INDEX(ID, PRODUCT_ID, FROM_USER_ID, OFFER, CREATE_TIME)
)