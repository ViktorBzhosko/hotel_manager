INSERT INTO hotel (avg_mark, convenience, location, name)
VALUES (4.4, 'FiveStars', 'Egypt', 'Mercuri');
INSERT INTO hotel (avg_mark, convenience, location, name)
VALUES (4.2, 'FiveStars', 'Egypt', 'Aladdin');
INSERT INTO hotel (avg_mark, convenience, location, name)
VALUES (4.3, 'FiveStars', 'Egypt', 'Alibaba');

INSERT INTO common_user (email, first_name, passport, phone_number, second_name)
VALUES ('vic308@mail.ru', 'viktor', 'ab123', '123456', 'bzhosko');

INSERT INTO room (accommodation, comfort, number_of_room, hotels_hotel_id, hotel_id)
VALUES ('SINGLE', 'SUITE', 1, 1, 1);
INSERT INTO room (accommodation, comfort, number_of_room, hotels_hotel_id, hotel_id)
VALUES ('SINGLE', 'SUITE', 2, 1, 1);

INSERT INTO booking (booking_status, date_chek_in, date_chek_out, hotel_hotel_id, room_room_id, users_user_id)
VALUES (0, '2021-01-01', '2021-01-05', 1, 1, 1);
