DROP TABLE Product if exists;
DROP TABLE Seller if exists;


CREATE TABLE Seller
(id int auto_increment primary key, validName varchar(100) unique not null);

CREATE TABLE Product
(productId int auto_increment primary key,
productName varchar(100),
productSeller varchar(100),
productPrice double,
productSellerId int references Seller(id));


