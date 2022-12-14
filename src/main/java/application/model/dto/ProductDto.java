package application.model.dto;

import application.model.dao.ProducerDao;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
