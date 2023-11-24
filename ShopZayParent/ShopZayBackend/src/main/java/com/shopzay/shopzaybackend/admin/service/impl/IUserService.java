package com.shopzay.shopzaybackend.admin.service.impl;

import com.shopzay.common.entity.Role;
import com.shopzay.common.entity.User;

import java.util.List;

public interface IUserService {
    public List<User> listAll();

    public List<Role> listRole();
    public User save(User user);

}
