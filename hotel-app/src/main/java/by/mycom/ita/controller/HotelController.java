package by.mycom.ita.controller;

import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.services.IHotelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final ObjectMapper objectMapper;
    private final IHotelService iServiceHotel;

    public HotelController(ObjectMapper objectMapper, IHotelService iServiceHotel) {
        this.objectMapper = objectMapper;
        this.iServiceHotel = iServiceHotel;
    }

    @ApiOperation(value = "Create hotel")
    @PostMapping("/create")
    public HotelDto create(@RequestBody HotelDto hotelDto) {
        final Hotel hotel = objectMapper.convertValue(hotelDto, Hotel.class);
        Hotel hotelCreated = iServiceHotel.create(hotel);
        return objectMapper.convertValue(hotelCreated, HotelDto.class);
    }

    @ApiOperation(value = "Read all hotels")
    @GetMapping("/read/all")
    public List<HotelDto> readAll() {
        List<Hotel> list = iServiceHotel.readAll();
        return list.stream().map(hotel -> objectMapper.convertValue(hotel, HotelDto.class))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Find hotel by id")
    @GetMapping("/read/{id}")
    public HotelDto readById(@PathVariable Long id) {
        Hotel hotel = iServiceHotel.readById(id);
        return objectMapper.convertValue(hotel, HotelDto.class);
    }

    @ApiOperation(value = "Update hotel")
    @PutMapping("/update/{id}")
    public HotelDto update(@PathVariable(value = "id") Long id,
                           @RequestBody HotelDto hotelDto) {
        final Hotel hotel = objectMapper.convertValue(hotelDto, Hotel.class);
        Hotel updatedHotel = iServiceHotel.update(id, hotel);
        return objectMapper.convertValue(updatedHotel, HotelDto.class);
    }

    @ApiOperation(value = "Delete hotel by id")
    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        iServiceHotel.deleteById(id);
    }

}
