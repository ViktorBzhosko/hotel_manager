package by.mycom.ita.dao;

import by.mycom.ita.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao extends JpaRepository<User, Long> {

    User findByLogin (String login);

}
