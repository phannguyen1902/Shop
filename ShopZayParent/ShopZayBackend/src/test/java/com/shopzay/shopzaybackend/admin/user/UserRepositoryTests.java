package com.shopzay.shopzaybackend.admin.user;

import com.shopzay.common.entity.Role;
import com.shopzay.common.entity.User;
import com.shopzay.shopzaybackend.admin.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;
    @Autowired
    private TestEntityManager entityManager;
    @Test
    public void testCreateUser (){
        Role roleAdmin = entityManager.find(Role.class,1);
        User userNguyen = new User("phannguyen190268@gmail.com","trongphan1@","Nguyen","Phan Van");
        userNguyen.addRole(roleAdmin );
        User saveUser = repo.save(userNguyen);
        assertThat(saveUser.getId()).isGreaterThan(0);

    }
    //test user with two roles
    @Test
    public void testCreateAllUser(){
        User user1 = new User("phantoai@gmail.com","phan1@","Nguyen","Van Kien");
        Role roleEditor = new Role(7);
        Role roleAssistant = new Role(9);
        user1.addRole(roleEditor);
        user1.addRole(roleAssistant);
        User saveUser = repo.save(user1);
        assertThat(saveUser.getId()).isGreaterThan(0);

    }
    @Test
    public void testListAllUsers(){
        Iterable<User> listUsers= repo.findAll();
        listUsers.forEach(user -> System.out.println(user));
    }
    @Test
    public void testGetById(){
        User user1 = repo.findById(1).get();
        System.out.println(user1);
        assertThat(user1.getId()).isGreaterThan(0);
    }
    @Test
    public void testUpdateUerDetails(){
        User user1 = repo.findById(1).get();
        user1.setEnabled(true);
        user1.setEmail("nguyen2002@gmail.com");
        repo.save(user1);

    }
    @Test
    public void testDeleteUser(){
       Integer userId = 2;
       repo.deleteById(userId);

    }


}
