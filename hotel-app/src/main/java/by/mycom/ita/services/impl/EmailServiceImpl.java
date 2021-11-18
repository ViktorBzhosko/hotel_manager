package by.mycom.ita.services.impl;

import by.mycom.ita.model.CommonUser;
import by.mycom.ita.services.ICommonUserService;
import by.mycom.ita.services.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements IEmailService {

    private final JavaMailSender emailSender;
    private final ICommonUserService commonUserService;

    private final static String MASSAGE_FOR_CLIENT = "Your reservation Cancelled";

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, ICommonUserService commonUserService) {
        this.emailSender = emailSender;
        this.commonUserService = commonUserService;
    }

    @Override
    public void sendSimpleMessage(Long id) {
        CommonUser foundClient = commonUserService.findById(id);
        String clientEmail = foundClient.getEmail();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@hotelmanager.com");
        message.setSubject("Notice");
        message.setTo(clientEmail);
        message.setText(MASSAGE_FOR_CLIENT);
        emailSender.send(message);
    }

    public void notifyResetPassword(Long userId, String textMessage){
        CommonUser foundClient = commonUserService.findById(userId);
        String clientEmail = foundClient.getEmail();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@hotelmanager.com");
        message.setSubject("Reset password notification");
        message.setTo(clientEmail);
        message.setText(textMessage);
        emailSender.send(message);
    }
}
