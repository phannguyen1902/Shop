package com.shopzay.shopzaybackend.admin.controller;

import com.shopzay.common.entity.Brand;
import com.shopzay.shopzaybackend.admin.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BrandController {
    public final BrandService service;

    public BrandController(BrandService service) {
        this.service = service;
    }
    @GetMapping("/brand")
    public String listBrand(Model model){
        List<Brand> listBrands = service.listAll();
        model.addAttribute("listBrands",listBrands);
        return ("Brand/brand");
    }
}
