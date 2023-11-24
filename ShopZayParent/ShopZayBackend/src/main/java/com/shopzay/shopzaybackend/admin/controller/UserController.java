package com.shopzay.shopzaybackend.admin.controller;

import com.shopzay.common.entity.Role;
import com.shopzay.common.entity.User;
import com.shopzay.shopzaybackend.admin.Util.FileUploadUtil;
import com.shopzay.shopzaybackend.admin.service.UserService;
import com.shopzay.shopzaybackend.admin.user.UserNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public String listAll(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "User/users";
    }

    @GetMapping("/user_create")
    public String newUser(Model model) {
        List<Role> listRole = service.listRole();
        User user = new User();
        user.setEnabled(true);
        model.addAttribute("user", user);
        model.addAttribute("listRole", listRole);
        model.addAttribute("pageTitle", "Create new user");
        return "User/user_form";
    }

    @PostMapping({"/users/save"})
    public String saveUser(User user, RedirectAttributes redirectAttributes, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (!service.isEmailUnique(user.getEmail())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Email đã tồn tại");
            return "redirect:/user_create"; // Trả về trang đăng ký với thông báo lỗi

        } else {
            if (multipartFile.isEmpty()) {
                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                user.setPhotos(fileName);
                User saveUser = service.save(user);
                String uploadDir = "user-photos/"+saveUser.getId();
                FileUploadUtil.saveFile(uploadDir,fileName,multipartFile);
            }
            redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String delete(@PathVariable(name = "id") Integer id) {
        service.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/edit_{id}")
    public String edit(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            User user = service.get(id);
            List<Role> listRole = service.listRole();
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            model.addAttribute("listRole", listRole);
            return "User/user_form";
        } catch (UserNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex);
            return "redirect:/users";
        }
    }

}
