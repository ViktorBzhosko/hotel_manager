package by.mycom.ita.controller;

import by.mycom.ita.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResetPasswordController {

    private final UserService userService;

    @Autowired
    public ResetPasswordController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/reset/password")
    public String getResetPage(Model model) {
        return "resetPage";
    }

    @GetMapping("/reset/password/form")
    public String getResetFormPage(Model model) {
        return "reset-form";
    }

    @PostMapping("/reset")
    public String resetPassword(@RequestParam(name = "username") String login) {
        userService.resetPassword(login);
        return "redirect:/reset/password/form";
    }

    @PostMapping("/reset/verification")
    public String resetVerification(@RequestParam String password,
                                    @RequestParam String uid) {
        userService.resetVerification(password, uid);
        return "login";
    }
}
