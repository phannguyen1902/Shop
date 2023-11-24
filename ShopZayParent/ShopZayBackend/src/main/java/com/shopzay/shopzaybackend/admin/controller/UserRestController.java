package com.shopzay.shopzaybackend.admin.controller;

import com.shopzay.common.entity.User;
import com.shopzay.shopzaybackend.admin.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRestController {
    private final UserService service;
    public UserRestController(UserService service) {
        this.service = service;
    }

    @PostMapping("/check_email")
    public String checkDuplicateEmail(User user, Model model) {
        if (!service.isEmailUnique(user.getEmail())) {
            model.addAttribute("errorMessage", "Email đã tồn tại");
            return "user_form";
        }
        service.save(user);
        return "redirect:/users";
    }

}
