DROP DATABASE IF EXISTS AMax;
CREATE DATABASE AMax;
USE AMax;

-- User Table
CREATE TABLE User (
                      email VARCHAR(50),
                      password VARCHAR(30)
);

INSERT INTO `User` (email, password)
VALUES ('akila2024e@gmail.com', '3938093');

-- Client Table
CREATE TABLE Client (
                        Client_ID VARCHAR(10) PRIMARY KEY,
                        Name VARCHAR(100),
                        Address VARCHAR(150),
                        Phone_No VARCHAR(15),
                        Email VARCHAR(100)
);

-- Project Table
CREATE TABLE Project (
                         Project_ID VARCHAR(10) PRIMARY KEY,
                         Name VARCHAR(100),
                         Start_date DATE,
                         End_date DATE,
                         Type VARCHAR(50),
                         Status VARCHAR(50),
                         Client_ID VARCHAR(10),
                         FOREIGN KEY (Client_ID) REFERENCES Client(Client_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Employee Table
CREATE TABLE Employee (
                          Employee_ID VARCHAR(10) PRIMARY KEY,
                          Name VARCHAR(100),
                          Phone_No VARCHAR(15),
                          Address VARCHAR(255),
                          Role VARCHAR(50)
);

-- EmployeeDetails Table
CREATE TABLE EmployeeDetails (
                                 Project_ID VARCHAR(10),
                                 Work_date DATE,
                                 Work_Day_Count INT,
                                 Employee_ID VARCHAR(10),
                                 FOREIGN KEY (Project_ID) REFERENCES Project(Project_ID) ON UPDATE CASCADE ON DELETE CASCADE,
                                 FOREIGN KEY (Employee_ID) REFERENCES Employee(Employee_ID) ON UPDATE CASCADE ON DELETE CASCADE,
                                 PRIMARY KEY (Project_ID, Employee_ID)
);

-- Payment Table
CREATE TABLE Payment (
                         Payment_ID VARCHAR(10) PRIMARY KEY,
                         Project_ID VARCHAR(10),
                         Date DATE,
                         Type VARCHAR(50),
                         Amount DECIMAL(10, 2),
                         FOREIGN KEY (Project_ID) REFERENCES Project(Project_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Expenses Table
CREATE TABLE Expenses (
                          Expense_ID VARCHAR(10) PRIMARY KEY,
                          Type VARCHAR(50),
                          Amount DECIMAL(10, 2),
                          Date DATE,
                          Project_ID VARCHAR(10),
                          Employee_ID VARCHAR(10),
                          FOREIGN KEY (Project_ID) REFERENCES Project(Project_ID) ON UPDATE CASCADE ON DELETE CASCADE,
                          FOREIGN KEY (Employee_ID) REFERENCES Employee(Employee_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Material Table
CREATE TABLE Material (
                          Material_ID VARCHAR(10) PRIMARY KEY,
                          Name VARCHAR(100),
                          Quantity_in_Stock VARCHAR(20),
                          Unit VARCHAR(20)
);

-- MaterialUsage Table
CREATE TABLE MaterialUsage (
                               Usage_ID VARCHAR(10) PRIMARY KEY,
                               Project_ID VARCHAR(10),
                               Material_ID VARCHAR(10),
                               Quantity_used VARCHAR(20),
                               Date DATE,
                               FOREIGN KEY (Project_ID) REFERENCES Project(Project_ID) ON UPDATE CASCADE ON DELETE CASCADE,
                               FOREIGN KEY (Material_ID) REFERENCES Material(Material_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Machine Table
CREATE TABLE Machine (
                         Machine_ID VARCHAR(10) PRIMARY KEY,
                         Name VARCHAR(100),
                         Status VARCHAR(50),
                         Description VARCHAR(150)
);

-- MachineMaintenance Table
CREATE TABLE MachineMaintenance (
                                    MMR_ID VARCHAR(10) PRIMARY KEY,
                                    Machine_ID VARCHAR(10),
                                    Date DATE,
                                    Cost DECIMAL(10, 2),
                                    Description VARCHAR(255),
                                    FOREIGN KEY (Machine_ID) REFERENCES Machine(Machine_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

-- MachineDetails Table
CREATE TABLE MachineDetails (
                                Project_ID VARCHAR(10),
                                Machine_ID VARCHAR(10),
                                FOREIGN KEY (Project_ID) REFERENCES Project(Project_ID) ON UPDATE CASCADE ON DELETE CASCADE,
                                FOREIGN KEY (Machine_ID) REFERENCES Machine(Machine_ID) ON UPDATE CASCADE ON DELETE CASCADE,
                                PRIMARY KEY (Project_ID, Machine_ID)
);

-- Supplier Table
CREATE TABLE Supplier (
                          Supplier_ID VARCHAR(10) PRIMARY KEY,
                          Name VARCHAR(100),
                          Address VARCHAR(250),
                          Phone_No VARCHAR(15),
                          Email VARCHAR(100)
);

-- MaterialBuy Table
CREATE TABLE MaterialBuy (
                             Payment_ID VARCHAR(10) PRIMARY KEY,
                             Material_ID VARCHAR(10),
                             Supplier_ID VARCHAR(10),
                             Date DATE,
                             Unit_Amount DECIMAL(10, 2),
                             Quantity VARCHAR(20),
                             Total_Price DECIMAL(20, 2),
                             FOREIGN KEY (Material_ID) REFERENCES Material(Material_ID) ON UPDATE CASCADE ON DELETE CASCADE,
                             FOREIGN KEY (Supplier_ID) REFERENCES Supplier(Supplier_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Report Table
CREATE TABLE Report (
                        Report_ID VARCHAR(10) PRIMARY KEY,
                        Material_ID VARCHAR(10),
                        Daily DATE,
                        FOREIGN KEY (Material_ID) REFERENCES Material(Material_ID) ON UPDATE CASCADE ON DELETE CASCADE
);


-- /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// --

-- Client Table
INSERT INTO Client (Client_ID, Name, Address, Phone_No, Email) VALUES
                                                                   ('C001', 'Chamath Dilshan', '123 Main St, Galle', '0771234567', 'chamath@gmail.com'),
                                                                   ('C002', 'Nadeesha Fernando', '45 Park Rd, Colombo', '0712345678', 'nadeesha323@gmail.com'),
                                                                   ('C003', 'Tharindu Silva', '78 Lake Ave, Kandy', '0723456789', 'tharindu@gmail.com'),
                                                                   ('C004', 'Ishara Wijesinghe', '9 River St, Jaffna', '0754567890', 'ishara92@gmail.com'),
                                                                   ('C005', 'Sanduni Jayasinghe', '56 Hill Rd, Matara', '0705678901', 'sanduni98@gmail.com');

-- Project Table
INSERT INTO Project (Project_ID, Name, Start_date, End_date, Type, Status, Client_ID) VALUES
                                                                                          ('P001', 'Bridge Construction', '2025-01-01', '2025-06-30', 'Infrastructure', 'Ongoing', 'C001'),
                                                                                          ('P002', 'Mall Building', '2025-02-15', '2025-08-15', 'Commercial', 'Planned', 'C002'),
                                                                                          ('P003', 'Apartment Complex', '2025-03-01', '2025-12-01', 'Residential', 'Ongoing', 'C003'),
                                                                                          ('P004', 'School Renovation', '2025-04-10', '2025-09-30', 'Educational', 'Planned', 'C004'),
                                                                                          ('P005', 'Hospital Expansion', '2025-05-01', '2025-11-30', 'Healthcare', 'Ongoing', 'C005');

-- Employee Table
INSERT INTO Employee (Employee_ID, Name, Phone_No, Address, Role) VALUES
                                                                      ('E001', 'Jagath Perera', '0711111111', 'Colombo 03', 'Engineer'),
                                                                      ('E002', 'Saman Silva', '0722222222', 'Galle 05', 'Supervisor'),
                                                                      ('E003', 'Kamal Fernando', '0733333333', 'Kandy 02', 'Architect'),
                                                                      ('E004', 'Nimal Jayawardena', '0744444444', 'Matara 01', 'Laborer'),
                                                                      ('E005', 'Sunil Rajapaksha', '0755555555', 'Jaffna 04', 'Foreman');

-- EmployeeDetails Table
INSERT INTO EmployeeDetails (Project_ID, Work_date, Work_Day_Count, Employee_ID) VALUES
                                                                                     ('P001', '2025-08-01', 5, 'E001'),
                                                                                     ('P002', '2025-08-02', 3, 'E002'),
                                                                                     ('P003', '2025-08-03', 4, 'E003'),
                                                                                     ('P004', '2025-08-04', 2, 'E004'),
                                                                                     ('P005', '2025-08-05', 6, 'E005');

-- Payment Table
INSERT INTO Payment (Payment_ID, Project_ID, Date, Type, Amount) VALUES
                                                                     ('PM001', 'P001', '2025-08-01', 'Advance', 50000.00),
                                                                     ('PM002', 'P002', '2025-08-02', 'Installment', 75000.00),
                                                                     ('PM003', 'P003', '2025-08-03', 'Full', 120000.00),
                                                                     ('PM004', 'P004', '2025-08-04', 'Advance', 40000.00),
                                                                     ('PM005', 'P005', '2025-08-05', 'Installment', 95000.00);

-- Expenses Table
INSERT INTO Expenses (Expense_ID, Type, Amount, Date, Project_ID, Employee_ID) VALUES
                                                                                   ('EX001', 'Transport', 5000.00, '2025-08-01', 'P001', 'E001'),
                                                                                   ('EX002', 'Food', 3000.00, '2025-08-02', 'P002', 'E002'),
                                                                                   ('EX003', 'Material', 15000.00, '2025-08-03', 'P003', 'E003'),
                                                                                   ('EX004', 'Equipment', 7000.00, '2025-08-04', 'P004', 'E004'),
                                                                                   ('EX005', 'Labor', 10000.00, '2025-08-05', 'P005', 'E005');

-- Material Table
INSERT INTO Material (Material_ID, Name, Quantity_in_Stock, Unit) VALUES
                                                                      ('M001', 'Cement', '500', 'Bags'),
                                                                      ('M002', 'Steel Rods', '200', 'Pieces'),
                                                                      ('M003', 'Sand', '1000', 'Kg'),
                                                                      ('M004', 'Bricks', '1500', 'Pieces'),
                                                                      ('M005', 'Paint', '300', 'Liters');

-- MaterialUsage Table
INSERT INTO MaterialUsage (Usage_ID, Project_ID, Material_ID, Quantity_used, Date) VALUES
                                                                                       ('MU001', 'P001', 'M001', '50', '2025-08-01'),
                                                                                       ('MU002', 'P002', 'M002', '20', '2025-08-02'),
                                                                                       ('MU003', 'P003', 'M003', '100', '2025-08-03'),
                                                                                       ('MU004', 'P004', 'M004', '150', '2025-08-04'),
                                                                                       ('MU005', 'P005', 'M005', '30', '2025-08-05');

-- Machine Table
INSERT INTO Machine (Machine_ID, Name, Status, Description) VALUES
                                                                ('MC001', 'Excavator', 'Active', 'Used for digging'),
                                                                ('MC002', 'Crane', 'Inactive', 'Used for lifting materials'),
                                                                ('MC003', 'Concrete Mixer', 'Active', 'Mixes cement and sand'),
                                                                ('MC004', 'Bulldozer', 'Active', 'Clears land'),
                                                                ('MC005', 'Loader', 'Inactive', 'Loads materials');

-- MachineMaintenance Table
INSERT INTO MachineMaintenance (MMR_ID, Machine_ID, Date, Cost, Description) VALUES
                                                                                 ('MM001', 'MC001', '2025-08-01', 5000.00, 'Engine oil change'),
                                                                                 ('MM002', 'MC002', '2025-08-02', 7000.00, 'Hydraulic check'),
                                                                                 ('MM003', 'MC003', '2025-08-03', 6000.00, 'Gear repair'),
                                                                                 ('MM004', 'MC004', '2025-08-04', 8000.00, 'Blade sharpening'),
                                                                                 ('MM005', 'MC005', '2025-08-05', 5500.00, 'Wheel replacement');

-- MachineDetails Table
INSERT INTO MachineDetails (Project_ID, Machine_ID) VALUES
                                                        ('P001', 'MC001'),
                                                        ('P002', 'MC002'),
                                                        ('P003', 'MC003'),
                                                        ('P004', 'MC004'),
                                                        ('P005', 'MC005');

-- Supplier Table
INSERT INTO Supplier (Supplier_ID, Name, Address, Phone_No, Email) VALUES
                                                                       ('S001', 'Supreme Cement', 'Colombo 07', '0711111111', 'cement@supreme.com'),
                                                                       ('S002', 'SteelWorks Ltd', 'Galle 03', '0722222222', 'steel@works.com'),
                                                                       ('S003', 'Sand Suppliers', 'Matara 02', '0733333333', 'sand@supplier.com'),
                                                                       ('S004', 'Brick Factory', 'Kandy 05', '0744444444', 'brick@factory.com'),
                                                                       ('S005', 'Paints Lanka', 'Jaffna 01', '0755555555', 'paint@lanka.com');

-- MaterialBuy Table
INSERT INTO MaterialBuy (Payment_ID, Material_ID, Supplier_ID, Date, Unit_Amount, Quantity, Total_Price) VALUES
                                                                                                             ('MB001', 'M001', 'S001', '2025-08-01', 1200.00, '50', 60000.00),
                                                                                                             ('MB002', 'M002', 'S002', '2025-08-02', 1500.00, '20', 30000.00),
                                                                                                             ('MB003', 'M003', 'S003', '2025-08-03', 50.00, '100', 5000.00),
                                                                                                             ('MB004', 'M004', 'S004', '2025-08-04', 25.00, '150', 3750.00),
                                                                                                             ('MB005', 'M005', 'S005', '2025-08-05', 300.00, '30', 9000.00);

-- Report Table
INSERT INTO Report (Report_ID, Material_ID, Daily) VALUES
                                                       ('R001', 'M001', '2025-08-01'),
                                                       ('R002', 'M002', '2025-08-02'),
                                                       ('R003', 'M003', '2025-08-03'),
                                                       ('R004', 'M004', '2025-08-04'),
                                                       ('R005', 'M005', '2025-08-05');
