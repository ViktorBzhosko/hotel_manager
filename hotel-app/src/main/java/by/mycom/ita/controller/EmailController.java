package by.mycom.ita.controller;

import by.mycom.ita.dto.EmailNotificationDto;
import by.mycom.ita.services.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final IEmailService emailService;

    @Autowired
    public EmailController(IEmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/notify/reset")
    private void resetNotification(@RequestBody EmailNotificationDto emailNotificationDto){
        emailService.notifyResetPassword(emailNotificationDto.getUserId(), emailNotificationDto.getMessage());
    }
}
