package by.mycom.ita.services;

import by.mycom.ita.dto.RoomDto;

public interface IRoomService {

    RoomDto create(RoomDto roomDto);

    RoomDto updateTarget(Long id);

    void update(RoomDto roomDto);

}
