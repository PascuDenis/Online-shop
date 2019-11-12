INSERT INTO Customer(CustomerId, FirstName, LastName, Username, Password, EmailAddress) VALUES
(1, 'FirstName01', 'LastName01', 'Username01', 'Password01', 'black.knight171@yahoo.com'),
(2, 'FirstName02', 'LastName02', 'Username02', 'Password02', 'black.knight171@yahoo.com'),
(3, 'FirstName03', 'LastName03', 'Username03', 'Password03', 'black.knight171@yahoo.com'),
(4, 'FirstName04', 'LastName04', 'Username04', 'Password04', 'black.knight171@yahoo.com'),
(5, 'FirstName05', 'LastName05', 'Username05', 'Password05', 'black.knight171@yahoo.com'),
(6, 'FirstName06', 'LastName06', 'Username06', 'Password06', 'black.knight171@yahoo.com');

INSERT INTO Address(AddressId, Country, City, County, Street) VALUES
(1, 'AddressCountry01','AddressCity01','AddressCounty01','AddressStreetAddress01'),
(2, 'AddressCountry02','AddressCity02','AddressCounty02','AddressStreetAddress02'),
(3, 'AddressCountry03','AddressCity03','AddressCounty03','AddressStreetAddress03'),
(4, 'AddressCountry04','AddressCity04','AddressCounty04','AddressStreetAddress04'),
(5, 'AddressCountry05','AddressCity05','AddressCounty05','AddressStreetAddress05');

INSERT INTO Location(LocationId, Name, AddressId) VALUES
(1, 'Name01',1),
(2, 'Name02',2),
(3, 'Name03',3),
(4, 'Name04',2),
(5, 'Name05',4);

INSERT INTO Revenue(RevenueId, LocationID, Date_, Sum_) VALUES
(1, 1,'2015-11-13',100),
(2, 2,'2015-11-13',200),
(3, 3,'2015-11-13',300),
(4, 4,'2015-11-13',4000),
(5, 5,'2015-11-13',5680);

INSERT INTO Supplier(SupplierId, Name) VALUES
(1, 'SupplierName01'),
(2, 'SupplierName02'),
(3, 'SupplierName03'),
(4, 'SupplierName04'),
(5, 'SupplierName05');


INSERT INTO ProductCategory(ProductCategoryId, Name, Description) VALUES
(1, 'CategoryName01','Description01'),
(2, 'CategoryName02','Description02'),
(3, 'CategoryName03','Description03'),
(4, 'CategoryName04','Description04'),
(5, 'CategoryName05','Description05');

INSERT INTO Product(ProductId, Name,Description, Price, Weight, ProductCategoryId, SupplierId, ImageUrl) VALUES
(1, 'Name01','Description01', 12,23,1,4,'Url01'),
(2, 'Name02','Description02', 12,25,2,3,'Url02'),
(3, 'Name03','Description03', 23,26,3,2,'Url03'),
(4, 'Name04','Description04', 54,31,1,5,'Url04'),
(5, 'Name05','Description05', 63,22,1,1,'Url05');

INSERT INTO Stock(StockId, ProductId, LocationId, Quantity) VALUES
(1, 1, 1, 9961),
(2, 1, 2, 9962),
(3, 1, 3, 9963),
(4, 1, 4, 9964),
(5, 2, 1, 9965),
(6, 2, 2, 9966),
(7, 2, 3, 9967),
(8, 3, 1, 9968),
(9, 3, 2, 9969),
(10, 3, 3, 9970),
(11, 4, 1, 9941),
(12, 4, 2, 9942),
(13, 4, 3, 9943),
(14, 4, 4, 9944),
(15, 4, 5, 9945),
(16, 5, 1, 9946),
(17, 5, 2, 9947),
(18, 5, 3, 9948),
(19, 5, 4, 9949),
(20, 5, 5, 9950);

INSERT INTO Orders(OrdersId, ShippedFrom, CustomerId, AddressId, CreatedAt) VALUES
(1, 1, 2, 1, '2015-11-13'),
(2, 1, 3, 2, '2015-11-13'),
(3, 1, 1, 2, '2015-11-13'),
(4, 1, 5, 4, '2015-11-13'),
(5, 2, 1, 5, '2015-11-13');


INSERT INTO OrderDetail(OrderDetailId, OrdersId, ProductId, Quantity) VALUES
(1, 5,1,11),
(2, 4,2,22),
(3, 2,2,33),
(4, 4,3,44),
(5, 1,4,55);
