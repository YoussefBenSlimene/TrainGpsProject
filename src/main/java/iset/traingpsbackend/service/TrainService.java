package iset.traingpsbackend.service;

import iset.traingpsbackend.model.Train;

import java.util.List;

public interface TrainService {

    public List<Train> getAllTrains();
    public Train getTrainById(Long id);
    public List<Train> getTrainByName(String name);
    public Train addTrain(Train train);

}
