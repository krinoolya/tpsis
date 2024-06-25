package com.distributiongraduates.controller.main;

import com.distributiongraduates.model.enums.Citizenship;
import com.distributiongraduates.model.enums.Marital;
import com.distributiongraduates.model.enums.Role;
import com.distributiongraduates.model.enums.YesNo;
import org.springframework.ui.Model;

public class Attributes extends Main {
    protected void AddAttributes(Model model) {
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUser());
    }

    protected void AddAttributesEnums(Model model) {
        model.addAttribute("yesnos", YesNo.values());
        model.addAttribute("citizenships", Citizenship.values());
        model.addAttribute("maritals", Marital.values());
    }

    protected void AddAttributesUsers(Model model) {
        AddAttributes(model);
        model.addAttribute("users", usersRepo.findAllByOrderByRole());
        model.addAttribute("roles", Role.values());
    }

    protected void AddAttributesProfile(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("user", usersRepo.getReferenceById(getUser().getId()));
    }

    protected void AddAttributesStudentEdit(Model model, Long id) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("student", usersRepo.getReferenceById(id));
    }

    protected void AddAttributesEmployeeEdit(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("student", usersRepo.getReferenceById(id));
    }
}
