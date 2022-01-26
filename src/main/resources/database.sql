create database hw7;
use hw7;

create table companies (
  id int not null auto_increment,
  name varchar(45) default null,
  quantity_staff int default null,
  primary key (id)
);

create table customers (
  id int not null auto_increment,
  name varchar(45) default null,
  category varchar(45) default null,
  primary key (id)
);

create table developers (
  id int not null auto_increment,
  name varchar(45) not null,
  age int default null,  
  company_id int default null,
  salary decimal(10,0) default null,
  primary key (id),
  foreign key (company_id) references companies (id)
);

create table skills (
  id int not null auto_increment,
  field varchar(45) default null,
  level varchar(45) default null,
  primary key (id)
);

create table projects (
  id int not null auto_increment,
  name varchar(45) default null,
  start timestamp default null,
  cost decimal(10,0) default null,
  primary key (id)
);

create table companies_projects (
  company_id int not null,
  project_id int not null,
  foreign key (company_id) references companies (id),
  foreign key (project_id) references projects (id)
);

create table customers_projects (
  customer_id int not null,
  project_id int not null,
  foreign key (customer_id) references customers (id),
  foreign key (project_id) references projects (id)
);

create table developers_projects (
  developer_id int not null,
  project_id int not null,
  foreign key (developer_id) references developers (id),
  foreign key (project_id) references projects (id)
);

create table developers_skills (
  developer_id int not null,
  skill_id int not null,
  foreign key (developer_id) references developers (id),
  foreign key (skill_id) references skills (id)
);

insert into companies values
(1,'GoIT',1000),
(2,'EPAM',10000),
(3,'DigiCODE',500),
(4,'N-iX',5000);

insert into developers values
(1,'Ivan',20,1,500),
(2,'Petro',25,2,550),
(3,'Vasyl',30,3,600),
(4,'Kyrylo',26,2,450);

insert into skills values
(1,'Java','junior'),
(2,'C++','middle'),
(3,'C#','junior'),
(4,'JS','senior');

insert into projects values
(1,'ShedullerBot','2005-05-20',10000),
(2,'NavigationBot','2006-06-20',20000),
(3,'TrainingBot','2007-07-20',25000),
(4,'LectionBot','2008-08-20',30000);

insert into customers values
(1,'NUPP','regular'),
(2,'PDAA','one-time'),
(3,'ChDTU','lost');

insert into companies_projects values
(1,1),(1,2),(1,3),(3,2),(2,3),(2,1);

insert into customers_projects values 
(1,1),(2,1),(1,2),(1,3),(2,3),(3,3),(1,4);

insert into developers_projects values
(3,1),(2,1),(3,2),(1,3),(1,4),(4,1),(4,2);

insert into developers_skills values
(1,1),(3,1),(4,1),(2,2),(3,2),(2,3),(1,4);