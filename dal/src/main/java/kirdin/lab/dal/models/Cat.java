package kirdin.lab.dal.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cat")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date birthdate;

    private String breed;

    @Enumerated(EnumType.STRING)
    private Color coloring;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cat_relation",
            joinColumns = @JoinColumn(name = "first_cat_id"),
            inverseJoinColumns = @JoinColumn(name = "second_cat_id"))
    private List<Cat> friends;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cat_relation",
            joinColumns = @JoinColumn(name = "second_cat_id"),
            inverseJoinColumns = @JoinColumn(name = "first_cat_id"))
    private List<Cat> friendsOf;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Cat(String name, Date birthdate, String breed, Color coloring) {
        this.birthdate = birthdate;
        this.name = name;
        this.breed = breed;
        this.coloring = coloring;
        friends = new ArrayList<>();
        friendsOf = new ArrayList<>();
    }

    public void addFriend(Cat cat) {
        friends.add(cat);
        cat.friendsOf.add(this);
    }
}