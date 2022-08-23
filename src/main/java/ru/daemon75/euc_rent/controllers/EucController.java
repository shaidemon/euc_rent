package ru.daemon75.euc_rent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.daemon75.euc_rent.models.Euc;
import ru.daemon75.euc_rent.models.User;
import ru.daemon75.euc_rent.services.EucsService;
import ru.daemon75.euc_rent.services.UsersService;
import ru.daemon75.euc_rent.util.EucValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/eucs")
public class EucController {
    private final EucsService eucsService;
    private final EucValidator eucValidator;
    private final UsersService usersService;

    @Autowired
    public EucController(EucValidator eucValidator, EucsService eucsService, UsersService usersService) {
        this.eucsService = eucsService;
        this.usersService = usersService;
        this.eucValidator = eucValidator;
    }

    @GetMapping()
    public String all(Model model) {
        model.addAttribute("users", usersService.getAll());
        model.addAttribute("eucs", eucsService.getAll());
        return "eucs/all";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("euc", eucsService.getById(id));
//        Integer userId = eucsService.getById(id).getUser().getId();
        User user = eucsService.getById(id).getUser();
        if (user != null) {
            model.addAttribute("username", user.getFullname());
        } else {
            model.addAttribute("username", "null");
        }
        model.addAttribute("rentUser", new User());
        model.addAttribute("users", usersService.getAll());
        return "eucs/show";
    }

    @GetMapping("/new")
    public String add(@ModelAttribute("euc") Euc euc) {
        return "eucs/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("euc", eucsService.getById(id));
        return "eucs/edit";
    }

    @PatchMapping("/{id}/rent")
    public String rentEuc(@PathVariable("id") int id, @ModelAttribute("rentUser") User rentUser) {
//        eucsService.toRent(id, rentUser.getId());
        eucsService.toRent(id, rentUser);
        return "redirect:/eucs";
    }

    @PatchMapping("/{id}/free")
    public String freeEuc(@PathVariable("id") int id) {
        eucsService.free(id);
        return "redirect:/eucs";
    }

    @PostMapping()
    public String create(@ModelAttribute("euc") @Valid Euc euc, BindingResult bindingResult) {
        eucValidator.validate(euc, bindingResult);
        if (bindingResult.hasErrors()) return "eucs/new";
        eucsService.save(euc);
        return "redirect:/eucs";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("euc") @Valid Euc euc, BindingResult bindingResult) {
        eucValidator.validate(euc, bindingResult);
        if (bindingResult.hasErrors()) return "eucs/edit";
        eucsService.update(id, euc);
        return "redirect:/eucs";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        eucsService.delete(id);
        return "redirect:/eucs";
    }
}
