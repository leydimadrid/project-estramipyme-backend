package com.project_estramipyme_backend.user.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    private TypeUser typeUser;
    private String typeDocument;
    private String numberDocument;
    private String businessName;
    private String sector;
    private String otherSector;
    private String email;
    private String password;

}
