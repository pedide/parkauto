package com.parkauto.parkauto.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="USER")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="IDUSER")
    private Long id;

    @Column(name="FIRSTNAME")
    private String firstname;

    @Column(name="LASTNAME")
    private String lastname;

    @Column(name="EMAIL")
    private String email;

    @Column(name="USERNAME")
    private String username;
    @Column(name="PASSWORD")
    private String password;

    @Column(name="ROLE")
    private Role role;  //ROLE_USER(read,edit), ROLE_ADMIN(...,delete)

    @Column(name="ProfileImageURL")
    private String profileImageURL;

    private String[] authorities; //[]=tableau de strings //Authorities = permissions(read,edit,delete)

    private boolean isActive; //Pour activer les rôles
    private boolean isNotLocked; //Pour bloquer ou pas un user
    private Date lastLoginDate = new Date();
    private Date lastLoginDateDisplay = new Date();
    private Date joinDate = new Date();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword(){
        return password;
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
}
