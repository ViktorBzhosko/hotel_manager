package by.mycom.ita.services.impl;

import by.mycom.ita.configuration.UserDetail;
import by.mycom.ita.dao.IUserDao;
import by.mycom.ita.model.Role;
import by.mycom.ita.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDao.findByLogin(login);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserDetail(user);
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userDao.findById(userId);
        return userFromDb.orElse(new User());
    }


    public boolean saveUser(User user) {
        User userFromDB = userDao.findByLogin(user.getLogin());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "Client")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setLogin(user.getLogin());
        userDao.save(user);
        return true;
    }


}
