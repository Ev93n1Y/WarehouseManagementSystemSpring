package application.model.dto;

import application.model.dao.ProductDao;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
//@AllArgsConstructor
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
