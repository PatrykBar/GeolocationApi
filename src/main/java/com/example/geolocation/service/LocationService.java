package com.example.geolocation.service;

import com.example.geolocation.models.MyLocation;
import com.example.geolocation.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public String addLocation(MyLocation location){

        if (!locationRepository.findById(location.getId()).isPresent() &&
            !locationRepository.findByDeviceId(location.getDeviceId()).isPresent()){
            locationRepository.save(location);
            return "Location added";
        }else{
            if(locationRepository.findByDeviceId(location.getDeviceId()).isPresent()){
                return "Location for Device already exist";
            }else {
                return "This same ID number !";
            }
        }
    }

    public boolean removeLocation(long id){
        locationRepository.deleteById(id);
        if (!locationRepository.findById(id).isPresent()){
            return false;
        }else {
            return true;
        }
    }

    public MyLocation updateLocation(MyLocation location){
        return locationRepository.save(location);
    }

    public List<MyLocation> printAllLocation(){
        return locationRepository.findAll();
    }

    public Optional<MyLocation> printLocationWithId(long id){
        return locationRepository.findById(id);
    }

}
