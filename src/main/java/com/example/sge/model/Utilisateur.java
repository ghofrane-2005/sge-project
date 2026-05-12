package com.example.sge.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public Utilisateur() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(
                "ROLE_" + role.name()));
    }
    @Override public boolean isAccountNonExpired()     { return true; }
    @Override public boolean isAccountNonLocked()      { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled()               { return true; }

    public Long   getId()               { return id; }
    public void   setId(Long id)        { this.id = id; }
    public String getUsername()         { return username; }
    public void   setUsername(String u) { this.username = u; }
    public String getPassword()         { return password; }
    public void   setPassword(String p) { this.password = p; }
    public Role   getRole()             { return role; }
    public void   setRole(Role r)       { this.role = r; }
}
