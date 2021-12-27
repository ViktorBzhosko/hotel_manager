package by.mycom.ita.controller;

import by.mycom.ita.dto.CommonUserDto;
import by.mycom.ita.services.UserFeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CommonUserController {

    private final UserFeignClient userFeignClient;

    public CommonUserController(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    @GetMapping("/user-create")
    public String createUserForm(CommonUserDto commonUserDto) {
        return "user-create";
    }

    @PostMapping("/user")
    public String createClient(@ModelAttribute @Valid CommonUserDto commonUserDto, Model model) {
        CommonUserDto createdUser = userFeignClient.createUser(commonUserDto);
        model.addAttribute("Client", createdUser);
        return "redirect:/user-create";
    }

    @ModelAttribute("user")
    private CommonUserDto createCommonUserDto() {
        return new CommonUserDto();
    }
}
