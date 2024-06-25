package com.distributiongraduates.controller;

import com.distributiongraduates.controller.main.Attributes;
import com.distributiongraduates.model.Employees;
import com.distributiongraduates.model.Users;
import com.distributiongraduates.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/employees")
public class EmployeesCont extends Attributes {

    @GetMapping
    public String employees(Model model) {
        AddAttributes(model);
        model.addAttribute("employees", usersRepo.findAllByRole(Role.EMPLOYEE));
        return "employees";
    }

    @GetMapping("/edit/{id}")
    public String employeeEdit(Model model, @PathVariable Long id) {
        AddAttributesEmployeeEdit(model, id);
        return "employee_edit";
    }

    @PostMapping("/edit/{id}")
    public String employeeEdit(Model model, @PathVariable Long id, @RequestParam MultipartFile photo, @RequestParam String faculty, @RequestParam String tel) {
        Users user = usersRepo.getReferenceById(id);
        try {
            if (photo != null && !Objects.requireNonNull(photo.getOriginalFilename()).isEmpty()) {
                String uuidFile = UUID.randomUUID().toString();
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) uploadDir.mkdir();
                String result = uuidFile + "_" + photo.getOriginalFilename();
                photo.transferTo(new File(uploadImg + "/" + result));
                user.setPhoto(result);
            }
        } catch (IOException e) {
            AddAttributesEmployeeEdit(model, id);
            model.addAttribute("message", "Некорректные данные!");
            return "employee_edit";
        }

        Employees employee = user.getEmployee();
        employee.set(faculty, tel);

        usersRepo.save(user);

        return "redirect:/employees";
    }
}
