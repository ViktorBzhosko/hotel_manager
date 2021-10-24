package by.mycom.ita.services.impl;

import by.mycom.ita.dao.CommonUserDao;
import by.mycom.ita.exception.DataIsIncorrectException;
import by.mycom.ita.exception.DataNotFoundException;
import by.mycom.ita.model.CommonUser;
import by.mycom.ita.model.enums.UserRole;
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
    public CommonUser createClient(CommonUser user) throws DataIsIncorrectException {
        CommonUser createUser = create(user, UserRole.CLIENT);
        return userDao.save(createUser);
    }

    @Override
    public CommonUser createManager(CommonUser user) throws DataIsIncorrectException {
        CommonUser createUser = create(user, UserRole.MANAGER);
        return userDao.save(createUser);
    }

    private CommonUser create(CommonUser user, UserRole userRole) {
        if (user.getFirstName() == null || user.getSecondName() == null || user.getPassport() == null
                || user.getEmail() == null || user.getPhoneNumber() == null) {
            throw new DataIsIncorrectException();
        }
        return CommonUser.builder()
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .passport(user.getPassport())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .userRole(userRole)
                .build();
    }

    public CommonUser getCurrentUser() {
        return CommonUser.builder()
                .firstName("Viktor")
                .secondName("Bzhosko")
                .passport("1562")
                .email("vic308@mail.ru")
                .phoneNumber(1489)
                .build();
    }

    @Override
    public CommonUser findById(Long id) throws DataNotFoundException {
        return userDao.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }
}
