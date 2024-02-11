create table user
(
    Name     varchar(255) not null,
    Email    varchar(255) not null,
    Gender   varchar(10)  not null,
    Birthday date         not null,
    Password varchar(255) not null,
    constraint Email
        unique (Email)
);

INSERT INTO signedusers.user (Name, Email, Gender, Birthday, Password) VALUES ('Heshan', 'heshan@gmail.com', 'Male', '1997-09-04', '789');
INSERT INTO signedusers.user (Name, Email, Gender, Birthday, Password) VALUES ('Sahan', 'sahan@gmail.com', 'Male', '2001-10-10', '741zxc');
