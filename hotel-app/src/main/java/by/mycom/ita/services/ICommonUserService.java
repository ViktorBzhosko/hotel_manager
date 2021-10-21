package by.mycom.ita.services;

import by.mycom.ita.exception.DataIsIncorrectException;
import by.mycom.ita.exception.DataNotFoundException;
import by.mycom.ita.model.CommonUser;

public interface ICommonUserService {

    CommonUser createClient(CommonUser user) throws DataIsIncorrectException;

    CommonUser createManager(CommonUser user) throws DataIsIncorrectException;

    CommonUser findById(Long id) throws DataNotFoundException;

    CommonUser getCurrentUser();

    void deleteById(Long id);
}
