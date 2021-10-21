package by.mycom.ita.dao;

import by.mycom.ita.model.CommonUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonUserDao extends JpaRepository<CommonUser,Long> {
  }
