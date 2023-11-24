package com.shopzay.shopzaybackend.admin.service;

import com.shopzay.common.entity.Category;
import com.shopzay.shopzaybackend.admin.repository.CategoryRepository;
import com.shopzay.shopzaybackend.admin.user.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class CategoryService {
    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public List<Category> listAll() {
        return (List<Category>) repo.findAll();
    }


    public List<Category> listCategoriesUsedInform() {
        List<Category> categoriesUsedInForm = new ArrayList<>();
        Iterable<Category> categoriesInDB = repo.findAll();

        for (Category category : categoriesInDB) {
            if (category.getParent() == null) {
                buildCategoryTree(categoriesUsedInForm, category, 0);
            }
        }

        return categoriesUsedInForm;
    }

    public void buildCategoryTree(List<Category> categoriesUsedInForm, Category category, int level) {
        categoriesUsedInForm.add(category);

        Set<Category> children = category.getChildren();

        if (children != null && !children.isEmpty()) {
            for (Category child : children) {
                child.setName(getIndentedName(child.getName(), level + 1));
                buildCategoryTree(categoriesUsedInForm, child, level + 1);
            }
        }
    }

    public String getIndentedName(String name, int level) {
        StringBuilder indentedName = new StringBuilder();

        for (int i = 0; i < level; i++) {
            indentedName.append("--");
        }

        indentedName.append(name);

        return indentedName.toString();
    }

    /*
    public List<Category> listCategoriesUsedInform() {
        List<Category> categoriesUsedInForm = new ArrayList<>();
        Iterable<Category> categoriesInDB = repo.findAll();
        for (Category category : categoriesInDB) {
            if (category.getParent() == null) {
                categoriesUsedInForm.add(category);
                listChildren(categoriesUsedInForm, category, 1);
            }
        }
        for (int i = 0; i < categoriesUsedInForm.size(); i++) {
            Category category = categoriesUsedInForm.get(i);
            String name = category.getName();
            if (category.getParent() != null) {
                name = "--" + name;
            }
            categoriesUsedInForm.set(i, new Category(name));
        }

        return categoriesUsedInForm;
    }
    public void listChildren(List<Category> categoriesUsedInForm, Category parent, int subLevel) {
        int newSubLevel = subLevel + 1;
        Set<Category> children = parent.getChildren();
        for (Category subCategory : children) {
            String name = "";
            for (int i = 0; i < newSubLevel; i++) {
                name += "--";
            }
            name += subCategory.getName();
            if (newSubLevel == 1) {
                name = "--" + name;
            }
            categoriesUsedInForm.add(new Category(name));
            if (subCategory.getChildren().size() > 0) {
                listChildren(categoriesUsedInForm, subCategory, newSubLevel);
            }
        }
    }

     */

    public Category get(Integer id) throws UserNotFoundException {
        try {
            return repo.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new UserNotFoundException("Could not find any user with id :" + id);
        }
    }


    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public void save(Category category) {
        repo.save(category);
    }
}
