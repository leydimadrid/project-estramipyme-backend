package com.project_estramipyme_backend.user.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String typeDocument;
    private String numberDocument;
    private String businessName;
    private String sector;
    private String otherSector;
    @Column(unique = true)  // Asegura que el email sea único
    private String email;

    private String password;  // Asegura que la contraseña esté oculta al serializar la entidad

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Devuelve la lista de roles o permisos del usuario. Puedes retornar una lista vacía si no estás usando roles.
        return List.of();
    }


    @Override
    public String getUsername() {
        return this.email;  // Usa el correo electrónico como nombre de usuario
    }

    @Override
    public String getPassword() {
        return this.password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;  // Puedes agregar lógica para determinar si la cuenta está expirada
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Puedes agregar lógica para determinar si la cuenta está bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Puedes agregar lógica para verificar si las credenciales han expirado
    }

    @Override
    public boolean isEnabled() {
        return true;  // Puedes agregar lógica para determinar si el usuario está habilitado
    }
}
