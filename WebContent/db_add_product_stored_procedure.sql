use ecom_webapp;
DELIMITER $$
CREATE PROCEDURE add_product(IN pname varchar(100), IN pprice decimal(10,2), IN pdesc varchar(200))
INSERT INTO eproduct(name,price,description) values (pname,pprice,pdesc)
$$
DELIMITER ;