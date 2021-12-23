package by.mycom.ita.services.impl;

import by.mycom.ita.configuration.ConfigClient;
import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.dto.RoomDto;
import by.mycom.ita.services.IRoomService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RoomServiceImpl implements IRoomService {

    private final RestTemplate restTemplate;

    private final ConfigClient client;
//    private final String Url = "http://localhost:8003/hotel-app";

    public RoomServiceImpl(RestTemplate restTemplate, ConfigClient client) {
        this.restTemplate = restTemplate;
        this.client = client;
    }

    @Override
    public RoomDto create(RoomDto roomDto) {
        return restTemplate.postForObject(client.serviceInfo() + "/room/create", roomDto, RoomDto.class);

    }

    @Override
    public RoomDto updateTarget(Long id) {
        return restTemplate.getForObject(client.serviceInfo() + "/room/find/" + id, RoomDto.class);

    }

    @Override
    public void update(RoomDto roomDto) {
        restTemplate.put(client.serviceInfo() + "/room/update" , roomDto, HotelDto.class);
    }
}
