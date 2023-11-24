package com.shopzay.shopzaybackend.category;

import com.shopzay.common.entity.Category;
<<<<<<< HEAD
import com.shopzay.shopzaybackend.admin.repository.CategoryRepository;
=======
import com.shopzay.shopzaybackend.admin.category.CategoryRepository;
>>>>>>> 2836b176665449ecda4244a3454c062fabae3ec8
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> 2836b176665449ecda4244a3454c062fabae3ec8
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository repo;
<<<<<<< HEAD

=======
>>>>>>> 2836b176665449ecda4244a3454c062fabae3ec8
    @Test
    public void testCreateCategory(){
        Category category=new Category("Computer");
        Category saveCategory = repo.save(category);
        assertThat(saveCategory.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateMultiSubCategory() {

        Category parent = new Category(2);
        Category subCategory = new Category("My Computer", parent);
        Category savedCategory = repo.save(subCategory);
        assertThat(savedCategory.getId()).isGreaterThan(0);

    }
    @Test
    public void testGetCategory(){
        Category category = repo.findById(1).get();
        System.out.println(category.getName());
        Set<Category> children = category.getChildren();
        for(Category subCategory : children){
            System.out.println(subCategory.getName());
        }
        assertThat(children.size()).isGreaterThan(0);
    }

}
