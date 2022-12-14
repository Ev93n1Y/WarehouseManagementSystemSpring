package application.repository;

import application.model.dao.RoleDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleDao, UUID> {
    RoleDao findByRole(String role);
}
