package com.dist;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-09-10 16:20
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class User {

    @NotNull
    private String email;

    private String password;

    @Size(min=1, max=20)
    private String name;

    @Min(18)
    private int age;

    // standard constructor, getters, setters


    public User(String email, String password, String name, int age) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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