package kirdin.lab.models.dto;


import kirdin.lab.models.jpa.Cat;
import kirdin.lab.models.jpa.Color;
import kirdin.lab.models.jpa.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class CatRequest {

    public CatRequest(Cat cat){
        name = cat.getName();
        birthdate = cat.getBirthdate();
        breed = cat.getBreed();
        coloring = cat.getColoring();
        owner = cat.getOwner();
    }

    private String name;

    private Date birthdate;

    private String breed;

    private Color coloring;

    private Owner owner;
}
