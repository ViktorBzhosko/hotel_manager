package by.mycom.ita.controller;

import by.mycom.ita.services.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final IEmailService emailService;

    @Autowired
    public EmailController(IEmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/notify/reset")
    private void resetNotification(@RequestParam Long userId,@RequestParam String message){
        emailService.notifyResetPassword(userId, message);
    }
}
