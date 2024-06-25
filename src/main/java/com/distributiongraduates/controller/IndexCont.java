package com.distributiongraduates.controller;

import com.distributiongraduates.controller.main.Attributes;
import com.distributiongraduates.model.enums.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexCont extends Attributes {

    @GetMapping
    public String index1() {
        return "redirect:/about";
    }

    @GetMapping("/index")
    public String index2() {
        return "redirect:/about";
    }

    @GetMapping("/catalog")
    public String index2(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("students", usersRepo.findAllByRole(Role.STUDENT));
        return "catalog";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam String group,  @RequestParam Marital marital, @RequestParam Citizenship citizenship, @RequestParam YesNo conscripted) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("students", usersRepo.findAllByRoleAndStudent_JobContainingAndStudent_MaritalAndStudent_CitizenshipAndStudent_Conscripted(Role.STUDENT, group, marital, citizenship, conscripted));
        model.addAttribute("marSelect", marital);
        model.addAttribute("gpSelect", group);
        model.addAttribute("citSelect", citizenship);
        model.addAttribute("conSelect", conscripted);
        return "catalog";
    }
}
