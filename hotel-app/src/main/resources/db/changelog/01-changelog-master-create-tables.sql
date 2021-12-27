drop table if exists hotel_rating;
drop table if exists hotel_favorites;
drop table if exists common_user;
drop table if exists booking;
drop table if exists room;
drop table if exists hotel;

create table hotel
(
    hotel_id    bigserial ,
    avg_mark    float,
    convenience varchar(255),
    location    varchar(255),
    name        varchar(255),
    primary key (hotel_id)
);

create table room
(
    room_id         bigserial,
    accommodation   varchar(255),
    comfort         varchar(255),
    number_of_room  integer not null,
    hotels_id bigserial,
    primary key (room_id),
    foreign key (hotels_id) references hotel (hotel_id)
);

create table booking
(
    id             bigserial,
    booking_status integer,
    date_chek_in   date,
    date_chek_out  date,
    hotel_id bigserial,
    room_id   bigserial,
    PRIMARY KEY (id),
    FOREIGN KEY (hotel_id) REFERENCES hotel (hotel_id),
    FOREIGN KEY (room_id) REFERENCES room (room_id)

);

create table common_user
(
    user_id      bigserial,
    email        varchar(255),
    first_name   varchar(255),
    passport     varchar(255),
    phone_number integer,
    second_name  varchar(255),
    booking_id   bigint,
    PRIMARY KEY (user_id),
    FOREIGN KEY (booking_id) REFERENCES booking (id)
);

create table hotel_favorites
(
    favorites_id   bigserial,
    hotel_id bigserial,
    user_id   bigserial,
    PRIMARY KEY (favorites_id),
    FOREIGN KEY (user_id) references common_user (user_id),
    FOREIGN KEY (hotel_id) references hotel (hotel_id)
);

create table hotel_rating
(
    id             bigserial,
    count_of_marks integer,
    mark           integer,
    hotel_id       bigserial,
    primary key (id),
    foreign key (hotel_id) references hotel (hotel_id)
);


