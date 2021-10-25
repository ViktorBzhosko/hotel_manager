create table common_user
(
    user_id      long not null auto_increment,
    email        varchar(255),
    first_name   varchar(255),
    passport     varchar(255),
    phone_number integer,
    second_name  varchar(255),
    user_role    integer,
    booking_id   bigint,
    PRIMARY KEY (user_id),
    Foreign Key (booking_id) references booking (id)
);

create table booking
(
    id             long not null auto_increment,
    booking_status integer,
    date_chek_in   date,
    date_chek_out  date,
    hotel_hotel_id bigint,
    room_room_id   bigint,
    users_user_id  bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (room_room_id) references room (room_id),
    FOREIGN KEY (users_user_id) references common_user (user_id),
    FOREIGN KEY (hotel_hotel_id) references hotel (hotel_id)
);

alter table booking
    add constraint fkbejtw8590cotmxmwao5axlmu8
        foreign key (users_user_id) references common_user;

create table hotel
(
    hotel_id    long not null auto_increment,
    avg_mark    double precision,
    convenience varchar(255),
    location    varchar(255),
    name        varchar(255)
);

create table hotel_rating
(
    id             long not null auto_increment,
    count_of_marks integer,
    mark           integer,
    hotel_id       bigint,
    primary key (id),
    foreign key (hotel_id) references hotel (hotel_id)
);

create table room
(
    room_id         long    not null auto_increment,
    accommodation   varchar(255),
    comfort         varchar(255),
    number_of_room  integer not null,
    hotels_hotel_id bigint,
    hotel_id        bigint,
    primary key (room_id),
    foreign key (hotels_hotel_id) references hotel (hotel_id),
    foreign key (hotel_id) references hotel (hotel_id)

);


create table hotel_favorites
(
    favorites_id   long not null auto_increment,
    hotel_hotel_id bigint,
    user_user_id   bigint,
    user_id        bigint,
    PRIMARY KEY (favorites_id),
    FOREIGN KEY (user_user_id) references common_user(user_id),
    FOREIGN KEY (hotel_hotel_id) references hotel(hotel_id),
    FOREIGN KEY (user_id) references common_user(user_id)
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

INSERT INTO room (accommodation, comfort, number_of_room, hotels_hotel_id, hotel_id)
VALUES ('TRIPLE', 'DELUXE', 1, 1, 1);
INSERT INTO room (accommodation, comfort, number_of_room, hotels_hotel_id, hotel_id)
VALUES ('APARTMENT', 'STANDARD', 2, 1, 1);
INSERT INTO room (accommodation, comfort, number_of_room, hotels_hotel_id, hotel_id)
VALUES ('SINGLE', 'DELUXE', 3, 1, 1);
INSERT INTO room (accommodation, comfort, number_of_room, hotels_hotel_id, hotel_id)
VALUES ('TRIPLE', 'DELUXE', 4, 1, 1);
INSERT INTO room (accommodation, comfort, number_of_room, hotels_hotel_id, hotel_id)
VALUES ('APARTMENT', 'STANDARD', 2, 2, 2);
INSERT INTO room (accommodation, comfort, number_of_room, hotels_hotel_id, hotel_id)
VALUES ('TRIPLE', 'FAMILY', 3, 3, 3);
INSERT INTO room (accommodation, comfort, number_of_room, hotels_hotel_id, hotel_id)
VALUES ('SINGLE', 'BUSINESS', 4, 4, 4);
INSERT INTO room (accommodation, comfort, number_of_room, hotels_hotel_id, hotel_id)
VALUES ('APARTMENT', 'STANDARD', 5, 5, 5);

INSERT INTO booking (booking_status, date_chek_in, date_chek_out, hotel_hotel_id, room_room_id, users_user_id)
VALUES (0, 2021 - 01 - 01, 2021 - 01 - 10, 1, 1, 1);
INSERT INTO booking (booking_status, date_chek_in, date_chek_out, hotel_hotel_id, room_room_id, users_user_id)
VALUES (1, 2021 - 02 - 01, 2021 - 02 - 10, 1, 2, 2);
INSERT INTO booking (booking_status, date_chek_in, date_chek_out, hotel_hotel_id, room_room_id, users_user_id)
VALUES (1, 2021 - 01 - 15, 2021 - 01 - 20, 1, 3, 3);
INSERT INTO booking (booking_status, date_chek_in, date_chek_out, hotel_hotel_id, room_room_id, users_user_id)
VALUES (2, 2021 - 02 - 15, 2021 - 02 - 20, 2, 1, 4);
INSERT INTO booking (booking_status, date_chek_in, date_chek_out, hotel_hotel_id, room_room_id, users_user_id)
VALUES (1, 2021 - 01 - 21, 2021 - 01 - 29, 1, 2, 5);

INSERT INTO common_user (email, first_name, passport, phone_number, second_name, user_role, booking_id)
VALUES ('vic308@mail.ru', 'viktor', 'ab123', '123456', 'bzhosko', 1, 1);
INSERT INTO common_user (email, first_name, passport, phone_number, second_name, user_role, booking_id)
VALUES ('vic308@mail.ru', 'sergey', 'ac147', '123456', 'rem', 1, 2);

INSERT INTO hotel_favorites (hotel_hotel_id, user_user_id, user_id)
VALUES (1, 1, 1);
INSERT INTO hotel_favorites (hotel_hotel_id, user_user_id, user_id)
VALUES (2, 1, 1);
