package by.mycom.ita.services;

import by.mycom.ita.dto.CommonUserDto;
import org.springframework.ui.Model;

public interface IUserService {

    void createUser(CommonUserDto commonUserDto, Model model);

}
