drop table if exists hotel_rating;
drop table if exists hotel_favorites;
drop table if exists common_user;
drop table if exists booking;
drop table if exists room;
drop table if exists hotel;

create table hotel
(
    hotel_id    bigserial,
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

INSERT INTO hotel (avg_mark, convenience, location, name)
VALUES (4.4, '5', 'Egypt', 'Mercuri');
INSERT INTO hotel (avg_mark, convenience, location, name)
VALUES (4.1, '5', 'Egypt', 'Alladin');
INSERT INTO hotel (avg_mark, convenience, location, name)
VALUES (4.2, '5', 'Egypt', 'Alibaba');
INSERT INTO hotel (avg_mark, convenience, location, name)
VALUES (5.0, '5', 'Spain', 'Mercuri');
INSERT INTO hotel (avg_mark, convenience, location, name)
VALUES (4.4, '5', 'Turkey', 'Sindbad');

INSERT INTO hotel_rating (count_of_marks, mark, hotel_id)
VALUES (12, 4, 1);
INSERT INTO hotel_rating (count_of_marks, mark, hotel_id)
VALUES (15, 5, 2);
INSERT INTO hotel_rating (count_of_marks, mark, hotel_id)
VALUES (16, 5, 3);
INSERT INTO hotel_rating (count_of_marks, mark, hotel_id)
VALUES (18, 4, 4);
INSERT INTO hotel_rating (count_of_marks, mark, hotel_id)
VALUES (21, 5, 5);

INSERT INTO room (accommodation, comfort, number_of_room, hotels_id)
VALUES ('TRIPLE', 'DELUXE', 1, 1);
INSERT INTO room (accommodation, comfort, number_of_room, hotels_id)
VALUES ('APARTMENT', 'STANDARD', 2, 1);
INSERT INTO room (accommodation, comfort, number_of_room, hotels_id)
VALUES ('SINGLE', 'DELUXE', 3, 1);
INSERT INTO room (accommodation, comfort, number_of_room, hotels_id)
VALUES ('TRIPLE', 'DELUXE', 4, 1);
INSERT INTO room (accommodation, comfort, number_of_room, hotels_id)
VALUES ('APARTMENT', 'STANDARD', 2, 2);
INSERT INTO room (accommodation, comfort, number_of_room, hotels_id)
VALUES ('TRIPLE', 'FAMILY', 3, 3);
INSERT INTO room (accommodation, comfort, number_of_room, hotels_id)
VALUES ('SINGLE', 'BUSINESS', 4, 4);
INSERT INTO room (accommodation, comfort, number_of_room, hotels_id)
VALUES ('APARTMENT', 'STANDARD', 5, 5);

INSERT INTO booking (booking_status, date_chek_in, date_chek_out, hotel_id, room_id)
VALUES (0, 2021 - 01 - 01, 2021 - 01 - 10, 1, 1);
INSERT INTO booking (booking_status, date_chek_in, date_chek_out, hotel_id, room_id)
VALUES (1, 2021 - 02 - 01, 2021 - 02 - 10, 1, 2);
INSERT INTO booking (booking_status, date_chek_in, date_chek_out, hotel_id, room_id)
VALUES (1, 2021 - 01 - 15, 2021 - 01 - 20, 1, 3);
INSERT INTO booking (booking_status, date_chek_in, date_chek_out, hotel_id, room_id)
VALUES (2, 2021 - 02 - 15, 2021 - 02 - 20, 2, 1);
INSERT INTO booking (booking_status, date_chek_in, date_chek_out, hotel_id, room_id)
VALUES (1, 2021 - 01 - 21, 2021 - 01 - 29, 1, 2);

INSERT INTO common_user (email, first_name, passport, phone_number, second_name,  booking_id)
VALUES ('vic308@mail.ru', 'viktor', 'ab123', '123456', 'bzhosko', 1);
INSERT INTO common_user (email, first_name, passport, phone_number, second_name, booking_id)
VALUES ('vic308@mail.ru', 'sergey', 'ac147', '123456', 'rem', 2);

INSERT INTO hotel_favorites (hotel_id, user_id)
VALUES (1, 1);
INSERT INTO hotel_favorites (hotel_id, user_id)
VALUES (2, 1);
