CREATE TABLE IF NOT EXISTS Customer(
	CustomerId INT auto_increment PRIMARY KEY,
	FirstName VARCHAR(255) NOT NULL,
	LastName VARCHAR(255) NOT NULL,
	Username VARCHAR(255) NOT NULL,
	Password VARCHAR(255) NOT NULL,
	EmailAddress VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Address(
    AddressId INT auto_increment PRIMARY KEY,
    Country VARCHAR(255) NOT NULL,
    City VARCHAR(255) NOT NULL,
    County VARCHAR(255) NOT NULL,
    Street VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Location (
    LocationId INT auto_increment PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    AddressId INT,
    FOREIGN KEY (AddressId) REFERENCES Address(AddressId)
);

CREATE TABLE IF NOT EXISTS Revenue(
    RevenueId INT auto_increment PRIMARY KEY,
    LocationId INT NOT NULL,
    Date_ DATE NOT NULL,
    Sum_ INT NOT NULL,
    FOREIGN KEY (LocationId) REFERENCES Location(LocationId)
);

CREATE TABLE IF NOT EXISTS Supplier(
    SupplierId INT auto_increment PRIMARY KEY,
    Name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS ProductCategory(
    ProductCategoryId INT auto_increment PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Description VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Product (
    ProductId INT auto_increment PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Description VARCHAR(255) NOT NULL,
    Price FLOAT NOT NULL,
    Weight FLOAT NOT NULL,
    ProductCategoryId INT NOT NULL,
    SupplierId INT NOT NULL,
    ImageUrl VARCHAR(255),
    FOREIGN KEY (ProductCategoryId) REFERENCES ProductCategory(ProductCategoryId),
    FOREIGN KEY (SupplierId) REFERENCES Supplier(SupplierId)
);

CREATE TABLE IF NOT EXISTS Stock(
    StockId INT auto_increment PRIMARY KEY,
    ProductID INT NOT NULL,
    LocationID INT NOT NULL,
    Quantity INT NOT NULL,
    FOREIGN KEY (ProductId) REFERENCES Product(ProductId),
    FOREIGN KEY (LocationId) REFERENCES Location(LocationId)
);

CREATE TABLE IF NOT EXISTS Orders(
    OrdersId INT auto_increment PRIMARY KEY,
    ShippedFrom INT NOT NULL,
    CustomerId INT NOT NULL,
    AddressId INT NOT NULL,
    CreatedAt DATE NOT NULL,
    FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId),
    FOREIGN KEY (ShippedFrom) REFERENCES Location(LocationId),
    FOREIGN KEY (AddressId) REFERENCES Address(AddressId)
  );

CREATE TABLE IF NOT EXISTS OrderDetail(
    OrderDetailId INT PRIMARY KEY,
    OrdersId INT NOT NULL,
    ProductId INT NOT NULL,
    Quantity INT NOT NULL,
    FOREIGN KEY (OrdersId) REFERENCES Orders(OrdersId),
    FOREIGN KEY (ProductId) REFERENCES Product(ProductId)
);

