package com.ricoh.test.dto.external.nobel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Laureate {

    private String id;
    private Languages knownName;
    private Languages fullName;
    private String portion;
    private String sortOrder;
    private Languages motivation;
    private Links links;
}
