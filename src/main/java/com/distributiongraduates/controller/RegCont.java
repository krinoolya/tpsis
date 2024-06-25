package com.distributiongraduates.controller;

import com.distributiongraduates.controller.main.Attributes;
import com.distributiongraduates.model.Users;
import com.distributiongraduates.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reg")
public class RegCont extends Attributes {

    @GetMapping
    public String reg(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
        return "reg";
    }

    @PostMapping
    public String regUser(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String surname, @RequestParam String name, @RequestParam String patronymic, @RequestParam String passport) {
        if (usersRepo.findAll().isEmpty()) {
            usersRepo.save(new Users(username, password, Role.ADMIN, surname, name, patronymic, passport));
            return "redirect:/login";
        }

        if (usersRepo.findByUsername(username) != null) {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            AddAttributesEnums(model);
            AddAttributes(model);
            return "reg";
        }

        usersRepo.save(new Users(username, password, Role.USER, surname, name, patronymic, passport));

        return "redirect:/login";
    }
}
