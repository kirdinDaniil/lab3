package kirdin.lab.dal.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class UserSecurity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String password;

    private String role;

    @JoinColumn(name = "owner_id")
    @OneToOne(fetch = FetchType.EAGER)
    private Owner owner;
}
