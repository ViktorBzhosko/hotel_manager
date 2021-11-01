package by.mycom.ita.services.impl;

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

    private final String Url = "http://localhost:8003/hotel-app";

    @PostConstruct
    private void createRoles(){
        Role roleAdmin = new Role(1L, "ROLE_ADMIN", null);
        Role roleManager = new Role(2L, "ROLE_MANAGER", null);
        Role roleClient = new Role(3L, "ROLE_CLIENT", null);
        roleDao.saveAll(List.of(roleAdmin,roleManager,roleClient));
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDao.findByUsername(login);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserDetail(user);
    }

    @Transactional
    public boolean saveUser(CommonUserDto commonUserDto) {

        User userFromDB = userDao.findByUsername(commonUserDto.getUsername());

        if (userFromDB != null) {
            return false;
        }

        CommonUserDto responseUser = restTemplate.postForObject(Url + "/users/create", commonUserDto, CommonUserDto.class);
        if (responseUser != null) {
            User user = objectMapper.convertValue(commonUserDto, User.class);
            user.setId(responseUser.getId());
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_ADMIN",null)));
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setUsername(user.getUsername());
            userDao.save(user);
            return true;
        }
        return false;
    }

    @Transactional
    public void resetPassword(String login) {
        User user = userDao.findByUsername(login);
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
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            user.setResetUid(null);
            userDao.save(user);
        }
    }
}
