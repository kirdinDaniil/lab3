package kirdin.lab.dal.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "owners")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date birthdate;

    @OneToMany(mappedBy = "owner",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private List<Cat> cats;

    public Owner(String name, Date birthdate){
        this.name = name;
        this.birthdate = birthdate;
        cats = new ArrayList<>();
    }

    public void addCat(Cat cat){
        cats.add(cat);
        cat.setOwner(this);
    }

}