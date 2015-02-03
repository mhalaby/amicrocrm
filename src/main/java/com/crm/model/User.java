package com.crm.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by muhammad on 03.02.15.
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    /**
     * The columns defs
     */
    @Id
    @Column(name = "username",unique = true,
            nullable = false, length = 45)
    private String username;
    @Column(name = "password")
    private String password;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserRole> userRole = new HashSet<UserRole>(0);
    public User() {
    }


    public User(String username, String password, Set<UserRole> userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Set<UserRole> getUserRole() {
        return this.userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

}
