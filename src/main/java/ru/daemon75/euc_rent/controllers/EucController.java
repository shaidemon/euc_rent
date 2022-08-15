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
import ru.daemon75.euc_rent.util.EucValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/eucs")
public class EucController {
    private final EucDao eucDao;
    private final EucValidator eucValidator;
    private final UserDao userDao;

    @Autowired
    public EucController(EucDao eucDao, EucValidator eucValidator, UserDao userDao) {
        this.eucDao = eucDao;
        this.eucValidator = eucValidator;
        this.userDao = userDao;
    }

    @GetMapping()
    public String all (Model model) {
        model.addAttribute("users", userDao.getAll());
        model.addAttribute("eucs", eucDao.getAll());
        return "eucs/all";
    }

    @GetMapping("/{id}")
    public String show (@PathVariable("id") int id, Model model) {
        model.addAttribute("euc", eucDao.getById(id));
        Integer userId = eucDao.getById(id).getUserId();
        if (userId != null) {
            model.addAttribute("username", userDao.getById(userId).getFullname());
        } else {
            model.addAttribute("username", "null");
        }
        model.addAttribute("rentUser", new User());
        model.addAttribute("users", userDao.getAll());
        return "eucs/show";
    }

    @GetMapping("/new")
    public String add (@ModelAttribute("euc") Euc euc) {
        return "eucs/new";
    }

    @GetMapping("/{id}/edit")
    public String edit (@PathVariable("id") int id, Model model) {
        model.addAttribute("euc", eucDao.getById(id));
        return "eucs/edit";
    }

    @PatchMapping("/{id}/rent")
    public String rentEuc (@PathVariable("id") int id, @ModelAttribute("rentUser") User rentUser) {
        eucDao.toRent(id, rentUser.getId());
        return "redirect:/eucs";
    }

    @PatchMapping("/{id}/free")
    public String freeEuc (@PathVariable("id") int id) {
        eucDao.free(id);
        return "redirect:/eucs";
    }

    @PostMapping()
    public String create (@ModelAttribute("euc") @Valid Euc euc, BindingResult bindingResult) {
        eucValidator.validate(euc, bindingResult);
        if (bindingResult.hasErrors()) return "eucs/new";
        eucDao.save(euc);
        return "redirect:/eucs";
    }

    @PatchMapping("/{id}")
    public String update (@PathVariable("id") int id, @ModelAttribute("euc") @Valid Euc euc, BindingResult bindingResult) {
        eucValidator.validate(euc, bindingResult);
        if (bindingResult.hasErrors()) return "eucs/edit";
        eucDao.update(id, euc);
        return "redirect:/eucs";
    }

    @DeleteMapping("/{id}")
    public String delete (@PathVariable("id") int id) {
        eucDao.delete(id);
        return "redirect:/eucs";
    }
}
