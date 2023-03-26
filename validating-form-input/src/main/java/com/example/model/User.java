package com.example.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
    @NotBlank
    @Pattern(regexp="^[A-Za-z]*$", message = "phai nhap ky tu chu cai")
    @Size(min = 2,max = 30,message = "name phai co 2-30 ky tu")
    private String name;
    @Min(18)
    private int age;
    public User(){
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
