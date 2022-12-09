package application.repository;

import application.model.dao.ProductDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductDao, UUID> {
}
