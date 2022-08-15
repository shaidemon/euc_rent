package ru.daemon75.euc_rent.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.daemon75.euc_rent.dao.UserDao;
import ru.daemon75.euc_rent.models.User;

@Component
public class UserValidator implements Validator {
    private final UserDao userDao;

    @Autowired
    public UserValidator(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        User existUser = userDao.getByLoginEmail(user.getLoginEmail());
        if ((existUser != null) && (user.getId() != existUser.getId())) {
            errors.rejectValue("loginEmail", "", "This login(email) already in use");
        }
    }
}
