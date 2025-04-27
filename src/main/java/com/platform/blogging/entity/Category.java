package com.platform.blogging.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private int id;
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;


    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Post> posts;
}
