package by.mycom.ita.controller;

import by.mycom.ita.dto.CommonUserDto;
import by.mycom.ita.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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

    @GetMapping("/registration/manager")
    public String registrationManager(Model model) {
        return "reg-manager";
    }

    @PostMapping("/registration")
    public String addUser(@Valid @ModelAttribute("userForm")  CommonUserDto commonUserDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";

        } else if (userService.saveClient(commonUserDto)) return "login";

        else return "registration";
    }

    @PostMapping("/registration/manager")
    public String addManager(@ModelAttribute @Valid CommonUserDto commonUserDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";

        } else if (userService.saveManager(commonUserDto)) return "login";

        else return "reg-manager";
    }

    @ModelAttribute("CommonUserDto")
    private CommonUserDto createCommonUserDto() {
        return new CommonUserDto();
    }
}
