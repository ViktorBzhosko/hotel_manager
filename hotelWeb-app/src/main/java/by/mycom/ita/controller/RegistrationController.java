package by.mycom.ita.controller;

import by.mycom.ita.dto.CommonUserDto;
import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new CommonUserDto());

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") CommonUserDto commonUserDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";

        } else if (userService.saveUser(commonUserDto)) return "login";

        else return "registration";
    }

    @ModelAttribute("CommonUserDto")
    private CommonUserDto createCommonUserDto() {
        return new CommonUserDto();
    }
}
