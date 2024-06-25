package com.distributiongraduates.controller;

import com.distributiongraduates.controller.main.Attributes;
import com.distributiongraduates.model.Specialties;
import com.distributiongraduates.model.Students;
import com.distributiongraduates.model.Users;
import com.distributiongraduates.model.enums.Role;
import com.distributiongraduates.model.enums.StudentStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/enrollments")
public class EnrollmentsCont extends Attributes {

    @GetMapping
    public String enrollments(Model model) {
        AddAttributes(model);
        model.addAttribute("enrollments", usersRepo.findAllByRoleAndSpecializationIsNotNull(Role.USER));
        return "enrollments";
    }

    @GetMapping("/enrollment/all")
    public String enrollmentsAll(Model model) {
        List<Users> users = usersRepo.findAllByRoleAndSpecializationIsNotNull(Role.USER);

        if (users.isEmpty()) {
            return "redirect:/enrollments";
        }

        int ok = 0;

        List<Specialties> specialties = specialtiesRepo.findAll();

        for (Specialties i : specialties) {
            if (i.getEnrolled() < i.getSum()) {
                List<Users> enroll = new ArrayList<>(i.getUsers().stream().filter(user -> user.getRole() == Role.USER).toList());
                if (enroll.isEmpty()) {
                    continue;
                }

                enroll.sort(Comparator.comparing(Users::getPoint));
                Collections.reverse(enroll);

                int count = i.getCount() - i.getEnrolledBudget();
                for (Users value : enroll) {
                    if (count > 0) {
                        value.setRole(Role.STUDENT);
                        value.setStudent(new Students());
                        value.getStudent().setStatus(StudentStatus.BUDGET);
                        usersRepo.save(value);
                        ok++;
                        count--;
                    }
                }
            }
        }


        model.addAttribute("message", "Распределено: " + ok);
        AddAttributes(model);
        model.addAttribute("enrollments", usersRepo.findAllByRoleAndSpecializationIsNotNull(Role.USER));
        return "enrollments";
    }


    @GetMapping("/enrollment/{id}")
    public String enrollments(Model model, @PathVariable Long id) {
        Users user = usersRepo.getReferenceById(id);

        Specialties specialization = user.getSpecialization();
        if (specialization.getEnrolled() < specialization.getSum()) {
            user.setRole(Role.STUDENT);
            user.setStudent(new Students());
            if (specialization.getEnrolledBudget() < specialization.getCount()) {
                user.getStudent().setStatus(StudentStatus.BUDGET);
            } else {
                user.getStudent().setStatus(StudentStatus.PAID);
            }
        } else {
            model.addAttribute("message", "Недостаточно мест!");
            AddAttributes(model);
            model.addAttribute("enrollments", usersRepo.findAllByRoleAndSpecializationIsNotNull(Role.USER));
            return "enrollments";
        }

        usersRepo.save(user);

        model.addAttribute("message", "Распределен!");
        AddAttributes(model);
        model.addAttribute("enrollments", usersRepo.findAllByRoleAndSpecializationIsNotNull(Role.USER));
        return "enrollments";
    }

    @GetMapping("/enrollment/{id}/reject")
    public String enrollmentReject(@PathVariable Long id) {
        Users user = usersRepo.getReferenceById(id);
        user.setSpecialization(null);
        usersRepo.save(user);
        return "redirect:/enrollments";
    }

    @GetMapping("/add")
    public String enrollmentAdd(Model model) {
        AddAttributes(model);
        model.addAttribute("specialties", specialtiesRepo.findAll());
        return "enrollment";
    }

    @PostMapping("/add")
    public String enrollmentAdd(Model model, @RequestParam int point, @RequestParam Long specializationId, @RequestParam MultipartFile photo) {
        Users user = getUser();
        user.setPoint(point);
        if (specializationId != 0) {
            user.setSpecialization(specialtiesRepo.getReferenceById(specializationId));
        }
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
            model.addAttribute("message", "Некорректные данные!");
            AddAttributes(model);
            model.addAttribute("specialties", specialtiesRepo.findAll());
            return "enrollment";
        }
        usersRepo.save(user);
        return "redirect:/enrollments/add";
    }
}
