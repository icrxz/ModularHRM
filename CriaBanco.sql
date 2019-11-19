CREATE DATABASE MODULARHRM
GO

USE MODULARHRM
GO

CREATE TABLE tbProfiles(
	Id int PRIMARY KEY IDENTITY(1,1),
	Name VARCHAR(100),
	PermissionLevel VARCHAR(100),
	CreatedDate DATETIME, 
	LastModifiedDate DATETIME,
	CreateById int,
	CONSTRAINT FK_User_CreateById FOREIGN KEY (CreateById) REFERENCES tbUser(Id),
	LastModifiedbyId int,
	CONSTRAINT FK_User_LastModifiedbyId FOREIGN KEY (LastModifiedbyId) REFERENCES tbUser(Id)
)
GO

CREATE TABLE tbUser(
	Id int PRIMARY KEY IDENTITY(1,1),
	Name VARCHAR(100),
	UserName VARCHAR(100),
	Password VARCHAR(100),
	Email VARCHAR(100),
	CreatedDate DATETIME, 
	LastModifiedDate DATETIME,
	Profile int not null,
	CONSTRAINT FK_User_Profile FOREIGN KEY (Profile) REFERENCES tbProfile(Id),
	CreateById int,
	CONSTRAINT FK_User_CreateById FOREIGN KEY (CreateById) REFERENCES tbUser(Id),
	LastModifiedbyId int,
	CONSTRAINT FK_User_LastModifiedbyId FOREIGN KEY (LastModifiedbyId) REFERENCES tbUser(Id)
)
GO

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
	CONSTRAINT FK_TeamMember_CreateById FOREIGN KEY (CreateById) REFERENCES tbUser(Id),
	LastModifiedbyId int, 
	CONSTRAINT FK_TeamMember_LastModifiedbyId FOREIGN KEY (LastModifiedbyId) REFERENCES tbUser(Id)
	
)

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
	Name VARCHAR(100),
	Description VARCHAR(100),
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
	CONSTRAINT FK_Task_AssignedTo FOREIGN KEY (AssignedTo) REFERENCES tbTeamMember(Id),
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