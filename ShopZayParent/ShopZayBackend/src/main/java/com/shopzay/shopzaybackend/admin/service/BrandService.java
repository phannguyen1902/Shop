package com.shopzay.shopzaybackend.admin.service;

import com.shopzay.common.entity.Brand;
import com.shopzay.shopzaybackend.admin.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    private final BrandRepository repo;

    public BrandService(BrandRepository repo) {
        this.repo = repo;
    }
    public List<Brand> listAll(){
        return (List<Brand>) repo.findAll();
    }
}
