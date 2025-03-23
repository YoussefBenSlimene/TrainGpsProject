package iset.traingpsbackend.repository;

import iset.traingpsbackend.model.Locate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocateRepository extends JpaRepository<Locate, Long> {
}