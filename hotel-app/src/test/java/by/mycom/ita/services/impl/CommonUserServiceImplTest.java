package by.mycom.ita.services.impl;

import by.mycom.ita.dao.CommonUserDao;
import by.mycom.ita.exception.DataIsIncorrectException;
import by.mycom.ita.model.CommonUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CommonUserServiceImplTest {

    @Mock
    CommonUserDao userDao;

    @InjectMocks
    private CommonUserServiceImpl commonUserService;

    @Test
    void whenCreate_returnClient() {
        CommonUser simpleUser = createSimpleUser();
        simpleUser.setUserRole(UserRole.CLIENT);
        Mockito.when(userDao.save(Mockito.any())).thenReturn(simpleUser);
        CommonUser client = commonUserService.createClient(simpleUser);
        Assertions.assertEquals(simpleUser.getUserRole(), client.getUserRole());
        Mockito.verify(userDao, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void whenCreateClient_ReturnException() {
        CommonUser user = createSimpleUserWithException();
        Assertions.assertThrows(DataIsIncorrectException.class,
                () -> commonUserService.createClient(user));
    }

    @Test
    void whenCreate_returnManager() {
        CommonUser simpleUser = createSimpleUser();
        simpleUser.setUserRole(UserRole.MANAGER);
        Mockito.when(userDao.save(Mockito.any())).thenReturn(simpleUser);
        CommonUser client = commonUserService.createManager(simpleUser);
        Assertions.assertEquals(simpleUser.getUserRole(), client.getUserRole());
        Mockito.verify(userDao, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void whenCreateManager_ReturnException() {
        CommonUser user = createSimpleUserWithException();
        Assertions.assertThrows(DataIsIncorrectException.class,
                () -> commonUserService.createManager(user));
    }

    @Test
    void whenCreate_returnAdmin() {
        CommonUser simpleUser = createSimpleUser();
        simpleUser.setUserRole(UserRole.ADMIN);
        Mockito.when(userDao.save(Mockito.any())).thenReturn(simpleUser);
        CommonUser client = commonUserService.createManager(simpleUser);
        Assertions.assertEquals(simpleUser.getUserRole(), client.getUserRole());
        Mockito.verify(userDao, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void whenCreateAdmin_ReturnException() {
        CommonUser user = createSimpleUserWithException();
        Assertions.assertThrows(DataIsIncorrectException.class,
                () -> commonUserService.createAdmin(user));
    }

    @Test
    void findById() {
        CommonUser user = createSimpleUser();
        Mockito.when(userDao.findById(10L)).thenReturn(Optional.ofNullable(user));
        CommonUser expected = commonUserService.findById(10L);
        Assertions.assertEquals(expected, user);
        Mockito.verify(userDao, Mockito.times(1)).findById(10L);
    }

    private CommonUser createSimpleUser() {
        return CommonUser.builder()
                .firstName("Viktor")
                .secondName("Bzhosko")
                .email("vic308@mail.ru")
                .passport("ab123")
                .phoneNumber(256)
                .build();
    }

    private CommonUser createSimpleUserWithException() {
        return CommonUser.builder()
                .firstName(null)
                .secondName("Bzhosko")
                .email("vic308@mail.ru")
                .passport("ab123")
                .phoneNumber(256)
                .build();
    }
}