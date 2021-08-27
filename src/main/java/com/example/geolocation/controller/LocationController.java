package com.example.geolocation.controller;

import com.example.geolocation.models.MyLocation;
import com.example.geolocation.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/allPositions")
    public ResponseEntity<List<MyLocation>> getListOfPositions(){
        List<MyLocation> locationList = locationService.printAllLocation();
        if(locationList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(locationList, HttpStatus.OK);
        }
    }

    @GetMapping("/getPosition/{id}")
    public ResponseEntity<MyLocation> getPosition(@PathVariable("id") Long id){
        Optional<MyLocation> location = locationService.printLocationWithId(id);
        if(location.isPresent()){
            return new ResponseEntity<>(location.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addLocation")
    public ResponseEntity<MyLocation> addLocation(@RequestBody MyLocation location){
        if(locationService.addLocation(location).equals("Location added")){
            return new ResponseEntity<>(location, HttpStatus.CREATED);
        }else if (locationService.addLocation(location).equals("Location for Device already exist")){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/editLocation")
    public ResponseEntity<MyLocation> editLocation(@RequestBody MyLocation location) {
        MyLocation location1 = locationService.updateLocation(location);
        if (location1 == null){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(location1, HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/deleteLocation/{id}")
    public ResponseEntity<MyLocation> deleteLocation(@PathVariable("id") Long id){
        Optional<MyLocation> location = locationService.printLocationWithId(id);
        if (location.isPresent()){
            locationService.removeLocation(id);
            return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
