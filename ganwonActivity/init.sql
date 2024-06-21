USE test

create table test.user(
    userNo BIGINT NOT NULL AUTO_INCREMENT primary key,
    userName varchar(255) NOT NULL,
    userId varchar(255) NOT NULL,
    userEmail varchar(255) NOT NULL,
    userPassword varchar(255) NOT NULL,
    userNick varchar(255) NOT NULL,
    userExit bit NULL,
    userBan bit NULL,
    userRole varchar(255) NOT NULL
);
