package com.distributiongraduates.controller;

import com.distributiongraduates.controller.main.Attributes;
import com.distributiongraduates.model.Specialties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/specialties")
public class SpecialtiesCont extends Attributes {
    @GetMapping
    public String specialties(Model model) {
        AddAttributes(model);
        model.addAttribute("specialties", specialtiesRepo.findAll());
        return "specialties";
    }

    @PostMapping("/add")
    public String specialtiesAdd(@RequestParam String name, @RequestParam int count) {
        specialtiesRepo.save(new Specialties(name, count));
        return "redirect:/specialties";
    }

    @PostMapping("/{id}/edit")
    public String specialtiesEdit(@RequestParam String name, @RequestParam int count, @PathVariable Long id) {
        Specialties specialties = specialtiesRepo.getReferenceById(id);
        specialties.set(name, count);
        specialtiesRepo.save(specialties);
        return "redirect:/specialties";
    }

    @GetMapping("/{id}/delete")
    public String specialtiesDelete(@PathVariable Long id) {
        specialtiesRepo.deleteById(id);
        return "redirect:/specialties";
    }
}