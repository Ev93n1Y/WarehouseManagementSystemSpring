package application.model.dao;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"id", "email", "firstName", "lastName"})
@ToString(of = {"id", "email", "firstName", "lastName"})
@Entity
@Table(name = "users")
public class UserDao {
    @Id
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    private UUID id;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Setter
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleDao> roles;

    public UserDao(UUID id, String email, String password, String firstName, String lastName, Set<RoleDao> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }
}
