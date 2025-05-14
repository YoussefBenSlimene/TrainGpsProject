package iset.traingpsbackend.repository;

import iset.traingpsbackend.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainRepository extends JpaRepository<Train,Long> {
    List<Train> getAllTrainByName(String name);
    Train getTrainById(Long id);
}