package ru.daemon75.euc_rent.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.daemon75.euc_rent.models.User;
import ru.daemon75.euc_rent.services.UsersService;

@Component
public class UserValidator implements Validator {
    private final UsersService usersService;

    @Autowired
    public UserValidator(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        User existUser = usersService.getByLoginEmail(user.getLoginEmail());
        if ((existUser != null) && (user.getId() != existUser.getId())) {
            errors.rejectValue("loginEmail", "", "This login(email) already in use");
        }
    }
}
