package iset.traingpsbackend.controller;

import iset.traingpsbackend.model.Train;
import iset.traingpsbackend.serviceImpl.LocateServiceImpl;
import iset.traingpsbackend.serviceImpl.TrainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/train")
public class TrainController {

    @Autowired
    private TrainServiceImpl trainServiceImpl;

    @Autowired
    private LocateServiceImpl locateService;


    @PostMapping("/add")
    public ResponseEntity<Train> addTrain(@RequestBody Train train) {



       return ResponseEntity.ok(trainServiceImpl.addTrain(train));
    }


    @PostMapping("/getTrainByName")
    public ResponseEntity<List<Train>> getTrainByName(@RequestBody String name) {
        return ResponseEntity.ok(trainServiceImpl.getTrainByName(name));
    }


    @GetMapping("/getAllTrains")
    public ResponseEntity<List<Train>> getAllTrains() {
        return ResponseEntity.ok(trainServiceImpl.getAllTrains());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Train> getTrainById(@PathVariable Long id) {
        return ResponseEntity.ok(trainServiceImpl.getTrainById(id));
    }
}
