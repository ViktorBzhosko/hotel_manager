package by.mycom.ita.controller;

import by.mycom.ita.dto.RoomDto;
import by.mycom.ita.services.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoomController {

    private final IRoomService roomService;

    @Autowired
    public RoomController(IRoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/room")
    public String createRoom(@RequestParam Long hotelId, @ModelAttribute RoomDto roomDto, Model model) {
        RoomDto room = roomService.create(hotelId, roomDto);
        model.addAttribute("room", room);
        return "room-create";
    }

    @GetMapping(value = "/room/update")
    public String update(@RequestParam(value = "id", required = false) Long roomId, Model model) {
        RoomDto roomDto = roomService.updateTarget(roomId);
        model.addAttribute("room", roomDto);
        return "room-form";
    }

    @PostMapping(value = "/updatedRoom")
    public String updated(@ModelAttribute RoomDto roomDto, Model model) {
        model.addAttribute("room", roomDto);
        roomService.update(roomDto);
        return "all-hotels";
    }

    @ModelAttribute("RoomDto")
    private RoomDto createHotelDto() {
        return new RoomDto();
    }

}
