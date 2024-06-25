package com.distributiongraduates.controller;

import com.distributiongraduates.controller.main.Attributes;
import com.distributiongraduates.model.Score;
import com.distributiongraduates.model.enums.Citizenship;
import com.distributiongraduates.model.enums.Marital;
import com.distributiongraduates.model.enums.Role;
import com.distributiongraduates.model.enums.YesNo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/statistics")
public class StatsCont extends Attributes {
    @GetMapping
    public String Stats(Model model) {
        AddAttributes(model);

        String[] topQuantityName = new String[]{Role.USER.getName(), Role.STUDENT.getName()};
        int[] topQuantityNumber = new int[]{usersRepo.findAllByRole(Role.USER).size(), usersRepo.findAllByRole(Role.STUDENT).size()};

        model.addAttribute("topQuantityName", topQuantityName);
        model.addAttribute("topQuantityNumber", topQuantityNumber);

        return "stats";
    }
}
