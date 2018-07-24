CREATE TABLE IF NOT EXISTS persistent_logins(
series varchar (255) primary key not null,
username varchar (255) not null,
token varchar (255) not null,
last_used TIMESTAMP not null,
);