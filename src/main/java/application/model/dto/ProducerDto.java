package application.model.dto;

import application.model.dao.ProductDao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@EqualsAndHashCode
@Data
@ToString
public class ProducerDto {
    private UUID id;

    @NotBlank(message = "Name can't be blank")
    @Size(max = 100)
    private String name;

    private Set<ProductDao> products;
}
