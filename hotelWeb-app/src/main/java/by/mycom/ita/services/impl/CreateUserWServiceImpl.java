package by.mycom.ita.services.impl;

import by.mycom.ita.dto.CommonUserDto;
import by.mycom.ita.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

@Service
public class CreateUserWServiceImpl implements IUserService {

    private final RestTemplate restTemplate;

    private final String Url = "http://localhost:5438/testdb/users/create";

    @Autowired
    public CreateUserWServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void createClient(CommonUserDto commonUserDto, Model model) {
        CommonUserDto createdUser = restTemplate.postForObject(Url + "/client", commonUserDto, CommonUserDto.class);
        model.addAttribute("Client", createdUser);
    }

    @Override
    public void createManager(CommonUserDto commonUserDto, Model model) {
        CommonUserDto createdUser = restTemplate.postForObject(Url + "/manager", commonUserDto, CommonUserDto.class);
        model.addAttribute("Manager", createdUser);
    }

    @Override
    public void createAdmin(CommonUserDto commonUserDto, Model model) {
        CommonUserDto createdUser = restTemplate.postForObject(Url + "admin", commonUserDto, CommonUserDto.class);
        model.addAttribute("Admin", createdUser);
    }
}
