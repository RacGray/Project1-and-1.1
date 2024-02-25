DROP TABLE Product if exists;
DROP TABLE Seller if exists;


CREATE TABLE Seller
(id int NOT NULL AUTO_INCREMENT PRIMARY KEY, validName varchar(100) UNIQUE);

CREATE TABLE Product
(productId int NOT NULL AUTO_INCREMENT PRIMARY KEY,
productName varchar(100),
productSeller varchar(100),
productPrice double,
productSellerId int references Seller(id));


