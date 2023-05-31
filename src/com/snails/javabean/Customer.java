package com.snails.javabean;

/**
 * @description: 客户实体类
 * @author: Snails
 * @create: 2023-05-29 22:52
 * @version: v1.0
 */
public class Customer {
    // 客户的姓名、性别、年龄、电话、邮箱
    private int id;
    private String name;
    private String gender;
    private int age;
    private String phone;
    private String email;

    /* 构造器 */
    public Customer() {
    }

    public Customer(String name, String gender, int age, String phone, String email) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    public Customer(int id, String name, String gender, int age, String phone, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    /* getter和 setter */

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /* toString */

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name='" + name + '\'' + ", gender=" + gender + ", age=" + age + ", phone" +
                "='" + phone + '\'' + ", email='" + email + '\'' + '}';
    }
}
