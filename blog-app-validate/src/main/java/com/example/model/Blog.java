package com.example.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "blogs")

public class Blog implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(name = "`name`");
    @NotBlank(groups = BasicInfo.class)
    @Pattern(regexp="^[A-Za-z]*$", message = "phai nhap ky tu chu cai", groups = BasicInfo.class)
    private String name;
    private String author;
    @NotBlank
    @Size(min = 2)
    private String access;
//    @Column(name = "created_date")
//    private LocalDate createdDate;


    public Blog() {
    }

    public Blog(Long id, String name, String author, String access) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.access = access;
    }

//    public Blog(Long id, String name, String author, String access, LocalDate createdDate) {
//        this.id = id;
//        this.name = name;
//        this.author = author;
//        this.access = access;
//        this.createdDate = createdDate;
//    }

//    public LocalDate getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(LocalDate createdDate) {
//        this.createdDate = createdDate;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Blog.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Blog blog = (Blog) target;
        String author = blog.getAuthor();
        ValidationUtils.rejectIfEmpty(errors,"author","author.empty");
        if(author.length()<2 || author.length()>40){
            errors.rejectValue("author","author.length");
        }
    }
}
