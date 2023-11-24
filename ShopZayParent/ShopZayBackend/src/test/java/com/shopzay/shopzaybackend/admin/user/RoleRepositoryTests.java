package com.shopzay.shopzaybackend.admin.user;

import com.shopzay.common.entity.Role;
import com.shopzay.shopzaybackend.admin.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
// không muốn thay thế cấu hình cơ sở dữ liệu
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// Add vao database
@Rollback(false)
public class RoleRepositoryTests {
    @Autowired
    private RoleRepository repo;
    @Test
    public void testCreateFirstRole(){
        Role roleadmin = new Role("admin","manage every thing");
        Role saveRole = repo.save(roleadmin);
        // Sau khi lưu trữ đối tượng Role, bạn sử dụng assertThat để kiểm tra xem ID của đối tượng đã được gán (lớn hơn 0) hay không.
        //  việc lưu trữ đã thành công và một ID hợp lệ đã được tạo.
        assertThat(saveRole.getId()).isGreaterThan(0);
    }
    @Test
    public void testCreateRestRole(){
        Role roleSalesPerson = new Role("SaleSperson","manage product price"
        +"customer,shipping,oders and sales report");
        Role roleEditor = new Role("Editor","manage categories  "+
                "brands,products,articles and menus");
        Role roleShipper = new Role("Shipper","views products, view oders "
        +"and update oders status");
<<<<<<< HEAD
        Role roleAssistant = new Role("Assistant","manage question and review");
=======
        Role roleAssistant = new Role("assistant","manage question and review");
>>>>>>> 2836b176665449ecda4244a3454c062fabae3ec8
        repo.saveAll(List.of(roleSalesPerson,roleEditor,roleShipper,roleAssistant));

    }
    @Test
    public void testDeleterRole(){
         Integer roleId = 5;
        repo.deleteById(roleId);
    }
}
