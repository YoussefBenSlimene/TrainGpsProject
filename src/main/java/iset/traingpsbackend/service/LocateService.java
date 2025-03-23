package iset.traingpsbackend.service;

import iset.traingpsbackend.model.Locate;

import java.util.List;
public interface LocateService {
    public List<Locate> getAllLocations();
    public Locate getLocationById(Long id);
    public Locate addLocation(Locate location);
    public void deleteLocation(Long id);
    public Integer count();
}
