package iset.traingpsbackend.controller;

import iset.traingpsbackend.model.Locate;
import iset.traingpsbackend.service.LocateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocateRestController {

    @Autowired
    private LocateService locateService;

    @GetMapping
    public List<Locate> getAllLocations() {
        return locateService.getAllLocations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Locate> getLocationById(@PathVariable Long id) {
        return ResponseEntity.ok(locateService.getLocationById(id));
    }

    @PostMapping
    public ResponseEntity<Locate> addLocation(@RequestBody Locate location) {
        return ResponseEntity.ok(locateService.addLocation(location));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locateService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> count() {
        return ResponseEntity.ok(locateService.count());
    }
}