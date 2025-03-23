package iset.traingpsbackend.repository;

import iset.traingpsbackend.model.Locate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocateRepository extends JpaRepository<Locate, Long> {
}
