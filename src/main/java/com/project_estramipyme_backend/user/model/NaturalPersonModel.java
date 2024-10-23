package com.project_estramipyme_backend.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class NaturalPersonModel extends UserModel {
    private String name;
    private String lastname;
    private String typeDocument;
    private Long numberDocument;

    public NaturalPersonModel(int id, String email, String password, String name, String lastname, String typeDocument, Long numberDocument) {
        super(id, email, password);
        this.name = name;
        this.lastname = lastname;
        this.typeDocument = typeDocument;
        this.numberDocument = numberDocument;
    }
}