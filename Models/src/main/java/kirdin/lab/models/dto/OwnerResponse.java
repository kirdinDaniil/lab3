package kirdin.lab.models.dto;

import kirdin.lab.models.jpa.Owner;
import kirdin.lab.models.util.DtoUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class OwnerResponse {

    public OwnerResponse(Owner owner){
        this.id = owner.getId();
        this.name = owner.getName();
        this.birthdate = owner.getBirthdate();
        this.cats = DtoUtil.castCats(owner.getCats());
    }

    private Long id;

    private String name;

    private Date birthdate;

    private List<CatResponse> cats;
}
