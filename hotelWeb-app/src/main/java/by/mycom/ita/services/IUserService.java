package by.mycom.ita.services;

import by.mycom.ita.dto.CommonUserDto;
import org.springframework.ui.Model;

public interface IUserService {

    void createClient(CommonUserDto commonUserDto, Model model);

    void createAdmin(CommonUserDto commonUserDto, Model model);

    void createManager(CommonUserDto commonUserDto, Model model);


}
