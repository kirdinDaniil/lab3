package kirdin.lab.models.dto;

import kirdin.lab.models.jpa.Cat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OwnerRequest {
    private String name;

    private Date birthdate;

    private List<Cat> cats;
}
