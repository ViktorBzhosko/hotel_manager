package by.mycom.ita.controller;

import by.mycom.ita.dto.RoomDto;
import by.mycom.ita.model.Room;
import by.mycom.ita.services.IRoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final ObjectMapper objectMapper;
    private final IRoomService iServiceRoom;

    @Autowired
    public RoomController(ObjectMapper objectMapper, IRoomService iServiceRoom) {
        this.objectMapper = objectMapper;
        this.iServiceRoom = iServiceRoom;
    }

    @PostMapping("/create")
    public List<RoomDto> create(@RequestBody RoomDto roomDto,
                                @RequestParam Long hotelId) {
        final Room room = objectMapper.convertValue(roomDto, Room.class);
        List<Room> roomCreated = iServiceRoom.create(room, hotelId);
        return roomCreated.stream().map(r -> objectMapper.convertValue(r, RoomDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/read/all")
    public List<RoomDto> readAll() {
        List<Room> list = iServiceRoom.readAll();
        return list.stream().map(room -> objectMapper.convertValue(room, RoomDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/read")
    public RoomDto readById(@RequestBody Long id) throws Exception {
        Room room = iServiceRoom.readById(id);
        return objectMapper.convertValue(room, RoomDto.class);
    }

    @PutMapping("/update")
    public RoomDto update(@RequestParam(value = "id") Long id,
                          @RequestBody RoomDto roomDto) {
        final Room room = objectMapper.convertValue(roomDto, Room.class);
        Room updatedRoom = iServiceRoom.update(id, room);
        return objectMapper.convertValue(updatedRoom, RoomDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        iServiceRoom.deleteById(id);
    }
}
