package by.mycom.ita.services.impl;

import by.mycom.ita.configuration.ConfigClient;
import by.mycom.ita.dto.CommonUserDto;
import by.mycom.ita.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

@Service
public class CreateUserServiceImpl implements IUserService {

    private final RestTemplate restTemplate;

    private final ConfigClient client;

    @Autowired
    public CreateUserServiceImpl(RestTemplate restTemplate, ConfigClient client) {
        this.restTemplate = restTemplate;
        this.client = client;
    }

    @Override
    public CommonUserDto createUser(CommonUserDto commonUserDto, Model model) {

        return restTemplate.postForObject(client.serviceInfo()+"/users/create", commonUserDto, CommonUserDto.class);
    }
}
