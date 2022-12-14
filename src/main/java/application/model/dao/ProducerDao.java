package application.model.dao;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"id", "name"})
@ToString(of = {"id", "name"})
@Entity
@Table(name = "producers")
public class ProducerDao {
    @Id
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    private UUID id;

    @Column(name = "name", length = 100, nullable = false
    )
    private String name;

    @Setter
    @OneToMany(
            //fetch = FetchType.EAGER,
            //cascade = {CascadeType.MERGE}
    )
    @JoinColumn(name = "producer_id")
    private Set<ProductDao> products;

    public ProducerDao(UUID id, String name, Set<ProductDao> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }
}
