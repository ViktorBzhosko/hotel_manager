package by.mycom.ita.services.impl;

import by.mycom.ita.dao.CommonUserDao;
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
    void whenCreate_thenReturnUser() {
        CommonUser simpleUser = createSimpleUser(0L);
        CommonUser simpleOtherUser = createSimpleUser(1L);

        Mockito.when(userDao.save(Mockito.any())).thenReturn(simpleOtherUser);
        CommonUser actualUser = commonUserService.create(simpleUser);

        Assertions.assertEquals(actualUser, simpleOtherUser);
    }

    @Test
    void findById() {
        CommonUser user = createSimpleUser(1L);
        Mockito.when(userDao.findById(10L)).thenReturn(Optional.ofNullable(user));
        CommonUser expected = commonUserService.findById(10L);
        Assertions.assertEquals(expected, user);
        Mockito.verify(userDao, Mockito.times(1)).findById(10L);
    }

    private CommonUser createSimpleUser(Long id) {
        return CommonUser.builder()
                .id(id)
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