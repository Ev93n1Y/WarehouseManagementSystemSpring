package application.model.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Getter
    @NotNull(message = "Producer must be select")
    private UUID producer_id;

    @Getter
    private ProducerDto producer;
}