package ru.daemon75.euc_rent.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.daemon75.euc_rent.models.Euc;
import ru.daemon75.euc_rent.services.EucsService;

@Component
public class EucValidator implements Validator {
    private final EucsService eucsService;

    @Autowired
    public EucValidator(EucsService eucsService) {
        this.eucsService = eucsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Euc.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Euc euc = (Euc) target;
        Euc existEuc = eucsService.getBySerialN(euc.getSerialN());
        if ((existEuc != null) && (existEuc.getId() != euc.getId())) {
            errors.rejectValue("serialN", "", "EUC with this serial already exist");
        }
    }
}
