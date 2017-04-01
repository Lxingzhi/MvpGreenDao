package com.xz.greendao_xz.databasebean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author: ZhangYi
 * date: 2017/2/16 16:40
 * email: 307221280@qq.com
 */

@Entity
public class User {
    @Id
    private Long id;
    private String name;
    private int age;
    @Transient
    private String tempUsageCount; // not persisted
    @Generated(hash = 586692638)
    public User() {
    }
    @Generated(hash = 1309193360)
    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
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
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }

}
