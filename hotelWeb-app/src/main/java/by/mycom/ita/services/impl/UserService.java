package by.mycom.ita.services.impl;

import by.mycom.ita.configuration.ConfigClient;
import by.mycom.ita.configuration.UserDetail;
import by.mycom.ita.dao.IRoleDao;
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

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ConfigClient client;
  
    private static Role ROLE_ADMIN;
    private static Role ROLE_MANAGER;
    private static Role ROLE_CLIENT;

    @PostConstruct
    private void createRoles() {
        ROLE_ADMIN = new Role(1L, "ROLE_ADMIN", null);
        ROLE_MANAGER = new Role(2L, "ROLE_MANAGER", null);
        ROLE_CLIENT = new Role(3L, "ROLE_CLIENT", null);

        roleDao.saveAll(List.of(ROLE_ADMIN, ROLE_MANAGER, ROLE_CLIENT));

        CommonUserDto userAdmin = CommonUserDto.builder()
                .firstName("Viktor")
                .secondName("Bzhosko")
                .passport("123654ad")
                .email("vbzhoska@mail.ru")
                .phoneNumber(123654)
                .username("admin")
                .password("123")
                .build();

        saveAdmin(userAdmin);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDao.findByUsername(login);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserDetail(user);
    }

    public boolean saveManager(CommonUserDto commonUserDto) {
        boolean isUserSaved = saveUser(commonUserDto, ROLE_MANAGER);
        if (isUserSaved) resetPasswordManager(commonUserDto.getUsername());
        return isUserSaved;
    }

    public boolean saveClient(CommonUserDto commonUserDto) {
        return saveUser(commonUserDto, ROLE_CLIENT);
    }

    private void saveAdmin(CommonUserDto commonUserDto) {
        saveUser(commonUserDto, ROLE_ADMIN);
    }

    @Transactional
    public boolean saveUser(CommonUserDto commonUserDto, Role role) {

        User userFromDB = userDao.findByUsername(commonUserDto.getUsername());

        if (userFromDB != null) {
            return false;
        }

        CommonUserDto responseUser = restTemplate.postForObject( client.serviceInfo()+"/users/create", commonUserDto, CommonUserDto.class);
        if (responseUser != null) {
            User user = objectMapper.convertValue(commonUserDto, User.class);
            user.setId(responseUser.getId());
            user.setRoles(Collections.singleton(role));
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setUsername(user.getUsername());
            userDao.save(user);
            return true;
        }
        return false;
    }

    public void resetPassword(String login) {
        resetPassword(login, "Reset code: ");
    }

    public void resetPasswordManager(String login) {
        resetPassword(login, "Code for change password: ");
    }

    @Transactional
    protected void resetPassword(String login, String message) {
        User user = userDao.findByUsername(login);
        UUID resetUid = UUID.randomUUID();
        user.setResetUid(resetUid.toString());
        EmailNotificationDto emailNotificationDto = EmailNotificationDto.builder()
                .userId(user.getId())
                .message(message + resetUid)
                .build();
        restTemplate.postForObject( client.serviceInfo()+"/email/notify/reset", emailNotificationDto, Void.class);
        userDao.save(user);
    }

    public void resetVerification(String newPassword, String resetUid) {
        User user = userDao.findByResetUid(resetUid);
        if (user != null) {
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            user.setResetUid(null);
            userDao.save(user);
        }
    }
}
