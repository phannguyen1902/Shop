package com.shopzay.common.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 128,nullable = false,unique = true)
    private String email;
    @Column(length = 64,nullable = false)
    private String password;
    @Column(name = "last_name",length = 45,nullable = false)
    private String lastname;
    @Column(name="first_name",length = 45,nullable = false)
    private String firstname;
    @Column(length = 64)
    private String photos;
    private boolean enabled ;
    // triển khai mối quan hệ nhiều nhiều user _ role
    @ManyToMany
    @JoinTable(name = "users_roles",
    joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles =  new HashSet<>();

    public User(String email, String password, String lastname, String firstname) {
        this.email = email;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
    }
    public void addRole(Role role){
        this.roles.add(role);
    }
/*
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", roles=" + roles +
                '}';
    }

 */
    @Transient
    public String getPhotosImagePath(){
        if ( photos == null)return "/image/default-image.png";
        return "/user-photos/" + this.id+"/"+this.photos;
    }
}
