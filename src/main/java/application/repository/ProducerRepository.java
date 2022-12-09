package application.repository;

import application.model.dao.ProducerDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProducerRepository extends JpaRepository<ProducerDao, UUID> {
}
