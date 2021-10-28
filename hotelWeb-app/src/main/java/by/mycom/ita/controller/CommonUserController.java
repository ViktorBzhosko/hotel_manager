package by.mycom.ita.controller;

import by.mycom.ita.dto.CommonUserDto;
import by.mycom.ita.services.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommonUserController {

    private final IUserService userService;

    public CommonUserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user-create")
    public String createUserForm(CommonUserDto commonUserDto) {
        return "user-create";
    }

    @PostMapping("/client")
    public String createClient(@ModelAttribute CommonUserDto commonUserDto , Model model) {
        userService.createClient(commonUserDto,model);
        return "redirect:/user-create";
    }

    @PostMapping("/manager")
    public String createManager(@ModelAttribute CommonUserDto commonUserDto , Model model) {
        userService.createManager(commonUserDto,model);
        return "redirect:/user-create";
    }

    @PostMapping("/manager")
    public String createAdmin(@ModelAttribute CommonUserDto commonUserDto , Model model) {
        userService.createAdmin(commonUserDto,model);
        return "redirect:/user-create";
    }

    @ModelAttribute("user")
    private CommonUserDto createCommonUserDto(){
        return new CommonUserDto();
    }
}
