package by.mycom.ita.services;

import by.mycom.ita.exception.DataIsIncorrectException;
import by.mycom.ita.model.CommonUser;

public interface ICommonUserService {

    CommonUser createClient(CommonUser user) throws DataIsIncorrectException;

    CommonUser createManager(CommonUser user) throws DataIsIncorrectException;

    CommonUser createAdmin(CommonUser user) throws DataIsIncorrectException;

    CommonUser getCurrentUser();

    CommonUser findById (Long id);

}
