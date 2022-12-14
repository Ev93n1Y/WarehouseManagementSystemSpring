package application.model.dto;

import application.model.dao.ProducerDao;
import jakarta.validation.constraints.*;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@EqualsAndHashCode
@Data
@ToString
public class ProductDto {
    private UUID id;

    @NotBlank(message = "Name can't be blank")
    @Size(max = 100)
    private String name;

    @NotNull(message = "Price can't be null")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;

    private ProducerDao producer;
}
