package com.petterp.latte.ec.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Petterp on 2019/4/21
 * Summary:User类，简称bing类
 * 邮箱：1509492795@qq.com
 */
@Entity(nameInDb = "user_profile")
public class UserProfile  {
    @Id
    private Long id;
    private String name=null;
    private String email=null;
    private String phone=null;
    private String pswd=null;
    @Generated(hash = 1779693233)
    public UserProfile(Long id, String name, String email, String phone,
            String pswd) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.pswd = pswd;
    }
    @Generated(hash = 968487393)
    public UserProfile() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPswd() {
        return this.pswd;
    }
    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

}
