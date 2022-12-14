package application.repository;

import application.model.dao.ProductDao;
import application.model.dao.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserDao, UUID> {
    UserDao findByEmail(String email);
}
