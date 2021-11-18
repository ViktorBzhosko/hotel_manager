package by.mycom.ita.dao;

import by.mycom.ita.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleDao extends JpaRepository<Role,Long> {
}
