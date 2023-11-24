package com.shopzay.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, length = 128, nullable = false)
    private String name;
    @Column(unique = true, length = 64, nullable = false)
    private String alias;
    @Column(length = 128, nullable = false)
    private String image;
    private boolean enabled;


    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private Set<Category> children = new HashSet<>();
    public Category(String name) {
        this.name = name;
        this.alias = name;
        this.image = "default.png";

    }


    public Category(String name, Category parent) {
        this(name);
        this.parent = parent;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Category(Integer id) {
        this.id = id;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Set<Category> getChildren() {
        return children;
    }

    public void setChildren(Set<Category> children) {
        this.children = children;
    }
}

