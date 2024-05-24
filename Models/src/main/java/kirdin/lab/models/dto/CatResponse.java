package kirdin.lab.models.dto;

import kirdin.lab.models.jpa.Cat;
import kirdin.lab.models.jpa.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@Getter
@Setter
public class CatResponse {

    public CatResponse(Cat cat){
        id = cat.getId();
        name = cat.getName();
        birthdate = cat.getBirthdate();
        breed = cat.getBreed();
        coloring = cat.getColoring();
        ownerId = cat.getOwner().getId();
    }

    private Long id;

    private String name;

    private Date birthdate;

    private String breed;

    private Color coloring;

    private Long ownerId;
}
