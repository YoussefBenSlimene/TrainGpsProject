package iset.traingpsbackend.serviceImpl;

import iset.traingpsbackend.model.Locate;
import iset.traingpsbackend.model.Train;
import iset.traingpsbackend.repository.LocateRepository;
import iset.traingpsbackend.service.LocateService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@Transactional
public class LocateServiceImpl implements LocateService {

    @Autowired
    private LocateRepository locateRepository;

    @Override
    public List<Locate> getAllLocations() {
        return locateRepository.findAll();
    }

    @Override
    public Locate getLocationById(Long id) {
        return locateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found!"));
    }

    @Override
    public Locate addLocation(Locate location, Long idTrain) {
        if (idTrain != null && (location.getTrain() == null ||
                location.getTrain().getId() == null)) {
            Train train = new Train();
            train.setId(idTrain);
            location.setTrain(train);
        }
        return locateRepository.save(location);
    }

    @Override
    public void deleteLocation(Long id) {
        locateRepository.deleteById(id);
    }

    @Override
    public Integer count() {
        return (int) locateRepository.count();
    }
}
