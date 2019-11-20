CREATE DATABASE MODULARHRM
GO

USE  MODULARHRM
GO

CREATE TABLE tbProfile(
	Id int PRIMARY KEY IDENTITY(1,1),
	Name VARCHAR(100) NOT NULL,
	PermissionLevel VARCHAR(100) NOT NULL,
	CreatedDate DATETIME, 
	LastModifiedDate DATETIME,
	CreateById int,
	LastModifiedbyId int
)
GO

CREATE TABLE tbUser(
	Id int PRIMARY KEY IDENTITY(1,1),
	Name VARCHAR(100),
	UserName VARCHAR(100) UNIQUE NOT NULL,
	Password VARCHAR(100) NOT NULL,
	Email VARCHAR(100) UNIQUE NOT NULL,
	CreatedDate DATETIME, 
	LastModifiedDate DATETIME,
	Profile int not null,
	CreateById int,
	LastModifiedbyId int
)
GO

ALTER TABLE tbProfile
ADD CONSTRAINT FK_Profile_User_LastModifiedbyId FOREIGN KEY (LastModifiedbyId) REFERENCES tbUser(Id)
GO

ALTER TABLE tbProfile
ADD CONSTRAINT FK_Profile_User_CreateById FOREIGN KEY (CreateById) REFERENCES tbUser(Id)

CREATE TABLE tbProject(
	Id int PRIMARY KEY IDENTITY(1,1),
	Name VARCHAR(100),
	CustomerName VARCHAR(100),
	CreatedDate DATETIME, 
	LastModifiedDate DATETIME,
	CreateById int, 
	CONSTRAINT FK_Project_CreateById FOREIGN KEY (CreateById) REFERENCES tbUser(Id),
	LastModifiedbyId int, 
	CONSTRAINT FK_Project_LastModifiedbyId FOREIGN KEY (LastModifiedbyId) REFERENCES tbUser(Id)
)
GO

CREATE TABLE tbTeam(
	Id int PRIMARY KEY IDENTITY(1,1),
	Name VARCHAR(100),
	CreatedDate DATETIME, 
	LastModifiedDate DATETIME,
	Manager int not null, 
	CONSTRAINT FK_Team_Manager FOREIGN KEY (Manager) REFERENCES tbUser(Id),
	Project int not null, 
	CONSTRAINT FK_Team_Project FOREIGN KEY (Project) REFERENCES tbProject(Id),
	CreateById int, 
	CONSTRAINT FK_Team_CreateById FOREIGN KEY (CreateById) REFERENCES tbUser(Id),
	LastModifiedbyId int, 
	CONSTRAINT FK_Team_LastModifiedbyId FOREIGN KEY (LastModifiedbyId) REFERENCES tbUser(Id)
)
GO

CREATE TABLE tbMember(
	Id int PRIMARY KEY IDENTITY(1, 1),
	Name VARCHAR(100),
	Role VARCHAR(100),
	Email VARCHAR(100),
	Phone VARCHAR(15),
	CreatedDate DATETIME, 
	LastModifiedDate DATETIME,
	CreateById int, 
	CONSTRAINT FK_Member_CreateById FOREIGN KEY (CreateById) REFERENCES tbUser(Id),
	LastModifiedbyId int, 
	CONSTRAINT FK_Member_LastModifiedbyId FOREIGN KEY (LastModifiedbyId) REFERENCES tbUser(Id)
	
)
GO

CREATE TABLE tbTeamMember(
	Id int PRIMARY KEY IDENTITY(1,1),
	Name VARCHAR(100),
	Member int not null, 
	CONSTRAINT FK_TeamMember_Member FOREIGN KEY (Member) REFERENCES tbMember(Id),
	Team int not null, 
	CONSTRAINT FK_TeamMember_Team FOREIGN KEY (Team) REFERENCES tbTeam(Id),
	CreatedDate DATETIME, 
	LastModifiedDate DATETIME,
	CreateById int, 
	CONSTRAINT FK_TeamMember_CreateById FOREIGN KEY (CreateById) REFERENCES tbUser(Id),
	LastModifiedbyId int, 
	CONSTRAINT FK_TeamMember_LastModifiedbyId FOREIGN KEY (LastModifiedbyId) REFERENCES tbUser(Id)
)
GO

CREATE TABLE tbEvent(
	Id int PRIMARY KEY IDENTITY(1,1),
	Name VARCHAR(100) NOT NULL,
	Description VARCHAR(100) NOT NULL,
	Date Datetime,
	Location VARCHAR(100),
	Type VARCHAR(100),
	CreatedDate DATETIME, 
	LastModifiedDate DATETIME,
	ResponsibleTeamMember int, 
	CONSTRAINT FK_Event_TeamMember FOREIGN KEY (ResponsibleTeamMember) REFERENCES tbTeamMember(Id),
	CreateById int, 
	CONSTRAINT FK_Event_CreateById FOREIGN KEY (CreateById) REFERENCES tbUser(Id),
	LastModifiedbyId int, 
	CONSTRAINT FK_Event_LastModifiedbyId FOREIGN KEY (LastModifiedbyId) REFERENCES tbUser(Id)
)
GO

CREATE TABLE tbTask(
	Id int PRIMARY KEY IDENTITY(1,1),
	Name VARCHAR(100),
	Description VARCHAR(100),
	DueDate	DATETIME, 	
	TaskCompleted BIT,  
	AssignedTo int not null, 
	CONSTRAINT FK_Task_AssignedToMember FOREIGN KEY (AssignedTo) REFERENCES tbTeamMember(Id),
	RelatedEvent int not null, 
	CONSTRAINT FK_Task_Event FOREIGN KEY (RelatedEvent) REFERENCES tbEvent(Id),
	CreatedDate DATETIME, 
	LastModifiedDate DATETIME,
	CreateById int, 
	CONSTRAINT FK_Task_CreateById FOREIGN KEY (CreateById) REFERENCES tbUser(Id),
	LastModifiedbyId int, 
	CONSTRAINT FK_Task_LastModifiedbyId FOREIGN KEY (LastModifiedbyId) REFERENCES tbUser(Id)
)
GO

INSERT INTO tbProfile VALUES ('modular', 'Desenvolvedor', GETDATE(), GETDATE(), NULL, NULL)
GO

INSERT INTO tbUser VALUES ('Igor', 'igor', '123', 'igor@gmail.com', GETDATE(), GETDATE(), 2, NULL, NULL)
GO
