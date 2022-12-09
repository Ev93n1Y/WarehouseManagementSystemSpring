package application.model.dao;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"id", "name", "price"})
@ToString(of = {"id", "name", "price"})
@Entity
@Table(name = "products")
public class ProductDao {
    @Id
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    private UUID id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST}
    )
    @JoinColumn(name = "producer_id")
    private ProducerDao producer;

    public ProductDao(UUID id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /*CREATE TABLE products
            (
                    id          UUID         PRIMARY KEY ,
                    name        VARCHAR(100) NOT NULL,
                    price       DECIMAL(10, 2),
                    producer_id UUID,
    --CONSTRAINT pk_products PRIMARY KEY (id)
    */
}
