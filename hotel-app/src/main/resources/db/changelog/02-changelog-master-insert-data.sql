INSERT INTO hotel (avg_mark, convenience, location, name)
VALUES (4.4, '5', 'Egypt', 'Mercuri');

INSERT INTO hotel_rating (count_of_marks, mark, hotel_id)
VALUES (12, 4, 1);

INSERT INTO room (accommodation, comfort, number_of_room, hotels_id)
VALUES ('TRIPLE', 'DELUXE', 1, 1);

INSERT INTO booking (booking_status, date_chek_in, date_chek_out, hotel_id, room_id)
VALUES (0, '2021-01-01', '2021-01-10', 1, 1);

INSERT INTO common_user (email, first_name, passport, phone_number, second_name,  booking_id)
VALUES ('vic308@mail.ru', 'viktor', 'ab123', '123456', 'bzhosko', 1);

INSERT INTO hotel_favorites (hotel_id, user_id)
VALUES (1, 1);
