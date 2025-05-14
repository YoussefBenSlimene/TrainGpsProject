package iset.traingpsbackend.serviceImpl;

import iset.traingpsbackend.model.Locate;
import iset.traingpsbackend.model.Train;
import iset.traingpsbackend.repository.TrainRepository;
import iset.traingpsbackend.service.TrainService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainRepository trainRepository;



    @Autowired
    private LocateServiceImpl locateService;

    @Override
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainById(Long id) {
        return trainRepository.getTrainById(id);
    }

    @Override
    public List<Train> getTrainByName(String name) {
        return trainRepository.getAllTrainByName(name);
    }
    @Override
    public Train addTrain(Train train) {
        // First check if there's location data
        Locate locationData = train.getLocation();

        // Remove location temporarily to avoid cascading issues
        train.setLocation(null);

        // Save train first to get an ID
        Train savedTrain = trainRepository.save(train);

        // Now handle location
        if (locationData != null) {
            // Set bidirectional relationship
            locationData.setTrain(savedTrain);

            // Save location
            Locate savedLocation = locateService.addLocation(locationData, savedTrain.getId());

            // Update train with saved location
            savedTrain.setLocation(savedLocation);
        } else {
            // Create a default location if none provided
            Locate newLocation = new Locate();
            newLocation.setLat(0.0);  // Default values
            newLocation.setLon(0.0);
            newLocation.setTrain(savedTrain);

            // Save location
            Locate savedLocation = locateService.addLocation(newLocation, savedTrain.getId());

            // Update train with saved location
            savedTrain.setLocation(savedLocation);
        }

        return savedTrain;
    }
}
