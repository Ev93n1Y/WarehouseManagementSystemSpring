package application.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
}
