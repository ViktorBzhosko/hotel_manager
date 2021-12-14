package by.mycom.ita.services.impl;

import by.mycom.ita.dao.CommonUserDao;
import by.mycom.ita.exception.DataNotFoundException;
import by.mycom.ita.model.CommonUser;
import by.mycom.ita.services.ICommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonUserServiceImpl implements ICommonUserService {

    private final CommonUserDao userDao;

    @Autowired
    public CommonUserServiceImpl(CommonUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public CommonUser create(CommonUser user) {
        return userDao.save(CommonUser.builder()
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .passport(user.getPassport())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build());
    }

    @Override
    public CommonUser findById(Long id) {
        return userDao.findById((long) id).orElseThrow(DataNotFoundException::new);
    }

}
