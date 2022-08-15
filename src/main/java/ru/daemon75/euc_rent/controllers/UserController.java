package ru.daemon75.euc_rent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.daemon75.euc_rent.dao.EucDao;
import ru.daemon75.euc_rent.dao.UserDao;
import ru.daemon75.euc_rent.models.Euc;
import ru.daemon75.euc_rent.models.User;
import ru.daemon75.euc_rent.util.UserValidator;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserDao userDao;
    private final UserValidator userValidator;
    private final EucDao eucDao;

    @Autowired
    public UserController(UserDao userDao, UserValidator userValidator, EucDao eucDao) {
        this.userDao = userDao;
        this.userValidator = userValidator;
        this.eucDao = eucDao;
    }

    @GetMapping()
    public String all(Model model) {
        model.addAttribute("users", userDao.getAll());
        return "users/all";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDao.getById(id));
        List<Euc> user_eucs = eucDao.getByUserId(id);
        if (!user_eucs.isEmpty()) model.addAttribute("eucs", user_eucs);
            else model.addAttribute("eucs", new ArrayList<Euc>());
        return "users/show";
    }

    @GetMapping("/new")
    public String add(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) return "users/new";
        userDao.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDao.getById(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) return "users/edit";
        userDao.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userDao.delete(id);
        return "redirect:/users";
    }
}
