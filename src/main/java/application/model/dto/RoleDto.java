package application.model.dto;

import application.model.dao.UserDao;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@EqualsAndHashCode
@Data
@ToString
public class RoleDto {
    private UUID id;

    @Size(max = 100)
    private String role;

    private Set<UserDao> users;

}
