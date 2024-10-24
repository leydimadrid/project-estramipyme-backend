package com.project_estramipyme_backend.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LegalPersonModel extends UserModel {
    private String businessName;
    private String legalRepresentative;
    private String typeDocument;
    private Long numberDocument;

    public LegalPersonModel(int id, String email, String password, String businessName, String legalRepresentative, String typeDocument, Long numberDocument) {
        super(id, email, password);
        this.businessName = businessName;
        this.legalRepresentative = legalRepresentative;
        this.typeDocument = typeDocument;
        this.numberDocument = numberDocument;
    }
}
