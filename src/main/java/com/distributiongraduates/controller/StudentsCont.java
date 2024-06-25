package com.distributiongraduates.controller;

import com.distributiongraduates.controller.main.Attributes;
import com.distributiongraduates.model.Students;
import com.distributiongraduates.model.Users;
import com.distributiongraduates.model.enums.Citizenship;
import com.distributiongraduates.model.enums.Marital;
import com.distributiongraduates.model.enums.Role;
import com.distributiongraduates.model.enums.YesNo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/students")
public class StudentsCont extends Attributes {

    @GetMapping("/{id}")
    public String Student(Model model, @PathVariable Long id) {
        AddAttributes(model);
        model.addAttribute("student", usersRepo.getReferenceById(id));
        return "student";
    }

    @GetMapping("/edit/{id}")
    public String StudentEdit(Model model, @PathVariable Long id) {
        AddAttributesStudentEdit(model, id);
        return "student_edit";
    }

    @GetMapping("/add")
    public String StudentAdd(Model model) {
        AddAttributes(model);
        return "student_add";
    }

    @PostMapping("/add")
    public String StudentAdd(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String passport, @RequestParam String surname, @RequestParam String name, @RequestParam String patronymic) {
        if (usersRepo.findByUsername(username) != null) {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            AddAttributes(model);
            return "student_add";
        }
        usersRepo.save(new Users(username, password, Role.USER, surname, name, patronymic, passport));
        return "redirect:/";
    }

    @PostMapping("/edit/{id}")
    public String StudentEditOld(Model model, @PathVariable Long id, @RequestParam MultipartFile photo, @RequestParam String surname, @RequestParam String name, @RequestParam String patronymic, @RequestParam String date, @RequestParam String issued, @RequestParam String issued_date, @RequestParam String identity, @RequestParam String address, @RequestParam String tel_mob, @RequestParam String tel_home, @RequestParam String email, @RequestParam String job, @RequestParam String post, @RequestParam Marital marital, @RequestParam Citizenship citizenship, @RequestParam YesNo conscripted) {
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
            AddAttributesStudentEdit(model, id);
            model.addAttribute("message", "Некорректные данные!");
            return "student_edit";
        }

        user.setSurname(surname);
        user.setName(name);
        user.setPatronymic(patronymic);

        Students student = user.getStudent();
        student.set(date, issued, issued_date, identity, address, tel_mob, tel_home, email, job, post, marital, citizenship, conscripted);

        usersRepo.save(user);

        return "redirect:/students/{id}";
    }
}
