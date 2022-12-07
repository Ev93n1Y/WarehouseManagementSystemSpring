package model.dao;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"id", "role"})
@ToString(of = {"id", "role"})
@Entity
@Table(name = "roles")
public class RoleDao {
    @Id
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    private UUID id;

    @Column(name = "role", length = 100, nullable = false, unique = true)
    private String role;

    @ManyToMany(
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "user_roles",
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            joinColumns = @JoinColumn(name = "role_id")
    )
    private Set<UserDao> users;
}
