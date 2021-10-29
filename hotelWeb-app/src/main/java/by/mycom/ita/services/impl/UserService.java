package by.mycom.ita.services.impl;

import by.mycom.ita.configuration.UserDetail;
import by.mycom.ita.dao.IUserDao;
import by.mycom.ita.dto.CommonUserDto;
import by.mycom.ita.dto.EmailNotificationDto;
import by.mycom.ita.model.Role;
import by.mycom.ita.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private final String Url = "http://localhost:5438/testdb";

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDao.findByLogin(login);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserDetail(user);
    }

    @Transactional
    public boolean saveUser(CommonUserDto commonUserDto) {

        User userFromDB = userDao.findByLogin(commonUserDto.getLogin());

        if (userFromDB != null) {
            return false;
        }

        CommonUserDto responseUser = restTemplate.postForObject(Url + "/users/create/client", commonUserDto, CommonUserDto.class);
        if (responseUser != null) {
            User user = objectMapper.convertValue(commonUserDto, User.class);
            user.setId(responseUser.getId());
            user.setRoles(Collections.singleton(Role.builder()
                    .roleName("Client")
                    .build()));
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setLogin(user.getLogin());
            userDao.save(user);
            return true;
        }
        return false;
    }

    @Transactional
    public void resetPassword(String login) {
        User user = userDao.findByLogin(login);
        UUID resetUid = UUID.randomUUID();
        user.setResetUid(resetUid.toString());
        EmailNotificationDto emailNotificationDto = EmailNotificationDto.builder()
                .userId(user.getId())
                .message("Reset code: " + resetUid)
                .build();
        restTemplate.postForObject(Url + "/email/notify/reset", emailNotificationDto, Void.class);
        userDao.save(user);
    }

    public void resetVerification(String newPassword, String resetUid) {
        User user = userDao.findByResetUid(resetUid);
        if (user != null) {
            user.setPassword(newPassword);
            user.setResetUid(null);
            userDao.save(user);
        }
    }
}
