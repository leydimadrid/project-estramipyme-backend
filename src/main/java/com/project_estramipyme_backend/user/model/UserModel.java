package com.project_estramipyme_backend.user.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;

    @Enumerated(EnumType.STRING)
    private TypeUser typeUser;

    @Enumerated(EnumType.STRING)
    private TypeDocument typeDocument;

    private String numberDocument;
    private String businessName;

    @Enumerated(EnumType.STRING)
    private Sector sector;

    private String otherSector;
    @Column(unique = true)
    private String email;

    private String password;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of();
    }


    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
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
