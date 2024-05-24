package kirdin.lab.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class OwnerRequest {
    private String name;

    private Date birthdate;
}
