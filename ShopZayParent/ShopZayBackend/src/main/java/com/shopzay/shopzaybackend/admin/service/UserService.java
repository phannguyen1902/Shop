package com.shopzay.shopzaybackend.admin.service;

import com.shopzay.common.entity.Role;
import com.shopzay.common.entity.User;
import com.shopzay.shopzaybackend.admin.repository.RoleRepository;
import com.shopzay.shopzaybackend.admin.repository.UserRepository;
import com.shopzay.shopzaybackend.admin.service.impl.IUserService;
import com.shopzay.shopzaybackend.admin.user.UserNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService implements IUserService {
    private final UserRepository repo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository repo, RoleRepository roleRepo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }


    public List<User> listAll() {
        return (List<User>) repo.findAll();
    }

    public List<Role> listRole() {
        return (List<Role>) roleRepo.findAll();
    }

    public User save(User user) {
        encoderPassword(user);
        repo.save(user);
        return repo.save(user);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    private void encoderPassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public boolean isEmailUnique(String email) {
        User userByEmail = repo.findByEmail(email);
        return userByEmail == null;
    }

    public User get(Integer id) throws UserNotFoundException {
        try {
            return repo.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new UserNotFoundException("Could not find any user with id :" + id);
        }
    }
}
