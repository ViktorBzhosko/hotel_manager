package by.mycom.ita.services.impl;

import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.dto.RoomDto;
import by.mycom.ita.services.IRoomService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RoomServiceImpl implements IRoomService {

    private final RestTemplate restTemplate;

    private final String Url = "http://localhost:8003/hotel-app";

    public RoomServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public RoomDto create(Long hotelId, RoomDto roomDto) {
        return restTemplate.postForObject(Url + "/room/create", roomDto, RoomDto.class);

    }

    @Override
    public RoomDto updateTarget(Long id) {
        return restTemplate.getForObject(Url + "/room/find/" + id, RoomDto.class);

    }

    @Override
    public void update(RoomDto roomDto) {
        restTemplate.put(Url + "/room/update" + roomDto.getId(), roomDto, HotelDto.class);
    }
}
