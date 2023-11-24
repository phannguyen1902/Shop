package com.shopzay.shopzaybackend.admin.controller;

import com.shopzay.common.entity.Category;
import com.shopzay.shopzaybackend.admin.service.CategoryService;
import com.shopzay.shopzaybackend.admin.user.UserNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/categories")
    public String listCategories(Model model) {
        List<Category> listCategories = service.listAll();
        model.addAttribute("listCategories", listCategories);
        return "Category/category";
    }

    @GetMapping("/categories_create")
    public String newCategory(Model model) {
        List<Category> listCategories = service.listCategoriesUsedInform();
        model.addAttribute("category", new Category());
        model.addAttribute("listCategories",listCategories);
        model.addAttribute("pageTitle", "Create New Category");
        return "Category/category_form";
    }
    @PostMapping("/category/save")
    public String saveCategory(Category category,
                               RedirectAttributes redirectAttributes,
                               @RequestParam("fileImage")MultipartFile multipartFile){

       service.save(category);
       redirectAttributes.addFlashAttribute("message", "The category has been saved success fully.");
       return "redirect:/categories";
    }
    @GetMapping("/category_edit_{id}")
    public String edit(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Category category = service.get(id);
            model.addAttribute("category", category);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            return "Category/category_form";
        } catch (UserNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex);
            return "redirect:/categories";
        }
    }
    @GetMapping("/category_delete/{id}")
    public String delete(@PathVariable(name = "id") Integer id) {
        service.delete(id);
        return "redirect:/categories";
    }
}

