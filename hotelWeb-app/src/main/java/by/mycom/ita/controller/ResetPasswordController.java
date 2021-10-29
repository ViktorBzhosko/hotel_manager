package by.mycom.ita.controller;

import by.mycom.ita.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ResetPasswordController {

    private final UserService userService;

    @Autowired
    public ResetPasswordController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/reset")
    public String resetPassword(String login) {
        userService.resetPassword(login);
        return "redirect:/reset-form";
    }

    @PostMapping("/reset/verification")
    public String resetVerification(String password, String uid) {
        userService.resetVerification(password, uid);
        return "login";
    }
}
