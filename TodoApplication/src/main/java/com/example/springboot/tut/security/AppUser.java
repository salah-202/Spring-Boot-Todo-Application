package com.example.springboot.tut.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "users")
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @NotEmpty
    private String name;

    @NotEmpty
    private String email;
    @NotEmpty
    @JsonIgnore
    private String password;

    private Date created;

    public AppUser(){};
    public AppUser(@NotEmpty String name,@NotEmpty String email,@NotEmpty String password){
        this.name = name;
        this.email = email;
        this.password = password;
        this.created = new Date();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public Date getCreated() {
        return created;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
