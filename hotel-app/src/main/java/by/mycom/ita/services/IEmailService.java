package by.mycom.ita.services;

public interface IEmailService {

    void sendSimpleMessage(Long id);

    void notifyResetPassword(Long userId, String textMessage);

}
