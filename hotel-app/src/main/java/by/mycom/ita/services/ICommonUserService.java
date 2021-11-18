package by.mycom.ita.services;

import by.mycom.ita.model.CommonUser;

public interface ICommonUserService {

    CommonUser create(CommonUser user);

    CommonUser findById(Long id);

}
