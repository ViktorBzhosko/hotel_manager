package by.mycom.ita.services.impl;

import by.mycom.ita.configuration.UserDetail;
import by.mycom.ita.model.User;
import by.mycom.ita.services.IAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUserImpl implements IAuthentication {

    @Override
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((UserDetail) authentication.getPrincipal()).getUser();
        return user.getId();
    }
}
