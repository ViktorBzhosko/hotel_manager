package by.mycom.ita.services.impl;

import by.mycom.ita.dto.CommonUserDto;
import by.mycom.ita.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

@Service
public class CreateUserServiceImpl implements IUserService {

    private final RestTemplate restTemplate;

    @Autowired
    public CreateUserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void createUser(CommonUserDto commonUserDto, Model model) {
        String url = "http://localhost:5438/testdb/users/create";
        CommonUserDto createdUser = restTemplate.postForObject(url, commonUserDto, CommonUserDto.class);
        model.addAttribute("Client", createdUser);
    }
}
