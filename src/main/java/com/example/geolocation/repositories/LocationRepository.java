package com.example.geolocation.repositories;


import com.example.geolocation.models.MyLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<MyLocation, Long> {

    Optional<MyLocation> findByDeviceId (int deviceId);

}
